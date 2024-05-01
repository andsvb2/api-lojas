package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.business.mappers.LojaFisicaDTOMapper;
import br.dev.andsv.apilojas.model.entities.LojaFisica;
import br.dev.andsv.apilojas.model.repository.LojaFisicaRepository;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.LojaFisicaDTO;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class LojaFisicaService {

    private final LojaFisicaRepository repository;
    private final LojaFisicaDTOMapper dtoMapper;

    public LojaFisicaService(LojaFisicaRepository repository, LojaFisicaDTOMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    public ResponseEntity<LojaFisicaDTO> localizarPorId(Long id, Principal principal) {
        Optional<LojaFisica> lojaFisica = Optional.ofNullable(repository.findByIdAndResponsavel(id, principal.getName()));
        if (lojaFisica.isPresent()) {
            LojaFisicaDTO dtoResponse = dtoMapper.lojaFisicaParaDTOResponse(lojaFisica.get());
            return ResponseEntity.ok(dtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> criarLojaFisica(LojaFisicaDTOCreateRequest lojaFisicaDTOCreateRequest,
                                                UriComponentsBuilder ucb,
                                                Principal principal) {
        LojaFisica novaLojaFisica = dtoMapper.dtoCreateRequestParaLojaFisica(lojaFisicaDTOCreateRequest);
        novaLojaFisica.setResponsavel(principal.getName());
        LojaFisica lojaFisicaSalva = repository.save(novaLojaFisica);
        URI localDaNovaLojaFisica = ucb
                .path("/api/v1/fisica/{id}")
                .buildAndExpand(lojaFisicaSalva.getId())
                .toUri();
        return ResponseEntity.created(localDaNovaLojaFisica).build();
    }

    public ResponseEntity<List<LojaFisicaDTO>> localizarTodasLojasFisicas(Pageable pageable, Principal principal) {
        Page<LojaFisicaDTO> pageLFisDTOResponse;
//        Populo a página de LojaFisicaDTOResponse a partir do retorno do repository através do map ao final do método.
        pageLFisDTOResponse = repository
                .findByResponsavel(principal.getName(),
                        PageRequest.of(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))))
                .map(dtoMapper::lojaFisicaParaDTOResponse);

        return ResponseEntity.ok(pageLFisDTOResponse.getContent());
    }

    public ResponseEntity<Void> atualizarLojaFisica(
            Long id,
            LojaFisicaDTOUpdateRequest dtoUpdate,
            Principal principal) {
        LojaFisica lojaFisicaAtual = repository.findByIdAndResponsavel(id, principal.getName());

//        Aqui eu inverto um pouco o fluxo normal de leitura do código para usar o Fail Fast Validation.
        if (lojaFisicaAtual == null) {
            return ResponseEntity.notFound().build();
        }

        LojaFisica lojaFisicaAtualizada = dtoMapper.dtoUpdateRequestParaLojaFisica(lojaFisicaAtual.getId(),lojaFisicaAtual.getEndereco().getId(), dtoUpdate, principal.getName());
        repository.save(lojaFisicaAtualizada);
        return ResponseEntity.noContent().build();
    }
}
