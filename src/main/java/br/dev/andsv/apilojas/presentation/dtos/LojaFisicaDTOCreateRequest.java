package br.dev.andsv.apilojas.presentation.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaFisica}
 */
public record LojaFisicaDTOCreateRequest(
        @NotBlank String cnpj,
        String nome,
        String segmento,
        String telefone,
        @NotBlank EnderecoDTOCreateRequest endereco,
        Integer numeroFuncionarios
) implements Serializable {
}