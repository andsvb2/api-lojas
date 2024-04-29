package br.dev.andsv.apilojas;

import br.dev.andsv.apilojas.core.entities.Endereco;
import br.dev.andsv.apilojas.core.entities.LojaFisica;
import br.dev.andsv.apilojas.core.entities.LojaVirtual;
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
    private JacksonTester<LojaFisica> jsonLojaFisica;

    @Autowired
    private JacksonTester<LojaFisica[]> jsonListLojaFisica;

    @Autowired
    private JacksonTester<LojaVirtual> jsonLojaVirtual;

    @Autowired
    private JacksonTester<LojaVirtual[]> jsonListLojaVirtual;

    private LojaFisica[] lojasFisicas;
    private Endereco[] enderecos;
    private LojaVirtual[] lojasVirtuais;


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
        lojasVirtuais = Arrays.array(
                new LojaVirtual(57L, "73.197.397/0001-27", "GamerCenter", "Eletrônicos", "(11) 3245-9835", "https://gcenter.com.br", "4.5"),
                new LojaVirtual("91.012.582/0001-81", "Pet Store", "Pet Shop", "(85) 912312-1287", "www.pet-store.com", "4.0"),
                new LojaVirtual("64.356.277/0001-34", "Livros Online", "Livrarias", "(98) 97623-1902", "https://www.lelivros.com.br", "3.7")
        );
    }

    @Test
    void lojaFisicaSerializacaoTest() throws IOException {
        LojaFisica lojaFisica = lojasFisicas[0];

        assertThat(jsonLojaFisica.write(lojaFisica)).isStrictlyEqualToJson("loja_fisica.json");

        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.cnpj").isEqualTo("15.916.727/0001-90");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.nome").isEqualTo("Outlet Express");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.segmento").isEqualTo("Vestuário");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.telefone").isEqualTo("(83) 91273-2356");

        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathNumberValue("@.endereco.id").isEqualTo(207);
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.logradouro").isEqualTo("Rua Trigésima");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.numero").isEqualTo("57-B");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.complemento").isEqualTo("Galpão 2");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.bairro").isEqualTo("Piracanã");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.cep").isEqualTo("68180-500");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.cidade").isEqualTo("Itaituba");
        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.estado").isEqualTo("Pará");

        assertThat(jsonLojaFisica.write(lojaFisica)).extractingJsonPathNumberValue("@.numeroFuncionarios").isEqualTo(50);
    }

    @Test
    void lojaFisicaDeserializacaoTest() throws IOException {
        String esperado = """
                {
                  "id": 99,
                  "cnpj": "15.916.727/0001-90",
                  "nome": "Outlet Express",
                  "segmento": "Vestuário",
                  "telefone": "(83) 91273-2356",
                  "endereco": {
                    "id": 207,
                    "logradouro":"Rua Trigésima",
                    "numero": "57-B",
                    "complemento": "Galpão 2",
                    "bairro": "Piracanã",
                    "cep": "68180-500",
                    "cidade": "Itaituba",
                    "estado": "Pará"
                  },
                  "numeroFuncionarios": 50
                }
                """;

        LojaFisica lojaFisica = lojasFisicas[0];

        assertThat(jsonLojaFisica.parse(esperado))
                .isEqualTo(new LojaFisica(
                        99L,
                        "15.916.727/0001-90",
                        "Outlet Express",
                        "Vestuário",
                        "(83) 91273-2356",
                        new Endereco(207L, "Rua Trigésima", "57-B", "Galpão 2", "Piracanã", "68180-500", "Itaituba", "Pará"),
                        50));
    }

    @Test
    void lojaVirtualSerializacaoTest() throws IOException {
        LojaVirtual lojaVirtual = lojasVirtuais[0];

        assertThat(jsonLojaVirtual.write(lojaVirtual)).isStrictlyEqualToJson("loja_virtual.json");

        assertThat(jsonLojaVirtual.write(lojaVirtual)).extractingJsonPathNumberValue("@.id").isEqualTo(57);
        assertThat(jsonLojaVirtual.write(lojaVirtual)).extractingJsonPathStringValue("@.cnpj").isEqualTo("73.197.397/0001-27");
        assertThat(jsonLojaVirtual.write(lojaVirtual)).extractingJsonPathStringValue("@.nome").isEqualTo("GamerCenter");
        assertThat(jsonLojaVirtual.write(lojaVirtual)).extractingJsonPathStringValue("@.telefone").isEqualTo("(11) 3245-9835");
        assertThat(jsonLojaVirtual.write(lojaVirtual)).extractingJsonPathStringValue("@.url").isEqualTo("https://gcenter.com.br");
        assertThat(jsonLojaVirtual.write(lojaVirtual)).extractingJsonPathStringValue("@.avaliacao").isEqualTo("4.5");
    }

}
