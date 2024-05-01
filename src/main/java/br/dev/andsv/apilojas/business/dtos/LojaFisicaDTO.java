package br.dev.andsv.apilojas.business.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaFisica}
 */
public record LojaFisicaDTO(
        @NotNull Long id,
        String cnpj,
        String nome,
        String segmento,
        String telefone,
        @NotBlank EnderecoDTO endereco,
        Integer numeroFuncionarios,
        String responsavel
) implements Serializable {
}