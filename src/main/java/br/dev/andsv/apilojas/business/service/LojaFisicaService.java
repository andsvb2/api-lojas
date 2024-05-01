package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.model.entities.LojaFisica;
import br.dev.andsv.apilojas.model.repository.LojaFisicaRepository;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class LojaFisicaService {

    private final LojaFisicaRepository repository;
    private final LojaFisicaDTOMapper dtoMapper;

    public LojaFisicaService(LojaFisicaRepository repository, LojaFisicaDTOMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    public ResponseEntity<?> localizarPorId(Long id) {
        Optional<LojaFisica> lojaFisica = repository.findById(id);
        if (lojaFisica.isPresent()) {
            LojaFisicaDTOResponse dtoResponse = dtoMapper.lojaFisicaParaDTOResponse(lojaFisica.get());
            return ResponseEntity.ok(dtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> criarLojaFisica(LojaFisicaDTOCreateRequest lojaFisicaDTOCreateRequest, UriComponentsBuilder ucb) {
        LojaFisica novaLojaFisica = dtoMapper.dtoRequestParaLojaFisica(lojaFisicaDTOCreateRequest);
        LojaFisica lojaFisicaSalva = repository.save(novaLojaFisica);
        URI localDaNovaLojaFisica = ucb
                .path("fisica/{id}")
                .buildAndExpand(lojaFisicaSalva.getId())
                .toUri();
        return ResponseEntity.created(localDaNovaLojaFisica).build();
    }



}
