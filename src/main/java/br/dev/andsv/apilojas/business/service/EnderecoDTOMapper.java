package br.dev.andsv.apilojas.business.service;

import br.dev.andsv.apilojas.model.entities.Endereco;
import br.dev.andsv.apilojas.presentation.dtos.EnderecoDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.EnderecoDTOResponse;
import org.springframework.stereotype.Service;

@Service
public class EnderecoDTOMapper {


    public EnderecoDTOResponse enderecoParaDTOResponse(Endereco endereco) {
        return new EnderecoDTOResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }

    public Endereco dtoRequestParaEndereco(EnderecoDTOCreateRequest enderecoDTOCreateRequest) {
        return new Endereco(
                enderecoDTOCreateRequest.logradouro(),
                enderecoDTOCreateRequest.numero(),
                enderecoDTOCreateRequest.complemento(),
                enderecoDTOCreateRequest.bairro(),
                enderecoDTOCreateRequest.cep(),
                enderecoDTOCreateRequest.cidade(),
                enderecoDTOCreateRequest.estado()
        );
    }
}
