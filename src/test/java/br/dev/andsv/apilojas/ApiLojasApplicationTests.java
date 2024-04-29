package br.dev.andsv.apilojas;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
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

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiLojasApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2-alpine"));

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnAPhysicalStoreWhenDataIsSaved() {
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

        System.out.println(documentContext);
    }

    @Test
    void shouldNotReturnAPhysicalStoreWhithAnUnkownId() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/fisica/99999", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

}
