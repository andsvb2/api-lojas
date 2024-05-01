package br.dev.andsv.apilojas.model.repository;

import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaVirtualRepository extends JpaRepository<LojaVirtual, Long> {

    LojaVirtual findByIdAndResponsavel(Long id, String responsavel);

    Page<LojaVirtual> findByResponsavel(String responsavel, PageRequest pageRequest);
}