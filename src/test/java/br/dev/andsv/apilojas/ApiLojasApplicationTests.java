package br.dev.andsv.apilojas;

import br.dev.andsv.apilojas.core.entities.Endereco;
import br.dev.andsv.apilojas.core.entities.LojaFisica;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiLojasApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2-alpine"));

    @Autowired
    TestRestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(ApiLojasApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void deveRetornarLojaFisicaQuandoDadoEstaSalvo() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/fisica/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);

        String cnpj = documentContext.read("$.cnpj");
        assertThat(cnpj).isEqualTo("15.916.727/0001-90");

        String nome = documentContext.read("$.nome");
        assertThat(nome).isEqualTo("Outlet Express");

        Number enderecoId = documentContext.read("$.endereco.id");
        assertThat(enderecoId).isEqualTo(207);

        String logradouro = documentContext.read("$.endereco.logradouro");
        assertThat(logradouro).isEqualTo("Rua Trigésima");

        String cep = documentContext.read("$.endereco.cep");
        assertThat(cep).isEqualTo("68180-500");

        System.out.println(documentContext);
    }

    @Test
    void naoDeveRetornarLojaFisicaComIdDesconhecida() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/fisica/99999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void deveRetornarLojaVirtualQuandoDadoEstaSalvo() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/virtual/57", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(57);

        String cnpj = documentContext.read("$.cnpj");
        assertThat(cnpj).isEqualTo("73.197.397/0001-27");

        String nome = documentContext.read("$.nome");
        assertThat(nome).isEqualTo("GamerCenter");

        String segmento = documentContext.read("$.segmento");
        assertThat(segmento).isEqualTo("Eletrônicos");

        String telefone = documentContext.read("$.telefone");
        assertThat(telefone).isEqualTo("(11) 3245-9835");

        String url = documentContext.read("$.url");
        assertThat(url).isEqualTo("https://gcenter.com.br");

        String avaliacao = documentContext.read("$.avaliacao");
        assertThat(avaliacao).isEqualTo("4.5");
    }

    @Test
    void naoDeveRetornarLojaVirtualComIdDesconhecida() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/virtual/99999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void deveCriarUmaNovaLojaFisica() {
        LojaFisica novaLojaFisica = new LojaFisica(
                null,
                "52.797.678/0001-40",
                "Babaçu Financeira ME",
                "Finanças",
                "(98) 3593-2158",
                new Endereco(
                        "Rua Dezoito de Janeiro",
                        "904",
                        null,
                        "Anil",
                        "65045-300",
                        "São Luís",
                        "Maranhão"
                ),
                30);

        ResponseEntity<Void> createResponse = restTemplate
                .postForEntity("/fisica", novaLojaFisica, Void.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI localDaNovaLojaFisica = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(localDaNovaLojaFisica, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();

        Number endereco_id = documentContext.read("$.endereco.id");
        assertThat(endereco_id).isNotNull();

        String cnpj = documentContext.read("$.cnpj");
        assertThat(cnpj).isEqualTo("52.797.678/0001-40");

        String cep = documentContext.read("$.endereco.cep");
        assertThat(cep).isEqualTo("65045-300");
    }

}
