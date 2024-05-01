package br.dev.andsv.apilojas.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaFisica}
 */
public record LojaFisicaDTOUpdateRequest(
        @NotNull Long id,
        @NotBlank String cnpj,
        String nome,
        String segmento,
        String telefone,
        @NotNull EnderecoDTOUpdateRequest endereco,
        Integer numeroFuncionarios
) implements Serializable {
}