package br.dev.andsv.apilojas.controllers;

import br.dev.andsv.apilojas.core.entities.LojaFisica;
import br.dev.andsv.apilojas.repository.LojaFisicaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fisica/")
public class LojaFisicaController {

    private final LojaFisicaRepository lojaFisicaRepository;

    public LojaFisicaController(LojaFisicaRepository lojaFisicaRepository) {
        this.lojaFisicaRepository = lojaFisicaRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<LojaFisica> findById(@PathVariable Long requestedId) {
        LojaFisica lojaFisica = lojaFisicaRepository.findById(requestedId).orElse(null);
        return ResponseEntity.ok(lojaFisica);
    }

}
