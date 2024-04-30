package br.dev.andsv.apilojas.controllers;

import br.dev.andsv.apilojas.core.entities.LojaVirtual;
import br.dev.andsv.apilojas.repository.LojaVirtualRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/virtual")
public class LojaVirtualController {

    private final LojaVirtualRepository lojaVirtualRepository;

    public LojaVirtualController(LojaVirtualRepository lojaVirtualRepository) {
        this.lojaVirtualRepository = lojaVirtualRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LojaVirtual> localizarPorId(@PathVariable Long requestedId) {
        Optional<LojaVirtual> lojaVirtual = lojaVirtualRepository.findById(requestedId);
        if (lojaVirtual.isPresent()) {
            return ResponseEntity.ok(lojaVirtual.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Void> criarLojaVirtual(
            @RequestBody LojaVirtual novaLojaVirtual,
            UriComponentsBuilder ucb) {
        LojaVirtual lojaVirtualSalva = lojaVirtualRepository.save(novaLojaVirtual);
        URI localDaNovaLojaVirtual = ucb
                .path("virtual/{id}")
                .buildAndExpand(lojaVirtualSalva.getId())
                .toUri();
        return ResponseEntity.created(localDaNovaLojaVirtual).build();
    }
}
