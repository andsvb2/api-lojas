package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.model.entities.LojaFisica;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOResponse;
import org.springframework.stereotype.Service;

@Service
public class LojaFisicaDTOMapper {

    private final EnderecoDTOMapper enderecoDTOMapper;

    public LojaFisicaDTOMapper(EnderecoDTOMapper enderecoDTOMapper) {
        this.enderecoDTOMapper = enderecoDTOMapper;
    }

    public LojaFisicaDTOResponse lojaFisicaParaDTOResponse(LojaFisica lojaFisica) {
        return new LojaFisicaDTOResponse(
                lojaFisica.getId(),
                lojaFisica.getCnpj(),
                lojaFisica.getNome(),
                lojaFisica.getSegmento(),
                lojaFisica.getTelefone(),
                enderecoDTOMapper.enderecoParaDTOResponse(lojaFisica.getEndereco()),
                lojaFisica.getNumeroFuncionarios()
        );
    }

    public LojaFisica dtoRequestParaLojaFisica(LojaFisicaDTOCreateRequest request) {
        return new LojaFisica(
                request.cnpj(),
                request.nome(),
                request.segmento(),
                request.telefone(),
                enderecoDTOMapper.dtoRequestParaEndereco(request.endereco()),
                request.numeroFuncionarios()
        );
    }
}
