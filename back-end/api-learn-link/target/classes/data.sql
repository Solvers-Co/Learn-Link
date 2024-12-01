DROP DATABASE IF EXISTS learnLink;

SELECT * FROM usuario;

CREATE DATABASE learnLink;

USE learnLink;

CREATE TABLE escolaridade (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              escolaridade VARCHAR(45)
);

CREATE TABLE classificacao (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               nome VARCHAR(45)
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

CREATE TABLE usuario (
                         id INT AUTO_INCREMENT,
                         nome VARCHAR(45),
                         email VARCHAR(45),
                         senha VARCHAR(255),
                         especialidade VARCHAR(45),
                         ultimoLogin DATETIME,
                         escolaridade_id INT,
                         classificacao_id INT,
                         endereco_id INT,
                         status_id INT,
                         PRIMARY KEY(id),
                         FOREIGN KEY (escolaridade_id) REFERENCES escolaridade(id),
                         FOREIGN KEY (classificacao_id) REFERENCES classificacao(id),
                         FOREIGN KEY (endereco_id) REFERENCES endereco(id),
                         FOREIGN KEY (status_id) REFERENCES tipo_status(id)
);

CREATE TABLE tipo_publicacao (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 tipo VARCHAR(15)
);

CREATE TABLE publicacao (
                            id INT AUTO_INCREMENT,
                            conteudo LONGTEXT,
                            data_hora DATETIME,
                            tipo_publicacao_id INT,
                            usuario_id INT,
                            publicacao_id INT,
                            PRIMARY KEY (id, tipo_publicacao_id),
                            FOREIGN KEY (tipo_publicacao_id) REFERENCES tipo_publicacao(id),
                            FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                            FOREIGN KEY (publicacao_id) REFERENCES publicacao(id)
);

CREATE TABLE imagem (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        link VARCHAR(255),
                        publicacao_id INT,
                        FOREIGN KEY (publicacao_id) REFERENCES publicacao(id)
);

CREATE TABLE tipo_reacao (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             nome VARCHAR(10),
                             pontuacao INT
);

CREATE TABLE reacao (
                        id INT AUTO_INCREMENT,
                        tipo_reacao_id INT,
                        publicacao_id INT,
                        tipo_publicacao_id INT,
                        PRIMARY KEY (id, tipo_reacao_id),
                        FOREIGN KEY (tipo_reacao_id) REFERENCES tipo_reacao(id),
                        FOREIGN KEY (publicacao_id) REFERENCES publicacao(id),
                        FOREIGN KEY (tipo_publicacao_id) REFERENCES tipo_publicacao(id)
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

INSERT INTO tipo_publicacao
(tipo)
VALUES
    ('COMUM'),
    ('COMENTARIO');

INSERT INTO publicacao
(conteudo, tipo_publicacao_id)
VALUES
    ('Estou com dificuldade em quimica orgânica', 1),
    ('Como eu tiro uma média ponderada?', 1),
    ('Quanto é 1+1?', 1),
    ('Qual a diferença de porque, por que e por quê?', 1),
    ('Porque é reposta, por que é pergunta e por que é pergunta no final da frase', 2);
