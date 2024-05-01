package br.dev.andsv.apilojas.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaFisica}
 */
public record LojaFisicaDTOResponse(
        @NotNull Long id,
        String cnpj,
        String nome,
        String segmento,
        String telefone,
        @NotBlank EnderecoDTOResponse endereco,
        Integer numeroFuncionarios
) implements Serializable {
}