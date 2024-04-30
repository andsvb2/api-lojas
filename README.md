# API Lojas

API em Spring Boot para gerenciamento de cadastros de lojas.
Este projeto foi desenvolvido para a seleção de estágio em backend da RPE.

## Como executar este projeto

- Baixe o projeto:
```shell
git clone git@github.com:andsvb2/api-lojas.git
```
- Construa o pacote:
```shell
./mvnw clean package
```
- Execute o pacote JAR:
```shell
java -jar ./target/api-lojas-0.0.1-SNAPSHOT.jar
```

De forma alternativa, é possível executar diretamente com o Maven:
```shell
./mvnw spring-boot:run
```

> [!IMPORTANT]
> É necessário ter Docker instalado para executar os testes, pois o projeto utiliza Testcontainers. 
 
## Como acessar a documentação do projeto
Caso esteja executando localmente, a documentação dos _endpoints_ é acessada a partir da URL:
``http://localhost:8080/swagger-ui/index.html``