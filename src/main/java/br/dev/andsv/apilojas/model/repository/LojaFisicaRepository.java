package br.dev.andsv.apilojas.model.repository;

import br.dev.andsv.apilojas.model.entities.LojaFisica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaFisicaRepository extends JpaRepository<LojaFisica, Long> {

    LojaFisica findByIdAndResponsavel(Long id, String responsavel);

    Page<LojaFisica> findByResponsavel(String responsavel, PageRequest pageRequest);

    boolean existsByIdAndResponsavel(Long id, String responsavel);
}