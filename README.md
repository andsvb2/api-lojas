# API Lojas

API em Spring Boot para gerenciamento de cadastros de lojas.
Este projeto foi desenvolvido para a seleção de estágio em backend da RPE.

## Tecnologias utilizadas
- Java 21
- Maven
- PostgreSQL
- Spring Boot
- Spring Data JPA
- Spring Web
- Swagger
- Spring Security
- Testcontainers

## Onde encontrar uma instância do projeto

Você pode encontrar o projeto sendo executado aqui: [https://api-lojas-y05k.onrender.com](https://api-lojas-y05k.onrender.com)  
O swagger da aplicação está no endereço: [https://api-lojas-y05k.onrender.com/swagger-ui/index.html](https://api-lojas-y05k.onrender.com/swagger-ui/index.html)  
O acesso só é possível mediante uso de credenciais de acesso (disponibilizada aos avaliadores da seleção).

Utilizei a plataforma [Render](https://render.com/) para hospedar a aplicação.  
Nela eu criei uma instância do PostgreSQL e iniciei a aplicação Spring utilizando uma [imagem docker que subi ao meu repositório pessoal](https://hub.docker.com/r/andsvb2/api-lojas).   

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

Ou, ainda, com o docker-compose fornecido:
 ```shell
docker compose up
```

> [!IMPORTANT]
> É necessário ter Docker instalado para executar os testes, pois o projeto utiliza Testcontainers. 
 
## Como acessar a documentação do projeto
Caso esteja executando localmente, a documentação dos _endpoints_ é acessada a partir da URL:
``http://localhost:8080/swagger-ui/index.html``