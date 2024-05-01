package br.dev.andsv.apilojas.model.repository;

import br.dev.andsv.apilojas.model.entities.LojaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaFisicaRepository extends JpaRepository<LojaFisica, Long> {
}