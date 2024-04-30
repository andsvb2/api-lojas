-- ENDEREÇO
INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (207, 'Rua Trigésima', '57-B', 'Galpão 2', 'Piracanã', '68180-500', 'Itaituba', 'Pará');

INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (128, 'Rua Silvério Miguel dos Santos', 'S/N', null, 'Gramame', '58067-140', 'João Pessoa', 'Paraíba');

INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (376, 'Rua Santos 163', '137', 'Apto. 204', 'Centro', '15763-970', 'Aspásia', 'São Paulo');

-- LOJA FÍSICA
INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios)
VALUES (99, '15.916.727/0001-90', 'Outlet Express', 'Vestuário', '(83) 91273-2356', 207, 50);

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios)
VALUES (100, '02.477.025/0001-06', 'Noah Joalheria ME', 'Joalheria', '(83) 2547-5278', 128, 15);

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios)
VALUES (101, '48.569.115/0001-28', 'Pizzaria Delivery Ltda', 'Restaurante', '(11) 3966-2679', 376, 10);

-- LOJA VIRTUAL
INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao)
VALUES (57, '73.197.397/0001-27', 'GamerCenter', 'Eletrônicos', '(11) 3245-9835', 'https://gcenter.com.br', '4.5');

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao)
VALUES (58, '23.759.609/0001-44', 'Raul Adega Online', 'Bebidas', '(86) 3857-8970', 'www.rauladega.com.br', '3.7');

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao)
VALUES (59, '37.694.867/0001-02', 'Fast Telas', 'Decoração', '(92) 3701-8763', 'https://www.fast-telas.com', '4.2');
