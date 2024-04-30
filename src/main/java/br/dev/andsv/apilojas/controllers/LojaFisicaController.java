package br.dev.andsv.apilojas.controllers;

import br.dev.andsv.apilojas.core.entities.LojaFisica;
import br.dev.andsv.apilojas.repository.LojaFisicaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * LojaFisicaController é o Controller para endpoints de LojaFisica.
 * @author Anderson S. Vieira
 */

@RestController
@RequestMapping("/fisica")
public class LojaFisicaController {

    private final LojaFisicaRepository lojaFisicaRepository;

    public LojaFisicaController(LojaFisicaRepository lojaFisicaRepository) {
        this.lojaFisicaRepository = lojaFisicaRepository;
    }

    /**
     * Este método busca e retorna uma LojaFisica pelo índice.
     * @param requestedId O id da LojaFisica requisitada.
     * @return LojaFisica Retorna uma LojaFisica ou null.
     */
    @GetMapping("/{requestedId}")
    private ResponseEntity<LojaFisica> localizarPorId(@PathVariable Long requestedId) {
        Optional<LojaFisica> lojaFisica = lojaFisicaRepository.findById(requestedId);
        if (lojaFisica.isPresent()) {
            return ResponseEntity.ok(lojaFisica.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Void> criarLojaFisica(
            @RequestBody LojaFisica novaLojaFisica,
            UriComponentsBuilder ucb) {
        LojaFisica lojaFisicaSalva = lojaFisicaRepository.save(novaLojaFisica);
        URI localdaNovaLojaFisica = ucb
                .path("fisica/{id}")
                .buildAndExpand(lojaFisicaSalva.getId())
                .toUri();
        return ResponseEntity.created(localdaNovaLojaFisica).build();
    }

}
