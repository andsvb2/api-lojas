package br.dev.andsv.apilojas.business.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.Endereco}
 */
public record EnderecoDTO(
        @NotNull Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        String cidade,
        String estado
) implements Serializable {
}