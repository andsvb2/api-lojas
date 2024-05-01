package br.dev.andsv.apilojas.presentation.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.Endereco}
 */
public record EnderecoDTOCreateRequest(
        @NotBlank String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String cidade,
        String estado
) implements Serializable {
}