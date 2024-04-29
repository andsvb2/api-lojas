INSERT INTO endereco(id, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES (207, 'Rua Trigésima', '57-B', 'Galpão 2', 'Piracanã', '68180-500', 'Itaituba', 'Pará');

INSERT INTO loja_fisica(id, cnpj, nome, segmento, telefone, endereco_id, numero_funcionarios)
VALUES (99, '15.916.727/0001-90', 'Outlet Express', 'Vestuário', '(83) 91273-2356', 207, 50);

INSERT INTO loja_virtual(id, cnpj, nome, segmento, telefone, url, avaliacao)
VALUES (57, '73.197.397/0001-27', 'GamerCenter', 'Eletrônicos', '(11) 3245-9835', 'https://gcenter.com.br', '4.5');