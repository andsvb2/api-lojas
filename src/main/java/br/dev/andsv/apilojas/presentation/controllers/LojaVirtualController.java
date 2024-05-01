package br.dev.andsv.apilojas.presentation.controllers;

import br.dev.andsv.apilojas.business.service.LojaVirtualService;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/virtual")
public class LojaVirtualController {

    private final LojaVirtualService service;

    public LojaVirtualController(LojaVirtualService service) {
        this.service = service;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<?> localizarPorId(@PathVariable Long requestedId) {
        return service.localizarPorId(requestedId);
    }

    @PostMapping
    private ResponseEntity<?> criarLojaVirtual(
            @RequestBody LojaVirtualDTOCreateRequest novaLojaVirtual,
            UriComponentsBuilder ucb) {
        return service.criarLojaVirtual(novaLojaVirtual, ucb);
    }
}
