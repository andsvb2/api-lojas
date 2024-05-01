package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import br.dev.andsv.apilojas.model.repository.LojaVirtualRepository;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class LojaVirtualService {

    private final LojaVirtualRepository repository;
    private final LojaVirtualDTOMapper dtoMapper;

    public LojaVirtualService(LojaVirtualRepository repository, LojaVirtualDTOMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    public ResponseEntity<LojaVirtualDTOResponse> localizarPorId(Long id) {
        Optional<LojaVirtual> lojaVirtual = repository.findById(id);
        if (lojaVirtual.isPresent()) {
            LojaVirtualDTOResponse dtoResponse = dtoMapper.lojaVirtualParaDTOResponse(lojaVirtual.get());
            return ResponseEntity.ok(dtoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> criarLojaVirtual(LojaVirtualDTOCreateRequest dtoCreateRequest, UriComponentsBuilder ucb) {
        LojaVirtual novaLojaVirtual = dtoMapper.dtoRequestParaLojaVirtual(dtoCreateRequest);
        LojaVirtual lojaVirtualSalva = repository.save(novaLojaVirtual);
        URI localDaNovaLojaVirtual = ucb
                .path("virtual/{id}")
                .buildAndExpand(lojaVirtualSalva.getId())
                .toUri();
        return ResponseEntity.created(localDaNovaLojaVirtual).build();
    }

    public ResponseEntity<List<LojaVirtualDTOResponse>> localizarTodasLojasVirtuais() {
        List<LojaVirtualDTOResponse> dtoResponseList;
        dtoResponseList = repository.findAll().stream().map(dtoMapper::lojaVirtualParaDTOResponse).toList();
        return ResponseEntity.ok(dtoResponseList);
    }
}
