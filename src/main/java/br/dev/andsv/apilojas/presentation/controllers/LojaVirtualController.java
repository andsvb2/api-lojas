package br.dev.andsv.apilojas.presentation.controllers;

import br.dev.andsv.apilojas.business.service.LojaVirtualService;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.LojaVirtualDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/virtual")
public class LojaVirtualController {

    private final LojaVirtualService service;

    public LojaVirtualController(LojaVirtualService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    private ResponseEntity<LojaVirtualDTO> localizarPorId(@PathVariable Long id, Principal principal) {
        return service.localizarPorId(id, principal);
    }

    @PostMapping
    private ResponseEntity<Void> criarLojaVirtual(
            @RequestBody LojaVirtualDTOCreateRequest novaLojaVirtual,
            UriComponentsBuilder ucb) {
        return service.criarLojaVirtual(novaLojaVirtual, ucb);
    }

    @GetMapping
    private ResponseEntity<List<LojaVirtualDTO>> localizarTodasLojasVirtuais(Pageable pageable, Principal principal) {
        return service.localizarTodasLojasVirtuais(pageable, principal);
    }
}
