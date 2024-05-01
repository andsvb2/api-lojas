package br.dev.andsv.apilojas.business.mappers;

import br.dev.andsv.apilojas.model.entities.Endereco;
import br.dev.andsv.apilojas.presentation.dtos.EnderecoDTOCreateRequest;
import br.dev.andsv.apilojas.business.dtos.EnderecoDTO;
import br.dev.andsv.apilojas.presentation.dtos.EnderecoDTOUpdateRequest;
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

    public Endereco dtoCreateRequestParaEndereco(EnderecoDTOCreateRequest enderecoDTOCreateRequest) {
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

    public Endereco dtoUpdateRequestParaEndereco(Long id, EnderecoDTOUpdateRequest request) {
        return new Endereco(
                id,
                request.logradouro(),
                request.numero(),
                request.complemento(),
                request.bairro(),
                request.cep(),
                request.cidade(),
                request.estado()
        );
    }
}
