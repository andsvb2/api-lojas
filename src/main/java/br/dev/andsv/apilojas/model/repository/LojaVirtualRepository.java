package br.dev.andsv.apilojas.model.repository;

import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaVirtualRepository extends JpaRepository<LojaVirtual, Long> {
}