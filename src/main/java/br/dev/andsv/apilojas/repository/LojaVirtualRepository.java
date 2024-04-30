package br.dev.andsv.apilojas.repository;

import br.dev.andsv.apilojas.core.entities.LojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaVirtualRepository extends JpaRepository<LojaVirtual, Long> {
}