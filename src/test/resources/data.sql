-- ENDEREÇO
INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (207, 'Rua Trigésima', '57-B', 'Galpão 2', 'Piracanã', '68180-500', 'Itaituba', 'Pará');

INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (128, 'Rua Silvério Miguel dos Santos', 'S/N', null, 'Gramame', '58067-140', 'João Pessoa', 'Paraíba');

INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (376, 'Rua Santos 163', '137', 'Apto. 204', 'Centro', '15763-970', 'Aspásia', 'São Paulo');

INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (100, 'Rua Santos Dumont', '415', null, 'Santa Rita', '68901-270', 'Macapá', 'Amapá');

INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (101, 'Avenida República Argentina', '825', 'Sala 703', 'Água Verde', '80620-010', 'Curitiba', 'Paraná');

-- LOJA FÍSICA
INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios, responsavel)
VALUES (100, '02.477.025/0001-06', 'Noah Joalheria ME', 'Joalheria', '(83) 2547-5278', 128, 15, 'andsvb2');

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios, responsavel)
VALUES (99, '15.916.727/0001-90', 'Outlet Express', 'Vestuário', '(83) 91273-2356', 207, 50, 'andsvb2');

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios, responsavel)
VALUES (101, '48.569.115/0001-28', 'Pizzaria Delivery Ltda', 'Restaurante', '(11) 3966-2679', 376, 10, 'andsvb2');

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios, responsavel)
VALUES (103, '22.642.083/0001-55', 'Ana e Mariane Esportes ME', 'Artigos esportivos', '(96) 3762-6894', 100, 20, 'rpe');

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios, responsavel)
VALUES (102, '67.410.030/0001-47', 'Marcos Vinicius e Malu Publicidade e Propaganda Ltda', 'Publicidade', '(41) 3828-9808', 101, 10, 'rpe');

-- LOJA VIRTUAL
INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao, responsavel)
VALUES (57, '73.197.397/0001-27', 'GamerCenter', 'Eletrônicos', '(11) 3245-9835', 'https://gcenter.com.br', '4.5', 'andsvb2');

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao, responsavel)
VALUES (59, '37.694.867/0001-02', 'Fast Telas', 'Decoração', '(92) 3701-8763', 'https://www.fast-telas.com', '4.2', 'andsvb2');

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao, responsavel)
VALUES (58, '23.759.609/0001-44', 'Raul Adega Online', 'Bebidas', '(86) 3857-8970', 'www.rauladega.com.br', '3.7', 'andsvb2');

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao, responsavel)
VALUES (61, '11.226.435/0001-75', 'Ricardo e Sarah Comercio de Bebidas Ltda', 'Bebidas', '(27) 3536-9133', 'www.ricardoesarahcomerciodebebidasltda.com.br', '4.2', 'rpe');

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao, responsavel)
VALUES (60, '62.840.947/0001-68', 'Brenda e Pedro Henrique Contábil ME', 'Contabilidade', '(85) 3983-1717', 'www.brendaepedrohenriquecontabilme.com.br', '3.7', 'rpe');
