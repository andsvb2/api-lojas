package br.dev.andsv.apilojas.repository;

import br.dev.andsv.apilojas.core.entities.LojaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaFisicaRepository extends JpaRepository<LojaFisica, Long> {
}