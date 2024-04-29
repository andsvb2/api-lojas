CREATE TABLE endereco
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    logradouro  VARCHAR(255),
    numero      VARCHAR(255),
    complemento VARCHAR(255),
    bairro      VARCHAR(255),
    cep         VARCHAR(255),
    cidade      VARCHAR(255),
    estado      VARCHAR(255),
    CONSTRAINT pk_endereco PRIMARY KEY (id)
);

CREATE TABLE loja_fisica
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    cnpj                VARCHAR(255),
    nome                VARCHAR(255),
    segmento            VARCHAR(255),
    telefone            VARCHAR(255),
    endereco_id         BIGINT,
    numero_funcionarios INTEGER,
    CONSTRAINT pk_loja_fisica PRIMARY KEY (id)
);

CREATE TABLE loja_virtual
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    cnpj      VARCHAR(255),
    nome      VARCHAR(255),
    segmento  VARCHAR(255),
    telefone  VARCHAR(255),
    url       VARCHAR(255),
    avaliacao VARCHAR(255),
    CONSTRAINT pk_loja_virtual PRIMARY KEY (id)
);

ALTER TABLE loja_fisica
    ADD CONSTRAINT uc_loja_fisica_cnpj UNIQUE (cnpj);

ALTER TABLE loja_fisica
    ADD CONSTRAINT uc_loja_fisica_endereco UNIQUE (endereco_id);

ALTER TABLE loja_virtual
    ADD CONSTRAINT uc_loja_virtual_cnpj UNIQUE (cnpj);

ALTER TABLE loja_fisica
    ADD CONSTRAINT FK_LOJA_FISICA_ON_ENDERECO FOREIGN KEY (endereco_id) REFERENCES endereco (id);