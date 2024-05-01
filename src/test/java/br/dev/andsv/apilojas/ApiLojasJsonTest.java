package br.dev.andsv.apilojas;

import br.dev.andsv.apilojas.model.entities.Endereco;
import br.dev.andsv.apilojas.model.entities.LojaFisica;
import br.dev.andsv.apilojas.model.entities.LojaVirtual;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ApiLojasJsonTest {

    private static final Logger log = LoggerFactory.getLogger(ApiLojasJsonTest.class);
    @Autowired
    private JacksonTester<LojaFisica> jsonLFis;
    @Autowired
    private JacksonTester<LojaFisica[]> jsonListLFis;
    @Autowired
    private JacksonTester<LojaVirtual> jsonLVirt;
    @Autowired
    private JacksonTester<LojaVirtual[]> jsonListLVirt;
    private LojaFisica[] lojasFisicas;
    private Endereco[] enderecos;
    private LojaVirtual[] lojasVirtuais;

    @BeforeEach
    void setup() {
        enderecos = Arrays.array(
                new Endereco(207L, "Rua Trigésima", "57-B", "Galpão 2", "Piracanã", "68180-500", "Itaituba", "Pará"),
                new Endereco(128L, "Rua Silvério Miguel dos Santos", "S/N", null, "Gramame", "58067-140", "João Pessoa", "Paraíba"),
                new Endereco(376L, "Rua Santos 163", "137", "Apto. 204", "Centro", "15763-970", "Aspásia", "São Paulo")
        );
        lojasFisicas = Arrays.array(
                new LojaFisica(99L, "15.916.727/0001-90", "Outlet Express", "Vestuário", "(83) 91273-2356", enderecos[0], 50, "andsvb2"),
                new LojaFisica(100L, "02.477.025/0001-06", "Noah Joalheria ME", "Joalheria", "(83) 2547-5278", enderecos[1], 15, "andsvb2"),
                new LojaFisica(101L, "48.569.115/0001-28", "Pizzaria Delivery Ltda", "Restaurante", "(11) 3966-2679", enderecos[2], 10, "andsvb2")
        );
        lojasVirtuais = Arrays.array(
                new LojaVirtual(57L, "73.197.397/0001-27", "GamerCenter", "Eletrônicos", "(11) 3245-9835", "https://gcenter.com.br", "4.5", "andsvb2"),
                new LojaVirtual(58L, "23.759.609/0001-44", "Raul Adega Online", "Bebidas", "(86) 3857-8970", "www.rauladega.com.br", "3.7", "andsvb2"),
                new LojaVirtual(59L, "37.694.867/0001-02", "Fast Telas", "Decoração", "(92) 3701-8763", "https://www.fast-telas.com", "4.2", "andsvb2")
        );
    }

    @Test
    void serializacaoLojaFisicaTest() throws IOException {
        LojaFisica lojaFisica = lojasFisicas[0];

        assertThat(jsonLFis.write(lojaFisica)).isStrictlyEqualToJson("loja_fisica.json");

        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.cnpj").isEqualTo("15.916.727/0001-90");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.nome").isEqualTo("Outlet Express");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.segmento").isEqualTo("Vestuário");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.telefone").isEqualTo("(83) 91273-2356");

        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathNumberValue("@.endereco.id").isEqualTo(207);
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.logradouro").isEqualTo("Rua Trigésima");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.numero").isEqualTo("57-B");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.complemento").isEqualTo("Galpão 2");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.bairro").isEqualTo("Piracanã");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.cep").isEqualTo("68180-500");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.cidade").isEqualTo("Itaituba");
        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathStringValue("@.endereco.estado").isEqualTo("Pará");

        assertThat(jsonLFis.write(lojaFisica)).extractingJsonPathNumberValue("@.numeroFuncionarios").isEqualTo(50);
    }

    @Test
    void desserializacaoLojaFisicaTest() throws IOException {
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
                  "numeroFuncionarios": 50,
                  "responsavel": "andsvb2"
                }
                """;

        assertThat(jsonLFis.parse(esperado))
                .usingRecursiveComparison()
                .isEqualTo(new LojaFisica(
                        99L,
                        "15.916.727/0001-90",
                        "Outlet Express",
                        "Vestuário",
                        "(83) 91273-2356",
                        new Endereco(207L, "Rua Trigésima", "57-B", "Galpão 2", "Piracanã", "68180-500", "Itaituba", "Pará"),
                        50,
                        "andsvb2"));

        assertThat(jsonLFis.parseObject(esperado).getId()).isEqualTo(99);
        assertThat(jsonLFis.parseObject(esperado).getCnpj()).isEqualTo("15.916.727/0001-90");
        assertThat(jsonLFis.parseObject(esperado).getNome()).isEqualTo("Outlet Express");
        assertThat(jsonLFis.parseObject(esperado).getTelefone()).isEqualTo("(83) 91273-2356");
        assertThat(jsonLFis.parseObject(esperado).getEndereco().getId()).isEqualTo(207L);
        assertThat(jsonLFis.parseObject(esperado).getEndereco().getCep()).isEqualTo("68180-500");
        assertThat(jsonLFis.parseObject(esperado).getNumeroFuncionarios()).isEqualTo(50);
    }

    @Test
    void serializacaoLojaVirtualTest() throws IOException {
        LojaVirtual lojaVirtual = lojasVirtuais[0];

        assertThat(jsonLVirt.write(lojaVirtual)).isStrictlyEqualToJson("loja_virtual.json");

        assertThat(jsonLVirt.write(lojaVirtual)).extractingJsonPathNumberValue("@.id").isEqualTo(57);
        assertThat(jsonLVirt.write(lojaVirtual)).extractingJsonPathStringValue("@.cnpj").isEqualTo("73.197.397/0001-27");
        assertThat(jsonLVirt.write(lojaVirtual)).extractingJsonPathStringValue("@.nome").isEqualTo("GamerCenter");
        assertThat(jsonLVirt.write(lojaVirtual)).extractingJsonPathStringValue("@.telefone").isEqualTo("(11) 3245-9835");
        assertThat(jsonLVirt.write(lojaVirtual)).extractingJsonPathStringValue("@.url").isEqualTo("https://gcenter.com.br");
        assertThat(jsonLVirt.write(lojaVirtual)).extractingJsonPathStringValue("@.avaliacao").isEqualTo("4.5");
    }

    @Test
    void desserializacaoLojaVirtualTest() throws IOException {
        String esperado = """
                {
                  "id": 57,
                  "cnpj": "73.197.397/0001-27",
                  "nome": "GamerCenter",
                  "segmento": "Eletrônicos",
                  "telefone": "(11) 3245-9835",
                  "url":  "https://gcenter.com.br",
                  "avaliacao": "4.5",
                  "responsavel": "andsvb2"
                }
                """;

        assertThat(jsonLVirt.parse(esperado))
                .isEqualTo(new LojaVirtual(
                        57L,
                        "73.197.397/0001-27",
                        "GamerCenter",
                        "Eletrônicos",
                        "(11) 3245-9835",
                        "https://gcenter.com.br",
                        "4.5",
                        "andsvb2"));

        assertThat(jsonLVirt.parseObject(esperado).getId()).isEqualTo(57L);
        assertThat(jsonLVirt.parseObject(esperado).getUrl()).isEqualTo("https://gcenter.com.br");
    }

    @Test
    void serializacaoListaLFisicaTest() throws IOException {
        assertThat(jsonListLFis.write(lojasFisicas)).isStrictlyEqualToJson("lista_loja_fisica.json");
    }

    @Test
    void desserializacaoListaLFisicaTest() throws IOException {
        String esperado = """
                [
                  { "id": 99, "cnpj": "15.916.727/0001-90", "nome": "Outlet Express", "segmento": "Vestuário", "telefone": "(83) 91273-2356", "endereco": { "id": 207, "logradouro": "Rua Trigésima", "numero": "57-B", "complemento": "Galpão 2", "bairro": "Piracanã", "cep": "68180-500", "cidade": "Itaituba", "estado": "Pará" }, "numeroFuncionarios": 50, "responsavel": "andsvb2" },
                  { "id": 100, "cnpj": "02.477.025/0001-06", "nome": "Noah Joalheria ME", "segmento": "Joalheria", "telefone": "(83) 2547-5278", "endereco": { "id": 128, "logradouro": "Rua Silvério Miguel dos Santos", "numero": "S/N", "complemento": null, "bairro": "Gramame", "cep": "58067-140", "cidade": "João Pessoa", "estado": "Paraíba" }, "numeroFuncionarios": 15, "responsavel": "andsvb2" },
                  { "id": 101, "cnpj": "48.569.115/0001-28", "nome": "Pizzaria Delivery Ltda", "segmento": "Restaurante", "telefone": "(11) 3966-2679", "endereco": { "id": 376, "logradouro": "Rua Santos 163", "numero": "137", "complemento": "Apto. 204", "bairro": "Centro", "cep": "15763-970", "cidade": "Aspásia", "estado": "São Paulo" }, "numeroFuncionarios": 10, "responsavel": "andsvb2" }
                ]
                """;

        assertThat(jsonListLFis.parse(esperado)).usingRecursiveComparison().isEqualTo(lojasFisicas);
    }

    @Test
    void serializacaoListaLVirtualTest() throws IOException {
        assertThat(jsonListLVirt.write(lojasVirtuais)).isStrictlyEqualToJson("lista_loja_virtual.json");
    }

    @Test
    void desserializacaoListaLVirtualTest() throws IOException {
        String esperado = """
                [
                  { "id": 57, "cnpj": "73.197.397/0001-27", "nome": "GamerCenter", "segmento": "Eletrônicos", "telefone": "(11) 3245-9835", "url":  "https://gcenter.com.br", "avaliacao": "4.5", "responsavel": "andsvb2" },
                  { "id": 58, "cnpj": "23.759.609/0001-44", "nome": "Raul Adega Online", "segmento": "Bebidas", "telefone": "(86) 3857-8970", "url":  "www.rauladega.com.br", "avaliacao": "3.7", "responsavel": "andsvb2" },
                  { "id": 59, "cnpj": "37.694.867/0001-02", "nome": "Fast Telas", "segmento": "Decoração", "telefone": "(92) 3701-8763", "url":  "https://www.fast-telas.com", "avaliacao": "4.2", "responsavel": "andsvb2" }
                ]
                """;

        assertThat(jsonListLVirt.parse(esperado)).usingRecursiveComparison().isEqualTo(lojasVirtuais);

    }

}
