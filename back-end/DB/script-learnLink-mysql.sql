-- Active: 1695823604597@@127.0.0.1@3306@learnLink
DROP DATABASE learnLink;

CREATE DATABASE learnLink;

USE learnLink;

CREATE TABLE escolaridade (
    id INT PRIMARY KEY AUTO_INCREMENT,
    escolaridade VARCHAR(45)
);

CREATE TABLE classificacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    classificacao VARCHAR(45)
);

CREATE TABLE endereco (
	id INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(255),
    numero INT,
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

CREATE TABLE usuario (
    id INT AUTO_INCREMENT,
    nome VARCHAR(45),
    cpf CHAR(11),
    email VARCHAR(45),
    senha VARCHAR(255),
    especialidade VARCHAR(45),
    escolaridade_id INT,
    classificacao_id INT,
    endereco_id INT,
    tipo_status_id INT,
    tipo_usuario_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (escolaridade_id) REFERENCES escolaridade(id),
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

CREATE TABLE arquivo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    tipo VARCHAR(45),
    url VARCHAR(255)
);

CREATE TABLE sala (
    id INT PRIMARY KEY AUTO_INCREMENT,
    publicacao_id INT,
    FOREIGN KEY (publicacao_id) REFERENCES publicacao(id)
);

CREATE TABLE sala_usuario (
    sala_id INT,
    usuario_id INT,
    PRIMARY KEY (sala_id, usuario_id),
    FOREIGN KEY (sala_id) REFERENCES sala(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE mensagem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conteudo VARCHAR(500),
    data_hora DATETIME,
    usuario_id INT,
    sala_id INT,
    arquivo_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (sala_id) REFERENCES sala(id),
    FOREIGN KEY (arquivo_id) REFERENCES arquivo(id)
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
    ('Maria Oliveira', '36598501236', 'maria.oliveira@gmail.com', 'maria123', 2),
    ('Ana Silva', '12345678910', 'ana.silva@gmail.com', 'ana123', 2),
    ('Pedro Souza', '98765432101', 'pedro.souza@gmail.com', 'pedro123', 2),
    ('Juliana Santos', '15975346820', 'juliana.santos@gmail.com', 'juliana123', 2),
    ('Lucas Ferreira', '25836914730', 'lucas.ferreira@gmail.com', 'lucas123', 2);

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
('SOCIOLOGIA');


INSERT INTO tipo_usuario (tipo_usuario) VALUES
('COMUM'),
('ADMIN');

INSERT INTO classificacao (classificacao) VALUES
('JUNIOR'),
('PLENO'),
('SENIOR'),
('ESPECIALISTA');

INSERT INTO escolaridade (escolaridade) VALUES
('ENSINO MEDIO COMPLETO'),
('CURSANDO ENSINO MEDIO');








    
