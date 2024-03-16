-- Inserir um novo usuário
insert into usuario (nome) values ('Fernanda');

-- Inserir um novo carro
insert into carro (codigo, modelo, fabricante) values ('PQR678', 'Fiesta', 'Ford');

-- Inserir uma nova peça associada a um carro específico (carro com ID 1)
insert into peca (codigo, nome, descricao, numero_serie, fabricante, modelo_carro_id) values ('006', 'Amortecedor', 'Amortecedor dianteiro', '24680', 'Cofap', 1);

-- Consultar todos os usuários
select * from usuario;

-- Consultar todos os carros
select * from Carro;

-- Consultar todas as peças
select * from peca;

-- Consultar peças de um carro específico (carro com ID 1)
select * from peca where modelo_carro_id = 1;

-- Atualizar o nome de um usuário (usuário com ID 1)
update usuario set nome = 'Felipe' where id = 1;

-- Atualizar o fabricante de um carro (carro com ID 2)
UPDATE carro SET fabricante = 'Chevrolet' where id = 2;

-- Atualizar o fabricante de uma peça (peça com ID 1)
update peca set fabricante = 'Bosch' where id = 1;

-- Excluir um usuário (usuário com ID 3)
delete from usuario where id = 3;

-- Excluir um carro (carro com ID 4)
delete from carro where id = 4;

-- Excluir uma peça (peça com ID 2)
delete from peca where id = 2;
