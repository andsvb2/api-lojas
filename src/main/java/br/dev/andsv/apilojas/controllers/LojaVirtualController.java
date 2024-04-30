package br.dev.andsv.apilojas.controllers;

import br.dev.andsv.apilojas.core.entities.LojaVirtual;
import br.dev.andsv.apilojas.repository.LojaVirtualRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
