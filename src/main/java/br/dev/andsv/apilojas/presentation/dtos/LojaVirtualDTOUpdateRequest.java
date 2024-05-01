package br.dev.andsv.apilojas.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link br.dev.andsv.apilojas.model.entities.LojaVirtual}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record LojaVirtualDTOUpdateRequest(
        @NotNull Long id,
        @NotBlank String cnpj,
        String nome,
        String segmento,
        String telefone,
        String url,
        String avaliacao
) implements Serializable {
}