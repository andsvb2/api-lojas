package br.dev.andsv.apilojas;

import br.dev.andsv.apilojas.core.entities.Endereco;
import br.dev.andsv.apilojas.core.entities.LojaFisica;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ApiLojasJsonTest {

    @Autowired
    private JacksonTester<LojaFisica> json;

    @Autowired
    private JacksonTester<LojaFisica[]> jsonList;

    private LojaFisica[] lojasFisicas;
    private Endereco[] enderecos;


    @BeforeEach
    void setup() {
        enderecos = Arrays.array(
                new Endereco(207L,"Rua Trigésima", "57-B", "Galpão 2", "Piracanã", "68180-500", "Itaituba", "Pará"),
                new Endereco("Rua 42", "S/N", null, "Setor Tradicional (São Sebastião)", "71691-150", "Brasília", "Distrito Federal"),
                new Endereco("Rua K", "156", "Apto. 302", "Sarandi", "91140-046", "Porto Alegre", "Rio Grande do Sul")
        );
        lojasFisicas = Arrays.array(
                new LojaFisica(99L, "15.916.727/0001-90", "Outlet Express", "Vestuário", "(83) 91273-2356", enderecos[0], 50),
                new LojaFisica(101L, "13.455.130/0001-60", "Mercadão das Frutas", "Alimentação", "(52) 3274-1238", enderecos[1], 25),
                new LojaFisica(203L, "84.297.567/0001-61", "Armazém do campo", "Produtos agrícolas", "(21) 98121-1235", enderecos[2], 30)
        );
    }

    @Test
    void lojaFisicaSerializacaoTest() throws IOException {
        LojaFisica lojaFisica = lojasFisicas[0];

        assertThat(json.write(lojaFisica)).isStrictlyEqualToJson("loja_fisica.json");

        assertThat(json.write(lojaFisica)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(json.write(lojaFisica)).extractingJsonPathNumberValue("@.endereco.id").isEqualTo(207);
        assertThat(json.write(lojaFisica)).extractingJsonPathNumberValue("@.numeroFuncionarios").isEqualTo(50);

        assertThat(json.write(lojaFisica)).extractingJsonPathStringValue("@.cnpj").isEqualTo("15.916.727/0001-90");
        assertThat(json.write(lojaFisica)).extractingJsonPathStringValue("@.segmento").isEqualTo("Vestuário");
        assertThat(json.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.logradouro").isEqualTo("Rua Trigésima");
        assertThat(json.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.cep").isEqualTo("68180-500");
    }

}
