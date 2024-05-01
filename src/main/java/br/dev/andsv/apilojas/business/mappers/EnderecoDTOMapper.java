package br.dev.andsv.apilojas.business.mappers;

import br.dev.andsv.apilojas.model.entities.Endereco;
import br.dev.andsv.apilojas.presentation.dtos.EnderecoDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.EnderecoDTO;
import org.springframework.stereotype.Service;

@Service
public class EnderecoDTOMapper {


    public EnderecoDTO enderecoParaDTOResponse(Endereco endereco) {
        return new EnderecoDTO(
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
