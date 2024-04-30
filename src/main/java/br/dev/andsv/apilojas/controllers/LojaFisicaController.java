package br.dev.andsv.apilojas.controllers;

import br.dev.andsv.apilojas.core.entities.LojaFisica;
import br.dev.andsv.apilojas.repository.LojaFisicaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
