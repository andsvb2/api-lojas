package br.dev.andsv.apilojas.business.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaVirtual}
 */
public record LojaVirtualDTO(
        @NotNull Long id,
        @NotBlank String cnpj,
        String nome,
        String segmento,
        String telefone,
        @NotBlank String url,
        String avaliacao,
        String responsavel) implements Serializable {
}