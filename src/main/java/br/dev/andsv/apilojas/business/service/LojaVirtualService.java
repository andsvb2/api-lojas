package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.business.dtos.LojaVirtualDTO;
import br.dev.andsv.apilojas.business.mappers.LojaVirtualDTOMapper;
import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import br.dev.andsv.apilojas.model.repository.LojaVirtualRepository;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOUpdateRequest;
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

@Service
public class LojaVirtualService {

    private final LojaVirtualRepository repository;
    private final LojaVirtualDTOMapper dtoMapper;

    public LojaVirtualService(LojaVirtualRepository repository, LojaVirtualDTOMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    public ResponseEntity<LojaVirtualDTO> localizarPorId(Long id, Principal principal) {
        LojaVirtual lojaVirtual = localizarLojaVirtualPorIdEResponsavel(id, principal);

//        Após criar método adicional eu uso Fail Fast Validation para retornar Not Found.
        if (lojaVirtual == null) {
            return ResponseEntity.notFound().build();
        }
        LojaVirtualDTO dtoResponse = dtoMapper.lojaVirtualParaDTOResponse(lojaVirtual);
        return ResponseEntity.ok(dtoResponse);
    }

    public ResponseEntity<Void> criarLojaVirtual(LojaVirtualDTOCreateRequest dtoCreateRequest,
                                                 UriComponentsBuilder ucb,
                                                 Principal principal) {
        LojaVirtual novaLojaVirtual = dtoMapper.dtoCreateRequestParaLojaVirtual(dtoCreateRequest);
        novaLojaVirtual.setResponsavel(principal.getName());
        LojaVirtual lojaVirtualSalva = repository.save(novaLojaVirtual);
        URI localDaNovaLojaVirtual = ucb
                .path("/api/v1/virtual/{id}")
                .buildAndExpand(lojaVirtualSalva.getId())
                .toUri();
        return ResponseEntity.created(localDaNovaLojaVirtual).build();
    }

    public ResponseEntity<List<LojaVirtualDTO>> localizarTodasLojasVirtuais(Pageable pageable, Principal principal) {
//        Da mesma forma que no Service de LojaFisica, aqui eu retorno uma página de LojaVirtualDTOResponse transformando
//        cada entidade recebida do repositório em usando a função map.
        Page<LojaVirtualDTO> dtoResponsePage;
        dtoResponsePage = repository.findByResponsavel(principal.getName(),
                        PageRequest.of(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))))
                .map(dtoMapper::lojaVirtualParaDTOResponse);
        return ResponseEntity.ok(dtoResponsePage.getContent());
    }

    public ResponseEntity<Void> atualizarLojaVirtual(
            Long id,
            LojaVirtualDTOUpdateRequest request,
            Principal principal) {
        LojaVirtual lojaVirtualAtual = localizarLojaVirtualPorIdEResponsavel(id, principal);

//        Aqui eu inverto um pouco o fluxo normal de leitura do código para usar o Fail Fast Validation.
        if (lojaVirtualAtual == null) {
            return ResponseEntity.notFound().build();
        }
        LojaVirtual lojaVirtualAtualizada = dtoMapper.dtoUpdateRequestParaLojaVirtual(lojaVirtualAtual.getId(), request, principal.getName());
        repository.save(lojaVirtualAtualizada);
        return ResponseEntity.noContent().build();
    }

    private LojaVirtual localizarLojaVirtualPorIdEResponsavel(Long id, Principal principal) {
        return repository.findByIdAndResponsavel(id, principal.getName());
    }
}
