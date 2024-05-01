package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.model.entities.LojaFisica;
import br.dev.andsv.apilojas.model.repository.LojaFisicaRepository;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    public ResponseEntity<LojaFisicaDTOResponse> localizarPorId(Long id) {
        Optional<LojaFisica> lojaFisica = repository.findById(id);
        if (lojaFisica.isPresent()) {
            LojaFisicaDTOResponse dtoResponse = dtoMapper.lojaFisicaParaDTOResponse(lojaFisica.get());
            return ResponseEntity.ok(dtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> criarLojaFisica(LojaFisicaDTOCreateRequest lojaFisicaDTOCreateRequest, UriComponentsBuilder ucb) {
        LojaFisica novaLojaFisica = dtoMapper.dtoRequestParaLojaFisica(lojaFisicaDTOCreateRequest);
        LojaFisica lojaFisicaSalva = repository.save(novaLojaFisica);
        URI localDaNovaLojaFisica = ucb
                .path("fisica/{id}")
                .buildAndExpand(lojaFisicaSalva.getId())
                .toUri();
        return ResponseEntity.created(localDaNovaLojaFisica).build();
    }

    public ResponseEntity<List<LojaFisicaDTOResponse>> localizarTodasLojasFisicas(Pageable pageable) {
        Page<LojaFisicaDTOResponse> pageLFisDTOResponse;
//        Populo a página de LojaFisicaDTOResponse a partir do retorno do repository através do map ao final do método.
        pageLFisDTOResponse = repository
                .findAll(PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))))
                .map(dtoMapper::lojaFisicaParaDTOResponse);

        return ResponseEntity.ok(pageLFisDTOResponse.getContent());
    }
}
