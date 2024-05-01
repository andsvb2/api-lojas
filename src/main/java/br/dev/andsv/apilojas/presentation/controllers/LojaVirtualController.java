package br.dev.andsv.apilojas.presentation.controllers;

import br.dev.andsv.apilojas.business.service.LojaVirtualService;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.LojaVirtualDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/virtual")
public class LojaVirtualController {

    private final LojaVirtualService service;

    public LojaVirtualController(LojaVirtualService service) {
        this.service = service;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LojaVirtualDTO> localizarPorId(@PathVariable Long requestedId) {
        return service.localizarPorId(requestedId);
    }

    @PostMapping
    private ResponseEntity<Void> criarLojaVirtual(
            @RequestBody LojaVirtualDTOCreateRequest novaLojaVirtual,
            UriComponentsBuilder ucb) {
        return service.criarLojaVirtual(novaLojaVirtual, ucb);
    }

    @GetMapping
    private ResponseEntity<List<LojaVirtualDTO>> localizarTodasLojasVirtuais(Pageable pageable) {
        return service.localizarTodasLojasVirtuais(pageable);
    }
}
