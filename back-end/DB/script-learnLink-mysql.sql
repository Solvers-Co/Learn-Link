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
('FILOSOFIA'),
('DOACOES');

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

-- select * from reacao;
-- select * from publicacao;
-- select * from endereco;
-- select * from usuario;

