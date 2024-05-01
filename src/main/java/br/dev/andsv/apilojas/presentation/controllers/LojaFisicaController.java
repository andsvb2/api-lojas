package br.dev.andsv.apilojas.presentation.controllers;

import br.dev.andsv.apilojas.business.service.LojaFisicaService;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.LojaFisicaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fisica")
public class LojaFisicaController {

    private final LojaFisicaService service;

    public LojaFisicaController(LojaFisicaService service) {
        this.service = service;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LojaFisicaDTO> localizarPorId(@PathVariable Long requestedId) {
        return service.localizarPorId(requestedId);
    }

    @PostMapping
    private ResponseEntity<Void> criarLojaFisica(
            @RequestBody LojaFisicaDTOCreateRequest novaLojaFisica,
            UriComponentsBuilder ucb) {
        return service.criarLojaFisica(novaLojaFisica, ucb);
    }

    @GetMapping
    private ResponseEntity<List<LojaFisicaDTO>> localizarTodasLojasFisicas(Pageable pageable) {
        return service.localizarTodasLojasFisicas(pageable);
    }

}
