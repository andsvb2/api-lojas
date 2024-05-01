package br.dev.andsv.apilojas.presentation.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaVirtual}
 */
public record LojaVirtualDTOCreateRequest(
        @NotBlank String cnpj,
        @NotBlank String nome,
        String segmento,
        String telefone,
        @NotBlank String url,
        String avaliacao,
        String responsavel) implements Serializable {
}