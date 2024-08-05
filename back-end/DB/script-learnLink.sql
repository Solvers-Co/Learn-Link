USE master;
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = N'learnLink')
    DROP DATABASE learnLink;
GO

CREATE DATABASE learnLink;
GO

USE learnLink;
GO

CREATE TABLE Escolaridade (
    esc_id INT PRIMARY KEY IDENTITY,
    esc_escolaridade VARCHAR(45)
);

CREATE TABLE Classificacao (
    cls_id INT PRIMARY KEY IDENTITY,
    cls_nome VARCHAR(45)
);

CREATE TABLE Estado (
    est_id INT PRIMARY KEY IDENTITY,
    est_nome VARCHAR(45)
);

CREATE TABLE Cidade (
    cid_id INT PRIMARY KEY IDENTITY,
    cid_nome VARCHAR(45),
    fk_estado INT,
    FOREIGN KEY (fk_estado) REFERENCES Estado(est_id)
);

CREATE TABLE Usuario (
    usr_id INT IDENTITY,
    usr_nome VARCHAR(45),
    usr_email VARCHAR(45),
    usr_senha VARCHAR(45),
    usr_especialidade VARCHAR(45),
    usr_celular VARCHAR(11),
    usr_pontos INT,
    fk_escolaridade INT,
    fk_cidade INT,
    fk_estado INT,
    fk_classificacao INT,
    PRIMARY KEY (usr_id),
    FOREIGN KEY (fk_escolaridade) REFERENCES Escolaridade(esc_id),
    FOREIGN KEY (fk_cidade) REFERENCES Cidade(cid_id),
    FOREIGN KEY (fk_estado) REFERENCES Estado(est_id),
    FOREIGN KEY (fk_classificacao) REFERENCES Classificacao(cls_id)
);

CREATE TABLE TipoPublicacao (
    tpb_id INT PRIMARY KEY IDENTITY,
    tpb_tipo VARCHAR(15)
);

CREATE TABLE Publicacao (
    pbc_id INT IDENTITY,
    pbc_titulo VARCHAR(45),
    pbc_descricao VARCHAR(MAX),
    pbc_dataHora DATETIME,
    fk_tipo_postagem INT,
    fk_usuario INT,
    fk_publicacao INT,
    PRIMARY KEY (pbc_id),
    FOREIGN KEY (fk_tipo_postagem) REFERENCES TipoPublicacao(tpb_id), 
    FOREIGN KEY (fk_usuario) REFERENCES Usuario(usr_id)
);

CREATE TABLE Imagem (
    img_id INT PRIMARY KEY IDENTITY,
    img_link VARCHAR(255),
    fk_publicacao INT,
    FOREIGN KEY (fk_publicacao) REFERENCES Publicacao(pbc_id)
); 

CREATE TABLE TipoReacao (
    trc_id INT PRIMARY KEY IDENTITY,
    trc_nome VARCHAR(10),
    trc_pontuacao INT
);

CREATE TABLE Reacao (
    rco_id INT IDENTITY,
    fk_tipo_reacao INT,
    fk_publicacao INT,
    fk_tipo_publicacao INT,
    PRIMARY KEY (rco_id),
    FOREIGN KEY (fk_tipo_reacao) REFERENCES TipoReacao(trc_id),
    FOREIGN KEY (fk_publicacao) REFERENCES Publicacao(pbc_id),
    FOREIGN KEY (fk_tipo_publicacao) REFERENCES TipoPublicacao(tpb_id)
);

CREATE TABLE Arquivo (
    arq_id INT PRIMARY KEY IDENTITY,
    arq_nome VARCHAR(45),
    arq_tipo VARCHAR(45),
    arq_url VARCHAR(255)
);

CREATE TABLE Sala (
    sal_id INT PRIMARY KEY IDENTITY,
    fk_publicacao INT,
    FOREIGN KEY (fk_publicacao) REFERENCES Publicacao(pbc_id)
);

CREATE TABLE SalaUsuario (
    fk_sala INT,
    fk_usuario INT,
    PRIMARY KEY (fk_sala, fk_usuario),
    FOREIGN KEY (fk_sala) REFERENCES Sala(sal_id),
    FOREIGN KEY (fk_usuario) REFERENCES Usuario(usr_id)
);

CREATE TABLE Mensagem (
    msg_id INT PRIMARY KEY IDENTITY,
    msg_conteudo VARCHAR(500),
    msg_data_hora DATETIME,
    fk_usuario INT,
    fk_sala INT,
    fk_arquivo INT,
    FOREIGN KEY (fk_usuario) REFERENCES Usuario(usr_id),
    FOREIGN KEY (fk_sala) REFERENCES Sala(sal_id),
    FOREIGN KEY (fk_arquivo) REFERENCES Arquivo(arq_id)
);
