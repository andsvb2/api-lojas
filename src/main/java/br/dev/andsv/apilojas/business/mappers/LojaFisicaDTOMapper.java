package br.dev.andsv.apilojas.business.mappers;

import br.dev.andsv.apilojas.model.entities.LojaFisica;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.LojaFisicaDTO;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class LojaFisicaDTOMapper {

    private final EnderecoDTOMapper enderecoDTOMapper;

    public LojaFisicaDTOMapper(EnderecoDTOMapper enderecoDTOMapper) {
        this.enderecoDTOMapper = enderecoDTOMapper;
    }

    public LojaFisicaDTO lojaFisicaParaDTOResponse(LojaFisica lojaFisica) {
        return new LojaFisicaDTO(
                lojaFisica.getId(),
                lojaFisica.getCnpj(),
                lojaFisica.getNome(),
                lojaFisica.getSegmento(),
                lojaFisica.getTelefone(),
                enderecoDTOMapper.enderecoParaDTOResponse(lojaFisica.getEndereco()),
                lojaFisica.getNumeroFuncionarios(),
                lojaFisica.getResponsavel()
        );
    }

    public LojaFisica dtoCreateRequestParaLojaFisica(LojaFisicaDTOCreateRequest request) {
        return new LojaFisica(
                request.cnpj(),
                request.nome(),
                request.segmento(),
                request.telefone(),
                enderecoDTOMapper.dtoCreateRequestParaEndereco(request.endereco()),
                request.numeroFuncionarios()
        );
    }

    public LojaFisica dtoUpdateRequestParaLojaFisica(Long id, Long endereco_id, LojaFisicaDTOUpdateRequest request, String responsavel) {
        return new LojaFisica(
                id,
                request.cnpj(),
                request.nome(),
                request.segmento(),
                request.telefone(),
                enderecoDTOMapper.dtoUpdateRequestParaEndereco(endereco_id, request.endereco()),
                request.numeroFuncionarios(),
                responsavel
        );
    }
}
