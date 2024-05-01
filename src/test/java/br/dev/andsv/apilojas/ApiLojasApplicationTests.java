package br.dev.andsv.apilojas;

import br.dev.andsv.apilojas.presentation.dtos.EnderecoDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaFisicaDTOCreateRequest;
import br.dev.andsv.apilojas.presentation.dtos.LojaVirtualDTOCreateRequest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
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

    /*
    TESTES PARA CONTROLLER DE LojaFisica
     */

    @Test
    void deveRetornarLojaFisicaQuandoDadoEstaSalvo() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/fisica/99", String.class);

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
    }

    @Test
    void naoDeveRetornarLojaFisicaComIdDesconhecida() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/fisica/99999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void deveCriarUmaNovaLojaFisica() {
        LojaFisicaDTOCreateRequest novaLojaFisica = new LojaFisicaDTOCreateRequest(
                "52.797.678/0001-40",
                "Babaçu Financeira ME",
                "Finanças",
                "(98) 3593-2158",
                new EnderecoDTOCreateRequest(
                        "Rua Dezoito de Janeiro",
                        "904",
                        null,
                        "Anil",
                        "65045-300",
                        "São Luís",
                        "Maranhão"
                ),
                30);

        ResponseEntity<?> createResponse = restTemplate
                .postForEntity("/api/v1/fisica", novaLojaFisica, Void.class);

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

    @Test
    void deveRetornarTodasAsLojasFisicasQuandoRequisitadas() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/fisica", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int totalLojasFisicas = documentContext.read("$.length()");
        assertThat(totalLojasFisicas).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101, 207, 128, 376);

        JSONArray cnpjs = documentContext.read("$..cnpj");
        assertThat(cnpjs).containsExactlyInAnyOrder("15.916.727/0001-90", "02.477.025/0001-06", "48.569.115/0001-28");

        JSONArray ceps = documentContext.read("$..cep");
        assertThat(ceps).containsExactlyInAnyOrder("68180-500", "58067-140", "15763-970");
    }

    @Test
    void deveRetornarUmaPaginaDeLojasFisicas() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/fisica?page=0&size=1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }

    @Test
    void deveRetornarUmaPaginaOrdenadaDeLojasFisicas() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/fisica?page=0&size=1&sort=id,desc", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        int id = documentContext.read("$[0].id");
        assertThat(id).isEqualTo(101);
    }

    @Test
    void deveRetornarUmaPaginaOrdenadadeLojaFisicaSemParametrosUsandoValoresPadrao() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/fisica", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");

        assertThat(page.size()).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactly(99, 207, 100, 128, 101, 376);
    }

    /*
    TESTES PARA CONTROLLER DE LojaVirtual
     */

    @Test
    void deveRetornarLojaVirtualQuandoDadoEstaSalvo() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/virtual/57", String.class);

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
                .getForEntity("/api/v1/virtual/99999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void deveCriarUmaNovaLojaVirtual() {
        LojaVirtualDTOCreateRequest novaLojaVirtual = new LojaVirtualDTOCreateRequest(
                "63.776.049/0001-50",
                "Nunes Importados",
                "Importação/Exportação",
                "(84) 2990-1094",
                "www.nunesimports.com",
                "2.8",
                "andsvb2");

        ResponseEntity<Void> createResponse = restTemplate
                .postForEntity("/api/v1/virtual", novaLojaVirtual, Void.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI localDaNovaLojaVirtual = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(localDaNovaLojaVirtual, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();

        String cnpj = documentContext.read("$.cnpj");
        assertThat(cnpj).isEqualTo("63.776.049/0001-50");

        String telefone = documentContext.read("$.telefone");
        assertThat(telefone).isEqualTo("(84) 2990-1094");
    }

    @Test
    void deveRetornarTodasAsLojasVirtuaisQuandoRequisitadas() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/virtual", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int totalLojasVirtuais = documentContext.read("$.length()");
        assertThat(totalLojasVirtuais).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(57, 58, 59);
    }

    @Test
    void deveRetornarUmaPaginaDeLojasVirtuais() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/virtual?page=0&size=1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }

    @Test
    void deveRetornarUmaPaginaOrdenadaDeLojasVirtuais() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/virtual?page=0&size=1&sort=id,desc", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        int id = documentContext.read("$[0].id");
        assertThat(id).isEqualTo(59);
    }

    @Test
    void deveRetornarUmaPaginaOrdenadadeLojaVirtualSemParametrosUsandoValoresPadrao() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/api/v1/virtual", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");

        assertThat(page.size()).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactly(57, 58, 59);
    }


}
