package br.dev.andsv.apilojas.presentation.controllers;

import br.dev.andsv.apilojas.business.service.LojaFisicaService;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/fisica")
public class LojaFisicaController {

    private final LojaFisicaService service;

    public LojaFisicaController(LojaFisicaService service) {
        this.service = service;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LojaFisicaDTOResponse> localizarPorId(@PathVariable Long requestedId) {
        return service.localizarPorId(requestedId);
    }

    @PostMapping
    private ResponseEntity<Void> criarLojaFisica(
            @RequestBody LojaFisicaDTOCreateRequest novaLojaFisica,
            UriComponentsBuilder ucb) {
        return service.criarLojaFisica(novaLojaFisica, ucb);
    }

    @GetMapping
    private ResponseEntity<List<LojaFisicaDTOResponse>> localizarTodasLojasFisicas(Pageable pageable) {
        return service.localizarTodasLojasFisicas(pageable);
    }

}
