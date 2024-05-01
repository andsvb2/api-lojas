package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOResponse;
import org.springframework.stereotype.Service;

@Service
public class LojaVirtualDTOMapper {

    public LojaVirtualDTOResponse lojaVirtualParaDTOResponse(LojaVirtual lojaVirtual) {
        return new LojaVirtualDTOResponse(
                lojaVirtual.getId(),
                lojaVirtual.getCnpj(),
                lojaVirtual.getNome(),
                lojaVirtual.getSegmento(),
                lojaVirtual.getTelefone(),
                lojaVirtual.getUrl(),
                lojaVirtual.getAvaliacao()
        );
    }

    public LojaVirtual dtoRequestParaLojaVirtual(LojaVirtualDTOCreateRequest request) {
        return new LojaVirtual(
                request.cnpj(),
                request.nome(),
                request.segmento(),
                request.telefone(),
                request.url(),
                request.avaliacao()
        );
    }

}
