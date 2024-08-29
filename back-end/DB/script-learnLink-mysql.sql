-- Active: 1723763035288@@127.0.0.1@3306@learnLink
DROP DATABASE IF EXISTS learnLink;

CREATE DATABASE learnLink;

USE learnLink;

CREATE TABLE classificacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    classificacao VARCHAR(45)
);

CREATE TABLE endereco (
	id INT PRIMARY KEY AUTO_INCREMENT,
    bairro VARCHAR(155),
    cidade VARCHAR(45),
    estado VARCHAR(45),
    cep CHAR(8)
);

CREATE TABLE tipo_status (
	id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(45)
);

CREATE TABLE tipo_usuario (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tipo_usuario VARCHAR(45)
);

CREATE TABLE especialidade (
    id INT PRIMARY KEY AUTO_INCREMENT,
    materia VARCHAR(45)
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT,
    nome VARCHAR(45),
    cpf CHAR(11),
    email VARCHAR(45),
    senha VARCHAR(255),
    especialidade_id INT,
    classificacao_id INT,
    endereco_id INT,
    tipo_status_id INT,
    tipo_usuario_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id),
    FOREIGN KEY (classificacao_id) REFERENCES classificacao(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    FOREIGN KEY (tipo_status_id) REFERENCES tipo_status(id),
    FOREIGN KEY (tipo_usuario_id) REFERENCES tipo_usuario(id)
);

CREATE TABLE registro_login (
    id INT PRIMARY KEY AUTO_INCREMENT,
    registro_login DATETIME,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE tipo_publicacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(15)
);

CREATE TABLE canal (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45)
);

CREATE TABLE publicacao (
    id INT AUTO_INCREMENT,
    conteudo LONGTEXT,
    data_hora DATETIME,
    tipo_publicacao_id INT,
    canal_id INT,
    usuario_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_publicacao_id) REFERENCES tipo_publicacao(id),
    FOREIGN KEY (canal_id) REFERENCES canal(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE comentario (
    id INT AUTO_INCREMENT,
    comentario LONGTEXT,
    data_hora DATETIME,
    publicacao_id INT,
    usuario_id INT,
    PRIMARY KEY (id),FOREIGN KEY (publicacao_id) REFERENCES publicacao(id), 
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE imagem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    link VARCHAR(255),
    publicacao_id INT,
    comentario_id INT,
    FOREIGN KEY (publicacao_id) REFERENCES publicacao(id),
    FOREIGN KEY (comentario_id) REFERENCES comentario(id)
); 

CREATE TABLE tipo_reacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(10),
    pontuacao INT
);

CREATE TABLE reacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo_reacao_id INT,
    publicacao_id INT,
    comentario_id INT,
    usuario_id INT,
    FOREIGN KEY (tipo_reacao_id) REFERENCES tipo_reacao(id),
    FOREIGN KEY (publicacao_id) REFERENCES publicacao(id),
    FOREIGN KEY (comentario_id) REFERENCES comentario(id),
	FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE denuncia (
	id INT PRIMARY KEY AUTO_INCREMENT,
    publicacao_id INT,
    comentario_id INT,
    usuario_id INT,
	FOREIGN KEY (publicacao_id) REFERENCES publicacao(id),
    FOREIGN KEY (comentario_id) REFERENCES comentario(id),
	FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

INSERT INTO tipo_status
        (status)
VALUES
    ('PENDENTE'),
    ('APROVADO'),
    ('NEGADO');
    
INSERT INTO usuario
    (nome, cpf, email, senha, tipo_status_id)
VALUES
    ('José Antônio', '69856985201', 'jose.antonio@gmail.com', 'jose123', 2),
    ('Maria Oliveira', '36598501236', 'maria.oliveira@gmail.com', 'maria123', 1),
    ('Ana Silva', '12345678910', 'ana.silva@gmail.com', 'ana123', 3),
    ('Pedro Souza', '98765432101', 'pedro.souza@gmail.com', 'pedro123', 2),
    ('Juliana Santos', '15975346820', 'juliana.santos@gmail.com', 'juliana123', 3),
    ('Lucas Ferreira', '25836914730', 'lucas.ferreira@gmail.com', 'lucas123', 1),
    ('Otávio Walkovics Carvalho', '25836914730', 'otavio.walk@gmail.com', 'ot123', 1),
    ('Gabriela Monteiro da Silva', '11223344556', 'gabriela.monteiro@gmail.com', 'gabriela123', 2),
    ('Fernanda Costa Rodrigues', '22334455667', 'fernanda.costa@gmail.com', 'fernanda123', 3),
    ('Bruno Henrique Santos Almeida', '33445566778', 'bruno.henrique@gmail.com', 'bruno123', 1),
    ('Carla Maria Souza Pereira', '44556677889', 'carla.maria@gmail.com', 'carla123', 2),
    ('Rodrigo Nascimento Figueira', '55667788990', 'rodrigo.nascimento@gmail.com', 'rodrigo123', 3),
    ('Mariana Oliveira da Costa', '66778899001', 'mariana.oliveira@gmail.com', 'mariana123', 1),
    ('Renato Moreira Vasconcelos', '77889900112', 'renato.moreira@gmail.com', 'renato123', 2),
    ('Alessandra Silva de Lima', '88990011223', 'alessandra.silva@gmail.com', 'alessandra123', 3),
    ('Felipe Carvalho Moura', '99001122334', 'felipe.carvalho@gmail.com', 'felipe123', 1),
    ('Patrícia Santos Silva Costa', '10111223344', 'patricia.santos@gmail.com', 'patricia123', 2),
    ('Victor Hugo Fernandes Rocha', '12131415161', 'victor.hugo@gmail.com', 'victor123', 3),
    ('Carolina de Almeida Souza', '13141516171', 'carolina.almeida@gmail.com', 'carolina123', 1),
    ('Diego Batista Cunha Lopes', '14151617181', 'diego.batista@gmail.com', 'diego123', 2),
    ('Beatriz Gonçalves da Cunha', '15161718191', 'beatriz.goncalves@gmail.com', 'beatriz123', 3),
    ('Daniel Oliveira da Silva', '16171819201', 'daniel.oliveira@gmail.com', 'daniel123', 1),
    ('Camila Costa Barros Rodrigues', '17181920212', 'camila.costa@gmail.com', 'camila123', 2),
    ('Thiago Correia Lopes', '18192021223', 'thiago.correia@gmail.com', 'thiago123', 3),
    ('Larissa Carvalho Souza', '19202122234', 'larissa.carvalho@gmail.com', 'larissa123', 1),
    ('Eduardo Henrique Martins', '20212223345', 'eduardo.martins@gmail.com', 'eduardo123', 2),
    ('Sabrina Fernandes Almeida', '21222324456', 'sabrina.fernandes@gmail.com', 'sabrina123', 3),
    ('Mateus Ribeiro da Silva', '22232425567', 'mateus.ribeiro@gmail.com', 'mateus123', 1),
    ('Bruna Carolina Dias Lima', '23242526678', 'bruna.dias@gmail.com', 'bruna123', 2),
    ('André Luiz Batista', '24252627789', 'andre.luiz@gmail.com', 'andre123', 3),
    ('Rafaela Ferreira Gomes', '25262728890', 'rafaela.ferreira@gmail.com', 'rafaela123', 1),
    ('Marcelo de Souza Carvalho', '26272829901', 'marcelo.carvalho@gmail.com', 'marcelo123', 2),
    ('Talita Almeida Correia', '27282930012', 'talita.almeida@gmail.com', 'talita123', 3),
    ('Gabriel Pereira Nascimento', '28293031123', 'gabriel.pereira@gmail.com', 'gabriel123', 1);

INSERT INTO tipo_publicacao
    (tipo)
VALUES
    ('COMUM');
    
INSERT INTO tipo_reacao
	(nome)
VALUES
	('CURTIDA');

INSERT INTO canal (nome) VALUES
('MATEMATICA'),
('PORTUGUES'),
('BIOLOGIA'),
('HISTORIA'),
('FISICA'),
('QUIMICA'),
('SOCIOLOGIA'),
('GEOGRAFIA'),
('INGLES'),
('FILOSOFIA');

INSERT INTO especialidade (materia) VALUES
('MATEMATICA'),
('PORTUGUES'),
('BIOLOGIA'),
('HISTORIA'),
('FISICA'),
('QUIMICA'),
('SOCIOLOGIA'),
('GEOGRAFIA'),
('INGLES'),
('FILOSOFIA');


INSERT INTO tipo_usuario (tipo_usuario) VALUES
('COMUM'),
('ADMIN');

INSERT INTO classificacao (classificacao) VALUES
('JUNIOR'),
('PLENO'),
('SENIOR'),
('ESPECIALISTA');



CREATE VIEW view_materias_nao_respondidas AS
SELECT
    canal.nome AS nome_materia,
    COUNT(publicacao.id) AS qtd_publicacoes_nao_respondidas
FROM
    canal
LEFT JOIN
    publicacao ON canal.id = publicacao.canal_id
LEFT JOIN
    comentario ON publicacao.id = comentario.publicacao_id
WHERE
    comentario.id IS NULL
GROUP BY
    canal.id, canal.nome;
    
CREATE VIEW vw_publicacoes_denunciadas AS
SELECT 
    publicacao_id,
    COUNT(*) AS quantidade_denuncias
FROM 
    denuncia
WHERE 
    publicacao_id IS NOT NULL
GROUP BY 
    publicacao_id;

CREATE VIEW vw_comentarios_denunciados AS
SELECT 
    comentario_id,
    COUNT(*) AS quantidade_denuncias
FROM 
    denuncia
WHERE 
    comentario_id IS NOT NULL
GROUP BY 
    comentario_id;


INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a fórmula de Bhaskara?', '2024-05-01 10:00:00', 1, 1, 1),
('Como resolver uma equação do segundo grau?', '2024-05-01 12:00:00', 1, 1, 2),
('O que é um logaritmo?', '2024-05-01 14:00:00', 1, 1, 3),
('Como resolver frações?', '2024-05-01 16:00:00', 1, 1, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula de Bhaskara é x = (-b ± √(b² - 4ac)) / 2a.', '2024-05-01 10:30:00', 1, 2),
('Para resolver, você deve encontrar as raízes da equação.', '2024-05-01 12:30:00', 2, 3),
('Um logaritmo é o expoente ao qual uma base deve ser elevada para obter um número.', '2024-05-01 14:30:00', 3, 1);
-- ('Para resolver frações, encontre o denominador comum.', '2024-05-01 16:30:00', 4, 5);

-- Para 2 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a diferença entre ácidos e bases?', '2024-05-02 09:00:00', 1, 6, 1),
('O que é a tabela periódica?', '2024-05-02 11:00:00', 1, 6, 2),
('Como funciona a ligação covalente?', '2024-05-02 13:00:00', 1, 6, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Ácidos liberam H+ em solução, enquanto bases liberam OH-.', '2024-05-02 09:30:00', 5, 2),
('A tabela periódica organiza os elementos químicos por propriedades.', '2024-05-02 11:30:00', 6, 1);

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a força resultante?', '2024-05-03 10:00:00', 1, 5, 1),
('O que é inércia?', '2024-05-03 12:00:00', 1, 5, 2),
('Explique a terceira lei de Newton.', '2024-05-03 14:00:00', 1, 5, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A força resultante é a soma vetorial de todas as forças atuantes.', '2024-05-03 10:30:00', 8, 4),
('Inércia é a tendência de um objeto de resistir a mudanças em seu estado de movimento.', '2024-05-03 12:30:00', 9, 5);




-- Para 4 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um triângulo?', '2024-05-04 09:00:00', 1, 1, 1),
('Qual é a soma dos ângulos internos de um quadrilátero?', '2024-05-04 11:00:00', 1, 1, 2),
('O que é um ângulo reto?', '2024-05-04 13:00:00', 1, 1, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de um triângulo é base vezes altura dividido por dois.', '2024-05-04 09:30:00', 10, 3),
('A soma dos ângulos internos de um quadrilátero é 360 graus.', '2024-05-04 11:30:00', 11, 1),
('Um ângulo reto é um ângulo de 90 graus.', '2024-05-04 13:30:00', 12, 2);

-- Para 5 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular o volume de um cubo?', '2024-05-05 10:00:00', 1, 2, 4),
('O que é uma equação linear?', '2024-05-05 12:00:00', 1, 2, 5),
('Como se define uma função?', '2024-05-05 14:00:00', 1, 2, 6);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O volume de um cubo é dado pelo cubo do comprimento da sua aresta.', '2024-05-05 10:30:00', 13, 6),
('Uma equação linear é uma equação de primeiro grau.', '2024-05-05 12:30:00', 14, 4),
('Uma função relaciona um conjunto de entradas a um conjunto de saídas.', '2024-05-05 14:30:00', 15, 5);

-- Para 6 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é uma célula?', '2024-05-06 09:00:00', 1, 3, 1),
('Explique o processo de fotossíntese.', '2024-05-06 11:00:00', 1, 3, 2),
('O que são organelas celulares?', '2024-05-06 13:00:00', 1, 3, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Uma célula é a unidade básica da vida.', '2024-05-06 09:30:00', 16, 2),
('Fotossíntese é o processo pelo qual as plantas produzem energia.', '2024-05-06 11:30:00', 17, 3),
('Organelas são estruturas especializadas dentro das células.', '2024-05-06 13:30:00', 18, 1);

INSERT INTO denuncia (publicacao_id, usuario_id) VALUES 
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(2, 15),
(2, 16),
(4, 17),
(5, 18),
(6, 19);

INSERT INTO denuncia (comentario_id, usuario_id) VALUES 
(1, 20),
(1, 21),
(2, 22),
(2, 23),
(3, 24),
(4, 25),
(5, 26),
(5, 27);


-- SELECT (INCOMPLETO) para recuperar todos os comentarios de uma publicação especifica para o botão de comentarios (Ausencia de um campo de curtidas na tabela comentarios)
-- SELECT c.id, c.comentario, u.nome AS autor
-- FROM comentario c
-- JOIN usuario u ON c.usuario_id = u.id
-- WHERE c.publicacao_id =1;


