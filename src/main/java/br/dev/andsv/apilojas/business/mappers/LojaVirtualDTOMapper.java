package br.dev.andsv.apilojas.business.mappers;

import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.LojaVirtualDTO;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class LojaVirtualDTOMapper {

    public LojaVirtualDTO lojaVirtualParaDTOResponse(LojaVirtual lojaVirtual) {
        return new LojaVirtualDTO(
                lojaVirtual.getId(),
                lojaVirtual.getCnpj(),
                lojaVirtual.getNome(),
                lojaVirtual.getSegmento(),
                lojaVirtual.getTelefone(),
                lojaVirtual.getUrl(),
                lojaVirtual.getAvaliacao(),
                lojaVirtual.getResponsavel()
        );
    }

    public LojaVirtual dtoCreateRequestParaLojaVirtual(LojaVirtualDTOCreateRequest request) {
        return new LojaVirtual(
                request.cnpj(),
                request.nome(),
                request.segmento(),
                request.telefone(),
                request.url(),
                request.avaliacao()
        );
    }

    public LojaVirtual dtoUpdateRequestParaLojaVirtual(Long id, LojaVirtualDTOUpdateRequest request, String responsavel) {
        return new LojaVirtual(
                id,
                request.cnpj(),
                request.nome(),
                request.segmento(),
                request.telefone(),
                request.url(),
                request.avaliacao(),
                responsavel
        );
    }

}
