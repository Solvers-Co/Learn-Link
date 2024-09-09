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
('Santo Amaro', 'São Paulo', 'São Paulo', '04711040');


INSERT INTO usuario (nome, cpf, email, senha, tipo_status_id, especialidade_id, classificacao_id, tipo_usuario_id) VALUES
('Lucas Pereira', '78945612309', 'lucas.pereira@example.com', 'lucas2024', 2, 1, 3, 1),
('Mariana Silva', '85296374102', 'mariana.silva@example.com', 'mari2024', 1, 2, 2, 2),
('João Fernandes', '95175385246', 'joao.fernandes@example.com', 'joao2024', 2, 3, 1, 1),
('Beatriz Costa', '75315948263', 'beatriz.costa@example.com', 'bia2024', 3, 4, 3, 1),
('Carlos Nogueira', '95148627309', 'carlos.nogueira@example.com', 'carlos2024', 1, 5, 2, 2),
('Ana Clara Lima', '12365478932', 'ana.lima@example.com', 'ana2024', 2, 6, 4, 1),
('Pedro Oliveira', '85236974105', 'pedro.oliveira@example.com', 'pedro2024', 3, 7, 1, 1),
('Fernanda Souza', '45698712309', 'fernanda.souza@example.com', 'fer2024', 1, 8, 2, 2),
('Ricardo Santos', '78945632109', 'ricardo.santos@example.com', 'ricardo2024', 2, 9, 3, 1),
('Juliana Mendes', '36925814705', 'juliana.mendes@example.com', 'ju2024', 3, 10, 4, 2),
('Matheus Rocha', '10293847562', 'matheus.rocha@example.com', 'matheus2024', 2, 3, 2, 1),
('Larissa Almeida', '56473829105', 'larissa.almeida@example.com', 'larissa2024', 1, 4, 1, 2),
('Gabriel Lima', '98765432100', 'gabriel.lima@example.com', 'gabriel2024', 2, 5, 3, 1),
('Tatiana Sousa', '65498732102', 'tatiana.sousa@example.com', 'tati2024', 3, 6, 4, 1),
('Felipe Costa', '78912345601', 'felipe.costa@example.com', 'felipe2024', 1, 7, 2, 2),
('Bruna Carvalho', '25836914702', 'bruna.carvalho@example.com', 'bruna2024', 2, 8, 1, 1),
('Eduardo Martins', '14725836903', 'eduardo.martins@example.com', 'eduardo2024', 3, 9, 3, 2),
('Camila Santos', '96385274106', 'camila.santos@example.com', 'camila2024', 1, 10, 2, 1),
('Rafael Gomes', '78965412307', 'rafael.gomes@example.com', 'rafael2024', 2, 1, 1, 1),
('Aline Ribeiro', '32165498708', 'aline.ribeiro@example.com', 'aline2024', 3, 2, 3, 2),
('Thiago Ferreira', '95135746809', 'thiago.ferreira@example.com', 'thiago2024', 1, 3, 4, 1),
('Patricia Moreira', '75395185204', 'patricia.moreira@example.com', 'patricia2024', 2, 4, 1, 2),
('Rodrigo Moraes', '25874196305', 'rodrigo.moraes@example.com', 'rodrigo2024', 3, 5, 2, 1),
('Gabriela Souza', '65412378906', 'gabriela.souza@example.com', 'gabriela2024', 1, 6, 3, 1),
('Vinicius Barros', '95128473607', 'vinicius.barros@example.com', 'vinicius2024', 2, 7, 4, 2),
('Isabela Lima', '78946351208', 'isabela.lima@example.com', 'isabela2024', 3, 8, 1, 1),
('Leonardo Almeida', '45678932109', 'leonardo.almeida@example.com', 'leonardo2024', 1, 9, 2, 2),
('Viviane Rocha', '12378945600', 'viviane.rocha@example.com', 'viviane2024', 2, 10, 3, 1),
('Marcelo Pereira', '32178965401', 'marcelo.pereira@example.com', 'marcelo2024', 3, 1, 4, 1),
('Daniele Cunha', '65498712302', 'daniele.cunha@example.com', 'daniele2024', 1, 2, 1, 2);


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



-- Para 1 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação linear?', '2024-08-01 09:00:00', 1, 1, 1),
('Qual é a importância dos conectivos na escrita?', '2024-08-01 11:00:00', 1, 2, 2),
('Qual o papel das enzimas na digestão?', '2024-08-01 13:00:00', 1, 3, 3),
('Quais são os principais eventos da Revolução Francesa?', '2024-08-01 15:00:00', 1, 4, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Equações lineares podem ser resolvidas isolando a variável.', '2024-08-01 09:30:00', 1, 2),
('A fórmula geral é ax + b = 0.', '2024-08-01 09:45:00', 1, 3),
('Sempre verifique as soluções substituindo-as na equação original.', '2024-08-01 10:00:00', 1, 4),

('Conectivos como "mas", "porque", "portanto" ajudam a conectar ideias.', '2024-08-01 11:30:00', 2, 3),
('Eles são essenciais para a coesão textual.', '2024-08-01 11:45:00', 2, 4),
('Cada conectivo tem uma função específica na argumentação.', '2024-08-01 12:00:00', 2, 1),

('Enzimas aceleram reações químicas no sistema digestivo.', '2024-08-01 13:30:00', 3, 4),
('Elas ajudam na quebra de macromoléculas em moléculas menores.', '2024-08-01 13:45:00', 3, 1),
('A digestão é fundamental para a absorção de nutrientes.', '2024-08-01 14:00:00', 3, 2),

('A Revolução Francesa começou com a queda da Bastilha.', '2024-08-01 15:30:00', 4, 1),
('Outros eventos importantes incluem a Declaração dos Direitos do Homem.', '2024-08-01 15:45:00', 4, 2),
('O período napoleônico também faz parte da Revolução.', '2024-08-01 16:00:00', 4, 3);

-- Para 2 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como fatorar um polinômio quadrático?', '2024-08-02 09:00:00', 1, 1, 2),
('Qual a função dos verbos em uma oração?', '2024-08-02 11:00:00', 1, 2, 3),
('Qual é a importância dos ecossistemas?', '2024-08-02 13:00:00', 1, 3, 4),
('O que foi a Segunda Guerra Mundial?', '2024-08-02 15:00:00', 1, 4, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Polinômios quadráticos podem ser fatorados em dois binômios.', '2024-08-02 09:30:00', 5, 3),
('Use a fórmula de Bhaskara para encontrar as raízes.', '2024-08-02 09:45:00', 5, 4),
('A fatoração ajuda a simplificar a resolução de equações.', '2024-08-02 10:00:00', 5, 1),

('Os verbos são essenciais para expressar ações ou estados.', '2024-08-02 11:30:00', 6, 4),
('Eles podem ser transitivos ou intransitivos.', '2024-08-02 11:45:00', 6, 1),
('Os verbos são o núcleo do predicado.', '2024-08-02 12:00:00', 6, 2),

('Ecossistemas são importantes para a biodiversidade.', '2024-08-02 13:30:00', 7, 1),
('Eles ajudam na regulação do clima.', '2024-08-02 13:45:00', 7, 2),
('A preservação dos ecossistemas é crucial para a saúde ambiental.', '2024-08-02 14:00:00', 7, 3),

('A Segunda Guerra Mundial ocorreu de 1939 a 1945.', '2024-08-02 15:30:00', 8, 2),
('Envolveu muitos países e causou grandes mudanças geopolíticas.', '2024-08-02 15:45:00', 8, 3),
('O conflito terminou com a assinatura do Tratado de Paz.', '2024-08-02 16:00:00', 8, 4);

-- Para 3 de Setembro de 2024
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

-- Para 4 de Setembro de 2024
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

-- Para 5 de Setembro de 2024
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

-- Para 6 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação exponencial?', '2024-08-06 09:00:00', 1, 1, 2),
('Qual a diferença entre adjetivo e advérbio?', '2024-08-06 11:00:00', 1, 2, 3),
('Quais são os tipos de interações entre organismos?', '2024-08-06 13:00:00', 1, 3, 4),
('Quais foram as consequências da Reforma Protestante?', '2024-08-06 15:00:00', 1, 4, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Use logaritmos para resolver equações exponenciais.', '2024-08-06 09:30:00', 21, 3),
('Equações do tipo a^x = b podem ser resolvidas usando log(a^x) = log(b).', '2024-08-06 09:45:00', 21, 4),
('Verifique as condições de existência dos logaritmos.', '2024-08-06 10:00:00', 21, 1),

('Adjetivos qualificam substantivos, enquanto advérbios qualificam verbos.', '2024-08-06 11:30:00', 22, 4),
('Advérbios podem indicar como, quando e onde algo acontece.', '2024-08-06 11:45:00', 22, 1),
('Adjetivos descrevem características dos substantivos.', '2024-08-06 12:00:00', 22, 2),

('Interações entre organismos incluem predatismo e simbiose.', '2024-08-06 13:30:00', 23, 1),
('Simbiose pode ser mutualismo, comensalismo ou parasitismo.', '2024-08-06 13:45:00', 23, 2),
('Essas interações são essenciais para o equilíbrio ecológico.', '2024-08-06 14:00:00', 23, 3),

('A Reforma Protestante levou à divisão da Igreja Católica.', '2024-08-06 15:30:00', 24, 2),
('Foi um movimento que resultou em novas denominações cristãs.', '2024-08-06 15:45:00', 24, 3),
('O evento também teve impacto político e social significativo.', '2024-08-06 16:00:00', 24, 4);

-- Para 7 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como aplicar a fórmula de Bhaskara?', '2024-08-07 09:00:00', 1, 1, 3),
('O que é um advérbio de intensidade?', '2024-08-07 11:00:00', 1, 2, 4),
('Qual o papel das cadeias alimentares no ecossistema?', '2024-08-07 13:00:00', 1, 3, 1),
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

-- Para 8 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a força gravitacional entre dois corpos?', '2024-08-08 09:00:00', 1, 5, 1),
('O que é uma reação de neutralização?', '2024-08-08 11:00:00', 1, 6, 2),
('Qual é a função da estratificação social?', '2024-08-08 13:00:00', 1, 7, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é F = G * (m1 * m2) / r^2.', '2024-08-08 09:30:00', 29, 2),
('G é a constante gravitacional, m1 e m2 são as massas dos corpos.', '2024-08-08 09:45:00', 29, 3),
('r é a distância entre os centros dos corpos.', '2024-08-08 10:00:00', 29, 4),

('Reação de neutralização ocorre quando um ácido e uma base reagem.', '2024-08-08 11:30:00', 30, 3),
('O produto é uma sal e água.', '2024-08-08 11:45:00', 30, 4),
('Pode ser utilizado para ajustar o pH de soluções.', '2024-08-08 12:00:00', 30, 1),

('Estratificação social refere-se à divisão da sociedade em camadas.', '2024-08-08 13:30:00', 31, 1),
('Ela é baseada em fatores como renda, educação e ocupação.', '2024-08-08 13:45:00', 31, 2),
('Essas camadas influenciam as oportunidades e a qualidade de vida.', '2024-08-08 14:00:00', 31, 3);

-- Para 9 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a lei de Newton para a ação e reação?', '2024-08-09 09:00:00', 1, 5, 2),
('Como calcular o pH de uma solução?', '2024-08-09 11:00:00', 1, 6, 3),
('O que é cultura na Sociologia?', '2024-08-09 13:00:00', 1, 7, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Para cada ação, há uma reação igual e oposta.', '2024-08-09 09:30:00', 32, 3),
('Esta lei é uma das bases da física clássica.', '2024-08-09 09:45:00', 32, 4),
('Ela explica a interação entre dois corpos.', '2024-08-09 10:00:00', 32, 1),

('O pH é calculado como o logaritmo negativo da concentração de íons H+.', '2024-08-09 11:30:00', 33, 4),
('Para pH = 7, a solução é neutra.', '2024-08-09 11:45:00', 33, 1),
('Valores abaixo de 7 indicam uma solução ácida.', '2024-08-09 12:00:00', 33, 2),

('Cultura é o conjunto de valores, normas e práticas de um grupo social.', '2024-08-09 13:30:00', 34, 2),
('Ela influencia o comportamento e a identidade dos indivíduos.', '2024-08-09 13:45:00', 34, 3),
('A cultura é transmitida através da socialização.', '2024-08-09 14:00:00', 34, 4);

-- Para 10 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a fórmula para a lei dos gases ideais?', '2024-08-10 09:00:00', 1, 5, 3),
('O que é um ácido de Lewis?', '2024-08-10 11:00:00', 1, 6, 4),
('Como a globalização afeta a sociedade?', '2024-08-10 13:00:00', 1, 7, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é PV = nRT.', '2024-08-10 09:30:00', 35, 4),
('P é a pressão, V é o volume, n é a quantidade de substância, R é a constante dos gases e T é a temperatura.', '2024-08-10 09:45:00', 35, 1),
('A fórmula assume que os gases são ideais.', '2024-08-10 10:00:00', 35, 2),

('Ácidos de Lewis são aceitadores de pares de elétrons.', '2024-08-10 11:30:00', 36, 1),
('Eles podem formar complexos com bases de Lewis.', '2024-08-10 11:45:00', 36, 2),
('Essa definição é mais geral que a de ácidos de Brønsted.', '2024-08-10 12:00:00', 36, 3),

('A globalização promove a integração econômica e cultural.', '2024-08-10 13:30:00', 37, 3),
('Ela pode levar a uma maior homogeneização cultural.', '2024-08-10 13:45:00', 37, 4),
('Também pode causar a perda de identidades culturais locais.', '2024-08-10 14:00:00', 37, 1);

-- Para 11 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a energia cinética de um objeto?', '2024-08-11 09:00:00', 1, 5, 4),
('O que é uma reação de oxirredução?', '2024-08-11 11:00:00', 1, 6, 1),
('Qual o impacto do capitalismo na sociedade?', '2024-08-11 13:00:00', 1, 7, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula é 1/2 * m * v^2.', '2024-08-11 09:30:00', 38, 1),
('m é a massa e v é a velocidade do objeto.', '2024-08-11 09:45:00', 38, 2),
('A energia cinética aumenta com o quadrado da velocidade.', '2024-08-11 10:00:00', 38, 3),

('Reações de oxirredução envolvem a transferência de elétrons.', '2024-08-11 11:30:00', 39, 2),
('O agente redutor doa elétrons e o agente oxidante os recebe.', '2024-08-11 11:45:00', 39, 3),
('Essas reações são fundamentais em processos biológicos e industriais.', '2024-08-11 12:00:00', 39, 4),

('O capitalismo é um sistema econômico baseado na propriedade privada e no lucro.', '2024-08-11 13:30:00', 40, 3),
('Ele pode levar a desigualdades econômicas e sociais.', '2024-08-11 13:45:00', 40, 4),
('O capitalismo também promove inovação e crescimento econômico.', '2024-08-11 14:00:00', 40, 1);

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
('A preparação deve ser feita com precisão para garantir a eficácia.', '2024-08-12 12:00:00', 42, 1),

('As instituições sociais são estruturas que organizam a vida social e regulam comportamentos.', '2024-08-12 13:30:00', 43, 4),
('Elas incluem a família, a escola e o governo.', '2024-08-12 13:45:00', 43, 1),
('Instituições influenciam a socialização e a manutenção da ordem social.', '2024-08-12 14:00:00', 43, 2);

-- Para 13 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a diferença entre energia potencial e cinética?', '2024-08-13 09:00:00', 1, 5, 2),
('O que são ácidos e bases em termos de Brønsted-Lowry?', '2024-08-13 11:00:00', 1, 6, 3),
('Como o positivismo influencia a sociologia?', '2024-08-13 13:00:00', 1, 7, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Energia potencial é a energia armazenada devido à posição ou configuração.', '2024-08-13 09:30:00', 44, 3),
('Energia cinética é a energia do movimento.', '2024-08-13 09:45:00', 44, 4),
('Ambos os tipos de energia são importantes em sistemas físicos.', '2024-08-13 10:00:00', 44, 1),

('Ácidos são doadores de prótons e bases são aceitadores de prótons.', '2024-08-13 11:30:00', 45, 4),
('Essa definição é mais ampla que a de Lewis.', '2024-08-13 11:45:00', 45, 1),
('Permite entender melhor as reações ácido-base.', '2024-08-13 12:00:00', 45, 2),

('O positivismo defende que o conhecimento deve ser baseado em fatos observáveis.', '2024-08-13 13:30:00', 46, 2),
('Ele influencia a sociologia ao enfatizar métodos científicos de estudo.', '2024-08-13 13:45:00', 46, 3),
('Positivismo busca objetividade e precisão na análise social.', '2024-08-13 14:00:00', 46, 4);

-- Para 14 de Setembro de 2024
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

-- Para 15 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são os principais biomas do Brasil?', '2024-08-15 09:00:00', 1, 8, 1),
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

-- Para 16 de Setembro de 2024
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

-- Para 17 de Setembro de 2024
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

-- Para 18 de Setembro de 2024
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

-- Para 19 de Setembro de 2024
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

-- Para 20 de Setembro de 2024
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

-- Para 21 de Setembro de 2024
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

-- Para 22 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são as principais teorias da evolução?', '2024-08-22 09:00:00', 1, 3, 1),
('Como o uso da matemática pode resolver problemas do cotidiano?', '2024-08-22 11:00:00', 1, 1, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Teorias incluem a seleção natural de Darwin e o Lamarquismo.', '2024-08-22 09:30:00', 71, 2),
('A teoria de Darwin é a mais amplamente aceita atualmente.', '2024-08-22 09:45:00', 71, 3),

('Matemática ajuda a otimizar processos, calcular custos e analisar dados.', '2024-08-22 11:30:00', 72, 3),
('É fundamental para a engenharia, finanças e ciências.', '2024-08-22 11:45:00', 72, 4);

-- Para 23 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são fenômenos atmosféricos?', '2024-08-23 09:00:00', 1, 8, 4),
('Quais são os principais períodos da literatura brasileira?', '2024-08-23 11:00:00', 1, 2, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Fenômenos incluem chuvas, ventos e tempestades.', '2024-08-23 09:30:00', 73, 1),
('São causados por interações entre diferentes camadas da atmosfera.', '2024-08-23 09:45:00', 73, 2),

('Períodos incluem o Barroco, Romantismo e Modernismo.', '2024-08-23 11:30:00', 74, 3),
('Cada período tem características distintas na linguagem e temas.', '2024-08-23 11:45:00', 74, 4);

-- Para 24 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como funciona o sistema nervoso?', '2024-08-24 09:00:00', 1, 3, 2),
('Quais são as principais guerras da História Moderna?', '2024-08-24 11:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O sistema nervoso é composto pelo cérebro, medula espinhal e nervos.', '2024-08-24 09:30:00', 75, 3),
('Ele controla as funções corporais e as respostas aos estímulos.', '2024-08-24 09:45:00', 75, 4),

('Principais guerras incluem a Primeira e a Segunda Guerra Mundial.', '2024-08-24 11:30:00', 76, 1),
('Essas guerras tiveram um impacto significativo na história global.', '2024-08-24 11:45:00', 76, 2);

-- Para 25 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a função exponencial?', '2024-08-25 09:00:00', 1, 1, 4),
('Como a sociologia estuda a estrutura social?', '2024-08-25 11:00:00', 1, 7, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A função exponencial é uma função matemática na forma f(x) = a^x.', '2024-08-25 09:30:00', 77, 2),
('É usada para modelar crescimento e decrescimento exponencial.', '2024-08-25 09:45:00', 77, 3),

('A sociologia analisa como instituições e grupos sociais influenciam a sociedade.', '2024-08-25 11:30:00', 78, 3),
('Estuda também as mudanças sociais e os problemas sociais.', '2024-08-25 11:45:00', 78, 4);

-- Para 26 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a importância do ciclo da água?', '2024-08-26 09:00:00', 1, 8, 1),
('Como a matemática é aplicada na engenharia?', '2024-08-26 11:00:00', 1, 1, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O ciclo da água é crucial para o equilíbrio ambiental e o clima.', '2024-08-26 09:30:00', 79, 2),
('Inclui processos como evaporação, condensação e precipitação.', '2024-08-26 09:45:00', 79, 3),

('Matemática é usada para resolver problemas de design e análise de estruturas.', '2024-08-26 11:30:00', 80, 4),
('Também é essencial para a modelagem e simulação de sistemas.', '2024-08-26 11:45:00', 80, 1);

-- Para 27 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que são os elementos da tabela periódica?', '2024-08-27 09:00:00', 1, 6, 3),
('Como a literatura reflete as mudanças sociais?', '2024-08-27 11:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Os elementos são substâncias químicas que constituem a matéria.', '2024-08-27 09:30:00', 81, 4),
('Cada elemento tem propriedades e aplicações específicas.', '2024-08-27 09:45:00', 81, 1),

('A literatura pode refletir as preocupações e valores da época em que foi escrita.', '2024-08-27 11:30:00', 82, 2),
('Ela também pode influenciar e responder às mudanças sociais.', '2024-08-27 11:45:00', 82, 3);

-- Para 28 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Quais são os principais tipos de solo?', '2024-08-28 09:00:00', 1, 8, 2),
('Como a física explica a gravidade?', '2024-08-28 11:00:00', 1, 5, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Tipos de solo incluem argiloso, arenoso e siltoso.', '2024-08-28 09:30:00', 83, 3),
('Cada tipo de solo tem características e usos diferentes.', '2024-08-28 09:45:00', 83, 4),

('A gravidade é uma força que atrai objetos para o centro da Terra.', '2024-08-28 11:30:00', 84, 4),
('É descrita pela lei da gravidade de Newton e a teoria da relatividade de Einstein.', '2024-08-28 11:45:00', 84, 1);

-- Para 29 de Setembro de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual é a importância do sistema solar?', '2024-08-29 09:00:00', 1, 8, 4),
('Como a química é usada na medicina?', '2024-08-29 11:00:00', 1, 6, 1);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O sistema solar é fundamental para entender nosso lugar no universo.', '2024-08-29 09:30:00', 85, 1),
('Ele inclui o Sol, planetas, luas e outros corpos celestes.', '2024-08-29 09:45:00', 85, 2),

('Química é usada para desenvolver medicamentos e tratamentos.', '2024-08-29 11:30:00', 86, 3),
('Ela também ajuda a entender as interações químicas no corpo.', '2024-08-29 11:45:00', 86, 4);

-- Para 30 de Setembro de 2024
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


-- Reações para o dia 1 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
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

-- Reações para o dia 3 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 7, 1),
(1, 7, 2),

(1, 8, 1),
(1, 8, 3),
(1, 8, 5),

(1, 9, 4),
(1, 9, 5);

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

-- Reações para o dia 7 de Setembro de 2024
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

-- Reações para o dia 11 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 31, 2),
(1, 31, 3),

(1, 32, 1),
(1, 32, 2),
(1, 32, 4),

(1, 33, 1),
(1, 33, 5);

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

-- Reações para o dia 13 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 37, 1),
(1, 37, 2),
(1, 37, 4),

(1, 38, 1),
(1, 38, 3),

(1, 39, 2),
(1, 39, 5);

-- Reações para o dia 14 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 40, 1),
(1, 40, 3),

(1, 41, 2),
(1, 41, 4),
(1, 41, 5),

(1, 42, 1),
(1, 42, 3);

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

-- Reações para o dia 17 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 49, 2),
(1, 49, 4),
(1, 49, 5),

(1, 50, 1),
(1, 50, 2),

(1, 51, 1),
(1, 51, 4);

-- Reações para o dia 18 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 52, 2),
(1, 52, 3),

(1, 53, 1),
(1, 53, 4),
(1, 53, 5),

(1, 54, 2),
(1, 54, 3);

-- Reações para o dia 19 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 55, 1),
(1, 55, 4),

(1, 56, 2),
(1, 56, 3),
(1, 56, 5),

(1, 57, 1),
(1, 57, 3);

-- Reações para o dia 20 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 58, 1),
(1, 58, 2),

(1, 59, 2),
(1, 59, 4),
(1, 59, 5),

(1, 60, 1),
(1, 60, 3);

-- Reações para o dia 21 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 61, 1),
(1, 61, 2),

(1, 62, 1),
(1, 62, 4),
(1, 62, 5),

(1, 63, 2),
(1, 63, 4);

-- Reações para o dia 22 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 64, 1),
(1, 64, 3),

(1, 65, 2),
(1, 65, 4),

(1, 66, 1),
(1, 66, 5);

-- Reações para o dia 23 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 67, 1),
(1, 67, 2),
(1, 67, 4),

(1, 68, 2),
(1, 68, 3),

(1, 69, 1),
(1, 69, 5);

-- Reações para o dia 24 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 70, 2),
(1, 70, 4),

(1, 71, 1),
(1, 71, 2),
(1, 71, 3),

(1, 72, 1),
(1, 72, 4);

-- Reações para o dia 25 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 73, 1),
(1, 73, 2),

(1, 74, 3),
(1, 74, 5),

(1, 75, 1),
(1, 75, 3);

-- Reações para o dia 26 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 76, 2),
(1, 76, 4),

(1, 77, 1),
(1, 77, 3),
(1, 77, 5),

(1, 78, 2),
(1, 78, 4);

-- Reações para o dia 27 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 79, 1),
(1, 79, 3),

(1, 80, 2),
(1, 80, 4),

(1, 81, 1),
(1, 81, 5);

-- Reações para o dia 28 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 82, 2),
(1, 82, 4),

(1, 83, 1),
(1, 83, 3),
(1, 83, 5),

(1, 84, 2),
(1, 84, 4);

-- Reações para o dia 29 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 85, 1),
(1, 85, 2),

(1, 86, 1),
(1, 86, 3),
(1, 86, 4),

(1, 87, 2),
(1, 87, 5);

-- Reações para o dia 30 de Setembro de 2024
INSERT INTO reacao (tipo_reacao_id, publicacao_id, usuario_id) VALUES
(1, 88, 1),
(1, 88, 2);
