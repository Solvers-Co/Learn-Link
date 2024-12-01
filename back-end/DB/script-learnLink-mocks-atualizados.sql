USE learnLink;

-- Mock de usuarios e endereços

INSERT INTO endereco (bairro, cidade, estado, cep) VALUES
('Vila Mariana', 'São Paulo', 'São Paulo', '04118020'),
('Jardim Paulista', 'São Paulo', 'São Paulo', '01415000'),
('Moema', 'São Paulo', 'São Paulo', '04523020'),
('Pinheiros', 'São Paulo', 'São Paulo', '05422000'),
('Butantã', 'São Paulo', 'São Paulo', '05503001'),
('Santana', 'São Paulo', 'São Paulo', '02012010'),
('Vila Madalena', 'São Paulo', 'São Paulo', '05436070'),
('Bela Vista', 'São Paulo', 'São Paulo', '01310000'),
('Liberdade', 'São Paulo', 'São Paulo', '01503000'),
('Consolação', 'São Paulo', 'São Paulo', '01301000'),
('Brooklin', 'São Paulo', 'São Paulo', '04571050'),
('Morumbi', 'São Paulo', 'São Paulo', '05614030'),
('Itaim Bibi', 'São Paulo', 'São Paulo', '04543010'),
('Lapa', 'São Paulo', 'São Paulo', '05072000'),
('Tatuapé', 'São Paulo', 'São Paulo', '03069000'),
('Aclimação', 'São Paulo', 'São Paulo', '01531001'),
('Cambuci', 'São Paulo', 'São Paulo', '01516010'),
('Perdizes', 'São Paulo', 'São Paulo', '05006000'),
('Pari', 'São Paulo', 'São Paulo', '03037050'),
('Vila Prudente', 'São Paulo', 'São Paulo', '03132010'),
('São Miguel Paulista', 'São Paulo', 'São Paulo', '08070000'),
('Ipiranga', 'São Paulo', 'São Paulo', '04209000'),
('Água Branca', 'São Paulo', 'São Paulo', '05002000'),
('Campo Belo', 'São Paulo', 'São Paulo', '04601002'),
('Freguesia do Ó', 'São Paulo', 'São Paulo', '02926000'),
('Vila Sônia', 'São Paulo', 'São Paulo', '05625000'),
('Vila Guilherme', 'São Paulo', 'São Paulo', '02049010'),
('Casa Verde', 'São Paulo', 'São Paulo', '02519000'),
('Jabaquara', 'São Paulo', 'São Paulo', '04340000'),
('Santo Amaro', 'São Paulo', 'São Paulo', '04711040'),
('Liberdade', 'São Paulo', 'São Paulo', '01506000');


INSERT INTO usuario (nome, cpf, email, senha, tipo_status_id, especialidade_id, classificacao_id, tipo_usuario_id) VALUES
-- usuarios mobile
('Beatriz Nogueira', '19494278895', 'user@user.com', '$2a$10$CxEv59bJaqay65KqJ.ntpeKLJzJZfjqmY24tb.GG4RTfB.HTgsOz2', 2, 3, null, 1),
('Lucas Pereira', '78945612309', 'lucas.pereira@example.com', '@Aa12345', 2, 1, 3, 1),
('Mariana Silva', '85296374102', 'mariana.silva@example.com', '@Aa98765', 1, 2, 2, 1),
('João Fernandes', '95175385246', 'joao.fernandes@example.com', '@Aa24689', 2, 3, 1, 1),
('Beatriz Costa', '75315948263', 'beatriz.costa@example.com', '@Aa65432', 3, 4, 3, 1),
('Carlos Nogueira', '95148627309', 'carlos.nogueira@example.com', '@Aa54321', 1, 5, 2, 1),
('Ana Clara Lima', '12365478932', 'ana.lima@example.com', '@Aa43219', 2, 6, 4, 1),
('Pedro Oliveira', '85236974105', 'pedro.oliveira@example.com', '@Aa32178', 3, 7, 1, 1),
('Fernanda Souza', '45698712309', 'fernanda.souza@example.com', '@Aa87654', 1, 8, 2, 1),
('Ricardo Santos', '78945632109', 'ricardo.santos@example.com', '@Aa13579', 2, 9, 3, 1),
('Juliana Mendes', '36925814705', 'juliana.mendes@example.com', '@Aa24680', 3, 10, 4, 1),
('Matheus Rocha', '10293847562', 'matheus.rocha@example.com', '@Aa65487', 2, 3, 2, 1),
('Larissa Almeida', '56473829105', 'larissa.almeida@example.com', '@Aa15937', 1, 4, 1, 1),
('Gabriel Lima', '98765432100', 'gabriel.lima@example.com', '@Aa75319', 2, 5, 3, 1),
('Tatiana Sousa', '65498732102', 'tatiana.sousa@example.com', '@Aa85246', 3, 6, 4, 1),
('Felipe Costa', '78912345601', 'felipe.costa@example.com', '@Aa96321', 1, 7, 2, 1),
('Bruna Carvalho', '25836914702', 'bruna.carvalho@example.com', '@Aa65497', 2, 8, 1, 1),
('Eduardo Martins', '14725836903', 'eduardo.martins@example.com', '@Aa32198', 3, 9, 3, 1),
('Camila Santos', '96385274106', 'camila.santos@example.com', '@Aa14725', 1, 10, 2, 1),
('Rafael Gomes', '78965412307', 'rafael.gomes@example.com', '@Aa97531', 2, 1, 1, 1),
('Aline Ribeiro', '32165498708', 'aline.ribeiro@example.com', '@Aa21354', 3, 2, 3, 1),
('Thiago Ferreira', '95135746809', 'thiago.ferreira@example.com', '@Aa98712', 1, 3, 4, 1),
('Patricia Moreira', '75395185204', 'patricia.moreira@example.com', '@Aa85213', 2, 4, 1, 1),
('Rodrigo Moraes', '25874196305', 'rodrigo.moraes@example.com', '@Aa36974', 3, 5, 2, 1),
('Gabriela Souza', '65412378906', 'gabriela.souza@example.com', '@Aa75328', 1, 6, 3, 1),
('Vinicius Barros', '95128473607', 'vinicius.barros@example.com', '@Aa98765', 2, 7, 4, 1),
('Isabela Lima', '78946351208', 'isabela.lima@example.com', '@Aa15982', 3, 8, 1, 1),
('Leonardo Almeida', '45678932109', 'leonardo.almeida@example.com', '@Aa95136', 1, 9, 2, 1),
('Viviane Rocha', '12378945600', 'viviane.rocha@example.com', '@Aa24681', 2, 10, 3, 1),
('Marcelo Pereira', '32178965401', 'marcelo.pereira@example.com', '@Aa65412', 3, 1, 4, 1),
('Daniele Cunha', '65498712302', 'daniele.cunha@example.com', '@Aa75396', 1, 2, 1, 1),

-- usuarios desktop
('Instituto Mafalda', '81761405896', 'adm@adm.com', '$2a$10$7VqM6PjHCNI9oMTygeB16OyIgiTLeJGmwu9ihTwp02/Ex91VSkJfi', 2, null, null, 2),
('Renato Oliveira', '12345678910', 'renato.oliveira@example.com', '@Aa11223', 2, 3, 1, 2),
('Claudia Lima', '98765432101', 'claudia.lima@example.com', '@Aa33445', 1, 4, 2, 2),
('Marcio Souza', '45612378902', 'marcio.souza@example.com', '@Aa55667', 3, 5, 3, 2);

UPDATE usuario SET endereco_id = 1 WHERE id = 1;
UPDATE usuario SET endereco_id = 2 WHERE id = 2;
UPDATE usuario SET endereco_id = 3 WHERE id = 3;
UPDATE usuario SET endereco_id = 4 WHERE id = 4;
UPDATE usuario SET endereco_id = 5 WHERE id = 5;
UPDATE usuario SET endereco_id = 6 WHERE id = 6;
UPDATE usuario SET endereco_id = 7 WHERE id = 7;
UPDATE usuario SET endereco_id = 8 WHERE id = 8;
UPDATE usuario SET endereco_id = 9 WHERE id = 9;
UPDATE usuario SET endereco_id = 10 WHERE id = 10;
UPDATE usuario SET endereco_id = 11 WHERE id = 11;
UPDATE usuario SET endereco_id = 12 WHERE id = 12;
UPDATE usuario SET endereco_id = 13 WHERE id = 13;
UPDATE usuario SET endereco_id = 14 WHERE id = 14;
UPDATE usuario SET endereco_id = 15 WHERE id = 15;
UPDATE usuario SET endereco_id = 16 WHERE id = 16;
UPDATE usuario SET endereco_id = 17 WHERE id = 17;
UPDATE usuario SET endereco_id = 18 WHERE id = 18;
UPDATE usuario SET endereco_id = 19 WHERE id = 19;
UPDATE usuario SET endereco_id = 20 WHERE id = 20;
UPDATE usuario SET endereco_id = 21 WHERE id = 21;
UPDATE usuario SET endereco_id = 22 WHERE id = 22;
UPDATE usuario SET endereco_id = 23 WHERE id = 23;
UPDATE usuario SET endereco_id = 24 WHERE id = 24;
UPDATE usuario SET endereco_id = 25 WHERE id = 25;
UPDATE usuario SET endereco_id = 26 WHERE id = 26;
UPDATE usuario SET endereco_id = 27 WHERE id = 27;
UPDATE usuario SET endereco_id = 28 WHERE id = 28;
UPDATE usuario SET endereco_id = 29 WHERE id = 29;
UPDATE usuario SET endereco_id = 30 WHERE id = 30;
UPDATE usuario SET endereco_id = 31 WHERE id = 31;



-- Para 1 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação linear?', '2024-08-01 09:00:00', 1, 1, 9),
('Qual é a importância dos conectivos na escrita?', '2024-08-01 11:00:00', 1, 2, 12),
('Qual o papel das enzimas na digestão?', '2024-08-01 13:00:00', 1, 3, 20),
('Quais são os principais eventos da Revolução Francesa?', '2024-08-01 15:00:00', 1, 4, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações lineares podem ser resolvidas isolando a variável.', '2024-08-01 09:30:00', 1, 4),
('A fórmula geral é ax + b = 0.', '2024-08-01 09:45:00', 1, 21),
('Sempre verifique as soluções substituindo-as na equação original.', '2024-08-01 10:00:00', 1, 20),

('Conectivos como "mas", "porque", "portanto" ajudam a conectar ideias.', '2024-08-01 11:30:00', 2, 20),
('Eles são essenciais para a coesão textual.', '2024-08-01 11:45:00', 2, 5),
('Cada conectivo tem uma função específica na argumentação.', '2024-08-01 12:00:00', 2, 8),

('Enzimas aceleram reações químicas no sistema digestivo.', '2024-08-01 13:30:00', 3, 4),
('Elas ajudam na quebra de macromoléculas em moléculas menores.', '2024-08-01 13:45:00', 3, 1),
('A digestão é fundamental para a absorção de nutrientes.', '2024-08-01 14:00:00', 3, 2),

('A Revolução Francesa começou com a queda da Bastilha.', '2024-08-01 15:30:00', 4, 1),
('Outros eventos importantes incluem a Declaração dos Direitos do Homem.', '2024-08-01 15:45:00', 4, 2),
('O período napoleônico também faz parte da Revolução.', '2024-08-01 16:00:00', 4, 3);

-- Para 2 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como fatorar um polinômio quadrático?', '2024-08-02 09:00:00', 1, 1, 8),
('Qual a função dos verbos em uma oração?', '2024-08-02 11:00:00', 1, 2, 10),
('Qual é a importância dos ecossistemas?', '2024-08-02 13:00:00', 1, 3, 11),
('O que foi a Segunda Guerra Mundial?', '2024-08-02 15:00:00', 1, 4, 13);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Polinômios quadráticos podem ser fatorados em dois binômios.', '2024-08-02 09:30:00', 5, 12),
('Use a fórmula de Bhaskara para encontrar as raízes.', '2024-08-02 09:45:00', 5, 11),
('A fatoração ajuda a simplificar a resolução de equações.', '2024-08-02 10:00:00', 5, 15),

('Os verbos são essenciais para expressar ações ou estados.', '2024-08-02 11:30:00', 6, 5),
('Eles podem ser transitivos ou intransitivos.', '2024-08-02 11:45:00', 6, 9),
('Os verbos são o núcleo do predicado.', '2024-08-02 12:00:00', 6, 22),

('Ecossistemas são importantes para a biodiversidade.', '2024-08-02 13:30:00', 7, 19),
('Eles ajudam na regulação do clima.', '2024-08-02 13:45:00', 7, 2),
('A preservação dos ecossistemas é crucial para a saúde ambiental.', '2024-08-02 14:00:00', 7, 3),

('A Segunda Guerra Mundial ocorreu de 1939 a 1945.', '2024-08-02 15:30:00', 8, 2),
('Envolveu muitos países e causou grandes mudanças geopolíticas.', '2024-08-02 15:45:00', 8, 3),
('O conflito terminou com a assinatura do Tratado de Paz.', '2024-08-02 16:00:00', 8, 4);

-- Para 3 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a diferença entre equação e inequação?', '2024-08-03 09:00:00', 1, 1, 3),
('Como identificar um sujeito na frase?', '2024-08-03 11:00:00', 1, 2, 4),
('Qual o papel dos produtores nos ecossistemas?', '2024-08-03 13:00:00', 1, 3, 1),
('Quais foram os principais líderes da Revolução Russa?', '2024-08-03 15:00:00', 1, 4, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações têm uma igualdade, enquanto inequações têm desigualdade.', '2024-08-03 09:30:00', 9, 4),
('Inequações podem ser representadas em intervalos.', '2024-08-03 09:45:00', 9, 1),
('Equações podem ter uma ou várias soluções.', '2024-08-03 10:00:00', 9, 2),

('O sujeito é quem faz a ação na frase.', '2024-08-03 11:30:00', 10, 1),
('Pode ser um substantivo, pronome ou expressão.', '2024-08-03 11:45:00', 10, 2),
('Identificar o sujeito ajuda a entender o sentido da frase.', '2024-08-03 12:00:00', 10, 3),

('Produtores são organismos que produzem seu próprio alimento.', '2024-08-03 13:30:00', 11, 2),
('Eles são a base da cadeia alimentar.', '2024-08-03 13:45:00', 11, 3),
('Sem produtores, a cadeia alimentar não funcionaria.', '2024-08-03 14:00:00', 11, 4),

('Líderes importantes da Revolução Russa incluem Lenin e Trotsky.', '2024-08-03 15:30:00', 12, 3),
('A Revolução levou à criação da União Soviética.', '2024-08-03 15:45:00', 12, 4),
('Trotsky foi um dos líderes da Revolução Bolchevique.', '2024-08-03 16:00:00', 12, 1);

-- Para 4 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver um sistema de equações lineares?', '2024-08-04 09:00:00', 1, 1, 4),
('Qual é a função de um adjetivo na frase?', '2024-08-04 11:00:00', 1, 2, 1),
('Como os organismos se adaptam ao ambiente?', '2024-08-04 13:00:00', 1, 3, 2),
('Qual foi a causa principal da Primeira Guerra Mundial?', '2024-08-04 15:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Sistemas de equações podem ser resolvidos por substituição ou eliminação.', '2024-08-04 09:30:00', 13, 2),
('Outra técnica é a utilização da matriz inversa.', '2024-08-04 09:45:00', 13, 3),
('Escolha a técnica mais adequada para o problema.', '2024-08-04 10:00:00', 13, 4),

('Adjetivos qualificam ou caracterizam substantivos.', '2024-08-04 11:30:00', 14, 3),
('Podem indicar cor, tamanho, forma, entre outros.', '2024-08-04 11:45:00', 14, 4),
('Eles ajudam a detalhar o que é descrito.', '2024-08-04 12:00:00', 14, 1),

('Organismos se adaptam através de mutações e seleção natural.', '2024-08-04 13:30:00', 15, 1),
('Essas adaptações ajudam na sobrevivência e reprodução.', '2024-08-04 13:45:00', 15, 2),
('A adaptação é uma resposta às mudanças no ambiente.', '2024-08-04 14:00:00', 15, 3),

('A causa principal da Primeira Guerra Mundial foi o assassinato do arquiduque Francisco Ferdinando.', '2024-08-04 15:30:00', 16, 4),
('Outros fatores incluem rivalidades imperiais e alianças militares.', '2024-08-04 15:45:00', 16, 1),
('O conflito envolveu muitas nações e causou grandes mudanças políticas.', '2024-08-04 16:00:00', 16, 2);

-- Para 5 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a fórmula para calcular a área de um triângulo?', '2024-08-05 09:00:00', 1, 1, 1),
('Como usar as vírgulas corretamente?', '2024-08-05 11:00:00', 1, 2, 2),
('O que são cadeias alimentares?', '2024-08-05 13:00:00', 1, 3, 3),
('Quem foram os principais autores do Modernismo?', '2024-08-05 15:00:00', 1, 4, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é (base x altura) / 2.', '2024-08-05 09:30:00', 17, 2),
('Certifique-se de medir a base e a altura corretamente.', '2024-08-05 09:45:00', 17, 3),
('A fórmula pode ser aplicada a qualquer triângulo.', '2024-08-05 10:00:00', 17, 4),

('Vírgulas são usadas para separar itens em uma lista.', '2024-08-05 11:30:00', 18, 4),
('Também são usadas para separar orações.', '2024-08-05 11:45:00', 18, 1),
('Evite o uso excessivo de vírgulas.', '2024-08-05 12:00:00', 18, 2),

('Cadeias alimentares mostram como a energia é transferida.', '2024-08-05 13:30:00', 19, 2),
('Elas começam com produtores e passam por consumidores.', '2024-08-05 13:45:00', 19, 3),
('Cada nível da cadeia alimentar depende do anterior.', '2024-08-05 14:00:00', 19, 4),

('Principais autores do Modernismo incluem Mário de Andrade e Manuel Bandeira.', '2024-08-05 15:30:00', 20, 1),
('O Modernismo também inclui influências de Oswald de Andrade.', '2024-08-05 15:45:00', 20, 2),
('O período é caracterizado por uma ruptura com o passado.', '2024-08-05 16:00:00', 20, 3);

-- Para 6 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação exponencial?', '2024-08-06 09:00:00', 1, 1, 2),
('Qual a diferença entre adjetivo e advérbio?', '2024-08-06 11:00:00', 1, 2, 3),
('Quais são os tipos de interações entre organismos?', '2024-08-06 13:00:00', 1, 3, 5),
('Quais foram as consequências da Reforma Protestante?', '2024-08-06 15:00:00', 1, 4, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Use logaritmos para resolver equações exponenciais.', '2024-08-06 09:30:00', 21, 3),
('Equações do tipo a^x = b podem ser resolvidas usando log(a^x) = log(b).', '2024-08-06 09:45:00', 21, 9),
('Verifique as condições de existência dos logaritmos.', '2024-08-06 10:00:00', 21, 1),

('Adjetivos qualificam substantivos, enquanto advérbios qualificam verbos.', '2024-08-06 11:30:00', 22, 17),
('Advérbios podem indicar como, quando e onde algo acontece.', '2024-08-06 11:45:00', 22, 21),
('Adjetivos descrevem características dos substantivos.', '2024-08-06 12:00:00', 22, 2),

('Interações entre organismos incluem predatismo e simbiose.', '2024-08-06 13:30:00', 23, 1),
('Simbiose pode ser mutualismo, comensalismo ou parasitismo.', '2024-08-06 13:45:00', 23, 2),
('Essas interações são essenciais para o equilíbrio ecológico.', '2024-08-06 14:00:00', 23, 3),

('A Reforma Protestante levou à divisão da Igreja Católica.', '2024-08-06 15:30:00', 24, 2),
('Foi um movimento que resultou em novas denominações cristãs.', '2024-08-06 15:45:00', 24, 3),
('O evento também teve impacto político e social significativo.', '2024-08-06 16:00:00', 24, 4);

-- Para 7 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como aplicar a fórmula de Bhaskara?', '2024-08-07 09:00:00', 1, 1, 3),
('O que é um advérbio de intensidade?', '2024-08-07 11:00:00', 1, 2, 4),
('Qual o papel das cadeias alimentares no ecossistema?', '2024-08-07 13:00:00', 1, 3, 7),
('Como o Renascimento influenciou a História?', '2024-08-07 15:00:00', 1, 4, 2);

-- INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
-- ('A fórmula de Bhaskara resolve equações quadráticas.', '2024-08-07 09:30:00', 25, 4),
-- ('Utilize a fórmula para encontrar as raízes de uma equação.', '2024-08-07 09:45:00', 25, 1),
-- ('Verifique se o discriminante é positivo, negativo ou zero.', '2024-08-07 10:00:00', 25, 2),

-- ('Advérbios de intensidade modificam a intensidade do verbo.', '2024-08-07 11:30:00', 26, 1),
-- ('Exemplos incluem "muito", "pouco", "extremamente".', '2024-08-07 11:45:00', 26, 2),
-- ('Eles ajudam a dar mais precisão à ação descrita.', '2024-08-07 12:00:00', 26, 3),

-- ('Cadeias alimentares mostram como a energia é transferida.', '2024-08-07 13:30:00', 27, 2),
-- ('Elas começam com produtores e seguem para os consumidores.', '2024-08-07 13:45:00', 27, 3),
-- ('Cada nível da cadeia depende do anterior.', '2024-08-07 14:00:00', 27, 4),

-- ('O Renascimento trouxe um novo foco na ciência e nas artes.', '2024-08-07 15:30:00', 28, 3),
-- ('Foi um período de grande desenvolvimento cultural e intelectual.', '2024-08-07 15:45:00', 28, 4),
-- ('A redescoberta dos clássicos influenciou a arte e a literatura.', '2024-08-07 16:00:00', 28, 1);

-- Para 8 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a força gravitacional entre dois corpos?', '2024-08-08 09:00:00', 1, 5, 1),
('O que é uma reação de neutralização?', '2024-08-08 11:00:00', 1, 6, 2),
('Qual é a função da estratificação social?', '2024-08-08 13:00:00', 1, 7, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é F = G * (m1 * m2) / r^2.', '2024-08-08 09:30:00', 29, 2),
('G é a constante gravitacional, m1 e m2 são as massas dos corpos.', '2024-08-08 09:45:00', 29, 8),
('r é a distância entre os centros dos corpos.', '2024-08-08 10:00:00', 29, 7),

('Reação de neutralização ocorre quando um ácido e uma base reagem.', '2024-08-08 11:30:00', 30, 10),
('O produto é uma sal e água.', '2024-08-08 11:45:00', 30, 4),
('Pode ser utilizado para ajustar o pH de soluções.', '2024-08-08 12:00:00', 30, 1),

('Estratificação social refere-se à divisão da sociedade em camadas.', '2024-08-08 13:30:00', 31, 10),
('Ela é baseada em fatores como renda, educação e ocupação.', '2024-08-08 13:45:00', 31, 2),
('Essas camadas influenciam as oportunidades e a qualidade de vida.', '2024-08-08 14:00:00', 31, 3);

-- Para 9 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a lei de Newton para a ação e reação?', '2024-08-09 09:00:00', 1, 5, 2),
('Como calcular o pH de uma solução?', '2024-08-09 11:00:00', 1, 6, 3),
('O que é cultura na Sociologia?', '2024-08-09 13:00:00', 1, 7, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Para cada ação, há uma reação igual e oposta.', '2024-08-09 09:30:00', 32, 3),
('Esta lei é uma das bases da física clássica.', '2024-08-09 09:45:00', 32, 4),
('Ela explica a interação entre dois corpos.', '2024-08-09 10:00:00', 32, 1),

('O pH é calculado como o logaritmo negativo da concentração de íons H+.', '2024-08-09 11:30:00', 33, 10),
('Para pH = 7, a solução é neutra.', '2024-08-09 11:45:00', 33, 1),
('Valores abaixo de 7 indicam uma solução ácida.', '2024-08-09 12:00:00', 33, 23),

('Cultura é o conjunto de valores, normas e práticas de um grupo social.', '2024-08-09 13:30:00', 34, 24),
('Ela influencia o comportamento e a identidade dos indivíduos.', '2024-08-09 13:45:00', 34, 3),
('A cultura é transmitida através da socialização.', '2024-08-09 14:00:00', 34, 4);

-- Para 10 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a fórmula para a lei dos gases ideais?', '2024-08-10 09:00:00', 1, 5, 3),
('O que é um ácido de Lewis?', '2024-08-10 11:00:00', 1, 6, 4),
('Como a globalização afeta a sociedade?', '2024-08-10 13:00:00', 1, 7, 10);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é PV = nRT.', '2024-08-10 09:30:00', 35, 4),
('P é a pressão, V é o volume, n é a quantidade de substância, R é a constante dos gases e T é a temperatura.', '2024-08-10 09:45:00', 35, 1),
('A fórmula assume que os gases são ideais.', '2024-08-10 10:00:00', 35, 2),

('Ácidos de Lewis são aceitadores de pares de elétrons.', '2024-08-10 11:30:00', 36, 10),
('Eles podem formar complexos com bases de Lewis.', '2024-08-10 11:45:00', 36, 2),
('Essa definição é mais geral que a de ácidos de Brønsted.', '2024-08-10 12:00:00', 36, 3),

('A globalização promove a integração econômica e cultural.', '2024-08-10 13:30:00', 37, 3),
('Ela pode levar a uma maior homogeneização cultural.', '2024-08-10 13:45:00', 37, 4),
('Também pode causar a perda de identidades culturais locais.', '2024-08-10 14:00:00', 37, 1);

-- Para 11 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a energia cinética de um objeto?', '2024-08-11 09:00:00', 1, 5, 4),
('O que é uma reação de oxirredução?', '2024-08-11 11:00:00', 1, 6, 1),
('Qual o impacto do capitalismo na sociedade?', '2024-08-11 13:00:00', 1, 7, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é 1/2 * m * v^2.', '2024-08-11 09:30:00', 38, 1),
('m é a massa e v é a velocidade do objeto.', '2024-08-11 09:45:00', 38, 2),
('A energia cinética aumenta com o quadrado da velocidade.', '2024-08-11 10:00:00', 38, 26),

('Reações de oxirredução envolvem a transferência de elétrons.', '2024-08-11 11:30:00', 39, 2),
('O agente redutor doa elétrons e o agente oxidante os recebe.', '2024-08-11 11:45:00', 39, 3),
('Essas reações são fundamentais em processos biológicos e industriais.', '2024-08-11 12:00:00', 39, 4),

('O capitalismo é um sistema econômico baseado na propriedade privada e no lucro.', '2024-08-11 13:30:00', 40, 3),
('Ele pode levar a desigualdades econômicas e sociais.', '2024-08-11 13:45:00', 40, 4),
('O capitalismo também promove inovação e crescimento econômico.', '2024-08-11 14:00:00', 40, 22);

-- Para 12 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a lei de Coulomb?', '2024-08-12 09:00:00', 1, 5, 1),
('Como preparar uma solução tampão?', '2024-08-12 11:00:00', 1, 6, 2),
('Qual é a função das instituições na sociedade?', '2024-08-12 13:00:00', 1, 7, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A lei de Coulomb descreve a força entre duas cargas elétricas.', '2024-08-12 09:30:00', 41, 2),
('A fórmula é F = k * (q1 * q2) / r^2.', '2024-08-12 09:45:00', 41, 3),
('k é a constante eletrostática e q1 e q2 são as cargas.', '2024-08-12 10:00:00', 41, 4),

('Uma solução tampão é preparada misturando um ácido fraco e seu sal correspondente.', '2024-08-12 11:30:00', 42, 3),
('Ela resiste a mudanças de pH quando pequenas quantidades de ácido ou base são adicionadas.', '2024-08-12 11:45:00', 42, 4),
('A preparação deve ser feita com precisão para garantir a eficácia.', '2024-08-12 12:00:00', 42, 9),

('As instituições sociais são estruturas que organizam a vida social e regulam comportamentos.', '2024-08-12 13:30:00', 43, 4),
('Elas incluem a família, a escola e o governo.', '2024-08-12 13:45:00', 43, 1),
('Instituições influenciam a socialização e a manutenção da ordem social.', '2024-08-12 14:00:00', 43, 2);

-- Para 13 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a diferença entre energia potencial e cinética?', '2024-08-13 09:00:00', 1, 5, 2),
('O que são ácidos e bases em termos de Brønsted-Lowry?', '2024-08-13 11:00:00', 1, 6, 3),
('Como o positivismo influencia a sociologia?', '2024-08-13 13:00:00', 1, 7, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Energia potencial é a energia armazenada devido à posição ou configuração.', '2024-08-13 09:30:00', 44, 3),
('Energia cinética é a energia do movimento.', '2024-08-13 09:45:00', 44, 4),
('Ambos os tipos de energia são importantes em sistemas físicos.', '2024-08-13 10:00:00', 44, 7),

('Ácidos são doadores de prótons e bases são aceitadores de prótons.', '2024-08-13 11:30:00', 45, 4),
('Essa definição é mais ampla que a de Lewis.', '2024-08-13 11:45:00', 45, 1),
('Permite entender melhor as reações ácido-base.', '2024-08-13 12:00:00', 45, 2),

('O positivismo defende que o conhecimento deve ser baseado em fatos observáveis.', '2024-08-13 13:30:00', 46, 2),
('Ele influencia a sociologia ao enfatizar métodos científicos de estudo.', '2024-08-13 13:45:00', 46, 3),
('Positivismo busca objetividade e precisão na análise social.', '2024-08-13 14:00:00', 46, 4);

-- Para 14 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a diferença entre corrente alternada e corrente contínua?', '2024-08-14 09:00:00', 1, 5, 3),
('O que é uma reação de precipitação?', '2024-08-14 11:00:00', 1, 6, 4),
('Qual é o papel da socialização na formação da identidade?', '2024-08-14 13:00:00', 1, 7, 1);

-- INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
-- ('Corrente alternada muda de direção periodicamente, enquanto a corrente contínua flui em uma única direção.', '2024-08-14 09:30:00', 47, 4),
-- ('Corrente alternada é usada em redes elétricas, enquanto a contínua é comum em baterias.', '2024-08-14 09:45:00', 47, 1),
-- ('Cada tipo tem suas aplicações específicas.', '2024-08-14 10:00:00', 47, 2),

-- ('Reação de precipitação resulta na formação de um sólido a partir de soluções líquidas.', '2024-08-14 11:30:00', 48, 1),
-- ('O sólido formado é conhecido como precipitado.', '2024-08-14 11:45:00', 48, 2),
-- ('Reações de precipitação são importantes em processos químicos e ambientais.', '2024-08-14 12:00:00', 48, 3),

-- ('Socialização é o processo pelo qual os indivíduos aprendem e internalizam normas e valores sociais.', '2024-08-14 13:30:00', 49, 2),
-- ('Ela desempenha um papel crucial na formação da identidade pessoal e social.', '2024-08-14 13:45:00', 49, 3),
-- ('A socialização ocorre através de interações com a família, escola e outros agentes sociais.', '2024-08-14 14:00:00', 49, 4);

-- Para 15 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são os principais biomas da merda do Brasil?', '2024-08-15 09:00:00', 1, 8, 1),
('Como se pronuncia a palavra "rhythm" em inglês?', '2024-08-15 11:00:00', 1, 9, 2),
('O que é a teoria do contrato social?', '2024-08-15 13:00:00', 1, 10, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Os principais biomas incluem Amazônia, Cerrado e Pantanal.', '2024-08-15 09:30:00', 50, 2),
('Cada bioma tem características únicas de flora e fauna.', '2024-08-15 09:45:00', 50, 3),
('Os biomas são fundamentais para a biodiversidade do país.', '2024-08-15 10:00:00', 50, 4),

('A palavra "rhythm" é pronunciada como /ˈrɪðəm/.', '2024-08-15 11:30:00', 51, 3),
('É uma palavra difícil devido à sua combinação de consoantes.', '2024-08-15 11:45:00', 51, 4),
('Praticar a pronúncia ajuda a melhorar a fluência.', '2024-08-15 12:00:00', 51, 1),

('A teoria do contrato social é uma ideia filosófica sobre a formação da sociedade.', '2024-08-15 13:30:00', 52, 4),
('Ela sugere que os indivíduos concordam em seguir regras para garantir segurança e ordem.', '2024-08-15 13:45:00', 52, 1),
('Filósofos como Hobbes, Locke e Rousseau contribuíram para essa teoria.', '2024-08-15 14:00:00', 52, 2);

-- Para 16 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como a geografia influencia o clima de uma região?', '2024-08-16 09:00:00', 1, 8, 2),
('Qual é a importância dos phrasal verbs em inglês?', '2024-08-16 11:00:00', 1, 9, 3),
('O que é o empirismo na filosofia?', '2024-08-16 13:00:00', 1, 10, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O clima é influenciado por fatores como altitude, latitude e proximidade do oceano.', '2024-08-16 09:30:00', 53, 3),
('Áreas elevadas tendem a ter climas mais frios.', '2024-08-16 09:45:00', 53, 4),
('Proximidade do oceano pode moderar as temperaturas.', '2024-08-16 10:00:00', 53, 1),

('Phrasal verbs são importantes para a fluência porque são comuns em conversas informais.', '2024-08-16 11:30:00', 54, 4),
('Eles podem ter significados diferentes dos verbos individuais.', '2024-08-16 11:45:00', 54, 1),
('Conhecer phrasal verbs ajuda na compreensão de diálogos e textos.', '2024-08-16 12:00:00', 54, 2),

('O empirismo é a teoria de que o conhecimento vem da experiência sensorial.', '2024-08-16 13:30:00', 55, 1),
('Filósofos empiristas acreditam que a observação e a experiência são fontes primárias de conhecimento.', '2024-08-16 13:45:00', 55, 2),
('O empirismo contrasta com o racionalismo, que enfatiza a razão.', '2024-08-16 14:00:00', 55, 3);

-- Para 17 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a diferença entre clima e tempo?', '2024-08-17 09:00:00', 1, 8, 3),
('Como se usa o tempo presente perfeito em inglês?', '2024-08-17 11:00:00', 1, 9, 4),
('O que é a ética kantiana?', '2024-08-17 13:00:00', 1, 10, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Clima refere-se às condições médias de longo prazo, enquanto o tempo é o estado atual da atmosfera.', '2024-08-17 09:30:00', 56, 4),
('O clima é mais estável e menos variável do que o tempo.', '2024-08-17 09:45:00', 56, 1),
('O tempo pode mudar rapidamente, enquanto o clima é uma média.', '2024-08-17 10:00:00', 56, 2),

('O tempo presente perfeito é usado para falar sobre ações que começaram no passado e continuam no presente.', '2024-08-17 11:30:00', 57, 1),
('É formado com o verbo auxiliar "have" seguido do particípio passado do verbo principal.', '2024-08-17 11:45:00', 57, 2),
('Exemplo: "I have lived here for five years."', '2024-08-17 12:00:00', 57, 3),

('A ética kantiana baseia-se na ideia de que as ações devem ser julgadas com base em princípios universais.', '2024-08-17 13:30:00', 58, 2),
('Kant acreditava que a moralidade é baseada na razão e na autonomia.', '2024-08-17 13:45:00', 58, 3),
('A ética kantiana enfatiza o dever e a obrigação moral.', '2024-08-17 14:00:00', 58, 4);

-- Para 18 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como a geografia pode explicar os desastres naturais?', '2024-08-18 09:00:00', 1, 8, 4),
('Quais são as regras do tempo futuro em inglês?', '2024-08-18 11:00:00', 1, 9, 1),
('O que é a teoria do utilitarismo?', '2024-08-18 13:00:00', 1, 10, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Desastres naturais como terremotos e vulcões estão frequentemente associados a placas tectônicas.', '2024-08-18 09:30:00', 59, 1),
('A geografia também pode explicar inundações e deslizamentos de terra.', '2024-08-18 09:45:00', 59, 2),
('A análise geográfica ajuda na preparação e mitigação de desastres.', '2024-08-18 10:00:00', 59, 3),

('O tempo futuro pode ser expresso com "will" ou "going to".', '2024-08-18 11:30:00', 60, 2),
('Exemplo com "will": "I will go to the store."', '2024-08-18 11:45:00', 60, 3),
('Exemplo com "going to": "I am going to visit my friend."', '2024-08-18 12:00:00', 60, 4),

('O utilitarismo é uma teoria ética que promove a maior felicidade para o maior número de pessoas.', '2024-08-18 13:30:00', 61, 3),
('É associado a filósofos como Jeremy Bentham e John Stuart Mill.', '2024-08-18 13:45:00', 61, 4),
('O utilitarismo avalia as ações com base em suas consequências.', '2024-08-18 14:00:00', 61, 1);

-- Para 19 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a diferença entre continente e país?', '2024-08-19 09:00:00', 1, 8, 1),
('Como utilizar os verbos modais em inglês?', '2024-08-19 11:00:00', 1, 9, 2),
('O que é a teoria da justiça de Rawls?', '2024-08-19 13:00:00', 1, 10, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Continentes são grandes massas de terra, enquanto países são divisões políticas dentro desses continentes.', '2024-08-19 09:30:00', 62, 2),
('Um continente pode conter vários países.', '2024-08-19 09:45:00', 62, 3),
('Exemplo: a África é um continente com muitos países.', '2024-08-19 10:00:00', 62, 4),

('Verbos modais expressam possibilidade, capacidade, permissão ou obrigação.', '2024-08-19 11:30:00', 63, 3),
('Exemplos incluem can, could, may, might, must.', '2024-08-19 11:45:00', 63, 4),
('Eles são seguidos pelo verbo principal na forma base.', '2024-08-19 12:00:00', 63, 1),

('A teoria da justiça de Rawls propõe dois princípios principais: justiça como equidade e a diferença de princípio.', '2024-08-19 13:30:00', 64, 4),
('O primeiro princípio garante a igualdade de direitos e liberdades básicas.', '2024-08-19 13:45:00', 64, 1),
('O segundo princípio permite desigualdades apenas se beneficiam os mais desfavorecidos.', '2024-08-19 14:00:00', 64, 2);

-- Para 20 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são os principais rios da América do Sul?', '2024-08-20 09:00:00', 1, 8, 2),
('Como se usa o tempo presente contínuo em inglês?', '2024-08-20 11:00:00', 1, 9, 3),
('O que é o niilismo?', '2024-08-20 13:00:00', 1, 10, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Principais rios incluem o Amazonas, Paraná e Orinoco.', '2024-08-20 09:30:00', 65, 3),
('O Amazonas é o maior em volume de água e comprimento.', '2024-08-20 09:45:00', 65, 4),
('Esses rios são vitais para o ecossistema e as populações locais.', '2024-08-20 10:00:00', 65, 1),

('O presente contínuo é usado para ações que estão ocorrendo no momento da fala.', '2024-08-20 11:30:00', 66, 4),
('É formado com o verbo "to be" seguido pelo gerúndio do verbo principal.', '2024-08-20 11:45:00', 66, 1),
('Exemplo: "I am studying English."', '2024-08-20 12:00:00', 66, 2),

('O niilismo é a filosofia que nega a existência de valores ou significados intrínsecos.', '2024-08-20 13:30:00', 67, 1),
('Ele afirma que a vida é essencialmente sem propósito.', '2024-08-20 13:45:00', 67, 2),
('O niilismo pode levar a uma visão cética ou pessimista da vida.', '2024-08-20 14:00:00', 67, 3);

-- Para 21 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a diferença entre um país e uma nação?', '2024-08-21 09:00:00', 1, 8, 3),
('Como se usa o passado simples em inglês?', '2024-08-21 11:00:00', 1, 9, 4),
('Qual é o papel da estética na filosofia?', '2024-08-21 13:00:00', 1, 10, 1);

-- INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
-- id da publicação não respondida 68, geografia
-- ('Um país é uma entidade política, enquanto uma nação é uma comunidade com identidade cultural e histórica.', '2024-08-21 09:30:00', 68, 4),
-- ('Um país pode conter várias nações.', '2024-08-21 09:45:00', 68, 1),
-- ('Exemplo: o Reino Unido é um país que contém nações como a Escócia e o País de Gales.', '2024-08-21 10:00:00', 68, 2),

-- id da publicação não respondida 69, ingles
-- ('O passado simples é usado para descrever ações concluídas no passado.', '2024-08-21 11:30:00', 69, 1),
-- ('É formado com o verbo principal na forma passada.', '2024-08-21 11:45:00', 69, 2),
-- ('Exemplo: "I visited London last year."', '2024-08-21 12:00:00', 69, 3),

-- id da publicação não respondida 70, filosofia
-- ('A estética é o ramo da filosofia que estuda a beleza e a arte.', '2024-08-21 13:30:00', 70, 2),
-- ('Ela explora questões sobre o que constitui o belo e o sublime.', '2024-08-21 13:45:00', 70, 3),
-- ('A estética também analisa a experiência estética e a apreciação artística.', '2024-08-21 14:00:00', 70, 4);

-- Para 22 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são as principais teorias da evolução?', '2024-08-22 09:00:00', 1, 3, 1),
('Como o uso da matemática pode resolver problemas do cotidiano?', '2024-08-22 11:00:00', 1, 1, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Teorias incluem a seleção natural de Darwin e o Lamarquismo.', '2024-08-22 09:30:00', 71, 2),
('A teoria de Darwin é a mais amplamente aceita atualmente.', '2024-08-22 09:45:00', 71, 3),

('Matemática ajuda a otimizar processos, calcular custos e analisar dados.', '2024-08-22 11:30:00', 72, 3),
('É fundamental para a engenharia, finanças e ciências.', '2024-08-22 11:45:00', 72, 4);

-- Para 23 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são fenômenos atmosféricos?', '2024-08-23 09:00:00', 1, 8, 4),
('Quais são os principais períodos da literatura brasileira?', '2024-08-23 11:00:00', 1, 2, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Fenômenos incluem chuvas, ventos e tempestades.', '2024-08-23 09:30:00', 73, 1),
('São causados por interações entre diferentes camadas da atmosfera.', '2024-08-23 09:45:00', 73, 2),

('Períodos incluem o Barroco, Romantismo e Modernismo.', '2024-08-23 11:30:00', 74, 3),
('Cada período tem características distintas na linguagem e temas.', '2024-08-23 11:45:00', 74, 4);

-- Para 24 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como funciona o sistema nervoso?', '2024-08-24 09:00:00', 1, 3, 2),
('Quais são as principais guerras da História Moderna?', '2024-08-24 11:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O sistema nervoso é composto pelo cérebro, medula espinhal e nervos.', '2024-08-24 09:30:00', 75, 3),
('Ele controla as funções corporais e as respostas aos estímulos.', '2024-08-24 09:45:00', 75, 4),

('Principais guerras incluem a Primeira e a Segunda Guerra Mundial.', '2024-08-24 11:30:00', 76, 1),
('Essas guerras tiveram um impacto significativo na história global.', '2024-08-24 11:45:00', 76, 2);

-- Para 25 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a função exponencial?', '2024-08-25 09:00:00', 1, 1, 4),
('Como a sociologia estuda a estrutura social?', '2024-08-25 11:00:00', 1, 7, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A função exponencial é uma função matemática na forma f(x) = a^x.', '2024-08-25 09:30:00', 77, 2),
('É usada para modelar crescimento e decrescimento exponencial.', '2024-08-25 09:45:00', 77, 3),

('A sociologia analisa como instituições e grupos sociais influenciam a sociedade.', '2024-08-25 11:30:00', 78, 3),
('Estuda também as mudanças sociais e os problemas sociais.', '2024-08-25 11:45:00', 78, 4);

-- Para 26 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a importância do ciclo da água?', '2024-08-26 09:00:00', 1, 8, 1),
('Como a matemática é aplicada na engenharia?', '2024-08-26 11:00:00', 1, 1, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O ciclo da água é crucial para o equilíbrio ambiental e o clima.', '2024-08-26 09:30:00', 79, 2),
('Inclui processos como evaporação, condensação e precipitação.', '2024-08-26 09:45:00', 79, 3),

('Matemática é usada para resolver problemas de design e análise de estruturas.', '2024-08-26 11:30:00', 80, 4),
('Também é essencial para a modelagem e simulação de sistemas.', '2024-08-26 11:45:00', 80, 1);

-- Para 27 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são os elementos da tabela periódica?', '2024-08-27 09:00:00', 1, 6, 3),
('Como a literatura reflete as mudanças sociais?', '2024-08-27 11:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Os elementos são substâncias químicas que constituem a matéria.', '2024-08-27 09:30:00', 81, 4),
('Cada elemento tem propriedades e aplicações específicas.', '2024-08-27 09:45:00', 81, 1),

('A literatura pode refletir as preocupações e valores da época em que foi escrita.', '2024-08-27 11:30:00', 82, 2),
('Ela também pode influenciar e responder às mudanças sociais.', '2024-08-27 11:45:00', 82, 3);

-- Para 28 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são os principais tipos de solo?', '2024-08-28 09:00:00', 1, 8, 2),
('Como a física explica a gravidade?', '2024-08-28 11:00:00', 1, 5, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Tipos de solo incluem argiloso, arenoso e siltoso.', '2024-08-28 09:30:00', 83, 3),
('Cada tipo de solo tem características e usos diferentes.', '2024-08-28 09:45:00', 83, 4),

('A gravidade é uma força que atrai objetos para o centro da Terra.', '2024-08-28 11:30:00', 84, 4),
('É descrita pela lei da gravidade de Newton e a teoria da relatividade de Einstein.', '2024-08-28 11:45:00', 84, 1);

-- Para 29 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a importância do sistema solar?', '2024-08-29 09:00:00', 1, 8, 4),
('Como a química é usada na medicina?', '2024-08-29 11:00:00', 1, 6, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O sistema solar é fundamental para entender nosso lugar no universo.', '2024-08-29 09:30:00', 85, 1),
('Ele inclui o Sol, planetas, luas e outros corpos celestes.', '2024-08-29 09:45:00', 85, 2),

('Química é usada para desenvolver medicamentos e tratamentos.', '2024-08-29 11:30:00', 86, 3),
('Ela também ajuda a entender as interações químicas no corpo.', '2024-08-29 11:45:00', 86, 4);

-- Para 30 de Agosto de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como a matemática é aplicada na computação?', '2024-08-30 09:00:00', 1, 1, 2),
('Quais são os principais estilos arquitetônicos?', '2024-08-30 11:00:00', 1, 8, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Matemática é usada em algoritmos, criptografia e modelagem de dados.', '2024-08-30 09:30:00', 87, 4),
('Ela é essencial para o desenvolvimento de software e hardware.', '2024-08-30 09:45:00', 87, 1),

('Estilos incluem gótico, barroco e moderno.', '2024-08-30 11:30:00', 88, 2),
('Cada estilo tem características distintas e influências históricas.', '2024-08-30 11:45:00', 88, 4);

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Gostaria de doar um caderno de 10 matérias novinho', '2024-08-31 09:05:00', 1, 11, 10),
('Tenho 5 canetas para doar quem gostaria?', '2024-08-31 10:00:00', 1, 11, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Eu quero!', '2024-08-31 10:30:00', 90, 4);

-- Para Janeiro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular o valor de uma incógnita em uma equação quadrática?', '2024-01-01 00:15:00', 1, 1, 10),
('Quais são os elementos fundamentais da comunicação verbal?', '2024-01-01 04:30:00', 1, 2, 15),
('O que é a fotossíntese e como ela ocorre?', '2024-01-01 08:45:00', 1, 3, 20),
('Quais são os principais eventos da Idade Média?', '2024-01-01 12:00:00', 1, 4, 25),

('Como resolver sistemas de equações lineares?', '2024-01-02 01:00:00', 1, 1, 5),
('A importância dos parágrafos na estrutura textual.', '2024-01-02 07:15:00', 1, 2, 12),
('Qual a função das proteínas no corpo humano?', '2024-01-02 10:30:00', 1, 3, 7),
('O que caracteriza o Renascimento?', '2024-01-02 14:45:00', 1, 4, 13),

('Como calcular a área de figuras geométricas?', '2024-01-03 02:10:00', 1, 1, 14),
('A evolução das palavras ao longo do tempo.', '2024-01-03 06:25:00', 1, 2, 22),
('Por que o corpo precisa de carboidratos?', '2024-01-03 09:40:00', 1, 3, 18),
('A Revolução Francesa e suas consequências para o mundo moderno.', '2024-01-03 13:55:00', 1, 4, 11),

('Como calcular a equação da reta?', '2024-01-04 00:45:00', 1, 1, 16),
('Os tipos de frases e suas funções na comunicação.', '2024-01-04 05:20:00', 1, 2, 19),
('Quais os principais tipos de células do corpo humano?', '2024-01-04 08:35:00', 1, 3, 12),
('A Idade Moderna e a influência da Revolução Industrial.', '2024-01-04 11:50:00', 1, 4, 14),

('Como determinar as raízes de uma equação de segundo grau?', '2024-01-05 01:10:00', 1, 1, 20),
('Como o uso adequado de conectivos pode melhorar a clareza do texto?', '2024-01-05 06:00:00', 1, 2, 8),
('Qual a importância das enzimas digestivas?', '2024-01-05 09:25:00', 1, 3, 16),
('Quais as principais características do feudalismo?', '2024-01-05 13:40:00', 1, 4, 14),

('Como aplicar as propriedades das potências?', '2024-01-06 02:30:00', 1, 1, 5),
('A análise sintática e suas etapas.', '2024-01-06 07:10:00', 1, 2, 13),
('A função dos lipídios no organismo.', '2024-01-06 10:20:00', 1, 3, 17),
('A transição da Idade Antiga para a Idade Média.', '2024-01-06 14:35:00', 1, 4, 9),

('Como calcular a soma de uma progressão aritmética?', '2024-01-07 00:05:00', 1, 1, 22),
('Como os advérbios alteram o significado das frases?', '2024-01-07 05:30:00', 1, 2, 18),
('Quais as etapas da mitose?', '2024-01-07 08:00:00', 1, 3, 14),
('A Revolução Industrial e seu impacto na sociedade moderna.', '2024-01-07 12:15:00', 1, 4, 11);

-- Comentários relacionados às publicações
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O discriminante pode ser usado para determinar o número de raízes reais.', '2024-01-01 00:30:00', 91, 25),
('A comunicação verbal é fundamental para uma boa interação entre as pessoas.', '2024-01-01 05:00:00', 92, 20),
('A fotossíntese é o processo pelo qual as plantas produzem seu alimento.', '2024-01-01 09:00:00', 93, 18),
('A Idade Média é marcada pelo feudalismo e pela ascensão da Igreja.', '2024-01-01 12:30:00', 94, 8),

('Uma forma de resolver sistemas de equações lineares é usando o método da substituição.', '2024-01-02 01:30:00', 95, 13),
('A introdução de parágrafos facilita a compreensão do texto, organizando as ideias.', '2024-01-02 07:45:00', 96, 7),
('As proteínas são essenciais para o crescimento e reparação dos tecidos.', '2024-01-02 11:00:00', 97, 9),
('O Renascimento foi um período de grande produção artística e científica.', '2024-01-02 15:00:00', 98, 16),

('A área de um triângulo pode ser calculada com a fórmula A = 1/2 * base * altura.', '2024-01-03 02:30:00', 99, 14),
('As palavras evoluem ao longo do tempo, mudando de significado e forma.', '2024-01-03 06:45:00', 100, 17),
('O carboidrato é a principal fonte de energia do nosso corpo.', '2024-01-03 10:00:00', 101, 14),
('A Revolução Francesa trouxe novas ideias sobre liberdade e igualdade.', '2024-01-03 14:30:00', 102, 18),

('A equação da reta pode ser representada por y = mx + b, onde m é a inclinação.', '2024-01-04 01:00:00', 103, 16),
('As frases podem ser declarativas, interrogativas, exclamativas ou imperativas.', '2024-01-04 05:45:00', 104, 12),
('As células são as unidades fundamentais do corpo humano.', '2024-01-04 09:00:00', 105, 5),
('A Revolução Industrial trouxe grandes transformações tecnológicas e sociais.', '2024-01-04 12:00:00', 106, 20);

-- Publicações e Comentários para 2024-02-01
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação quadrática?', '2024-02-01 09:30:00', 1, 1, 10),
('Qual é a importância da comunicação eficaz?', '2024-02-01 13:15:00', 1, 2, 8),
('O que são enzimas e qual a sua função no organismo?', '2024-02-01 18:00:00', 1, 3, 12);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Você pode usar a fórmula de Bhaskara para resolver equações quadráticas.', '2024-02-01 10:00:00', 107, 6),
('A comunicação eficaz é essencial para o ambiente de trabalho.', '2024-02-01 14:00:00', 108, 18),
('As enzimas facilitam processos biológicos essenciais para a vida.', '2024-02-01 18:30:00', 109, 6);

-- Publicações e Comentários para 2024-02-03
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a importância dos conectivos na argumentação?', '2024-02-03 10:00:00', 1, 2, 14),
('O que é a digestão celular?', '2024-02-03 12:45:00', 1, 3, 16),
('Como a Revolução Industrial mudou a economia mundial?', '2024-02-03 18:30:00', 1, 4, 11);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Conectivos como "porém" e "portanto" são fundamentais.', '2024-02-03 10:30:00', 110, 19),
('A digestão celular ocorre dentro das células e é crucial para a energia.', '2024-02-03 13:00:00', 111, 9),
('A Revolução Industrial trouxe muitas inovações tecnológicas.', '2024-02-03 19:00:00', 112, 22);

-- Publicações e Comentários para 2024-02-05
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de figuras geométricas?', '2024-02-05 09:15:00', 1, 1, 19),
('O que é a teoria da evolução?', '2024-02-05 14:30:00', 1, 3, 13),
('Quais foram as causas da Primeira Guerra Mundial?', '2024-02-05 18:10:00', 1, 4, 8);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de um círculo é dada por πr².', '2024-02-05 09:30:00', 113, 7),
('A teoria da evolução explica como as espécies se adaptam ao ambiente.', '2024-02-05 15:00:00', 114, 15),
('A Primeira Guerra Mundial teve causas políticas e territoriais complexas.', '2024-02-05 18:30:00', 115, 14);

-- Publicações e Comentários para 2024-02-07
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver sistemas de equações lineares?', '2024-02-07 09:45:00', 1, 1, 9),
('Qual a função do sistema circulatório?', '2024-02-07 14:30:00', 1, 3, 18),
('Quais foram os principais resultados da Revolução Francesa?', '2024-02-07 18:00:00', 1, 4, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Você pode usar o método da substituição ou da adição.', '2024-02-07 10:00:00', 116, 16),
('O sistema circulatório transporta oxigênio e nutrientes pelo corpo.', '2024-02-07 15:00:00', 117, 22),
('A Declaração dos Direitos do Homem foi um marco da Revolução Francesa.', '2024-02-07 18:30:00', 118, 11);

-- Publicações e Comentários para 2024-02-09
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação de segundo grau?', '2024-02-09 08:00:00', 1, 1, 12),
('Qual a diferença entre seres autótrofos e heterótrofos?', '2024-02-09 13:00:00', 1, 3, 19),
('Como as mulheres foram fundamentais na Revolução Industrial?', '2024-02-09 17:30:00', 1, 4, 7);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Lembre-se de que a equação deve estar igualada a zero.', '2024-02-09 08:30:00', 119, 13),
('Seres autótrofos produzem seu próprio alimento, enquanto heterótrofos dependem de outros organismos.', '2024-02-09 13:30:00', 120, 13),
('As mulheres desempenharam um papel crucial na industrialização, especialmente nas fábricas.', '2024-02-09 18:00:00', 121, 9);

-- Publicações e Comentários para 2024-02-11
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como a matemática ajuda a resolver problemas do dia a dia?', '2024-02-11 10:30:00', 1, 1, 16),
('Qual a importância do estudo da geografia?', '2024-02-11 14:00:00', 1, 8, 14),
('Como o capitalismo influencia a sociedade atual?', '2024-02-11 17:15:00', 1, 7, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A matemática pode ser usada para planejar orçamentos e calcular juros.', '2024-02-11 11:00:00', 122, 22),
('O estudo da geografia nos ajuda a entender a distribuição de recursos no planeta.', '2024-02-11 14:30:00', 123, 20),
('O capitalismo influencia a economia global e as relações de classe.', '2024-02-11 18:00:00', 124, 12);

-- Publicações e comentários para MARÇO
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação quadrática?', '2024-03-01 09:00:00', 1, 1, 12),
('Quais são as funções sintáticas das orações subordinadas?', '2024-03-01 13:20:00', 1, 2, 14),
('O que é a célula e suas organelas?', '2024-03-03 09:40:00', 1, 3, 22),
('Quais os principais marcos da Idade Média?', '2024-03-05 09:05:00', 1, 4, 18),

('Qual a importância da lei de Newton?', '2024-03-07 01:00:00', 1, 5, 19),
('Como calcular a aceleração de um corpo?', '2024-03-09 13:20:00', 1, 5, 8),
('O que é a tabela periódica e como usá-la?', '2024-03-11 13:30:00', 1, 6, 16),
('Qual é o papel do carbono no ciclo biogeoquímico?', '2024-03-13 12:55:00', 1, 3, 13),

('Qual a diferença entre direitos humanos e direitos fundamentais?', '2024-03-15 13:00:00', 1, 7, 8),
('Quais as diferenças entre o capitalismo e o socialismo?', '2024-03-17 09:50:00', 1, 7, 16),
('Qual a importância do uso de preposições em inglês?', '2024-03-19 13:45:00', 1, 9, 9),
('Como entender melhor as correntes filosóficas?', '2024-03-21 13:25:00', 1, 10, 14),

('Qual a origem das principais línguas do mundo?', '2024-03-23 13:15:00', 1, 9, 14),
('Como a física pode ser aplicada no dia a dia?', '2024-03-25 09:00:00', 1, 5, 10),
('O que caracteriza o iluminismo?', '2024-03-27 13:15:00', 1, 4, 14),
('Quais as principais contribuições da filosofia antiga?', '2024-03-29 13:25:00', 1, 10, 9),

('Como funciona o sistema circulatório?', '2024-03-31 13:45:00', 1, 3, 22);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Você pode resolver a equação quadrática usando a fórmula de Bhaskara.', '2024-03-01 09:30:00', 125, 14),
('Não se esqueça de verificar as raízes reais e imaginárias da equação.', '2024-03-01 09:45:00', 125, 12),

('Oração subordinada adverbial indica uma circunstância.', '2024-03-01 13:45:00', 126, 12),
('Oração subordinada substantiva pode ser sujeito, objeto direto ou indireto.', '2024-03-01 14:00:00', 126, 5),

('A célula é a unidade básica da vida e as organelas são responsáveis por suas funções vitais.', '2024-03-03 10:00:00', 127, 22),
('As organelas mais importantes são o núcleo, mitocôndrias e ribossomos.', '2024-03-03 10:20:00', 127, 18),

('Na Idade Média, a Igreja teve um papel central na política e na educação.', '2024-03-05 09:30:00', 128, 16),
('O feudalismo e a vida camponesa eram características predominantes.', '2024-03-05 09:40:00', 128, 18),

('A lei de Newton é fundamental para entender o movimento dos corpos.', '2024-03-07 01:20:00', 129, 19),
('Ela descreve como a força é proporcional à aceleração e massa.', '2024-03-07 01:40:00', 129, 22),

('Para calcular a aceleração, divida a variação da velocidade pelo tempo.', '2024-03-09 13:40:00', 130, 8),
('Certifique-se de usar as unidades corretas para a aceleração.', '2024-03-09 13:55:00', 130, 5),

('A tabela periódica organiza os elementos com base em suas propriedades.', '2024-03-11 13:50:00', 131, 16),
('Os metais, não-metais e gases nobres têm características distintas.', '2024-03-11 14:00:00', 131, 13),

('O carbono é essencial para a vida, pois é a base das moléculas orgânicas.', '2024-03-13 13:10:00', 132, 22),
('Ele é encontrado em todos os seres vivos, no formato de carboidratos, lipídios e proteínas.', '2024-03-13 13:30:00', 132, 9),

('Direitos humanos protegem a dignidade de todos os seres humanos.', '2024-03-15 13:30:00', 133, 8),
('Direitos fundamentais são aqueles que garantem a liberdade, igualdade e segurança.', '2024-03-15 13:40:00', 133, 14),

('O capitalismo e o socialismo possuem filosofias econômicas e sociais muito distintas.', '2024-03-17 10:00:00', 134, 16),
('Enquanto o capitalismo prioriza a propriedade privada, o socialismo visa a igualdade social.', '2024-03-17 10:20:00', 134, 10),

('As preposições em inglês são importantes para mostrar relações entre palavras.', '2024-03-19 14:00:00', 135, 9),
('É fundamental conhecer as preposições mais comuns, como in, on, at, by.', '2024-03-19 14:10:00', 135, 16),

('As correntes filosóficas, como o racionalismo e empirismo, ajudam a compreender o conhecimento.', '2024-03-21 13:30:00', 136, 14),
('Entender essas correntes é essencial para estudar a filosofia clássica.', '2024-03-21 13:40:00', 136, 5),

('A origem das línguas está relacionada às culturas e à evolução histórica das sociedades.', '2024-03-23 13:30:00', 137, 14),
('A história das línguas pode ser estudada de forma comparativa.', '2024-03-23 13:45:00', 137, 22),

('A física está em tudo ao nosso redor, desde o movimento dos corpos até o funcionamento da luz.', '2024-03-25 09:30:00', 138, 10),
('Ela nos ajuda a entender as leis fundamentais do universo.', '2024-03-25 09:40:00', 138, 18),

('O iluminismo trouxe novas ideias sobre liberdade e direitos do indivíduo.', '2024-03-27 13:25:00', 139, 14),
('Essa corrente influenciou a Revolução Francesa e o surgimento da democracia moderna.', '2024-03-27 13:40:00', 139, 22),

('As contribuições de Sócrates, Platão e Aristóteles são fundamentais para a filosofia ocidental.', '2024-03-29 13:40:00', 140, 9),
('A filosofia antiga aborda questões sobre ética, política e conhecimento.', '2024-03-29 13:50:00', 140, 16),

('O sistema circulatório é responsável pelo transporte de oxigênio e nutrientes pelo corpo.', '2024-03-31 13:50:00', 141, 22),
('Ele é composto pelo coração, vasos sanguíneos e sangue.', '2024-03-31 13:55:00', 141, 18);

-- Publicações ABRIL
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um triângulo?', '2024-04-01 09:00:00', 1, 1, 12),
('A importância da leitura crítica nos textos acadêmicos.', '2024-04-01 13:40:00', 1, 2, 14),
('Quais são as funções dos lipídios no corpo humano?', '2024-04-03 09:30:00', 1, 3, 16),
('A Revolução Industrial e suas implicações sociais.', '2024-04-05 09:10:00', 1, 4, 18),

('Como resolver um sistema linear?', '2024-04-07 09:00:00', 1, 1, 10),
('As diferenças entre monossílabos, dissílabos e polissílabos.', '2024-04-09 09:25:00', 1, 2, 16),
('O impacto da fotossíntese no ambiente.', '2024-04-11 09:40:00', 1, 3, 18),
('A Era Vargas e seus principais eventos históricos.', '2024-04-13 09:10:00', 1, 4, 12),

('Como calcular a velocidade média?', '2024-04-15 09:30:00', 1, 5, 14),
('Quais são as diferenças entre ácidos e bases?', '2024-04-17 09:25:00', 1, 6, 12),
('As leis da termodinâmica e sua aplicação no cotidiano.', '2024-04-19 09:00:00', 1, 5, 16),
('A importância da geografia no estudo das migrações humanas.', '2024-04-21 09:10:00', 1, 7, 16),

('Como se dá o processo de secularização na sociedade?', '2024-04-23 09:25:00', 1, 8, 22),
('O conceito de cultura e sua influência nas sociedades.', '2024-04-25 09:35:00', 1, 9, 16),
('O que são as teorias sobre a origem da vida?', '2024-04-27 09:05:00', 1, 3, 18),
('As mudanças políticas durante o Império Romano.', '2024-04-29 09:15:00', 1, 4, 20);

-- Comentários ABRIL
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de um triângulo é dada por (base * altura) / 2.', '2024-04-01 09:30:00', 142, 14),
('É importante verificar a unidade da base e da altura.', '2024-04-01 09:45:00', 142, 18),

('A leitura crítica envolve analisar, interpretar e questionar o texto.', '2024-04-01 14:00:00', 143, 12),
('Devemos identificar os argumentos e evidências do autor.', '2024-04-01 14:30:00', 143, 22),

('Lipídios são essenciais para a formação das membranas celulares.', '2024-04-03 09:50:00', 144, 16),
('Eles também têm funções energéticas e de isolamento.', '2024-04-03 10:00:00', 144, 18),

('Para resolver um sistema linear, é necessário encontrar os valores das incógnitas.', '2024-04-07 09:10:00', 145, 12),
('Uma das formas de resolver é utilizando a substituição ou a adição.', '2024-04-07 09:30:00', 145, 14),

('Monossílabos são palavras com uma sílaba, enquanto dissílabos possuem duas.', '2024-04-09 09:40:00', 146, 18),
('Já polissílabos possuem três ou mais sílabas.', '2024-04-09 09:50:00', 146, 22),

('A fotossíntese é essencial para a produção de oxigênio no planeta.', '2024-04-11 10:00:00', 147, 10),
('Sem a fotossíntese, as cadeias alimentares não funcionariam.', '2024-04-11 10:30:00', 147, 12),

('A Era Vargas é marcada por transformações políticas e sociais no Brasil.', '2024-04-13 09:30:00', 148, 5),
('O período foi caracterizado por um governo autoritário e intervencionista.', '2024-04-13 09:45:00', 148, 16),

('A velocidade média é dada pela razão entre distância e tempo.', '2024-04-15 09:45:00', 149, 14),
('Não podemos esquecer de que a unidade de medida é importante para o cálculo correto.', '2024-04-15 10:00:00', 149, 22),

('Ácidos liberam prótons em solução, enquanto as bases liberam íons hidróxido.', '2024-04-17 09:40:00', 150, 16),
('Esses dois tipos de substâncias são fundamentais para muitas reações químicas.', '2024-04-17 09:55:00', 150, 18),

('As leis da termodinâmica são essenciais para entender como a energia se comporta.', '2024-04-19 09:15:00', 151, 20),
('Elas são fundamentais para a física e para a engenharia.', '2024-04-19 09:40:00', 151, 16),

('O estudo das migrações ajuda a entender o comportamento das populações ao longo do tempo.', '2024-04-21 09:30:00', 152, 18),
('A geografia é essencial para analisar esses fluxos e suas consequências.', '2024-04-21 09:50:00', 152, 20),

('A secularização é um processo de distanciamento entre a religião e as instituições públicas.', '2024-04-23 09:40:00', 153, 22),
('Ela tem implicações profundas na sociedade, alterando valores e normas.', '2024-04-23 10:00:00', 153, 18),

('A cultura é um reflexo das práticas sociais e das crenças de uma sociedade.', '2024-04-25 09:50:00', 154, 12),
('Ela influencia a maneira como as pessoas se relacionam entre si e com o mundo.', '2024-04-25 10:00:00', 154, 5),

('As teorias sobre a origem da vida envolvem desde explicações religiosas até científicas.', '2024-04-27 09:20:00', 155, 22),
('A teoria da evolução é uma das mais amplamente aceitas na comunidade científica.', '2024-04-27 09:40:00', 155, 18),

('O Império Romano passou por diversas transformações políticas ao longo dos séculos.', '2024-04-29 09:30:00', 156, 20),
('As mudanças foram muitas vezes influenciadas por invasões e conflitos internos.', '2024-04-29 09:50:00', 156, 14);

-- MAIO
-- Publicações
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação do segundo grau?', '2024-05-01 00:10:00', 1, 1, 22),
('A importância do verbo na construção de frases.', '2024-05-01 04:15:00', 1, 2, 16),
('O que são proteínas e qual o seu papel no corpo humano?', '2024-05-01 08:30:00', 1, 3, 18),
('O impacto da Revolução Industrial no mundo moderno.', '2024-05-01 12:45:00', 1, 4, 14),
('Como fazer uma análise de circuito elétrico?', '2024-05-01 16:30:00', 1, 5, 20),
('Quais são os principais elementos químicos e suas propriedades?', '2024-05-01 20:00:00', 1, 6, 10);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Para resolver, use a fórmula de Bhaskara ou o método de fatoração.', '2024-05-01 00:30:00', 157, 5),
('Lembre-se que o verbo é o núcleo da oração.', '2024-05-01 04:30:00', 158, 18),
('As proteínas são essenciais para o crescimento muscular e recuperação.', '2024-05-01 09:00:00', 159, 14),
('A Revolução Industrial mudou a forma como os produtos eram fabricados.', '2024-05-01 13:00:00', 160, 16),
('No circuito, você pode usar a Lei de Ohm para calcular a resistência.', '2024-05-01 17:00:00', 161, 10),
('A tabela periódica é uma ferramenta essencial para entender as propriedades dos elementos.', '2024-05-01 20:30:00', 162, 22);

-- Publicações
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a média ponderada?', '2024-05-02 01:00:00', 1, 1, 12),
('A importância da pontuação na clareza do texto.', '2024-05-02 05:20:00', 1, 2, 16),
('Qual o papel das vitaminas no organismo?', '2024-05-02 09:30:00', 1, 3, 22),
('A história da Revolução Russa e suas consequências.', '2024-05-02 13:40:00', 1, 4, 18),
('Como funciona a Lei da Gravitação Universal?', '2024-05-02 17:00:00', 1, 5, 20),
('Quais são as propriedades das substâncias ácidas?', '2024-05-02 21:10:00', 1, 6, 14);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Multiplique as notas pelas respectivas ponderações e depois divida pela soma dos pesos.', '2024-05-02 01:30:00', 163, 18),
('A pontuação correta ajuda a evitar ambiguidades e confusões.', '2024-05-02 05:45:00', 164, 5),
('As vitaminas ajudam na regulação de processos metabólicos no corpo.', '2024-05-02 09:50:00', 165, 14),
('A Revolução Russa deu origem ao regime socialista na União Soviética.', '2024-05-02 14:00:00', 166, 20),
('A gravidade mantém os planetas em órbita ao redor do sol.', '2024-05-02 17:30:00', 167, 12),
('Ácidos podem liberar íons hidrogênio em soluções aquosas.', '2024-05-02 21:30:00', 168, 22);

-- Publicações
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são as fórmulas básicas de trigonometria?', '2024-05-03 02:05:00', 1, 1, 10),
('A construção de uma argumentação eficaz.', '2024-05-03 06:20:00', 1, 2, 18),
('Como funcionam os anticorpos no sistema imunológico?', '2024-05-03 10:30:00', 1, 3, 14),
('As causas e efeitos da Primeira Guerra Mundial.', '2024-05-03 14:45:00', 1, 4, 20),
('Como calcular a aceleração de um corpo?', '2024-05-03 18:00:00', 1, 5, 22),
('O ciclo da água na natureza.', '2024-05-03 22:10:00', 1, 6, 16);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula básica é seno = cateto oposto / hipotenusa.', '2024-05-03 02:30:00', 169, 22),
('Para uma boa argumentação, você precisa apresentar claramente suas ideias e justificar suas opiniões.', '2024-05-03 06:40:00', 170, 12),
('Os anticorpos são proteínas que reconhecem e neutralizam agentes patogênicos.', '2024-05-03 10:50:00', 171, 20),
('A Primeira Guerra Mundial foi desencadeada pelo assassinato do arquiduque Francisco Ferdinando.', '2024-05-03 15:00:00', 172, 16),
('A aceleração é a taxa de variação da velocidade em função do tempo.', '2024-05-03 18:30:00', 173, 14),
('O ciclo da água envolve processos como evaporação, condensação e precipitação.', '2024-05-03 22:30:00', 174, 5);


-- 01, 02 e 03 de JUNHO
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um triângulo?', '2024-06-01 00:10:00', 1, 1, 24),
('A importância dos adjetivos na língua portuguesa.', '2024-06-01 04:15:00', 1, 2, 19),
('A digestão no sistema gastrointestinal.', '2024-06-01 08:30:00', 1, 3, 17),
('Quais foram os principais marcos da Revolução Francesa?', '2024-06-01 12:45:00', 1, 4, 22),
('A gravidade e suas descobertas na física.', '2024-06-01 16:30:00', 1, 5, 18),
('Quais são os elementos químicos essenciais para a vida?', '2024-06-01 20:00:00', 1, 6, 12),

('O que são os números primos?', '2024-06-02 01:00:00', 1, 1, 20),
('Como utilizar os tempos verbais em português?', '2024-06-02 05:20:00', 1, 2, 14),
('O papel das enzimas na digestão de proteínas.', '2024-06-02 09:30:00', 1, 3, 16),
('Causas e consequências da Revolução Francesa.', '2024-06-02 13:40:00', 1, 4, 18),
('Os modelos atômicos na física moderna.', '2024-06-02 17:00:00', 1, 5, 22),
('Quais as características dos elementos químicos?', '2024-06-02 21:10:00', 1, 6, 12),

('A história da matemática através dos séculos.', '2024-06-03 02:05:00', 1, 1, 18),
('Como se organizar um texto dissertativo?', '2024-06-03 06:20:00', 1, 2, 14),
('A digestão das gorduras no corpo humano.', '2024-06-03 10:30:00', 1, 3, 22),
('O impacto da Revolução Francesa nas outras revoluções.', '2024-06-03 14:45:00', 1, 4, 16),
('O que é o movimento pendular na física?', '2024-06-03 18:00:00', 1, 5, 24),
('Como se dá o ciclo da água no meio ambiente?', '2024-06-03 22:10:00', 1, 7, 20);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área do triângulo é dada por base x altura dividido por 2.', '2024-06-01 00:30:00', 175, 4),
('Os adjetivos são essenciais para descrever as qualidades dos substantivos.', '2024-06-01 04:45:00', 176, 21),
('As enzimas quebram as macromoléculas em nutrientes mais simples durante a digestão.', '2024-06-01 08:45:00', 177, 20),
('A queda da Bastilha foi um evento crucial na Revolução Francesa.', '2024-06-01 13:00:00', 178, 1),
('Newton foi um dos primeiros a descrever a gravidade com sua famosa fórmula.', '2024-06-01 17:00:00', 179, 19),
('A água é essencial para os processos biológicos no corpo.', '2024-06-01 20:30:00', 180, 5),

('Os números primos são divisíveis apenas por 1 e por eles mesmos.', '2024-06-02 01:20:00', 181, 4),
('É importante entender os tempos verbais para manter a coesão no texto.', '2024-06-02 05:40:00', 182, 20),
('As enzimas quebram as proteínas em peptídeos durante a digestão.', '2024-06-02 09:50:00', 183, 1),
('A Revolução Francesa teve um grande impacto nas mudanças políticas europeias.', '2024-06-02 14:00:00', 184, 3),
('Os átomos são compostos por prótons, nêutrons e elétrons.', '2024-06-02 17:30:00', 185, 2),
('A tabela periódica organiza os elementos químicos de acordo com suas propriedades.', '2024-06-02 21:30:00', 186, 12),

('A história da matemática começa com as primeiras contagens e a geometria.', '2024-06-03 02:30:00', 187, 22),
('Um bom texto dissertativo apresenta uma introdução, desenvolvimento e conclusão claros.', '2024-06-03 06:40:00', 188, 20),
('O fígado produz bile, que é essencial na digestão das gorduras.', '2024-06-03 10:50:00', 189, 4),
('A Revolução Francesa inspirou movimentos revolucionários em outros países.', '2024-06-03 15:00:00', 190, 5),
('O movimento pendular foi estudado por Galileo Galilei.', '2024-06-03 18:15:00', 191, 12),
('O ciclo da água inclui evaporação, condensação e precipitação.', '2024-06-03 22:30:00', 192, 14);

-- Inserções para o dia 08 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são verbos irregulares?', '2024-06-08 08:00:00', 1, 1, 18),
('Como identificar uma oração subordinada?', '2024-06-08 09:00:00', 1, 2, 19),
('O que é o ciclo da água?', '2024-06-08 10:30:00', 1, 3, 17),
('Quais as principais causas da Revolução Francesa?', '2024-06-08 12:00:00', 1, 4, 22);

-- Comentários para as publicações do dia 08 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Verbos irregulares são aqueles que não seguem a conjugação padrão.', '2024-06-08 08:30:00', 183, 24),
('Eles têm formas diferentes para o passado e o particípio, como "ir" (fui) e "ver" (vi).', '2024-06-08 08:45:00', 183, 20),
('Exemplos de verbos irregulares são: fazer, ser, ter, entre outros.', '2024-06-08 09:00:00', 183, 18),

('Uma oração subordinada depende de uma oração principal para ter sentido.', '2024-06-08 09:30:00', 184, 22),
('Ela pode ser adjetiva, adverbial ou substantiva, dependendo da função que exerce na frase.', '2024-06-08 10:00:00', 184, 16),
('Exemplo de oração subordinada: "Embora estivesse cansado, ele continuou estudando."', '2024-06-08 10:30:00', 184, 19),

('O ciclo da água é o processo contínuo de evaporação, condensação e precipitação.', '2024-06-08 11:00:00', 185, 14),
('Água evapora dos oceanos, se condensa nas nuvens e depois precipita como chuva ou neve.', '2024-06-08 11:30:00', 185, 17),
('Esse processo é essencial para a manutenção da vida e dos ecossistemas no planeta.', '2024-06-08 12:00:00', 185, 16),

('A Revolução Francesa teve como principais causas a desigualdade social e econômica.', '2024-06-08 13:00:00', 186, 5),
('O movimento teve início com a crise financeira do reino e o descontentamento popular.', '2024-06-08 13:30:00', 186, 22),
('A queda da Bastilha em 14 de julho de 1789 é um marco da Revolução Francesa.', '2024-06-08 14:00:00', 186, 18);

-- Inserções para o dia 09 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um triângulo?', '2024-06-09 08:00:00', 1, 1, 18),
('Quais são as causas da Primeira Guerra Mundial?', '2024-06-09 09:00:00', 1, 2, 19),
('Definição e exemplos de adjetivos comparativos', '2024-06-09 10:30:00', 1, 3, 17),
('O que foi a escravidão no Brasil?', '2024-06-09 12:00:00', 1, 4, 22);

-- Comentários para o dia 09 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de um triângulo é dada por A = (base * altura) / 2.', '2024-06-09 08:30:00', 187, 24),
('O valor da base e da altura deve ser em unidades compatíveis.', '2024-06-09 09:00:00', 187, 20),

('A Primeira Guerra Mundial foi desencadeada por tensões políticas, econômicas e militares entre potências europeias.', '2024-06-09 09:30:00', 188, 18),
('Entre os principais fatores estavam o nacionalismo, o imperialismo e alianças militares.', '2024-06-09 10:00:00', 188, 17),

('Adjetivos comparativos expressam uma relação entre dois ou mais elementos.', '2024-06-09 10:45:00', 189, 16),
('Exemplo: "Maria é mais inteligente do que João."', '2024-06-09 11:15:00', 189, 19),

('A escravidão no Brasil começou no século XVI, com a chegada dos portugueses.', '2024-06-09 13:00:00', 190, 5),
('Foi uma das maiores do mundo, durando mais de 300 anos.', '2024-06-09 13:30:00', 190, 22);

-- Inserções para o dia 11 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são substantivos coletivos?', '2024-06-11 08:00:00', 1, 1, 18),
('Principais eventos da Idade Média', '2024-06-11 09:00:00', 1, 2, 19),
('Como usar as crase corretamente?', '2024-06-11 10:30:00', 1, 3, 17),
('O impacto da Revolução Industrial', '2024-06-11 12:00:00', 1, 4, 22);

-- Comentários para o dia 11 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Substantivos coletivos são palavras que indicam um conjunto de seres da mesma espécie.', '2024-06-11 08:30:00', 191, 24),
('Exemplo: "cardume" para um grupo de peixes.', '2024-06-11 09:00:00', 191, 20),

('A Idade Média foi marcada pelo feudalismo, pela Igreja Católica e pelas cruzadas.', '2024-06-11 09:30:00', 192, 18),
('Foi uma era de grande transformações sociais e culturais.', '2024-06-11 10:00:00', 192, 17),

('A crase ocorre quando há a fusão da preposição "a" com o artigo feminino "a".', '2024-06-11 10:45:00', 193, 16),
('Exemplo: "Vou à escola"', '2024-06-11 11:15:00', 193, 19),

('A Revolução Industrial mudou a forma de produção, criando a indústria e alterando a sociedade.', '2024-06-11 13:00:00', 194, 5),
('Ela também trouxe grandes avanços tecnológicos e aumentou as desigualdades sociais.', '2024-06-11 13:30:00', 194, 22);

-- Inserções para o dia 13 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação do 2º grau?', '2024-06-13 08:00:00', 1, 1, 18),
('Causas e consequências da Revolução Americana', '2024-06-13 09:00:00', 1, 2, 19),
('Diferença entre frases afirmativas e negativas', '2024-06-13 10:30:00', 1, 3, 17),
('O que é o sistema feudal?', '2024-06-13 12:00:00', 1, 4, 22);

-- Comentários para o dia 13 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A equação do 2º grau é resolvida usando a fórmula: x = (-b ± √(b² - 4ac)) / 2a.', '2024-06-13 08:30:00', 195, 24),
('Ela tem a forma ax² + bx + c = 0.', '2024-06-13 09:00:00', 195, 20),

('A Revolução Americana teve como causas principais o descontentamento das colônias com as políticas britânicas.', '2024-06-13 09:30:00', 196, 18),
('Ela resultou na independência dos Estados Unidos e influenciou outras revoluções.', '2024-06-13 10:00:00', 196, 17),

('Frases afirmativas declaram algo, enquanto as negativas negam uma ação ou estado.', '2024-06-13 10:45:00', 197, 16),
('Exemplo afirmativo: "Ele estuda todos os dias." Exemplo negativo: "Ele não estuda todos os dias."', '2024-06-13 11:15:00', 197, 19),

('O sistema feudal era caracterizado pela relação de vassalagem entre senhores e servos.', '2024-06-13 13:00:00', 198, 5),
('Ele foi predominante na Europa medieval e envolvia a posse de terras e a produção agrícola.', '2024-06-13 13:30:00', 198, 22);

-- Inserções para o dia 15 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como se forma um plural regular?', '2024-06-15 08:00:00', 1, 1, 18),
('Consequências da Reforma Protestante', '2024-06-15 09:00:00', 1, 2, 19),
('Como fazer a concordância verbal?', '2024-06-15 10:30:00', 1, 3, 17),
('O que foi o Renascimento?', '2024-06-15 12:00:00', 1, 4, 22);

-- Comentários para o dia 15 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Plurais regulares se formam geralmente acrescentando "-s" ou "-es" ao final das palavras.', '2024-06-15 08:30:00', 199, 24),
('Exemplo: "livro" (singular) / "livros" (plural).', '2024-06-15 09:00:00', 199, 20),

('A Reforma Protestante levou à divisão da Igreja Católica e ao surgimento das igrejas protestantes.', '2024-06-15 09:30:00', 200, 18),
('Ela teve grande impacto político, social e religioso na Europa.', '2024-06-15 10:00:00', 200, 17),

('A concordância verbal é a correspondência entre o sujeito e o verbo.', '2024-06-15 10:45:00', 201, 16),
('Exemplo: "Eles vão ao mercado."', '2024-06-15 11:15:00', 201, 19),

('O Renascimento foi um movimento cultural e artístico que marcou a transição entre a Idade Média e a Idade Moderna.', '2024-06-15 13:00:00', 202, 5),
('Ele trouxe novos valores como o humanismo, valorizando o indivíduo e a razão.', '2024-06-15 13:30:00', 202, 22);

-- Inserções para o dia 17 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como identificar um verbo transitivo?', '2024-06-17 08:00:00', 1, 1, 18),
('As características da sociedade feudal', '2024-06-17 09:00:00', 1, 2, 19),
('O que são orações subordinadas adverbiais?', '2024-06-17 10:30:00', 1, 3, 17),
('O impacto da Revolução Industrial nas cidades', '2024-06-17 12:00:00', 1, 4, 22);

-- Comentários para o dia 17 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Verbos transitivos exigem um objeto direto ou indireto para completar o sentido.', '2024-06-17 08:30:00', 203, 24),
('Exemplo: "Ele comprou um livro."', '2024-06-17 09:00:00', 203, 20),

('A sociedade feudal era marcada pela divisão em classes: senhores feudais, vassalos e servos.', '2024-06-17 09:30:00', 204, 18),
('A terra era a principal fonte de riqueza e poder.', '2024-06-17 10:00:00', 204, 17),

('Orações subordinadas adverbiais são aquelas que funcionam como advérbios, explicando circunstâncias.', '2024-06-17 10:45:00', 205, 16),
('Exemplo: "Ele saiu quando começou a chover."', '2024-06-17 11:15:00', 205, 19),

('A Revolução Industrial gerou um processo de urbanização nas cidades, com a criação de fábricas e aumento do trabalho assalariado.', '2024-06-17 13:00:00', 206, 5),
('Isso provocou o crescimento das grandes cidades e a migração rural-urbana.', '2024-06-17 13:30:00', 206, 22);

-- Inserções para o dia 25 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação de 1º grau?', '2024-06-25 08:00:00', 1, 1, 18),
('A invenção da imprensa por Gutenberg', '2024-06-25 09:00:00', 1, 2, 19),
('Exemplos de orações coordenadas', '2024-06-25 10:30:00', 1, 3, 17),
('A Revolução Francesa e suas consequências', '2024-06-25 12:00:00', 1, 4, 22);

-- Comentários para o dia 25 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações de 1º grau têm a forma ax + b = 0 e podem ser resolvidas isolando a variável x.', '2024-06-25 08:30:00', 207, 24),
('Exemplo: 2x + 5 = 11. Resultado: x = 3.', '2024-06-25 09:00:00', 207, 20),

('A invenção da imprensa por Gutenberg foi um marco na história, permitindo a impressão de livros em grande escala.', '2024-06-25 09:30:00', 208, 18),
('Isso contribuiu para a disseminação do conhecimento e a popularização da leitura.', '2024-06-25 10:00:00', 208, 17),

('Orações coordenadas são aquelas que estão na mesma estrutura e são ligadas por uma conjunção.', '2024-06-25 10:45:00', 209, 16),
('Exemplo: "Eu estudei e passei no exame."', '2024-06-25 11:15:00', 209, 19),

('A Revolução Francesa resultou na queda da monarquia e na ascensão de um governo republicano.', '2024-06-25 13:00:00', 210, 5),
('Ela inspirou movimentos de liberdade e igualdade em outras partes do mundo.', '2024-06-25 13:30:00', 210, 22);

-- Inserções para o dia 27 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são adjetivos comparativos?', '2024-06-27 08:00:00', 1, 1, 18),
('O que foi o feudalismo?', '2024-06-27 09:00:00', 1, 2, 19),
('Como identificar uma oração subordinada substantiva?', '2024-06-27 10:30:00', 1, 3, 17),
('Consequências da Revolução Industrial', '2024-06-27 12:00:00', 1, 4, 22);

-- Comentários para o dia 27 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Adjetivos comparativos servem para comparar dois ou mais elementos.', '2024-06-27 08:30:00', 211, 24),
('Exemplo: "João é mais alto do que Pedro."', '2024-06-27 09:00:00', 211, 20),

('O feudalismo foi um sistema social e econômico baseado na posse de terras e na relação entre senhores e vassalos.', '2024-06-27 09:30:00', 212, 18),
('Ele predominou na Europa durante a Idade Média.', '2024-06-27 10:00:00', 212, 17),

('Orações subordinadas substantivas funcionam como o sujeito ou objeto da oração principal.', '2024-06-27 10:45:00', 213, 16),
('Exemplo: "Espero que você venha."', '2024-06-27 11:15:00', 213, 19),

('A Revolução Industrial trouxe a urbanização das cidades e o crescimento das fábricas.', '2024-06-27 13:00:00', 214, 5),
('Isso alterou profundamente as condições de trabalho e a estrutura social.', '2024-06-27 13:30:00', 214, 22);

-- Inserções para o dia 29 de junho

INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como fazer uma análise sintática?', '2024-06-29 08:00:00', 1, 1, 18),
('A importância da democracia na Grécia Antiga', '2024-06-29 09:00:00', 1, 2, 19),
('Quais as diferenças entre as orações subordinadas e coordenadas?', '2024-06-29 10:30:00', 1, 3, 17),
('Como as máquinas influenciaram a Revolução Industrial?', '2024-06-29 12:00:00', 1, 4, 22);

-- Comentários para o dia 29 de junho

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A análise sintática envolve a identificação dos elementos que formam a frase, como sujeito, predicado, objetos, etc.', '2024-06-29 08:30:00', 215, 24),
('Exemplo: "O menino (sujeito) corre rápido (predicado)."', '2024-06-29 09:00:00', 215, 20),

('A democracia na Grécia Antiga era exercida principalmente nas cidades-estado, como Atenas.', '2024-06-29 09:30:00', 216, 18),
('Era um sistema onde os cidadãos participavam ativamente das decisões políticas.', '2024-06-29 10:00:00', 216, 17),

('Orações subordinadas dependem de outra oração para completar o sentido, enquanto as coordenadas não dependem.', '2024-06-29 10:45:00', 217, 16),
('Exemplo: "Ele foi ao mercado e comprou frutas." (oração coordenada).', '2024-06-29 11:15:00', 217, 19),

('As máquinas a vapor, por exemplo, impulsionaram a mecanização da indústria têxtil e o crescimento das fábricas.', '2024-06-29 13:00:00', 218, 5),
('A Revolução Industrial teve grande impacto na produção em massa e nas condições de trabalho.', '2024-06-29 13:30:00', 218, 22);

-- Publicações e comentários de JULHO
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um triângulo?', '2024-07-01 01:00:00', 1, 1, 22),
('A Revolução Francesa: causas e consequências.', '2024-07-01 09:30:00', 1, 4, 18),
('A teoria da evolução de Darwin.', '2024-07-01 17:15:00', 1, 3, 20),
('O que é a fotossíntese?', '2024-07-02 02:15:00', 1, 3, 14),
('A importância da preservação ambiental.', '2024-07-02 10:10:00', 1, 7, 16),
('A Primeira Guerra Mundial: causas e efeitos.', '2024-07-02 18:40:00', 1, 4, 12),
('A fórmula da velocidade média.', '2024-07-03 03:30:00', 1, 5, 18),
('Como resolver uma equação quadrática?', '2024-07-03 11:00:00', 1, 1, 20),
('Reações químicas e suas leis.', '2024-07-03 19:10:00', 1, 6, 16),
('A revolução industrial no século XIX.', '2024-07-04 02:00:00', 1, 4, 22),
('A química dos ácidos e bases.', '2024-07-04 10:30:00', 1, 6, 14),
('O conceito de trabalho e energia.', '2024-07-04 18:15:00', 1, 5, 20),
('Os direitos do homem durante a Revolução Francesa.', '2024-07-05 04:00:00', 1, 4, 12),
('A formação da linguagem: teorias e desafios.', '2024-07-05 12:30:00', 1, 2, 16),
('O impacto da filosofia no desenvolvimento científico.', '2024-07-05 20:10:00', 1, 10, 18),
('O impacto das guerras mundiais no mundo contemporâneo.', '2024-07-06 01:40:00', 1, 4, 14),
('Como os combustíveis fósseis afetam o meio ambiente?', '2024-07-06 09:00:00', 1, 7, 20),
('As leis da termodinâmica.', '2024-07-06 16:50:00', 1, 5, 22),
('A influência do positivismo na política moderna.', '2024-07-07 03:15:00', 1, 10, 18),
('A relação entre ética e moral na filosofia.', '2024-07-07 11:20:00', 1, 10, 12),
('Geografia física: como os continentes se formaram?', '2024-07-07 19:30:00', 1, 8, 16),
('O conceito de democracia na Grécia antiga.', '2024-07-08 02:50:00', 1, 4, 20),
('Como o capitalismo afeta a sociedade moderna?', '2024-07-08 10:00:00', 1, 7, 14),
('A teoria da relatividade de Einstein.', '2024-07-08 18:25:00', 1, 5, 16),
('A importância da matemática no nosso dia a dia.', '2024-07-09 04:40:00', 1, 1, 12),
('A arte de se comunicar eficazmente.', '2024-07-09 12:00:00', 1, 2, 18),
('Como calcular a energia cinética?', '2024-07-09 20:15:00', 1, 5, 22),
('O papel da ciência nas mudanças sociais.', '2024-07-10 01:30:00', 1, 10, 14),
('Teorias da evolução social.', '2024-07-10 09:20:00', 1, 7, 20),
('Como estudar para o vestibular?', '2024-07-10 17:10:00', 1, 2, 16),
('A história das grandes navegações.', '2024-07-11 02:00:00', 1, 4, 18),
('A ética kantiana e seus impactos na sociedade.', '2024-07-11 10:30:00', 1, 10, 22),
('A importância da memória histórica.', '2024-07-11 18:00:00', 1, 4, 14),
('A filosofia do renascimento.', '2024-07-12 03:40:00', 1, 10, 16),
('A relação entre ciência e religião.', '2024-07-12 11:50:00', 1, 10, 14),
('A química por trás da reação de combustão.', '2024-07-12 19:20:00', 1, 6, 20);

-- Inserção de comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O cálculo da área do triângulo é feito por base x altura / 2.', '2024-07-01 01:30:00', 219, 5),
('A queda da Bastilha foi o marco inicial da Revolução.', '2024-07-01 10:00:00', 220, 16),
('Darwin explicou como as espécies evoluem ao longo do tempo.', '2024-07-01 17:30:00', 221, 18),
('A fotossíntese é fundamental para a produção de oxigênio.', '2024-07-02 02:30:00', 222, 14),
('Precisamos pensar na preservação dos recursos naturais.', '2024-07-02 10:30:00', 223, 16),
('A Primeira Guerra teve um impacto devastador na Europa.', '2024-07-02 19:00:00', 224, 12),
('Velocidade média é a distância dividida pelo tempo total.', '2024-07-03 03:45:00', 225, 18),
('A equação quadrática pode ser resolvida pela fórmula de Bhaskara.', '2024-07-03 11:30:00', 226, 20),
('Reações químicas podem ser exotérmicas ou endotérmicas.', '2024-07-03 19:30:00', 227, 16),
('A Revolução Industrial teve grande impacto na economia mundial.', '2024-07-04 02:30:00', 228, 22),
('Ácidos fortes dissociam completamente em solução.', '2024-07-04 10:45:00', 229, 14),
('Trabalho e energia são conceitos fundamentais na física.', '2024-07-04 18:30:00', 230, 20),
('A Declaração dos Direitos do Homem foi um marco importante.', '2024-07-05 04:30:00', 231, 12),
('A argumentação lógica é fundamental para uma boa redação.', '2024-07-05 12:45:00', 232, 16),
('A filosofia influencia profundamente a ciência.', '2024-07-05 20:30:00', 233, 18),
('Combustíveis fósseis são uma grande ameaça ao meio ambiente.', '2024-07-06 01:50:00', 234, 14),
('A teoria da termodinâmica é essencial para entender a física.', '2024-07-06 09:30:00', 235, 20),
('A filosofia positivista influenciou as ciências sociais.', '2024-07-07 03:30:00', 236, 18),
('A ética é a base das nossas ações cotidianas.', '2024-07-07 11:40:00', 237, 12),
('Os continentes se formaram ao longo de milhões de anos.', '2024-07-07 19:45:00', 238, 16),
('A democracia grega era direta, o que é diferente dos sistemas modernos.', '2024-07-08 03:00:00', 239, 20),
('O capitalismo atual afeta profundamente a estrutura social.', '2024-07-08 10:15:00', 240, 14),
('A teoria da relatividade mudou nossa visão do universo.', '2024-07-08 18:40:00', 241, 16),
('A matemática é essencial para entender fenômenos naturais.', '2024-07-09 04:50:00', 242, 12),
('A comunicação é uma habilidade essencial para o sucesso.', '2024-07-09 12:10:00', 243, 18),
('A energia cinética é dada pela fórmula mv²/2.', '2024-07-09 20:25:00', 244, 22);

-- SETEMBRO
-- 2024-09-01
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação linear?', '2024-09-01 09:00:00', 1, 1, 22),
('O que são os conectivos na língua portuguesa?', '2024-09-01 13:00:00', 1, 2, 18),
('A importância das enzimas na digestão', '2024-09-01 19:00:00', 1, 3, 20),
('Quais foram os principais eventos da Revolução Francesa?', '2024-09-01 21:00:00', 1, 4, 16);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações lineares podem ser resolvidas isolando a variável.', '2024-09-01 09:30:00', 245, 16),
('A fórmula geral é ax + b = 0.', '2024-09-01 09:45:00', 245, 22),
('Sempre verifique as soluções substituindo-as na equação original.', '2024-09-01 10:00:00', 245, 20),

('Conectivos como "mas", "porque", "portanto" ajudam a conectar ideias.', '2024-09-01 13:30:00', 246, 14),
('Eles são essenciais para a coesão textual.', '2024-09-01 13:45:00', 246, 18),
('Cada conectivo tem uma função específica na argumentação.', '2024-09-01 14:00:00', 246, 12),

('Enzimas aceleram reações químicas no sistema digestivo.', '2024-09-01 19:30:00', 247, 22),
('Elas ajudam na quebra de macromoléculas em moléculas menores.', '2024-09-01 19:45:00', 247, 16),
('A digestão é fundamental para a absorção de nutrientes.', '2024-09-01 20:00:00', 247, 14),

('A Revolução Francesa começou com a queda da Bastilha.', '2024-09-01 21:30:00', 248, 20),
('Outros eventos importantes incluem a Declaração dos Direitos do Homem.', '2024-09-01 21:45:00', 248, 18),
('O período napoleônico também faz parte da Revolução.', '2024-09-01 22:00:00', 248, 16);

-- 2024-09-02
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a média ponderada?', '2024-09-02 08:00:00', 1, 1, 14),
('A importância dos conectivos na escrita', '2024-09-02 14:00:00', 1, 2, 16),
('O que são as enzimas digestivas?', '2024-09-02 20:00:00', 1, 3, 18),
('Principais fatos da Revolução Francesa', '2024-09-02 22:00:00', 1, 4, 12);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A média ponderada é importante para calcular notas finais.', '2024-09-02 08:30:00', 249, 20),
('Pode-se usar diferentes pesos para cada nota dependendo da importância.', '2024-09-02 08:45:00', 249, 16),
('Sempre calcule a média ponderada antes de definir o peso de cada prova.', '2024-09-02 09:00:00', 249, 14),

('Os conectivos ajudam a formar parágrafos coerentes.', '2024-09-02 14:30:00', 250, 22),
('Cada conectivo tem a função de ligar ideias no texto.', '2024-09-02 14:45:00', 250, 14),
('A coesão textual depende da escolha dos conectivos adequados.', '2024-09-02 15:00:00', 250, 16),

('As enzimas são essenciais para a digestão dos alimentos.', '2024-09-02 20:30:00', 251, 16),
('Elas atuam quebrando as moléculas complexas em mais simples.', '2024-09-02 20:45:00', 251, 14),
('Com as enzimas, o corpo consegue absorver melhor os nutrientes.', '2024-09-02 21:00:00', 251, 22),

('A Revolução Francesa teve início em 1789 com a queda da Bastilha.', '2024-09-02 22:30:00', 252, 20),
('Esse evento é um marco na história das revoluções sociais.', '2024-09-02 22:45:00', 252, 16),
('A Revolução Francesa levou à queda da monarquia e ao surgimento da República.', '2024-09-02 23:00:00', 252, 14);

-- 2024-09-03
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação quadrática?', '2024-09-03 09:00:00', 1, 1, 20),
('O uso de conectivos em frases', '2024-09-03 15:00:00', 1, 2, 14),
('Função das enzimas na digestão de proteínas', '2024-09-03 21:00:00', 1, 3, 16),
('Eventos marcantes da Revolução Francesa', '2024-09-03 23:00:00', 1, 4, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações quadráticas podem ser resolvidas usando a fórmula de Bhaskara.', '2024-09-03 09:30:00', 253, 14),
('Para equações com raízes negativas, a fórmula de Bhaskara dá valores imaginários.', '2024-09-03 09:45:00', 253, 22),
('A fórmula de Bhaskara é essencial para resolver equações quadráticas.', '2024-09-03 10:00:00', 253, 16),

('Conectivos ajudam a unir frases e dar fluidez ao texto.', '2024-09-03 15:30:00', 254, 20),
('Existem diferentes tipos de conectivos para diferentes situações.', '2024-09-03 15:45:00', 254, 18),
('Estude os conectivos mais comuns, como "portanto", "logo", "mas".', '2024-09-03 16:00:00', 254, 22),

('As enzimas são proteínas que ajudam a digerir proteínas complexas.', '2024-09-03 21:30:00', 255, 18),
('Elas atuam nas proteínas quebrando-as em aminoácidos.', '2024-09-03 21:45:00', 255, 14),
('Sem enzimas, o processo de digestão seria muito mais lento.', '2024-09-03 22:00:00', 255, 20),

('A Revolução Francesa foi marcada por várias fases de violência.', '2024-09-03 23:30:00', 256, 22),
('O período do Terror é um dos mais conhecidos da Revolução.', '2024-09-03 23:45:00', 256, 16),
('A Revolução Francesa também gerou o início do Império Napoleônico.', '2024-09-04 00:00:00', 256, 14);

-- 2024-09-04
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um triângulo?', '2024-09-04 10:00:00', 1, 1, 18),
('O que é a digestão de carboidratos?', '2024-09-04 16:00:00', 1, 3, 20);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de um triângulo é base x altura dividido por 2.', '2024-09-04 10:30:00', 257, 14),
('Essa fórmula é válida para qualquer triângulo.', '2024-09-04 10:45:00', 257, 18),

('Carboidratos são quebrados em glicose pelo processo de digestão.', '2024-09-04 16:30:00', 258, 16),
('A glicose é uma importante fonte de energia para o corpo.', '2024-09-04 16:45:00', 258, 22);

-- 2024-09-05
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um número primo?', '2024-09-05 08:00:00', 1, 1, 16),
('A importância dos conectivos na argumentação', '2024-09-05 12:00:00', 1, 2, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um número primo é aquele que só pode ser dividido por 1 e por ele mesmo.', '2024-09-05 08:30:00', 259, 20),
('Exemplo: 2, 3, 5, 7, 11 são números primos.', '2024-09-05 08:45:00', 259, 18),

('Os conectivos são essenciais para organizar e ligar as ideias no texto.', '2024-09-05 12:30:00', 260, 22),
('Sem conectivos, o texto ficaria desorganizado e difícil de entender.', '2024-09-05 12:45:00', 260, 14);

-- 2024-09-06
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de um círculo?', '2024-09-06 11:00:00', 1, 1, 22),
('A importância da digestão no corpo humano', '2024-09-06 15:00:00', 1, 3, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de um círculo é π vezes o raio ao quadrado.', '2024-09-06 11:30:00', 261, 20),
('π é aproximadamente 3.14.', '2024-09-06 11:45:00', 261, 22),

('A digestão é um processo importante para que o corpo absorva nutrientes.', '2024-09-06 15:30:00', 262, 16),
('Sem uma digestão eficiente, o corpo não absorve os nutrientes adequados.', '2024-09-06 15:45:00', 262, 14);

-- 2024-09-07
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver um sistema de equações?', '2024-09-07 09:00:00', 1, 1, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Para resolver um sistema de equações, é necessário isolar uma das variáveis.', '2024-09-07 09:30:00', 263, 16),
('Após isolar, substitua na outra equação para encontrar o valor da outra variável.', '2024-09-07 09:45:00', 263, 14);

-- 2024-09-08
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a média simples?', '2024-09-08 10:00:00', 1, 1, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A média simples é a soma dos valores dividida pela quantidade de valores.', '2024-09-08 10:30:00', 264, 16),
('Exemplo: (2 + 4 + 6)/3 = 4.', '2024-09-08 10:45:00', 264, 14);

-- 2024-09-09
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A importância do estudo da biologia para a medicina', '2024-09-09 09:00:00', 1, 3, 22);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O estudo da biologia é essencial para entender como o corpo humano funciona.', '2024-09-09 09:30:00', 265, 14),
('Com esse conhecimento, podemos desenvolver melhores tratamentos para doenças.', '2024-09-09 09:45:00', 265, 16);

-- 2024-09-10
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como realizar uma boa redação para o vestibular?', '2024-09-10 10:00:00', 1, 2, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A estrutura da redação é fundamental: introdução, desenvolvimento e conclusão.', '2024-09-10 10:30:00', 266, 16);

-- 2024-09-11
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A importância da teoria de Charles Darwin para a biologia', '2024-09-11 15:00:00', 1, 3, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Darwin revolucionou a biologia com a teoria da evolução.', '2024-09-11 15:30:00', 267, 14);

-- 2024-09-12
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são os sistemas de numeração?', '2024-09-12 09:00:00', 1, 1, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Os sistemas de numeração são maneiras de representar os números, como o sistema decimal.', '2024-09-12 09:30:00', 268, 14);

-- 2024-09-13
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver equações do 2º grau?', '2024-09-13 11:00:00', 1, 1, 22);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Para resolver equações do 2º grau, usamos a fórmula de Bhaskara.', '2024-09-13 11:30:00', 269, 16);

-- 2024-09-14
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A relação entre genética e doenças hereditárias', '2024-09-14 13:00:00', 1, 3, 16);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A genética desempenha um papel crucial no desenvolvimento de doenças hereditárias.', '2024-09-14 13:30:00', 270, 18);

-- 2024-09-15
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como organizar uma pesquisa científica?', '2024-09-15 10:00:00', 1, 2, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Uma pesquisa científica precisa de um problema de pesquisa, revisão da literatura e metodologia clara.', '2024-09-15 10:30:00', 271, 20);

-- 2024-09-16
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A importância dos ácidos graxos para o corpo', '2024-09-16 12:00:00', 1, 3, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Os ácidos graxos são essenciais para a saúde cardiovascular e celular.', '2024-09-16 12:30:00', 272, 22);

-- 2024-09-17
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como estudar para a prova de física?', '2024-09-17 09:00:00', 1, 1, 16);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Estude os conceitos fundamentais e pratique muitos exercícios de diferentes temas.', '2024-09-17 09:30:00', 273, 14);

-- 2024-09-18
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A importância do sono para o desempenho acadêmico', '2024-09-18 11:00:00', 1, 2, 22);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Dormir bem ajuda a consolidar a memória e melhora o desempenho cognitivo.', '2024-09-18 11:30:00', 274, 16);

-- 2024-09-19
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como fazer uma boa revisão antes da prova?', '2024-09-19 13:00:00', 1, 1, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Faça uma revisão das matérias que você tem mais dificuldade e resolva provas anteriores.', '2024-09-19 13:30:00', 275, 20);

-- 2024-09-20
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Dicas para melhorar a redação no vestibular', '2024-09-20 09:00:00', 1, 2, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Tenha clareza nas ideias, use conectivos e revise o texto.', '2024-09-20 09:30:00', 276, 16);

-- 2024-09-21
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O papel das enzimas na digestão', '2024-09-21 11:00:00', 1, 3, 20);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('As enzimas facilitam a quebra dos alimentos em substâncias mais simples para absorção.', '2024-09-21 11:30:00', 277, 14);

-- 2024-09-22
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A importância da leitura para o desenvolvimento cognitivo', '2024-09-22 13:00:00', 1, 1, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A leitura amplia o vocabulário e melhora a compreensão e interpretação de textos.', '2024-09-22 13:30:00', 278, 22);

-- 2024-09-23
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como funciona a fotossíntese?', '2024-09-23 10:00:00', 1, 3, 16);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fotossíntese é o processo pelo qual as plantas produzem seu próprio alimento utilizando luz solar, dióxido de carbono e água.', '2024-09-23 10:30:00', 279, 14);

-- 2024-09-24
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a economia de mercado?', '2024-09-24 09:00:00', 1, 2, 22);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Na economia de mercado, os preços e a produção são definidos pelo mercado, sem intervenção do governo.', '2024-09-24 09:30:00', 280, 16);

-- 2024-09-25
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('A teoria da relatividade de Einstein', '2024-09-25 14:00:00', 1, 3, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A teoria da relatividade de Einstein revolucionou a física ao explicar a gravidade e o movimento de objetos no espaço-tempo.', '2024-09-25 14:30:00', 281, 22);

-- 2024-09-26
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a velocidade média?', '2024-09-26 11:00:00', 1, 1, 14);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A velocidade média é calculada dividindo a distância total percorrida pelo tempo total gasto.', '2024-09-26 11:30:00', 282, 16);

-- 2024-09-27
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O papel do sistema imunológico no combate a doenças', '2024-09-27 13:00:00', 1, 3, 20);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O sistema imunológico é responsável por identificar e destruir agentes patogênicos no corpo.', '2024-09-27 13:30:00', 283, 14);

-- 2024-09-28
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como funciona o ciclo da água?', '2024-09-28 10:00:00', 1, 3, 16);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O ciclo da água envolve a evaporação da água dos oceanos, a condensação formando nuvens e a precipitação de volta à terra.', '2024-09-28 10:30:00', 284, 14);

-- 2024-09-29
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a área de figuras geométricas?', '2024-09-29 09:00:00', 1, 1, 22);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A área de uma figura geométrica é calculada de acordo com suas propriedades, como base, altura ou raio, dependendo da forma.', '2024-09-29 09:30:00', 285, 20);

-- 2024-09-30
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a filosofia?', '2024-09-30 11:00:00', 1, 2, 18);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A filosofia busca compreender questões sobre a existência, moralidade e conhecimento.', '2024-09-30 11:30:00', 286, 22);

-- Publicações e comentários para as datas de login
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação do 2º grau?', '2024-10-01 02:30:00', 1, 1, 22),
('A importância de estudar gramática para escrever bem', '2024-10-01 08:00:00', 1, 2, 18),
('Como as plantas realizam a fotossíntese?', '2024-10-01 14:30:00', 1, 3, 20),
('O que causou a Revolução Francesa?', '2024-10-01 20:00:00', 1, 4, 16),

('O que é a álgebra linear?', '2024-10-02 03:30:00', 1, 1, 16),
('As principais regras de concordância verbal', '2024-10-02 09:00:00', 1, 2, 22),
('O que são os tipos de células no corpo humano?', '2024-10-02 15:30:00', 1, 3, 20),
('A importância da Revolução Industrial', '2024-10-02 21:00:00', 1, 4, 14),

('Como resolver sistemas lineares?', '2024-10-03 04:30:00', 1, 1, 18),
('O uso dos pronomes pessoais na comunicação', '2024-10-03 10:00:00', 1, 2, 14),
('Como o sistema digestivo funciona?', '2024-10-03 16:00:00', 1, 3, 20),
('Quais foram as principais batalhas da Segunda Guerra Mundial?', '2024-10-03 22:30:00', 1, 4, 16),

('O conceito de derivada em cálculo', '2024-10-04 05:00:00', 1, 1, 22),
('A função do sujeito na oração', '2024-10-04 11:30:00', 1, 2, 14),
('O ciclo do carbono e seu impacto no meio ambiente', '2024-10-04 17:00:00', 1, 3, 18),
('O impacto das grandes navegações', '2024-10-04 23:30:00', 1, 4, 20);

-- OUTUBRO
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações quadráticas podem ser resolvidas pela fórmula de Bhaskara.', '2024-10-01 03:00:00', 286, 4),
('A gramática é essencial para evitar ambiguidades na comunicação.', '2024-10-01 08:30:00', 287, 21),
('A fotossíntese é fundamental para a produção de oxigênio na Terra.', '2024-10-01 15:00:00', 288, 4),
('A queda da Bastilha marcou o início de um novo capítulo na história da França.', '2024-10-01 20:30:00', 289, 2),

('A álgebra linear é uma das ferramentas mais poderosas em matemática aplicada.', '2024-10-02 04:00:00', 290, 8),
('Na língua portuguesa, é fundamental saber quando usar os pronomes corretamente.', '2024-10-02 09:30:00', 291, 5),
('As células são os blocos fundamentais de todos os seres vivos.', '2024-10-02 16:30:00', 292, 2),
('A Revolução Industrial mudou profundamente a sociedade e a economia global.', '2024-10-02 21:30:00', 293, 3),

('Sistemas lineares podem ser resolvidos usando o método da substituição ou da matriz inversa.', '2024-10-03 05:00:00', 294, 1),
('O uso correto de pronomes evita erros comuns na fala e na escrita.', '2024-10-03 10:30:00', 295, 20),
('O sistema digestivo é responsável por transformar os alimentos em nutrientes.', '2024-10-03 17:00:00', 296, 4),
('As batalhas de Stalingrado e Normandia foram decisivas para o fim da Segunda Guerra.', '2024-10-03 23:00:00', 297, 3),

('Derivadas são usadas para calcular taxas de variação e otimizar funções.', '2024-10-04 06:00:00', 298, 2),
('O sujeito da oração é o termo que indica quem ou o que realiza a ação.', '2024-10-04 12:00:00', 299, 5),
('O ciclo do carbono envolve a troca de carbono entre os organismos e o ambiente.', '2024-10-04 18:30:00', 300, 1),
('As grandes navegações ajudaram a expandir o comércio global e a exploração de novas terras.', '2024-10-04 23:45:00', 301, 3);

-- Publicações de NOVEMBRO
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação linear de forma simples', '2024-11-01 08:00:00', 1, 1, 25),
('Técnicas para entender funções exponenciais', '2024-11-01 14:00:00', 1, 1, 20),
('Dúvidas comuns sobre a Revolução Industrial', '2024-11-01 16:00:00', 1, 4, 18),
('A importância da Gramática na escrita acadêmica', '2024-11-02 10:00:00', 1, 2, 22),
('Desafios da sociedade contemporânea', '2024-11-02 18:00:00', 1, 7, 15),
('Como estudar para as provas de física', '2024-11-03 09:00:00', 1, 5, 19),
('Estratégias para melhorar seu desempenho nas redações', '2024-11-04 11:00:00', 1, 2, 22),
('Por que estudar Filosofia pode ser interessante', '2024-11-04 20:00:00', 1, 10, 25),
('Como melhorar a interpretação de textos', '2024-11-05 12:00:00', 1, 2, 20),
('A influência da globalização nas economias', '2024-11-06 13:00:00', 1, 8, 22),
('A revolução digital e o impacto no mercado de trabalho', '2024-11-07 15:00:00', 1, 8, 19),
('Estudos sobre a Segunda Guerra Mundial', '2024-11-08 16:00:00', 1, 4, 16),
('Como as redes sociais influenciam nossa comunicação', '2024-11-09 18:00:00', 1, 8, 22),
('Metodologias de ensino na educação básica', '2024-11-10 10:00:00', 1, 8, 21),
('Principais teorias sobre o comportamento humano', '2024-11-11 14:00:00', 1, 7, 20),
('O papel da mídia nas democracias', '2024-11-12 13:00:00', 1, 8, 18),
('Dicas para escrever um artigo científico de sucesso', '2024-11-13 17:00:00', 1, 2, 22),
('A importância da leitura crítica de livros', '2024-11-14 11:00:00', 1, 2, 19),
('Os principais conceitos de economia para vestibular', '2024-11-15 09:00:00', 1, 8, 25),
('Não sei por que esse caralho de ENEM existe', '2024-11-16 10:00:00', 1, 2, 20),
('Odeio essa merda de matéria', '2024-11-17 12:00:00', 1, 2, 18),

('Matemática aplicada na resolução de problemas cotidianos', '2024-11-18 12:00:00', 1, 1, 19),
('Como a filosofia pode ajudar a entender a ética', '2024-11-19 10:00:00', 1, 10, 20),
('O impacto das novas tecnologias no ensino', '2024-11-20 16:00:00', 1, 8, 18),
('Como se preparar para provas de matemática', '2024-11-21 08:00:00', 1, 1, 21),
('História do Brasil: do Império à República', '2024-11-22 14:00:00', 1, 4, 22),
('Como estudar para as provas de física', '2024-11-23 11:00:00', 1, 5, 20),
('O conceito de justiça em diferentes culturas', '2024-11-24 13:00:00', 1, 7, 19),
('A importância do pensamento crítico no mundo atual', '2024-11-25 16:00:00', 1, 7, 18),
('Como organizar seus estudos para o vestibular', '2024-11-26 08:00:00', 1, 2, 16),
('O papel da ética nas decisões políticas', '2024-11-27 19:00:00', 1, 10, 22),
('Entendendo a física quântica para iniciantes', '2024-11-28 17:00:00', 1, 5, 25),
('Como a arte reflete a sociedade', '2024-11-29 14:00:00', 1, 10, 16),
('Os desafios da preservação ambiental', '2024-11-30 13:00:00', 1, 8, 25);

-- Comentários nas Publicações
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Muito bom! A explicação ficou bem clara.', '2024-11-01 10:00:00', 302, 22),
('Eu tinha dúvida sobre isso, muito útil.', '2024-11-01 17:00:00', 302, 25),
('Interessante! Vou aplicar isso nos meus estudos.', '2024-11-02 14:00:00', 303, 20),
('Muito relevante, vou compartilhar com meus amigos.', '2024-11-03 16:00:00', 304, 19),
('Excelente postagem, me ajudou muito.', '2024-11-04 10:00:00', 305, 22),
('Ótimo conteúdo, gostei bastante.', '2024-11-05 14:00:00', 306, 18),
('Achei a ideia interessante, vou tentar', '2024-11-06 12:00:00', 307, 25),
('Esse tipo de conteúdo é muito útil para vestibulandos.', '2024-11-07 09:00:00', 308, 16),
('Concordo com os pontos levantados, excelente postagem.', '2024-11-08 15:00:00', 309, 20),
('Tema muito relevante para os dias de hoje.', '2024-11-09 13:00:00', 310, 19),
('Gostei do ponto sobre a globalização, tem tudo a ver com o mercado atual.', '2024-11-10 20:00:00', 311, 16),
('Adorei o conteúdo, me ajudou bastante nas minhas dúvidas.', '2024-11-11 11:00:00', 312, 22),
('Muito boa explicação sobre a Segunda Guerra.', '2024-11-12 17:00:00', 313, 21),
('O tema é realmente muito atual, muito bom!', '2024-11-13 13:00:00', 314, 18),
('Ótima postagem sobre o impacto da mídia!', '2024-11-14 14:00:00', 315, 20),
('Tema muito pertinente para os tempos atuais.', '2024-11-15 09:00:00', 316, 22),
('Achei muito interessante, explicação muito clara.', '2024-11-16 15:00:00', 317, 16),
('Excelente, vou estudar mais sobre isso.', '2024-11-17 17:00:00', 318, 19),
('Muito bom, vou tentar aplicar essas técnicas.', '2024-11-18 12:00:00', 319, 18),
('Gostei da explicação, ficou bem didática.', '2024-11-19 10:00:00', 320, 22),
('Ótimo artigo sobre justiça, bem explicado.', '2024-11-20 14:00:00', 321, 25),
('Muito bom, a física quântica é um tema que interessa muito.', '2024-11-21 13:00:00', 322, 18),
('Que o ENEM vá a merda!', '2024-11-22 09:00:00', 323, 19),
('Péssimo conteúdo, nunca vou usar essa merda', '2024-11-23 12:00:00', 324, 20),
('Bem inútil essa publicação!', '2024-11-24 15:00:00', 325, 25);


-- DEZEMBRO
-- Publicação 1
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Como resolver um sistema de equações linear?', '2024-12-01 00:00:00', 1, 1, 20);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('A solução é dada pelo método da substituição ou da comparação.', '2024-12-01 00:30:00', 326, 18),
       ('Outro método muito eficaz é o da matriz inversa.', '2024-12-01 01:00:00', 326, 22);

-- Publicação 2
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('A importância da utilização de vírgulas em frases complexas.', '2024-12-01 06:00:00', 1, 2, 18);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('Vírgulas ajudam a separar orações e clarificar o significado.', '2024-12-01 06:30:00', 327, 22),
       ('Elas são essenciais para a fluidez e compreensão do texto.', '2024-12-01 07:00:00', 327, 25);

-- Publicação 1
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Quais são os principais elementos da célula animal?', '2024-12-02 01:00:00', 1, 3, 19);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('As células animais possuem núcleos, mitocôndrias e ribossomos.', '2024-12-02 01:30:00', 328, 22),
       ('Cada organela tem uma função específica na célula.', '2024-12-02 02:00:00', 328, 16);

-- Publicação 2
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('O que foi a Revolução Industrial?', '2024-12-02 07:00:00', 1, 4, 22);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('A Revolução Industrial começou no século XVIII e transformou a produção.', '2024-12-02 07:30:00', 329, 20),
       ('Foi um período de grande inovação tecnológica e mudanças sociais.', '2024-12-02 08:00:00', 329, 20);

-- Publicação 1
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Como funciona a fotossíntese nas plantas?', '2024-12-03 02:00:00', 1, 3, 18);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('A fotossíntese ocorre nos cloroplastos das células vegetais.', '2024-12-03 02:30:00', 330, 20),
       ('Durante o processo, as plantas transformam a luz solar em energia.', '2024-12-03 03:00:00', 330, 22);

-- Publicação 2
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Qual a teoria da relatividade de Einstein?', '2024-12-03 08:00:00', 1, 5, 20);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('A teoria da relatividade mostra como o tempo e o espaço são afetados pela gravidade.', '2024-12-03 08:30:00', 331, 25),
       ('Ela revolucionou nossa compreensão da física e do universo.', '2024-12-03 09:00:00', 331, 22);

-- Publicação 1
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Como as reações químicas afetam o nosso corpo?', '2024-12-04 03:00:00', 1, 6, 19);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('Reações químicas acontecem continuamente no nosso corpo, como na digestão.', '2024-12-04 03:30:00', 332, 22),
       ('Elas também estão presentes na respiração celular e na produção de energia.', '2024-12-04 04:00:00', 332, 20);

-- Publicação 2
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('O que é o conceito de cidadania?', '2024-12-04 09:00:00', 1, 7, 22);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('Cidadania envolve o direito e o dever de participar da vida política.', '2024-12-04 09:30:00', 333, 19),
       ('Também significa ser parte de uma comunidade com direitos iguais.', '2024-12-04 10:00:00', 333, 20);

-- Publicação 1
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Como resolver um problema de física envolvendo movimento uniforme?', '2024-12-05 00:30:00', 1, 5, 20);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('Para resolver, basta usar a fórmula distância = velocidade x tempo.', '2024-12-05 01:00:00', 334, 18),
       ('Lembre-se de manter as unidades consistentes ao aplicar a fórmula.', '2024-12-05 01:30:00', 334, 25);

-- Publicação 2
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) 
VALUES ('Qual a origem do ser humano segundo a teoria da evolução?', '2024-12-05 06:30:00', 1, 3, 18);

-- Comentários
INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) 
VALUES ('A teoria de Darwin afirma que a evolução ocorre por seleção natural.', '2024-12-05 07:00:00', 335, 22),
       ('Essa teoria revolucionou a biologia e nossa compreensão da vida.', '2024-12-05 07:30:00', 335, 20);


-- Reações para o dia 1 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),

(1, 2, 1),
(1, 2, 3),
(1, 2, 4),

(1, 3, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),

(1, 2, 1),
(1, 2, 3),
(1, 2, 4),

(1, 3, 5);

-- Reações para o dia 2 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 4, 1),
(1, 4, 2),
(1, 4, 3),
(1, 4, 4),

(1, 5, 2),
(1, 5, 5),

(1, 6, 3),
(1, 6, 4),
(1, 6, 5);


INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 4, 1),
(1, 4, 2),

(1, 5, 2),
(1, 5, 5),

(1, 6, 3),
(1, 6, 5);

-- Reações para o dia 3 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 7, 1),
(1, 7, 2),

(1, 8, 1),
(1, 8, 3),
(1, 8, 5),

(1, 9, 4),
(1, 9, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 7, 1);

-- Reações para o dia 4 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 10, 1),
(1, 10, 2),
(1, 10, 4),

(1, 11, 2),
(1, 11, 5),

(1, 12, 1),
(1, 12, 3),
(1, 12, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 10, 1),
(1, 10, 2),
(1, 10, 4),

(1, 11, 2),
(1, 11, 5),

(1, 12, 1),
(1, 12, 3),
(1, 12, 5);

-- Reações para o dia 5 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 13, 1),
(1, 13, 3),
(1, 13, 4),
(1, 13, 5),

(1, 14, 2),
(1, 14, 4),

(1, 15, 2),
(1, 15, 3),
(1, 15, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 13, 10),
(1, 13, 12),
(1, 13, 8),
(1, 13, 9),

(1, 14, 6),
(1, 14, 10),

(1, 15, 20),
(1, 15, 14),
(1, 15, 22);

-- Reações para o dia 6 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 16, 1),
(1, 16, 2),
(1, 16, 3),
(1, 16, 5),

(1, 17, 2),
(1, 17, 4),

(1, 18, 3),
(1, 18, 4),
(1, 18, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 16, 5),
(1, 16, 9),

(1, 17, 20),
(1, 17, 7),

(1, 18, 15),
(1, 18, 16),
(1, 18, 17);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 19, 9),
(1, 19, 2),
(1, 19, 4),

(1, 20, 7),
(1, 20, 5),

(1, 21, 25),
(1, 21, 2),
(1, 21, 3);

INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 19, 1),
(1, 19, 2),
(1, 19, 4),

(1, 20, 2),
(1, 20, 3),
(1, 20, 5),

(1, 21, 1),
(1, 21, 3),
(1, 21, 5);

-- Reações para o dia 8 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 22, 2),
(1, 22, 3),
(1, 22, 4),

(1, 23, 1),
(1, 23, 4),
(1, 23, 5),

(1, 24, 2),
(1, 24, 5);

-- Reações para o dia 9 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 25, 1),
(1, 25, 2),
(1, 25, 4),

(1, 26, 2),
(1, 26, 4),
(1, 26, 5),

(1, 27, 1),
(1, 27, 3),
(1, 27, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 25, 1),
(1, 25, 2),
(1, 25, 4),

(1, 26, 2),
(1, 26, 4),
(1, 26, 5),

(1, 27, 1),
(1, 27, 3),
(1, 27, 5);

-- Reações para o dia 10 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 28, 2),
(1, 28, 4),
(1, 28, 5),

(1, 29, 1),
(1, 29, 2),
(1, 29, 3),
(1, 29, 4),

(1, 30, 1),
(1, 30, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 28, 2),
(1, 28, 7),
(1, 28, 5),

(1, 29, 15),
(1, 29, 20),

(1, 30, 10),
(1, 30, 9);

-- Reações para o dia 11 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 31, 2),
(1, 31, 3),

(1, 32, 1),
(1, 32, 2),
(1, 32, 4),

(1, 33, 1),
(1, 33, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 31, 18),
(1, 31, 24),

(1, 32, 14),
(1, 32, 7),
(1, 32, 12),

(1, 33, 25),
(1, 33, 6);

-- Reações para o dia 12 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 34, 1),
(1, 34, 3),

(1, 35, 2),
(1, 35, 4),
(1, 35, 5),

(1, 36, 2),
(1, 36, 3),
(1, 36, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 34, 20),
(1, 34, 11),

(1, 35, 9),
(1, 35, 23),
(1, 35, 16),

(1, 36, 3),
(1, 36, 10),
(1, 36, 22);

-- Reações para o dia 13 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 37, 1),
(1, 37, 2),
(1, 37, 4),

(1, 38, 1),
(1, 38, 3),

(1, 39, 2),
(1, 39, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 37, 8),
(1, 37, 21),
(1, 37, 15),

(1, 39, 5),
(1, 39, 17);

-- Reações para o dia 14 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 40, 1),
(1, 40, 3),

(1, 41, 2),
(1, 41, 4),
(1, 41, 5),

(1, 42, 1),
(1, 42, 3);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 40, 2),
(1, 40, 4),

(1, 41, 1),
(1, 41, 22),
(1, 41, 16);

-- Reações para o dia 15 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 43, 1),
(1, 43, 2),
(1, 43, 4),

(1, 44, 2),
(1, 44, 3),
(1, 44, 5),

(1, 45, 1),
(1, 45, 3);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 43, 9),
(1, 43, 12),
(1, 43, 25),

(1, 44, 18),
(1, 44, 23),
(1, 44, 15),

(1, 45, 5),
(1, 45, 17);

-- Reações para o dia 16 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 46, 2),
(1, 46, 4),

(1, 47, 1),
(1, 47, 3),
(1, 47, 5),

(1, 48, 1),
(1, 48, 2),
(1, 48, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 46, 21),
(1, 46, 4),

(1, 47, 7),
(1, 47, 24),
(1, 47, 13),

(1, 48, 19),
(1, 48, 3),
(1, 48, 10);

-- Reações para o dia 17 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 49, 2),
(1, 49, 4),
(1, 49, 5),

(1, 50, 1),
(1, 50, 2),

(1, 51, 1),
(1, 51, 4);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 49, 11),
(1, 49, 6),
(1, 49, 2),

(1, 50, 20),
(1, 50, 9),

(1, 51, 25);

-- Reações para o dia 18 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 52, 2),
(1, 52, 3),

(1, 53, 1),
(1, 53, 4),
(1, 53, 5),

(1, 54, 2),
(1, 54, 3);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 52, 15),
(1, 52, 5),

(1, 53, 8),
(1, 53, 24),
(1, 53, 18),
(1, 53, 19),
(1, 53, 5),
(1, 53, 4),

(1, 54, 6),
(1, 54, 11);

-- Reações para o dia 19 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 55, 1),
(1, 55, 4),

(1, 56, 2),
(1, 56, 3),
(1, 56, 5),

(1, 57, 1),
(1, 57, 3);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 55, 22),
(1, 55, 7),

(1, 56, 1),
(1, 56, 13),
(1, 56, 3),

(1, 57, 17),
(1, 57, 25);

-- Reações para o dia 20 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 58, 1),
(1, 58, 2),

(1, 59, 2),
(1, 59, 4),
(1, 59, 5),

(1, 60, 1),
(1, 60, 3);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 58, 11),
(1, 58, 19),

(1, 59, 6),
(1, 59, 23),
(1, 59, 15),

(1, 60, 10),
(1, 60, 14);

-- Reações para o dia 21 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 61, 1),
(1, 61, 2),

(1, 62, 1),
(1, 62, 4),
(1, 62, 5),

(1, 63, 2),
(1, 63, 4);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 61, 3),
(1, 61, 17),

(1, 62, 7),
(1, 62, 4),

(1, 63, 2),
(1, 63, 18);

-- Reações para o dia 22 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 64, 1),
(1, 64, 3),

(1, 65, 2),
(1, 65, 4),

(1, 66, 1),
(1, 66, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 64, 9),
(1, 64, 25),

(1, 66, 8),
(1, 66, 16);

-- Reações para o dia 23 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 67, 1),
(1, 67, 2),
(1, 67, 4),

(1, 68, 2),
(1, 68, 3),

(1, 69, 1),
(1, 69, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 67, 22),
(1, 67, 14),

(1, 68, 24),

(1, 69, 11),
(1, 69, 12);

-- Reações para o dia 24 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 70, 2),
(1, 70, 4),

(1, 71, 1),
(1, 71, 2),
(1, 71, 3),

(1, 72, 1),
(1, 72, 4);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 70, 10),
(1, 70, 18),

(1, 71, 4),
(1, 71, 25),
(1, 71, 1),

(1, 72, 7),
(1, 72, 20);

-- Reações para o dia 25 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 73, 1),
(1, 73, 2),

(1, 74, 3),
(1, 74, 5),

(1, 75, 1),
(1, 75, 3);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 74, 16),
(1, 74, 19),

(1, 75, 8),
(1, 75, 13);

-- Reações para o dia 26 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 76, 2),
(1, 76, 4),

(1, 77, 1),
(1, 77, 3),
(1, 77, 5),

(1, 78, 2),
(1, 78, 4);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 76, 14),
(1, 76, 2),

(1, 77, 23),
(1, 77, 15),
(1, 77, 17),

(1, 78, 24),
(1, 78, 12);

-- Reações para o dia 27 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 79, 1),
(1, 79, 3),

(1, 80, 2),
(1, 80, 4),

(1, 81, 1),
(1, 81, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 79, 5),

(1, 80, 8),
(1, 80, 1),

(1, 81, 6),
(1, 81, 22);

-- Reações para o dia 28 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 82, 2),
(1, 82, 4),

(1, 83, 1),
(1, 83, 3),
(1, 83, 5),

(1, 84, 2),
(1, 84, 4);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 82, 9),
(1, 82, 4),

(1, 83, 2),
(1, 83, 12),
(1, 83, 11),

(1, 84, 20),
(1, 84, 7);

-- Reações para o dia 29 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 85, 1),
(1, 85, 2),

(1, 86, 1),
(1, 86, 3),
(1, 86, 4),

(1, 87, 2),
(1, 87, 5);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 85, 15),
(1, 85, 19),

(1, 86, 10),
(1, 86, 24),
(1, 86, 18),

(1, 87, 5),
(1, 87, 17);

-- Reações para o dia 30 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 88, 1),
(1, 88, 2);

INSERT INTO reacao (tipo_reacao_id, comentario_id, usuario_id) VALUES
(1, 88, 3),
(1, 88, 9);


-- Denuncias
-- Publicacoes
INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (1, 2);

INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (50, 10);

INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (20, 5);

INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (10, 9);

INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (15, 4);

INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (355, 20);

INSERT INTO denuncia (publicacao_id, usuario_id)
VALUES (356, 18);

-- Comentarios
INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (1, 1);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (2, 2);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (3, 3);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (4, 4);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (5, 5);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (567, 19);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (568, 20);

INSERT INTO denuncia (comentario_id, usuario_id)
VALUES (569, 25);

INSERT INTO registro_login (registro_login, usuario_id)
VALUES 
	('2024-01-01 00:15:00', 10),
	('2024-01-01 04:30:00', 15),
	('2024-01-01 08:45:00', 20),
	('2024-01-01 12:00:00', 25),
	('2024-01-01 18:30:00', 18),

	('2024-01-02 01:00:00', 5),
	('2024-01-02 07:15:00', 12),
	('2024-01-02 10:30:00', 7),
	('2024-01-02 14:45:00', 13),
	('2024-01-02 17:00:00', 9),

	('2024-01-03 02:10:00', 14),
	('2024-01-03 06:25:00', 22),
	('2024-01-03 09:40:00', 18),
	('2024-01-03 13:55:00', 11),
	('2024-01-03 19:00:00', 8),

	('2024-01-04 00:45:00', 16),
	('2024-01-04 05:20:00', 19),
	('2024-01-04 08:35:00', 12),
	('2024-01-04 11:50:00', 14),
	('2024-01-04 17:30:00', 23),

	('2024-01-05 01:10:00', 20),
	('2024-01-05 06:00:00', 8),
	('2024-01-05 09:25:00', 16),
	('2024-01-05 13:40:00', 14),
	('2024-01-05 16:55:00', 18),

	('2024-01-06 02:30:00', 5),
	('2024-01-06 07:10:00', 13),
	('2024-01-06 10:20:00', 17),
	('2024-01-06 14:35:00', 9),
	('2024-01-06 18:00:00', 6),

	('2024-01-07 00:05:00', 22),
	('2024-01-07 05:30:00', 18),
	('2024-01-07 08:00:00', 14),
	('2024-01-07 12:15:00', 11),
	('2024-01-07 16:50:00', 19),

	('2024-01-08 01:25:00', 25),
	('2024-01-08 06:10:00', 12),
	('2024-01-08 09:45:00', 7),
	('2024-01-08 13:00:00', 16),
	('2024-01-08 17:30:00', 13),

	('2024-01-09 00:40:00', 11),
	('2024-01-09 04:55:00', 8),
	('2024-01-09 09:15:00', 18),
	('2024-01-09 13:25:00', 20),
	('2024-01-09 16:35:00', 6),
    
	('2024-01-11 02:05:00', 15),
	('2024-01-11 06:30:00', 22),
	('2024-01-11 10:00:00', 9),
	('2024-01-11 12:45:00', 17),
	('2024-01-11 18:30:00', 12),

	('2024-01-13 01:00:00', 20),
	('2024-01-13 04:10:00', 13),
	('2024-01-13 08:45:00', 10),
	('2024-01-13 11:00:00', 19),
	('2024-01-13 16:20:00', 25),

	('2024-01-15 00:30:00', 14),
	('2024-01-15 06:00:00', 8),
	('2024-01-15 09:30:00', 16),
	('2024-01-15 13:45:00', 18),
	('2024-01-15 17:25:00', 10),

	('2024-01-17 02:15:00', 9),
	('2024-01-17 07:40:00', 12),
	('2024-01-17 11:10:00', 17),
	('2024-01-17 14:00:00', 13),
	('2024-01-17 19:00:00', 8),

	('2024-01-19 01:20:00', 22),
	('2024-01-19 05:50:00', 18),
	('2024-01-19 09:00:00', 11),
	('2024-01-19 12:30:00', 14),
	('2024-01-19 16:45:00', 9),

	('2024-01-21 00:55:00', 6),
	('2024-01-21 04:35:00', 19),
	('2024-01-21 08:10:00', 16),
	('2024-01-21 11:20:00', 12),
	('2024-01-21 17:30:00', 23),

	('2024-01-23 01:30:00', 8),
	('2024-01-23 06:00:00', 14),
	('2024-01-23 09:40:00', 19),
	('2024-01-23 13:10:00', 7),
	('2024-01-23 16:55:00', 25),

	('2024-01-25 02:00:00', 11),
	('2024-01-25 06:40:00', 16),
	('2024-01-25 10:10:00', 14),
	('2024-01-25 13:50:00', 20),
	('2024-01-25 18:30:00', 18),

	('2024-01-27 00:25:00', 9),
	('2024-01-27 05:10:00', 12),
	('2024-01-27 09:30:00', 14),
	('2024-01-27 12:55:00', 7),
	('2024-01-27 17:20:00', 18),

	('2024-01-29 01:15:00', 16),
	('2024-01-29 04:25:00', 22),
	('2024-01-29 08:50:00', 19),
	('2024-01-29 12:30:00', 25),
	('2024-01-29 16:40:00', 10),

	('2024-01-31 02:10:00', 14),
	('2024-01-31 05:30:00', 18),
	('2024-01-31 09:00:00', 7),
	('2024-01-31 12:40:00', 19),
	('2024-01-31 17:20:00', 15),
    
    ('2024-02-01 00:15:00', 10),
	('2024-02-01 04:50:00', 8),
	('2024-02-01 09:25:00', 12),
	('2024-02-01 13:40:00', 6),
	('2024-02-01 17:55:00', 18),

	('2024-02-03 01:05:00', 14),
	('2024-02-03 04:20:00', 16),
	('2024-02-03 09:10:00', 11),
	('2024-02-03 12:50:00', 9),
	('2024-02-03 18:30:00', 22),

	('2024-02-05 00:45:00', 19),
	('2024-02-05 05:00:00', 13),
	('2024-02-05 09:20:00', 8),
	('2024-02-05 12:30:00', 15),
	('2024-02-05 17:10:00', 7),

	('2024-02-07 02:30:00', 9),
	('2024-02-07 06:00:00', 18),
	('2024-02-07 10:15:00', 14),
	('2024-02-07 13:45:00', 11),
	('2024-02-07 17:30:00', 16),

	('2024-02-09 00:25:00', 12),
	('2024-02-09 04:40:00', 19),
	('2024-02-09 08:10:00', 7),
	('2024-02-09 12:20:00', 13),
	('2024-02-09 15:30:00', 20),

	('2024-02-11 02:10:00', 16),
	('2024-02-11 06:25:00', 14),
	('2024-02-11 09:40:00', 11),
	('2024-02-11 12:55:00', 18),
	('2024-02-11 17:05:00', 9),

	('2024-02-13 00:55:00', 7),
	('2024-02-13 05:15:00', 16),
	('2024-02-13 09:25:00', 19),
	('2024-02-13 13:10:00', 13),
	('2024-02-13 18:00:00', 25),

	('2024-02-15 01:00:00', 9),
	('2024-02-15 06:40:00', 12),
	('2024-02-15 10:05:00', 18),
	('2024-02-15 13:35:00', 14),
	('2024-02-15 17:10:00', 20),

	('2024-02-17 00:50:00', 11),
	('2024-02-17 04:25:00', 13),
	('2024-02-17 08:40:00', 15),
	('2024-02-17 12:15:00', 16),
	('2024-02-17 17:00:00', 22),

	('2024-02-19 01:20:00', 10),
	('2024-02-19 06:00:00', 12),
	('2024-02-19 10:25:00', 8),
	('2024-02-19 13:35:00', 14),
	('2024-02-19 17:30:00', 19),

	('2024-02-21 00:45:00', 16),
	('2024-02-21 05:05:00', 7),
	('2024-02-21 09:00:00', 18),
	('2024-02-21 13:40:00', 14),
	('2024-02-21 18:15:00', 20),

	('2024-02-23 02:10:00', 11),
	('2024-02-23 06:35:00', 14),
	('2024-02-23 09:50:00', 22),
	('2024-02-23 13:00:00', 16),
	('2024-02-23 18:40:00', 19),

	('2024-02-25 01:10:00', 18),
	('2024-02-25 05:00:00', 12),
	('2024-02-25 08:20:00', 7),
	('2024-02-25 12:30:00', 9),
	('2024-02-25 17:10:00', 15),

	('2024-02-27 00:35:00', 16),
	('2024-02-27 05:40:00', 11),
	('2024-02-27 09:10:00', 14),
	('2024-02-27 12:45:00', 19),
	('2024-02-27 17:30:00', 10),

	('2024-02-29 01:00:00', 7),
	('2024-02-29 05:20:00', 13),
	('2024-02-29 09:00:00', 22),
	('2024-02-29 12:50:00', 18),
	('2024-02-29 17:15:00', 16),
    
    ('2024-03-01 00:30:00', 12),
	('2024-03-01 05:15:00', 18),
	('2024-03-01 09:00:00', 10),
	('2024-03-01 13:20:00', 14),

	('2024-03-03 01:10:00', 8),
	('2024-03-03 04:30:00', 19),
	('2024-03-03 09:40:00', 22),
	('2024-03-03 12:55:00', 12),

	('2024-03-05 00:25:00', 16),
	('2024-03-05 04:50:00', 20),
	('2024-03-05 09:05:00', 18),
	('2024-03-05 13:35:00', 13),

	('2024-03-07 01:00:00', 19),
	('2024-03-07 04:40:00', 12),
	('2024-03-07 09:15:00', 14),
	('2024-03-07 13:00:00', 16),

	('2024-03-09 02:00:00', 10),
	('2024-03-09 06:10:00', 15),
	('2024-03-09 09:40:00', 12),
	('2024-03-09 13:20:00', 8),

	('2024-03-11 01:15:00', 14),
	('2024-03-11 04:45:00', 18),
	('2024-03-11 09:05:00', 11),
	('2024-03-11 13:30:00', 16),

	('2024-03-13 00:45:00', 22),
	('2024-03-13 05:10:00', 16),
	('2024-03-13 09:30:00', 9),
	('2024-03-13 12:55:00', 13),

	('2024-03-15 01:10:00', 14),
	('2024-03-15 05:00:00', 16),
	('2024-03-15 09:45:00', 13),
	('2024-03-15 13:00:00', 8),

	('2024-03-17 02:20:00', 10),
	('2024-03-17 06:15:00', 12),
	('2024-03-17 09:50:00', 16),
	('2024-03-17 13:25:00', 14),

	('2024-03-19 01:30:00', 20),
	('2024-03-19 05:40:00', 14),
	('2024-03-19 09:10:00', 16),
	('2024-03-19 13:45:00', 9),

	('2024-03-21 00:50:00', 18),
	('2024-03-21 04:30:00', 11),
	('2024-03-21 09:00:00', 16),
	('2024-03-21 13:25:00', 14),

	('2024-03-23 02:00:00', 10),
	('2024-03-23 05:55:00', 16),
	('2024-03-23 09:40:00', 12),
	('2024-03-23 13:15:00', 14),

	('2024-03-25 01:40:00', 13),
	('2024-03-25 05:20:00', 16),
	('2024-03-25 09:00:00', 10),
	('2024-03-25 13:50:00', 22),

	('2024-03-27 00:30:00', 18),
	('2024-03-27 05:00:00', 12),
	('2024-03-27 09:20:00', 13),
	('2024-03-27 13:15:00', 14),

	('2024-03-29 01:30:00', 14),
	('2024-03-29 06:00:00', 20),
	('2024-03-29 09:10:00', 18),
	('2024-03-29 13:25:00', 9),

	('2024-03-31 00:25:00', 16),
	('2024-03-31 04:30:00', 14),
	('2024-03-31 09:00:00', 10),
	('2024-03-31 13:45:00', 22),
    
	('2024-04-01 00:30:00', 12),
	('2024-04-01 05:15:00', 16),
	('2024-04-01 09:00:00', 18),
	('2024-04-01 13:40:00', 14),

	('2024-04-03 01:00:00', 10),
	('2024-04-03 05:10:00', 22),
	('2024-04-03 09:30:00', 16),
	('2024-04-03 13:25:00', 19),

	('2024-04-05 00:45:00', 14),
	('2024-04-05 04:30:00', 16),
	('2024-04-05 09:10:00', 18),
	('2024-04-05 13:55:00', 22),

	('2024-04-07 01:30:00', 20),
	('2024-04-07 05:50:00', 13),
	('2024-04-07 09:00:00', 10),
	('2024-04-07 14:20:00', 16),

	('2024-04-09 02:10:00', 18),
	('2024-04-09 06:00:00', 14),
	('2024-04-09 09:25:00', 16),
	('2024-04-09 13:30:00', 12),

	('2024-04-11 00:15:00', 14),
	('2024-04-11 05:30:00', 20),
	('2024-04-11 09:40:00', 18),
	('2024-04-11 13:55:00', 16),

	('2024-04-13 01:25:00', 16),
	('2024-04-13 05:15:00', 14),
	('2024-04-13 09:10:00', 12),
	('2024-04-13 13:30:00', 18),

	('2024-04-15 02:20:00', 20),
	('2024-04-15 06:05:00', 22),
	('2024-04-15 09:30:00', 14),
	('2024-04-15 13:40:00', 10),

	('2024-04-17 01:10:00', 18),
	('2024-04-17 05:00:00', 16),
	('2024-04-17 09:25:00', 12),
	('2024-04-17 14:15:00', 20),

	('2024-04-19 02:30:00', 14),
	('2024-04-19 06:10:00', 22),
	('2024-04-19 09:00:00', 16),
	('2024-04-19 13:55:00', 18),

	('2024-04-21 01:40:00', 12),
	('2024-04-21 05:20:00', 14),
	('2024-04-21 09:10:00', 16),
	('2024-04-21 13:50:00', 20),

	('2024-04-23 02:00:00', 18),
	('2024-04-23 05:45:00', 22),
	('2024-04-23 09:25:00', 12),
	('2024-04-23 13:40:00', 16),

	('2024-04-25 01:30:00', 14),
	('2024-04-25 06:00:00', 10),
	('2024-04-25 09:35:00', 16),
	('2024-04-25 13:50:00', 18),

	('2024-04-27 02:10:00', 14),
	('2024-04-27 06:15:00', 18),
	('2024-04-27 09:05:00', 22),
	('2024-04-27 13:45:00', 16),

	('2024-04-29 01:45:00', 16),
	('2024-04-29 05:40:00', 14),
	('2024-04-29 09:15:00', 20),
	('2024-04-29 13:30:00', 18),
    
    ('2024-05-01 00:10:00', 22),
	('2024-05-01 04:15:00', 16),
	('2024-05-01 08:30:00', 18),
	('2024-05-01 12:45:00', 14),
	('2024-05-01 16:30:00', 20),
	('2024-05-01 20:00:00', 10),

	('2024-05-02 01:00:00', 12),
	('2024-05-02 05:20:00', 16),
	('2024-05-02 09:30:00', 22),
	('2024-05-02 13:40:00', 18),
	('2024-05-02 17:00:00', 20),
	('2024-05-02 21:10:00', 14),

	('2024-05-03 02:05:00', 10),
	('2024-05-03 06:20:00', 18),
	('2024-05-03 10:30:00', 14),
	('2024-05-03 14:45:00', 20),
	('2024-05-03 18:00:00', 22),
	('2024-05-03 22:10:00', 16),

	('2024-05-05 01:25:00', 16),
	('2024-05-05 05:15:00', 18),
	('2024-05-05 09:40:00', 12),
	('2024-05-05 13:50:00', 20),
	('2024-05-05 17:10:00', 14),
	('2024-05-05 21:25:00', 22),

	('2024-05-07 02:30:00', 18),
	('2024-05-07 06:00:00', 14),
	('2024-05-07 10:10:00', 20),
	('2024-05-07 14:20:00', 22),
	('2024-05-07 18:00:00', 16),
	('2024-05-07 22:15:00', 12),

	('2024-05-09 01:35:00', 22),
	('2024-05-09 05:25:00', 16),
	('2024-05-09 09:15:00', 18),
	('2024-05-09 13:30:00', 14),
	('2024-05-09 17:10:00', 10),
	('2024-05-09 21:40:00', 20),

	('2024-05-11 02:40:00', 14),
	('2024-05-11 06:10:00', 18),
	('2024-05-11 10:00:00', 22),
	('2024-05-11 14:25:00', 16),
	('2024-05-11 18:35:00', 12),
	('2024-05-11 22:00:00', 20),

	('2024-05-13 01:50:00', 20),
	('2024-05-13 05:40:00', 16),
	('2024-05-13 09:30:00', 18),
	('2024-05-13 13:00:00', 14),
	('2024-05-13 17:50:00', 22),
	('2024-05-13 21:10:00', 12),

	('2024-05-15 02:10:00', 14),
	('2024-05-15 06:20:00', 18),
	('2024-05-15 10:40:00', 12),
	('2024-05-15 14:25:00', 20),
	('2024-05-15 18:30:00', 16),
	('2024-05-15 22:00:00', 22),

	('2024-05-17 01:20:00', 22),
	('2024-05-17 05:00:00', 18),
	('2024-05-17 09:10:00', 12),
	('2024-05-17 13:40:00', 20),
	('2024-05-17 17:00:00', 16),
	('2024-05-17 21:50:00', 14),

	('2024-05-19 02:30:00', 20),
	('2024-05-19 06:10:00', 12),
	('2024-05-19 10:20:00', 18),
	('2024-05-19 14:50:00', 16),
	('2024-05-19 18:00:00', 22),
	('2024-05-19 22:15:00', 14),

	('2024-05-21 01:45:00', 16),
	('2024-05-21 05:20:00', 12),
	('2024-05-21 09:30:00', 14),
	('2024-05-21 13:50:00', 22),
	('2024-05-21 17:00:00', 16),
	('2024-05-21 21:40:00', 18),

	('2024-05-23 02:00:00', 18),
	('2024-05-23 06:15:00', 14),
	('2024-05-23 10:00:00', 22),
	('2024-05-23 14:30:00', 12),
	('2024-05-23 18:40:00', 20),
	('2024-05-23 22:00:00', 16),

	('2024-05-25 01:10:00', 12),
	('2024-05-25 05:25:00', 22),
	('2024-05-25 09:10:00', 16),
	('2024-05-25 13:50:00', 14),
	('2024-05-25 17:00:00', 18),
	('2024-05-25 21:20:00', 20),

	('2024-05-27 02:15:00', 16),
	('2024-05-27 06:00:00', 22),
	('2024-05-27 10:40:00', 14),
	('2024-05-27 14:25:00', 18),
	('2024-05-27 18:10:00', 12),
	('2024-05-27 22:00:00', 20),

	('2024-05-29 01:35:00', 14),
	('2024-05-29 05:20:00', 20),
	('2024-05-29 09:00:00', 16),
	('2024-05-29 13:40:00', 18),
	('2024-05-29 17:10:00', 22),
	('2024-05-29 21:25:00', 12),

	('2024-05-31 02:50:00', 20),
	('2024-05-31 06:10:00', 14),
	('2024-05-31 10:25:00', 12),
	('2024-05-31 14:50:00', 16),
	('2024-05-31 18:20:00', 18),
	('2024-05-31 22:30:00', 22),
    
    ('2024-06-01 00:10:00', 24),
	('2024-06-01 04:15:00', 19),
	('2024-06-01 08:30:00', 17),
	('2024-06-01 12:45:00', 22),
	('2024-06-01 16:30:00', 18),
	('2024-06-01 20:00:00', 12),

	('2024-06-02 01:00:00', 20),
	('2024-06-02 05:20:00', 14),
	('2024-06-02 09:30:00', 16),
	('2024-06-02 13:40:00', 18),
	('2024-06-02 17:00:00', 22),
	('2024-06-02 21:10:00', 12),

	('2024-06-03 02:05:00', 18),
	('2024-06-03 06:20:00', 14),
	('2024-06-03 10:30:00', 22),
	('2024-06-03 14:45:00', 16),
	('2024-06-03 18:00:00', 24),
	('2024-06-03 22:10:00', 20),

	('2024-06-04 01:25:00', 20),
	('2024-06-04 05:15:00', 16),
	('2024-06-04 09:40:00', 18),
	('2024-06-04 13:50:00', 22),
	('2024-06-04 17:10:00', 14),
	('2024-06-04 21:25:00', 12),

	('2024-06-05 02:30:00', 24),
	('2024-06-05 06:00:00', 18),
	('2024-06-05 10:10:00', 20),
	('2024-06-05 14:20:00', 22),
	('2024-06-05 18:00:00', 14),
	('2024-06-05 22:15:00', 16),

	('2024-06-06 02:40:00', 22),
	('2024-06-06 06:10:00', 12),
	('2024-06-06 10:00:00', 20),
	('2024-06-06 14:25:00', 18),
	('2024-06-06 18:35:00', 14),
	('2024-06-06 22:00:00', 16),

	('2024-06-07 01:50:00', 18),
	('2024-06-07 05:40:00', 14),
	('2024-06-07 09:30:00', 22),
	('2024-06-07 13:00:00', 16),
	('2024-06-07 17:50:00', 12),
	('2024-06-07 21:10:00', 20),

	('2024-06-08 02:10:00', 20),
	('2024-06-08 06:20:00', 14),
	('2024-06-08 10:00:00', 22),
	('2024-06-08 14:25:00', 18),
	('2024-06-08 18:30:00', 16),
	('2024-06-08 22:00:00', 12),

	('2024-06-09 01:20:00', 22),
	('2024-06-09 05:00:00', 18),
	('2024-06-09 09:10:00', 12),
	('2024-06-09 13:40:00', 20),
	('2024-06-09 17:00:00', 16),
	('2024-06-09 21:50:00', 14),

	('2024-06-11 02:30:00', 20),
	('2024-06-11 06:10:00', 22),
	('2024-06-11 10:20:00', 14),
	('2024-06-11 14:50:00', 18),
	('2024-06-11 18:00:00', 16),
	('2024-06-11 22:15:00', 12),

	('2024-06-13 02:00:00', 16),
	('2024-06-13 06:15:00', 22),
	('2024-06-13 10:00:00', 14),
	('2024-06-13 14:30:00', 18),
	('2024-06-13 18:40:00', 12),
	('2024-06-13 22:00:00', 20),

	('2024-06-14 01:10:00', 12),
	('2024-06-14 05:25:00', 16),
	('2024-06-14 09:10:00', 22),
	('2024-06-14 13:50:00', 18),
	('2024-06-14 17:00:00', 14),
	('2024-06-14 21:20:00', 20),

	('2024-06-15 02:15:00', 14),
	('2024-06-15 06:00:00', 20),
	('2024-06-15 10:40:00', 18),
	('2024-06-15 14:25:00', 22),
	('2024-06-15 18:10:00', 12),
	('2024-06-15 22:00:00', 16),

	('2024-06-17 02:30:00', 22),
	('2024-06-17 06:00:00', 18),
	('2024-06-17 10:10:00', 20),
	('2024-06-17 14:25:00', 16),
	('2024-06-17 18:35:00', 14),
	('2024-06-17 22:00:00', 12),

	('2024-06-19 01:35:00', 16),
	('2024-06-19 05:20:00', 12),
	('2024-06-19 09:00:00', 20),
	('2024-06-19 13:40:00', 18),
	('2024-06-19 17:10:00', 22),
	('2024-06-19 21:25:00', 14),

	('2024-06-21 02:50:00', 20),
	('2024-06-21 06:10:00', 14),
	('2024-06-21 10:25:00', 18),
	('2024-06-21 14:50:00', 16),
	('2024-06-21 18:20:00', 22),
	('2024-06-21 22:30:00', 12),

	('2024-06-23 01:00:00', 12),
	('2024-06-23 05:20:00', 20),
	('2024-06-23 09:10:00', 22),
	('2024-06-23 13:40:00', 16),
	('2024-06-23 17:00:00', 14),
	('2024-06-23 21:10:00', 18),

	('2024-06-25 02:40:00', 18),
	('2024-06-25 06:00:00', 22),
	('2024-06-25 10:40:00', 14),
	('2024-06-25 14:25:00', 16),
	('2024-06-25 18:00:00', 20),
	('2024-06-25 22:15:00', 12),

	('2024-06-27 02:15:00', 16),
	('2024-06-27 06:10:00', 12),
	('2024-06-27 10:00:00', 18),
	('2024-06-27 14:30:00', 22),
	('2024-06-27 18:40:00', 20),
	('2024-06-27 22:00:00', 14),

	('2024-06-29 01:30:00', 12),
	('2024-06-29 05:00:00', 16),
	('2024-06-29 09:10:00', 18),
	('2024-06-29 13:00:00', 22),
	('2024-06-29 17:20:00', 20),
	('2024-06-29 21:25:00', 14),
    
    ('2024-07-01 01:00:00', 22),
	('2024-07-01 09:30:00', 18),
	('2024-07-01 17:15:00', 20),

	('2024-07-02 02:15:00', 14),
	('2024-07-02 10:10:00', 16),
	('2024-07-02 18:40:00', 12),

	('2024-07-03 03:30:00', 18),
	('2024-07-03 11:00:00', 20),
	('2024-07-03 19:10:00', 16),

	('2024-07-04 02:00:00', 22),
	('2024-07-04 10:30:00', 14),
	('2024-07-04 18:15:00', 20),

	('2024-07-05 04:00:00', 12),
	('2024-07-05 12:30:00', 16),
	('2024-07-05 20:10:00', 18),

	('2024-07-06 01:40:00', 14),
	('2024-07-06 09:00:00', 20),
	('2024-07-06 16:50:00', 22),

	('2024-07-07 03:15:00', 18),
	('2024-07-07 11:20:00', 12),
	('2024-07-07 19:30:00', 16),

	('2024-07-08 02:50:00', 20),
	('2024-07-08 10:00:00', 14),
	('2024-07-08 18:25:00', 16),

	('2024-07-09 04:40:00', 12),
	('2024-07-09 12:00:00', 18),
	('2024-07-09 20:15:00', 22),

	('2024-07-10 01:30:00', 14),
	('2024-07-10 09:20:00', 20),
	('2024-07-10 17:10:00', 16),

	('2024-07-11 02:00:00', 18),
	('2024-07-11 10:30:00', 22),
	('2024-07-11 18:00:00', 14),

	('2024-07-12 03:40:00', 16),
	('2024-07-12 11:50:00', 14),
	('2024-07-12 19:20:00', 20),

    ('2024-08-01 09:15:00', 1),
    ('2024-08-01 10:30:00', 2),
    ('2024-08-01 11:45:00', 3),
    ('2024-08-01 13:00:00', 4),
    ('2024-08-01 14:15:00', 5),
    ('2024-08-01 15:30:00', 6),
    ('2024-08-01 16:45:00', 7),
    ('2024-08-01 18:00:00', 8),
    ('2024-08-01 19:15:00', 9),
    ('2024-08-01 20:30:00', 10),

    ('2024-08-02 09:15:00', 11),
    ('2024-08-02 10:30:00', 12),
    ('2024-08-02 11:45:00', 13),
    ('2024-08-02 13:00:00', 14),
    ('2024-08-02 14:15:00', 15),
    ('2024-08-02 15:30:00', 16),
    ('2024-08-02 16:45:00', 17),
    ('2024-08-02 18:00:00', 18),
    ('2024-08-02 19:15:00', 19),
    ('2024-08-02 20:30:00', 20),

    ('2024-08-03 09:15:00', 21),
    ('2024-08-03 10:30:00', 22),
    ('2024-08-03 11:45:00', 23),
    ('2024-08-03 13:00:00', 24),
    ('2024-08-03 14:15:00', 25),
    ('2024-08-03 15:30:00', 1),
    ('2024-08-03 16:45:00', 2),
    ('2024-08-03 18:00:00', 3),
    ('2024-08-03 19:15:00', 4),
    ('2024-08-03 20:30:00', 5),

    ('2024-08-04 09:15:00', 6),
    ('2024-08-04 10:30:00', 7),
    ('2024-08-04 11:45:00', 8),
    ('2024-08-04 13:00:00', 9),
    ('2024-08-04 14:15:00', 10),
    ('2024-08-04 15:30:00', 11),
    ('2024-08-04 16:45:00', 12),
    ('2024-08-04 18:00:00', 13),
    ('2024-08-04 19:15:00', 14),
    ('2024-08-04 20:30:00', 15),

    ('2024-08-05 09:15:00', 16),
    ('2024-08-05 10:30:00', 17),
    ('2024-08-05 11:45:00', 18),
    ('2024-08-05 13:00:00', 19),
    ('2024-08-05 14:15:00', 20),
    ('2024-08-05 15:30:00', 21),
    ('2024-08-05 16:45:00', 22),
    ('2024-08-05 18:00:00', 23),
    ('2024-08-05 19:15:00', 24),
    ('2024-08-05 20:30:00', 25),
    
    ('2024-08-06 09:15:00', 1),
    ('2024-08-06 10:30:00', 2),
    ('2024-08-06 11:45:00', 3),
    ('2024-08-06 13:00:00', 4),
    ('2024-08-06 14:15:00', 5),
    ('2024-08-06 15:30:00', 6),
    ('2024-08-06 16:45:00', 7),
    ('2024-08-06 18:00:00', 8),
    ('2024-08-06 19:15:00', 9),
    ('2024-08-06 20:30:00', 10),

    ('2024-08-07 09:15:00', 11),
    ('2024-08-07 10:30:00', 12),
    ('2024-08-07 11:45:00', 13),
    ('2024-08-07 13:00:00', 14),
    ('2024-08-07 14:15:00', 15),
    ('2024-08-07 15:30:00', 16),
    ('2024-08-07 16:45:00', 17),
    ('2024-08-07 18:00:00', 18),
    ('2024-08-07 19:15:00', 19),
    ('2024-08-07 20:30:00', 20),

    ('2024-08-08 09:15:00', 21),
    ('2024-08-08 10:30:00', 22),
    ('2024-08-08 11:45:00', 23),
    ('2024-08-08 13:00:00', 24),
    ('2024-08-08 14:15:00', 25),
    ('2024-08-08 15:30:00', 1),
    ('2024-08-08 16:45:00', 2),
    ('2024-08-08 18:00:00', 3),
    ('2024-08-08 19:15:00', 4),
    ('2024-08-08 20:30:00', 5),

    ('2024-08-09 09:15:00', 6),
    ('2024-08-09 10:30:00', 7),
    ('2024-08-09 11:45:00', 8),
    ('2024-08-09 13:00:00', 9),
    ('2024-08-09 14:15:00', 10),
    ('2024-08-09 15:30:00', 11),
    ('2024-08-09 16:45:00', 12),
    ('2024-08-09 18:00:00', 13),
    ('2024-08-09 19:15:00', 14),
    ('2024-08-09 20:30:00', 15),

    ('2024-08-10 09:15:00', 16),
    ('2024-08-10 10:30:00', 17),
    ('2024-08-10 11:45:00', 18),
    ('2024-08-10 13:00:00', 19),
    ('2024-08-10 14:15:00', 20),
    ('2024-08-10 15:30:00', 21),
    ('2024-08-10 16:45:00', 22),
    ('2024-08-10 18:00:00', 23),
    ('2024-08-10 19:15:00', 24),
    ('2024-08-10 20:30:00', 25),

    ('2024-08-11 09:15:00', 1),
    ('2024-08-11 10:30:00', 2),
    ('2024-08-11 11:45:00', 3),
    ('2024-08-11 13:00:00', 4),
    ('2024-08-11 14:15:00', 5),
    ('2024-08-11 15:30:00', 6),
    ('2024-08-11 16:45:00', 7),
    ('2024-08-11 18:00:00', 8),
    ('2024-08-11 19:15:00', 9),
    ('2024-08-11 20:30:00', 10),

    ('2024-08-12 09:15:00', 11),
    ('2024-08-12 10:30:00', 12),
    ('2024-08-12 11:45:00', 13),
    ('2024-08-12 13:00:00', 14),
    ('2024-08-12 14:15:00', 15),
    ('2024-08-12 15:30:00', 16),
    ('2024-08-12 16:45:00', 17),
    ('2024-08-12 18:00:00', 18),
    ('2024-08-12 19:15:00', 19),
    ('2024-08-12 20:30:00', 20),

    ('2024-08-13 09:15:00', 21),
    ('2024-08-13 10:30:00', 22),
    ('2024-08-13 11:45:00', 23),
    ('2024-08-13 13:00:00', 24),
    ('2024-08-13 14:15:00', 25),
    ('2024-08-13 15:30:00', 1),
    ('2024-08-13 16:45:00', 2),
    ('2024-08-13 18:00:00', 3),
    ('2024-08-13 19:15:00', 4),
    ('2024-08-13 20:30:00', 5),

    ('2024-08-14 09:15:00', 6),
    ('2024-08-14 10:30:00', 7),
    ('2024-08-14 11:45:00', 8),
    ('2024-08-14 13:00:00', 9),
    ('2024-08-14 14:15:00', 10),
    ('2024-08-14 15:30:00', 11),
    ('2024-08-14 16:45:00', 12),
    ('2024-08-14 18:00:00', 13),
    ('2024-08-14 19:15:00', 14),
    ('2024-08-14 20:30:00', 15),
    
    ('2024-08-15 09:15:00', 16),
    ('2024-08-15 10:30:00', 17),
    ('2024-08-15 11:45:00', 18),
    ('2024-08-15 13:00:00', 19),
    ('2024-08-15 14:15:00', 20),
    ('2024-08-15 15:30:00', 21),
    ('2024-08-15 16:45:00', 22),
    ('2024-08-15 18:00:00', 23),
    ('2024-08-15 19:15:00', 24),
    ('2024-08-15 20:30:00', 25),

    ('2024-08-16 09:15:00', 1),
    ('2024-08-16 10:30:00', 2),
    ('2024-08-16 11:45:00', 3),
    ('2024-08-16 13:00:00', 4),
    ('2024-08-16 14:15:00', 5),
    ('2024-08-16 15:30:00', 6),
    ('2024-08-16 16:45:00', 7),
    ('2024-08-16 18:00:00', 8),
    ('2024-08-16 19:15:00', 9),
    ('2024-08-16 20:30:00', 10),

    ('2024-08-17 09:15:00', 11),
    ('2024-08-17 10:30:00', 12),
    ('2024-08-17 11:45:00', 13),
    ('2024-08-17 13:00:00', 14),
    ('2024-08-17 14:15:00', 15),
    ('2024-08-17 15:30:00', 16),
    ('2024-08-17 16:45:00', 17),
    ('2024-08-17 18:00:00', 18),
    ('2024-08-17 19:15:00', 19),
    ('2024-08-17 20:30:00', 20),

    ('2024-08-18 09:15:00', 21),
    ('2024-08-18 10:30:00', 22),
    ('2024-08-18 11:45:00', 23),
    ('2024-08-18 13:00:00', 24),
    ('2024-08-18 14:15:00', 25),
    ('2024-08-18 15:30:00', 1),
    ('2024-08-18 16:45:00', 2),
    ('2024-08-18 18:00:00', 3),
    ('2024-08-18 19:15:00', 4),
    ('2024-08-18 20:30:00', 5),

    ('2024-08-19 09:15:00', 6),
    ('2024-08-19 10:30:00', 7),
    ('2024-08-19 11:45:00', 8),
    ('2024-08-19 13:00:00', 9),
    ('2024-08-19 14:15:00', 10),
    ('2024-08-19 15:30:00', 11),
    ('2024-08-19 16:45:00', 12),
    ('2024-08-19 18:00:00', 13),
    ('2024-08-19 19:15:00', 14),
    ('2024-08-19 20:30:00', 15),

    ('2024-08-20 09:15:00', 16),
    ('2024-08-20 10:30:00', 17),
    ('2024-08-20 11:45:00', 18),
    ('2024-08-20 13:00:00', 19),
    ('2024-08-20 14:15:00', 20),
    ('2024-08-20 15:30:00', 21),
    ('2024-08-20 16:45:00', 22),
    ('2024-08-20 18:00:00', 23),
    ('2024-08-20 19:15:00', 24),
    ('2024-08-20 20:30:00', 25),

    ('2024-08-21 09:15:00', 1),
    ('2024-08-21 10:30:00', 2),
    ('2024-08-21 11:45:00', 3),
    ('2024-08-21 13:00:00', 4),
    ('2024-08-21 14:15:00', 5),
    ('2024-08-21 15:30:00', 6),
    ('2024-08-21 16:45:00', 7),
    ('2024-08-21 18:00:00', 8),
    ('2024-08-21 19:15:00', 9),
    ('2024-08-21 20:30:00', 10),

    ('2024-08-22 09:15:00', 11),
    ('2024-08-22 10:30:00', 12),
    ('2024-08-22 11:45:00', 13),
    ('2024-08-22 13:00:00', 14),
    ('2024-08-22 14:15:00', 15),
    ('2024-08-22 15:30:00', 16),
    ('2024-08-22 16:45:00', 17),
    ('2024-08-22 18:00:00', 18),
    ('2024-08-22 19:15:00', 19),
    ('2024-08-22 20:30:00', 20),

    ('2024-08-23 09:15:00', 21),
    ('2024-08-23 10:30:00', 22),
    ('2024-08-23 11:45:00', 23),
    ('2024-08-23 13:00:00', 24),
    ('2024-08-23 14:15:00', 25),
    ('2024-08-23 15:30:00', 1),
    ('2024-08-23 16:45:00', 2),
    ('2024-08-23 18:00:00', 3),
    ('2024-08-23 19:15:00', 4),
    ('2024-08-23 20:30:00', 5),

    ('2024-08-24 09:15:00', 6),
    ('2024-08-24 10:30:00', 7),
    ('2024-08-24 11:45:00', 8),
    ('2024-08-24 13:00:00', 9),
    ('2024-08-24 14:15:00', 10),
    ('2024-08-24 15:30:00', 11),
    ('2024-08-24 16:45:00', 12),
    ('2024-08-24 18:00:00', 13),
    ('2024-08-24 19:15:00', 14),
    ('2024-08-24 20:30:00', 15),

    ('2024-08-25 09:15:00', 16),
    ('2024-08-25 10:30:00', 17),
    ('2024-08-25 11:45:00', 18),
    ('2024-08-25 13:00:00', 19),
    ('2024-08-25 14:15:00', 20),
    ('2024-08-25 15:30:00', 21),
    ('2024-08-25 16:45:00', 22),
    ('2024-08-25 18:00:00', 23),
    ('2024-08-25 19:15:00', 24),
    ('2024-08-25 20:30:00', 25),

    ('2024-08-26 09:15:00', 1),
    ('2024-08-26 10:30:00', 2),
    ('2024-08-26 11:45:00', 3),
    ('2024-08-26 13:00:00', 4),
    ('2024-08-26 14:15:00', 5),
    ('2024-08-26 15:30:00', 6),
    ('2024-08-26 16:45:00', 7),
    ('2024-08-26 18:00:00', 8),
    ('2024-08-26 19:15:00', 9),
    ('2024-08-26 20:30:00', 10),

    ('2024-08-27 09:15:00', 11),
    ('2024-08-27 10:30:00', 12),
    ('2024-08-27 11:45:00', 13),
    ('2024-08-27 13:00:00', 14),
    ('2024-08-27 14:15:00', 15),
    ('2024-08-27 15:30:00', 16),
    ('2024-08-27 16:45:00', 17),
    ('2024-08-27 18:00:00', 18),
    ('2024-08-27 19:15:00', 19),
    ('2024-08-27 20:30:00', 20),

    ('2024-08-28 09:15:00', 21),
    ('2024-08-28 10:30:00', 22),
    ('2024-08-28 11:45:00', 23),
    ('2024-08-28 13:00:00', 24),
    ('2024-08-28 14:15:00', 25),
    ('2024-08-28 15:30:00', 1),
    ('2024-08-28 16:45:00', 2),
    ('2024-08-28 18:00:00', 3),
    ('2024-08-28 19:15:00', 4),
    ('2024-08-28 20:30:00', 5),

    ('2024-08-29 09:15:00', 6),
    ('2024-08-29 10:30:00', 7),
    ('2024-08-29 11:45:00', 8),
    ('2024-08-29 13:00:00', 9),
    ('2024-08-29 14:15:00', 10),
    ('2024-08-29 15:30:00', 11),
    ('2024-08-29 16:45:00', 12),
    ('2024-08-29 18:00:00', 13),
    ('2024-08-29 19:15:00', 14),
    ('2024-08-29 20:30:00', 15),

    ('2024-08-30 09:15:00', 16),
    ('2024-08-30 10:30:00', 17),
    ('2024-08-30 11:45:00', 18),
    ('2024-08-30 13:00:00', 19),
    ('2024-08-30 14:15:00', 20),
    ('2024-08-30 15:30:00', 21),
    ('2024-08-30 16:45:00', 22),
    ('2024-08-30 18:00:00', 23),
    ('2024-08-30 19:15:00', 24),
    ('2024-08-30 20:30:00', 25),

    ('2024-08-31 09:15:00', 1),
    ('2024-08-31 10:30:00', 2),
    ('2024-08-31 11:45:00', 3),
    ('2024-08-31 13:00:00', 4),
    ('2024-08-31 14:15:00', 5),
    ('2024-08-31 15:30:00', 6),
    ('2024-08-31 16:45:00', 7),
    ('2024-08-31 18:00:00', 8),
    ('2024-08-31 19:15:00', 9),
    ('2024-08-31 20:30:00', 10),
    
    ('2024-09-01 01:10:00', 22),
	('2024-09-01 07:20:00', 18),
	('2024-09-01 13:30:00', 20),
	('2024-09-01 19:40:00', 16),

	('2024-09-02 02:00:00', 14),
	('2024-09-02 08:10:00', 16),
	('2024-09-02 14:30:00', 18),
	('2024-09-02 20:40:00', 12),

	('2024-09-03 03:20:00', 20),
	('2024-09-03 09:30:00', 14),
	('2024-09-03 15:40:00', 16),
	('2024-09-03 21:50:00', 18),

	('2024-09-04 04:10:00', 12),
	('2024-09-04 10:20:00', 20),
	('2024-09-04 16:30:00', 22),
	('2024-09-04 22:40:00', 18),

	('2024-09-05 05:00:00', 14),
	('2024-09-05 11:10:00', 16),
	('2024-09-05 17:20:00', 20),
	('2024-09-05 23:30:00', 18),

	('2024-09-06 06:00:00', 22),
	('2024-09-06 12:10:00', 18),
	('2024-09-06 18:20:00', 14),
	('2024-09-06 00:30:00', 16),

	('2024-09-07 07:00:00', 16),
	('2024-09-07 13:10:00', 20),
	('2024-09-07 19:30:00', 14),
	('2024-09-07 01:40:00', 22),

	('2024-09-08 08:00:00', 12),
	('2024-09-08 14:10:00', 16),
	('2024-09-08 20:20:00', 18),
	('2024-09-08 02:30:00', 20),

	('2024-09-09 09:00:00', 18),
	('2024-09-09 15:10:00', 22),
	('2024-09-09 21:20:00', 16),
	('2024-09-09 03:30:00', 14),

	('2024-09-10 10:00:00', 14),
	('2024-09-10 16:10:00', 20),
	('2024-09-10 22:20:00', 18),
	('2024-09-10 04:40:00', 12),

	('2024-09-11 11:00:00', 22),
	('2024-09-11 17:10:00', 14),
	('2024-09-11 23:20:00', 20),
	('2024-09-11 05:50:00', 16),

	('2024-09-12 12:00:00', 16),
	('2024-09-12 18:10:00', 14),
	('2024-09-12 00:20:00', 22),
	('2024-09-12 06:30:00', 18),

	('2024-09-13 13:00:00', 20),
	('2024-09-13 19:10:00', 18),
	('2024-09-13 01:20:00', 16),
	('2024-09-13 07:30:00', 14),

	('2024-09-14 14:00:00', 12),
	('2024-09-14 20:10:00', 22),
	('2024-09-14 02:20:00', 18),
	('2024-09-14 08:30:00', 16),

	('2024-09-15 15:00:00', 14),
	('2024-09-15 21:10:00', 16),
	('2024-09-15 03:20:00', 20),
	('2024-09-15 09:30:00', 18),

	('2024-09-16 16:00:00', 18),
	('2024-09-16 22:10:00', 20),
	('2024-09-16 04:20:00', 22),
	('2024-09-16 10:30:00', 14),

	('2024-09-17 17:00:00', 16),
	('2024-09-17 23:10:00', 20),
	('2024-09-17 05:20:00', 18),
	('2024-09-17 11:30:00', 14),

	('2024-09-18 18:00:00', 12),
	('2024-09-18 00:10:00', 22),
	('2024-09-18 06:20:00', 14),
	('2024-09-18 12:30:00', 20),

	('2024-09-19 19:00:00', 18),
	('2024-09-19 01:10:00', 16),
	('2024-09-19 07:20:00', 14),
	('2024-09-19 13:30:00', 22),

	('2024-09-20 20:00:00', 12),
	('2024-09-20 02:10:00', 20),
	('2024-09-20 08:20:00', 22),
	('2024-09-20 14:30:00', 16),

	('2024-09-21 21:00:00', 18),
	('2024-09-21 03:10:00', 14),
	('2024-09-21 09:20:00', 16),
	('2024-09-21 15:30:00', 20),

	('2024-09-22 22:00:00', 14),
	('2024-09-22 04:10:00', 18),
	('2024-09-22 10:20:00', 20),
	('2024-09-22 16:30:00', 22),

	('2024-09-23 23:00:00', 16),
	('2024-09-23 05:10:00', 14),
	('2024-09-23 11:20:00', 18),
	('2024-09-23 17:30:00', 20),

	('2024-09-24 00:00:00', 22),
	('2024-09-24 06:10:00', 20),
	('2024-09-24 12:20:00', 14),
	('2024-09-24 18:30:00', 16),

	('2024-09-25 01:00:00', 16),
	('2024-09-25 07:10:00', 22),
	('2024-09-25 13:20:00', 14),
	('2024-09-25 19:30:00', 20),

	('2024-09-26 02:00:00', 18),
	('2024-09-26 08:10:00', 14),
	('2024-09-26 14:20:00', 16),
	('2024-09-26 20:30:00', 20),

	('2024-09-27 03:00:00', 22),
	('2024-09-27 09:10:00', 14),
	('2024-09-27 15:20:00', 20),
	('2024-09-27 21:30:00', 18),

	('2024-09-28 04:00:00', 16),
	('2024-09-28 10:10:00', 14),
	('2024-09-28 16:20:00', 20),
	('2024-09-28 22:30:00', 22),

	('2024-09-29 05:00:00', 18),
	('2024-09-29 11:10:00', 22),
	('2024-09-29 17:20:00', 14),
	('2024-09-29 23:30:00', 16),

	('2024-09-30 06:00:00', 20),
	('2024-09-30 12:10:00', 14),
	('2024-09-30 18:20:00', 22),
	('2024-09-30 00:30:00', 16),

	('2024-10-01 01:00:00', 22),
	('2024-10-01 07:15:00', 18),
	('2024-10-01 13:30:00', 20),
	('2024-10-01 19:45:00', 16),

	('2024-10-02 02:00:00', 16),
	('2024-10-02 08:10:00', 22),
	('2024-10-02 14:20:00', 20),
	('2024-10-02 20:30:00', 14),

	('2024-10-03 03:00:00', 18),
	('2024-10-03 09:15:00', 14),
	('2024-10-03 15:30:00', 20),
	('2024-10-03 21:45:00', 16),

	('2024-10-04 04:00:00', 22),
	('2024-10-04 10:10:00', 14),
	('2024-10-04 16:20:00', 18),
	('2024-10-04 22:30:00', 20),

	('2024-10-05 05:00:00', 16),
	('2024-10-05 11:15:00', 20),
	('2024-10-05 17:30:00', 22),
	('2024-10-05 23:45:00', 14),

	('2024-10-06 06:00:00', 18),
	('2024-10-06 12:10:00', 14),
	('2024-10-06 18:20:00', 20),
	('2024-10-06 00:30:00', 22),

	('2024-10-07 07:00:00', 16),
	('2024-10-07 13:15:00', 22),
	('2024-10-07 19:30:00', 14),
	('2024-10-07 01:45:00', 18),

	('2024-10-08 08:00:00', 20),
	('2024-10-08 14:10:00', 16),
	('2024-10-08 20:20:00', 22),
	('2024-10-08 02:30:00', 14),

	('2024-10-09 09:00:00', 16),
	('2024-10-09 15:10:00', 22),
	('2024-10-09 21:20:00', 20),
	('2024-10-09 03:40:00', 18),

	('2024-10-10 10:00:00', 22),
	('2024-10-10 16:10:00', 18),
	('2024-10-10 22:20:00', 14),
	('2024-10-10 04:50:00', 20),

	('2024-10-11 11:00:00', 20),
	('2024-10-11 17:10:00', 14),
	('2024-10-11 23:20:00', 16),
	('2024-10-11 05:00:00', 22),

	('2024-10-12 12:00:00', 18),
	('2024-10-12 18:10:00', 14),
	('2024-10-12 00:20:00', 22),
	('2024-10-12 06:30:00', 16),

	('2024-10-13 13:00:00', 22),
	('2024-10-13 19:10:00', 18),
	('2024-10-13 01:20:00', 14),
	('2024-10-13 07:40:00', 20),

	('2024-10-14 14:00:00', 20),
	('2024-10-14 20:10:00', 16),
	('2024-10-14 02:20:00', 18),
	('2024-10-14 08:30:00', 22),

	('2024-10-15 15:00:00', 14),
	('2024-10-15 21:10:00', 22),
	('2024-10-15 03:20:00', 16),
	('2024-10-15 09:40:00', 20),

	('2024-10-16 16:00:00', 18),
	('2024-10-16 22:10:00', 14),
	('2024-10-16 04:20:00', 22),
	('2024-10-16 10:40:00', 20),

	('2024-10-17 17:00:00', 14),
	('2024-10-17 23:10:00', 18),
	('2024-10-17 05:20:00', 16),
	('2024-10-17 11:40:00', 22),
    
	('2024-11-01 00:00:00', 25),
	('2024-11-01 06:00:00', 20),
	('2024-11-01 12:00:00', 18),
	('2024-11-01 18:00:00', 22),
	('2024-11-01 20:00:00', 15),
	('2024-11-01 23:00:00', 19),

	('2024-11-02 01:00:00', 22),
	('2024-11-02 07:00:00', 18),
	('2024-11-02 13:00:00', 20),
	('2024-11-02 19:00:00', 16),
	('2024-11-02 21:00:00', 22),
	('2024-11-02 23:30:00', 18),

	('2024-11-03 02:00:00', 20),
	('2024-11-03 08:00:00', 18),
	('2024-11-03 14:00:00', 22),
	('2024-11-03 16:00:00', 19),
	('2024-11-03 20:00:00', 16),
	('2024-11-03 22:00:00', 21),

	('2024-11-04 03:00:00', 18),
	('2024-11-04 09:00:00', 21),
	('2024-11-04 15:00:00', 22),
	('2024-11-04 17:00:00', 25),
	('2024-11-04 21:00:00', 16),
	('2024-11-04 23:45:00', 20),

	('2024-11-05 00:30:00', 18),
	('2024-11-05 06:30:00', 20),
	('2024-11-05 12:30:00', 22),
	('2024-11-05 18:30:00', 19),
	('2024-11-05 20:30:00', 15),
	('2024-11-05 22:30:00', 21),

	('2024-11-06 01:00:00', 25),
	('2024-11-06 07:00:00', 20),
	('2024-11-06 13:00:00', 22),
	('2024-11-06 19:00:00', 16),
	('2024-11-06 21:00:00', 18),
	('2024-11-06 23:00:00', 22),

	('2024-11-07 02:00:00', 18),
	('2024-11-07 08:00:00', 20),
	('2024-11-07 14:00:00', 22),
	('2024-11-07 16:00:00', 19),
	('2024-11-07 20:00:00', 15),
	('2024-11-07 22:00:00', 21),

	('2024-11-08 03:00:00', 22),
	('2024-11-08 09:00:00', 16),
	('2024-11-08 15:00:00', 21),
	('2024-11-08 17:00:00', 18),
	('2024-11-08 21:00:00', 20),
	('2024-11-08 23:45:00', 19),

	('2024-11-09 00:30:00', 25),
	('2024-11-09 06:30:00', 20),
	('2024-11-09 12:30:00', 22),
	('2024-11-09 18:30:00', 19),
	('2024-11-09 20:30:00', 16),
	('2024-11-09 22:30:00', 21),

	('2024-11-10 01:00:00', 18),
	('2024-11-10 07:00:00', 20),
	('2024-11-10 13:00:00', 22),
	('2024-11-10 15:00:00', 25),
	('2024-11-10 19:00:00', 21),
	('2024-11-10 21:00:00', 16),

	('2024-11-11 02:00:00', 22),
	('2024-11-11 08:00:00', 18),
	('2024-11-11 14:00:00', 16),
	('2024-11-11 16:00:00', 20),
	('2024-11-11 20:00:00', 22),
	('2024-11-11 22:00:00', 21),

	('2024-11-12 03:00:00', 19),
	('2024-11-12 09:00:00', 20),
	('2024-11-12 15:00:00', 22),
	('2024-11-12 17:00:00', 18),
	('2024-11-12 21:00:00', 16),
	('2024-11-12 23:30:00', 21),

	('2024-11-13 00:00:00', 22),
	('2024-11-13 06:00:00', 18),
	('2024-11-13 12:00:00', 20),
	('2024-11-13 18:00:00', 16),
	('2024-11-13 20:00:00', 19),
	('2024-11-13 23:00:00', 25),

	('2024-11-14 01:00:00', 18),
	('2024-11-14 07:00:00', 22),
	('2024-11-14 13:00:00', 19),
	('2024-11-14 15:00:00', 16),
	('2024-11-14 19:00:00', 20),
	('2024-11-14 21:00:00', 25),

	('2024-11-15 02:00:00', 20),
	('2024-11-15 08:00:00', 22),
	('2024-11-15 14:00:00', 18),
	('2024-11-15 16:00:00', 16),
	('2024-11-15 20:00:00', 25),
	('2024-11-15 22:00:00', 19),

	('2024-11-16 03:00:00', 22),
	('2024-11-16 09:00:00', 25),
	('2024-11-16 15:00:00', 16),
	('2024-11-16 17:00:00', 20),
	('2024-11-16 21:00:00', 18),
	('2024-11-16 23:00:00', 19),

	('2024-11-17 00:30:00', 22),
	('2024-11-17 06:30:00', 16),
	('2024-11-17 12:30:00', 20),
	('2024-11-17 18:30:00', 19),
	('2024-11-17 20:30:00', 21),
	('2024-11-17 22:30:00', 25),

	('2024-11-18 01:00:00', 18),
	('2024-11-18 07:00:00', 16),
	('2024-11-18 13:00:00', 22),
	('2024-11-18 15:00:00', 19),
	('2024-11-18 19:00:00', 25),
	('2024-11-18 21:00:00', 20),

	('2024-11-19 02:00:00', 16),
	('2024-11-19 08:00:00', 19),
	('2024-11-19 14:00:00', 20),
	('2024-11-19 16:00:00', 18),
	('2024-11-19 20:00:00', 25),
	('2024-11-19 22:00:00', 22),

	('2024-11-20 03:00:00', 25),
	('2024-11-20 09:00:00', 22),
	('2024-11-20 15:00:00', 18),
	('2024-11-20 17:00:00', 16),
	('2024-11-20 21:00:00', 22),
	('2024-11-20 23:30:00', 20),

	('2024-11-21 00:00:00', 16),
	('2024-11-21 06:00:00', 18),
	('2024-11-21 12:00:00', 19),
	('2024-11-21 18:00:00', 22),
	('2024-11-21 20:00:00', 20),
	('2024-11-21 22:00:00', 21),

	('2024-11-22 01:00:00', 16),
	('2024-11-22 07:00:00', 20),
	('2024-11-22 13:00:00', 22),
	('2024-11-22 15:00:00', 25),
	('2024-11-22 19:00:00', 18),
	('2024-11-22 21:00:00', 16),

	('2024-11-23 02:00:00', 22),
	('2024-11-23 08:00:00', 18),
	('2024-11-23 14:00:00', 20),
	('2024-11-23 16:00:00', 21),
	('2024-11-23 20:00:00', 25),
	('2024-11-23 22:00:00', 16),

	('2024-11-24 03:00:00', 18),
	('2024-11-24 09:00:00', 22),
	('2024-11-24 15:00:00', 19),
	('2024-11-24 17:00:00', 25),
	('2024-11-24 21:00:00', 16),
	('2024-11-24 23:00:00', 22),

	('2024-11-25 00:30:00', 25),
	('2024-11-25 06:30:00', 19),
	('2024-11-25 12:30:00', 16),
	('2024-11-25 18:30:00', 18),
	('2024-11-25 20:30:00', 22),
	('2024-11-25 22:30:00', 20),

	('2024-11-26 01:00:00', 16),
	('2024-11-26 07:00:00', 19),
	('2024-11-26 13:00:00', 25),
	('2024-11-26 15:00:00', 20),
	('2024-11-26 19:00:00', 22),
	('2024-11-26 21:00:00', 18),

	('2024-11-27 02:00:00', 18),
	('2024-11-27 08:00:00', 16),
	('2024-11-27 14:00:00', 20),
	('2024-11-27 16:00:00', 22),
	('2024-11-27 20:00:00', 25),
	('2024-11-27 22:00:00', 19),

	('2024-11-28 03:00:00', 22),
	('2024-11-28 09:00:00', 16),
	('2024-11-28 15:00:00', 25),
	('2024-11-28 17:00:00', 18),
	('2024-11-28 21:00:00', 22),
	('2024-11-28 23:00:00', 19),

	('2024-11-29 00:30:00', 20),
	('2024-11-29 06:30:00', 22),
	('2024-11-29 12:30:00', 18),
	('2024-11-29 18:30:00', 19),
	('2024-11-29 20:30:00', 25),
	('2024-11-29 22:30:00', 16),

	('2024-11-30 01:00:00', 22),
	('2024-11-30 07:00:00', 18),
	('2024-11-30 13:00:00', 19),
	('2024-11-30 15:00:00', 20),
	('2024-11-30 19:00:00', 22),
	('2024-11-30 21:00:00', 16),
    
    ('2024-12-01 00:00:00', 20),
	('2024-12-01 06:00:00', 18),
	('2024-12-01 12:00:00', 22),
	('2024-12-01 18:00:00', 25),

	('2024-12-02 01:00:00', 19),
	('2024-12-02 07:00:00', 22),
	('2024-12-02 13:00:00', 16),
	('2024-12-02 19:00:00', 20),

	('2024-12-03 02:00:00', 18),
	('2024-12-03 08:00:00', 20),
	('2024-12-03 14:00:00', 22),
	('2024-12-03 20:00:00', 25),

	('2024-12-04 03:00:00', 19),
	('2024-12-04 09:00:00', 22),
	('2024-12-04 15:00:00', 18),
	('2024-12-04 21:00:00', 20),

	('2024-12-05 00:30:00', 20),
	('2024-12-05 06:30:00', 18),
	('2024-12-05 12:30:00', 22),
	('2024-12-05 18:30:00', 25);

    
    UPDATE publicacao SET status = 'ATIVO' WHERE id < 10000;
    UPDATE comentario SET status = 'ATIVO' WHERE id < 10000;
