INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a fórmula de Bhaskara?', '2024-05-01 10:00:00', 1, 1, 1),
('Como resolver uma equação do segundo grau?', '2024-05-01 12:00:00', 1, 1, 2),
('O que é um logaritmo?', '2024-05-01 14:00:00', 1, 1, 3),
('Como resolver frações?', '2024-05-01 16:00:00', 1, 1, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula de Bhaskara é x = (-b ± √(b² - 4ac)) / 2a.', '2024-05-01 10:30:00', 1, 2),
('Para resolver, você deve encontrar as raízes da equação.', '2024-05-01 12:30:00', 2, 3),
('Um logaritmo é o expoente ao qual uma base deve ser elevada para obter um número.', '2024-05-01 14:30:00', 3, 1),
('Para resolver frações, encontre o denominador comum.', '2024-05-01 16:30:00', 4, 5);

-- Para 2 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a diferença entre ácidos e bases?', '2024-05-02 09:00:00', 1, 6, 1),
('O que é a tabela periódica?', '2024-05-02 11:00:00', 1, 6, 2),
('Como funciona a ligação covalente?', '2024-05-02 13:00:00', 1, 6, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Ácidos liberam H+ em solução, enquanto bases liberam OH-.', '2024-05-02 09:30:00', 5, 2),
('A tabela periódica organiza os elementos químicos por propriedades.', '2024-05-02 11:30:00', 6, 1);

-- Para 3 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a força resultante?', '2024-05-03 10:00:00', 1, 5, 1),
('O que é inércia?', '2024-05-03 12:00:00', 1, 5, 2),
('Explique a terceira lei de Newton.', '2024-05-03 14:00:00', 1, 5, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A força resultante é a soma vetorial de todas as forças atuantes.', '2024-05-03 10:30:00', 6, 4),
('Inércia é a tendência de um objeto de resistir a mudanças em seu estado de movimento.', '2024-05-03 12:30:00', 7, 5);

-- Para 4 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que foi a Revolução Industrial?', '2024-05-04 09:00:00', 1, 4, 1),
('Quais foram as causas da Primeira Guerra Mundial?', '2024-05-04 11:00:00', 1, 4, 2),
('Explique o Renascimento.', '2024-05-04 13:00:00', 1, 4, 3),
('Quem foi Napoleão Bonaparte?', '2024-05-04 15:00:00', 1, 4, 4),
('O que foi a Guerra Fria?', '2024-05-04 17:00:00', 1, 4, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A Revolução Industrial foi um período de grandes mudanças tecnológicas e econômicas.', '2024-05-04 09:30:00', 10, 2),
('A Primeira Guerra Mundial foi causada por uma combinação de fatores políticos e militares.', '2024-05-04 11:30:00', 11, 3),
('O Renascimento foi um movimento cultural que marcou o fim da Idade Média.', '2024-05-04 13:30:00', 12, 4),
('Napoleão Bonaparte foi um líder militar e político francês.', '2024-05-04 15:30:00', 13, 5),
('A Guerra Fria foi uma rivalidade geopolítica entre os EUA e a URSS.', '2024-05-04 17:30:00', 14, 6);

-- Para 5 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é o teorema de Pitágoras?', '2024-05-05 08:00:00', 1, 1, 3),
('Como calcular a área de um triângulo?', '2024-05-05 10:00:00', 1, 1, 1),
('Qual a função do DNA?', '2024-05-05 12:00:00', 1, 3, 2),
('Explique a fotossíntese.', '2024-05-05 14:00:00', 1, 3, 4),
('O que é um verbo?', '2024-05-05 16:00:00', 1, 2, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O teorema de Pitágoras é a² + b² = c².', '2024-05-05 08:30:00', 15, 4),
('A área de um triângulo é (base * altura) / 2.', '2024-05-05 10:30:00', 16, 3),
('O DNA contém as instruções genéticas para o desenvolvimento.', '2024-05-05 12:30:00', 17, 1),
('A fotossíntese é o processo pelo qual as plantas produzem alimento.', '2024-05-05 14:30:00', 18, 5),
('Um verbo é uma palavra que descreve uma ação.', '2024-05-05 16:30:00', 19, 6);

-- Para 6 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Qual a diferença entre mitose e meiose?', '2024-05-06 09:00:00', 1, 3, 2),
('Como calcular a velocidade média?', '2024-05-06 11:00:00', 1, 5, 3),
('Explique a estrutura do átomo.', '2024-05-06 13:00:00', 1, 6, 4),
('O que é um substantivo?', '2024-05-06 15:00:00', 1, 2, 5),
('O que é a Revolução Francesa?', '2024-05-06 17:00:00', 1, 4, 6);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Mitose é divisão celular para crescimento, meiose é para reprodução.', '2024-05-06 09:30:00', 20, 1),
('Velocidade média é a razão entre a distância percorrida e o tempo.', '2024-05-06 11:30:00', 21, 6),
('O átomo é composto por prótons, nêutrons e elétrons.', '2024-05-06 13:30:00', 22, 3),
('Um substantivo é uma palavra que designa seres, objetos, etc.', '2024-05-06 15:30:00', 23, 1),
('A Revolução Francesa foi um movimento social e político na França.', '2024-05-06 17:30:00', 24, 4);

-- Continue assim para os dias restantes do mês de maio de 2024

-- Para 7 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é o efeito estufa?', '2024-05-07 08:00:00', 1, 3, 2),
('Como funciona a Lei da Gravidade?', '2024-05-07 10:00:00', 1, 5, 3),
('Explique a diferença entre prosa e poesia.', '2024-05-07 12:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O efeito estufa é o aquecimento da Terra pela retenção de calor.', '2024-05-07 08:30:00', 25, 1),
('A Lei da Gravidade é a força de atração entre dois corpos.', '2024-05-07 10:30:00', 26, 5),
('Prosa é escrita em parágrafos, poesia em versos.', '2024-05-07 12:30:00', 27, 6);

-- Para 8 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular o volume de um cilindro?', '2024-05-08 09:00:00', 1, 1, 1),
('O que é o ciclo da água?', '2024-05-08 11:00:00', 1, 3, 2),
('Explique a Revolução Russa.', '2024-05-08 13:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O volume de um cilindro é πr²h.', '2024-05-08 09:30:00', 28, 3),
('O ciclo da água é a circulação contínua da água na Terra.', '2024-05-08 11:30:00', 29, 4),
('A Revolução Russa levou à formação da União Soviética.', '2024-05-08 13:30:00', 30, 5);

-- Para 9 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver sistemas lineares?', '2024-05-09 10:00:00', 1, 1, 1),
('Qual é a fórmula da velocidade?', '2024-05-09 12:00:00', 1, 5, 2);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Use substituição ou eliminação para resolver sistemas.', '2024-05-09 10:30:00', 31, 3),
('A fórmula da velocidade é v = d/t.', '2024-05-09 12:30:00', 32, 6);

-- Para 10 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a Revolução Americana?', '2024-05-10 09:00:00', 1, 4, 2),
('Como funciona a fotossíntese?', '2024-05-10 11:00:00', 1, 3, 3),
('Explique o que é uma metáfora.', '2024-05-10 13:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A Revolução Americana levou à independência dos EUA.', '2024-05-10 09:30:00', 33, 5),
('A fotossíntese é como as plantas produzem energia.', '2024-05-10 11:30:00', 34, 1),
('Uma metáfora é uma figura de linguagem que compara dois elementos.', '2024-05-10 13:30:00', 35, 6);

-- Para 11 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular o perímetro de um círculo?', '2024-05-11 10:00:00', 1, 1, 3),
('O que foi a Segunda Guerra Mundial?', '2024-05-11 12:00:00', 1, 4, 4),
('Explique a divisão celular.', '2024-05-11 14:00:00', 1, 3, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O perímetro de um círculo é 2πr.', '2024-05-11 10:30:00', 36, 2),
('A Segunda Guerra Mundial foi um conflito global de 1939 a 1945.', '2024-05-11 12:30:00', 37, 1),
('Divisão celular é o processo pelo qual uma célula se divide.', '2024-05-11 14:30:00', 38, 4);

-- Para 12 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é uma função quadrática?', '2024-05-12 08:00:00', 1, 1, 1),
('Como calcular o pH de uma solução?', '2024-05-12 10:00:00', 1, 6, 2),
('Explique o Iluminismo.', '2024-05-12 12:00:00', 1, 4, 3),
('O que é a teoria da evolução?', '2024-05-12 14:00:00', 1, 3, 4),
('Qual a função do pronome?', '2024-05-12 16:00:00', 1, 2, 5),
('Como calcular a energia cinética?', '2024-05-12 18:00:00', 1, 5, 6);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Uma função quadrática é do tipo ax² + bx + c.', '2024-05-12 08:30:00', 39, 4),
('O pH é calculado como -log[H+].', '2024-05-12 10:30:00', 40, 3),
('O Iluminismo foi um movimento intelectual do século XVIII.', '2024-05-12 12:30:00', 41, 1),
('A teoria da evolução explica a origem das espécies.', '2024-05-12 14:30:00', 42, 2),
('O pronome substitui ou acompanha o substantivo.', '2024-05-12 16:30:00', 43, 6),
('Energia cinética é calculada como (1/2)mv².', '2024-05-12 18:30:00', 44, 5);

-- Para 13 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como resolver uma equação linear?', '2024-05-13 09:00:00', 1, 1, 1),
('O que é uma reação de combustão?', '2024-05-13 11:00:00', 1, 6, 2),
('Explique a Guerra de Secessão.', '2024-05-13 13:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Para resolver uma equação linear, isole a variável.', '2024-05-13 09:30:00', 45, 5),
('Uma reação de combustão envolve a queima de um combustível com oxigênio.', '2024-05-13 11:30:00', 46, 4),
('A Guerra de Secessão foi um conflito civil nos EUA.', '2024-05-13 13:30:00', 47, 1);

-- Para 14 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('Como calcular a média aritmética?', '2024-05-14 10:00:00', 1, 1, 1),
('O que é a fotossíntese?', '2024-05-14 12:00:00', 1, 3, 2),
('Explique a Revolução Francesa.', '2024-05-14 14:00:00', 1, 4, 3),
('O que é um verbo transitivo?', '2024-05-14 16:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A média aritmética é a soma dos valores dividida pelo número de valores.', '2024-05-14 10:30:00', 48, 3),
('A fotossíntese é como as plantas produzem energia.', '2024-05-14 12:30:00', 49, 5),
('A Revolução Francesa foi um movimento social e político na França.', '2024-05-14 14:30:00', 50, 6),
('Um verbo transitivo requer um objeto para completar seu sentido.', '2024-05-14 16:30:00', 51, 1);

-- Para 15 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a fórmula de Bhaskara?', '2024-05-15 09:00:00', 1, 1, 1),
('Como calcular a aceleração?', '2024-05-15 11:00:00', 1, 5, 2),
('Explique a diferença entre mitose e meiose.', '2024-05-15 13:00:00', 1, 3, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A fórmula de Bhaskara é usada para resolver equações quadráticas.', '2024-05-15 09:30:00', 52, 4),
('A aceleração é a variação da velocidade ao longo do tempo.', '2024-05-15 11:30:00', 53, 6),
('Mitose é divisão celular para crescimento, meiose é para reprodução.', '2024-05-15 13:30:00', 54, 1);

-- Continue assim para os dias restantes do mês de maio de 2024
-- ...

-- Para 16 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é o ângulo de elevação?', '2024-05-16 09:00:00', 1, 1, 2),
('Como calcular a densidade?', '2024-05-16 11:00:00', 1, 5, 3),
('Explique a Lei de Ohm.', '2024-05-16 13:00:00', 1, 5, 4),
('O que é uma metáfora?', '2024-05-16 15:00:00', 1, 2, 5),
('Explique a Guerra dos Cem Anos.', '2024-05-16 17:00:00', 1, 4, 6);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('O ângulo de elevação é o ângulo entre a linha do horizonte e a linha de visão.', '2024-05-16 09:30:00', 55, 1),
('Densidade é massa dividida pelo volume.', '2024-05-16 11:30:00', 56, 4),
('A Lei de Ohm é V = IR.', '2024-05-16 13:30:00', 57, 1),
('Uma metáfora é uma comparação implícita entre duas coisas.', '2024-05-16 15:30:00', 58, 6),
('A Guerra dos Cem Anos foi um conflito entre Inglaterra e França.', '2024-05-16 17:30:00', 59, 3);

-- Para 17 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um número primo?', '2024-05-17 09:00:00', 1, 1, 3),
('Como calcular a força de atrito?', '2024-05-17 11:00:00', 1, 5, 4),
('Explique a diferenciação celular.', '2024-05-17 13:00:00', 1, 3, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um número primo é divisível apenas por 1 e por ele mesmo.', '2024-05-17 09:30:00', 60, 2),
('A força de atrito é a resistência ao movimento entre duas superfícies.', '2024-05-17 11:30:00', 61, 6),
('Diferenciação celular é o processo pelo qual células se especializam.', '2024-05-17 13:30:00', 62, 4);

-- Para 18 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é uma progressão aritmética?', '2024-05-18 10:00:00', 1, 1, 1),
('Explique a tabela periódica.', '2024-05-18 12:00:00', 1, 6, 2),
('Como calcular a área de um círculo?', '2024-05-18 14:00:00', 1, 1, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Uma progressão aritmética é uma sequência de números com a mesma diferença entre eles.', '2024-05-18 10:30:00', 63, 5),
('A tabela periódica organiza os elementos por número atômico.', '2024-05-18 12:30:00', 64, 4),
('A área de um círculo é πr².', '2024-05-18 14:30:00', 65, 6);

-- Para 19 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a Segunda Lei de Newton?', '2024-05-19 09:00:00', 1, 5, 1),
('Como funciona a eletrólise?', '2024-05-19 11:00:00', 1, 6, 2),
('Explique o movimento retilíneo uniforme.', '2024-05-19 13:00:00', 1, 5, 3),
('O que é uma célula eucariótica?', '2024-05-19 15:00:00', 1, 3, 4),
('Como calcular a média ponderada?', '2024-05-19 17:00:00', 1, 1, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A Segunda Lei de Newton é F = ma.', '2024-05-19 09:30:00', 66, 3),
('Eletrólise é a decomposição de substâncias usando corrente elétrica.', '2024-05-19 11:30:00', 67, 5),
('Movimento retilíneo uniforme é o movimento com velocidade constante.', '2024-05-19 13:30:00', 68, 6),
('Célula eucariótica tem núcleo definido.', '2024-05-19 15:30:00', 69, 1),
('Média ponderada é a média onde cada valor tem um peso.', '2024-05-19 17:30:00', 70, 4);

-- Para 20 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a Primeira Lei de Newton?', '2024-05-20 08:00:00', 1, 5, 1),
('Como calcular a força centrípeta?', '2024-05-20 10:00:00', 1, 5, 2),
('Explique a diferença entre massa e peso.', '2024-05-20 12:00:00', 1, 5, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A Primeira Lei de Newton é a lei da inércia.', '2024-05-20 08:30:00', 71, 4),
('Força centrípeta é a força que mantém um objeto em movimento circular.', '2024-05-20 10:30:00', 72, 6),
('Massa é a quantidade de matéria, peso é a força gravitacional sobre a massa.', '2024-05-20 12:30:00', 73, 1);

-- Continue assim para os dias restantes do mês de maio de 2024

-- Para 21 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a terceira lei de Newton?', '2024-05-21 08:00:00', 1, 5, 2),
('Como calcular o volume de uma esfera?', '2024-05-21 10:00:00', 1, 1, 3),
('Explique a fotossíntese.', '2024-05-21 12:00:00', 1, 3, 4),
('O que foi o Renascimento?', '2024-05-21 14:00:00', 1, 4, 5),
('Como calcular a energia potencial?', '2024-05-21 16:00:00', 1, 5, 6);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A terceira lei de Newton é a lei da ação e reação.', '2024-05-21 08:30:00', 74, 1),
('O volume de uma esfera é (4/3)πr³.', '2024-05-21 10:30:00', 75, 5),
('A fotossíntese é o processo pelo qual as plantas produzem energia.', '2024-05-21 12:30:00', 76, 2),
('O Renascimento foi um movimento cultural e artístico na Europa.', '2024-05-21 14:30:00', 77, 3),
('A energia potencial é mgh.', '2024-05-21 16:30:00', 78, 4);

-- Para 22 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é uma célula procariótica?', '2024-05-22 09:00:00', 1, 3, 1),
('Como calcular a velocidade média?', '2024-05-22 11:00:00', 1, 5, 2),
('Explique a Revolução Industrial.', '2024-05-22 13:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Célula procariótica não tem núcleo definido.', '2024-05-22 09:30:00', 79, 4),
('Velocidade média é a razão entre a distância percorrida e o tempo.', '2024-05-22 11:30:00', 80, 1),
('A Revolução Industrial foi a transição para novos processos de fabricação.', '2024-05-22 13:30:00', 81, 5);

-- Para 23 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um triângulo equilátero?', '2024-05-23 10:00:00', 1, 1, 1),
('Como calcular a força gravitacional?', '2024-05-23 12:00:00', 1, 5, 2),
('Explique a Revolução Francesa.', '2024-05-23 14:00:00', 1, 4, 3),
('O que é um advérbio?', '2024-05-23 16:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um triângulo equilátero tem todos os lados iguais.', '2024-05-23 10:30:00', 82, 3),
('Força gravitacional é F = G(m1m2)/r².', '2024-05-23 12:30:00', 83, 4),
('A Revolução Francesa foi um movimento social e político na França.', '2024-05-23 14:30:00', 84, 6),
('Advérbio modifica verbo, adjetivo ou outro advérbio.', '2024-05-23 16:30:00', 85, 1);

-- Para 24 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um número racional?', '2024-05-24 09:00:00', 1, 1, 1),
('Como calcular a energia potencial gravitacional?', '2024-05-24 11:00:00', 1, 5, 2),
('Explique a Revolução Russa.', '2024-05-24 13:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um número racional pode ser expresso como uma fração.', '2024-05-24 09:30:00', 86, 4),
('Energia potencial gravitacional é mgh.', '2024-05-24 11:30:00', 87, 3),
('A Revolução Russa levou à formação da União Soviética.', '2024-05-24 13:30:00', 88, 5);

-- Para 25 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é uma função linear?', '2024-05-25 10:00:00', 1, 1, 1),
('Como calcular a densidade?', '2024-05-25 12:00:00', 1, 6, 2),
('Explique o período regencial no Brasil.', '2024-05-25 14:00:00', 1, 4, 3),
('O que é um verbo intransitivo?', '2024-05-25 16:00:00', 1, 2, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Uma função linear é do tipo y = mx + b.', '2024-05-25 10:30:00', 89, 3),
('Densidade é massa dividida pelo volume.', '2024-05-25 12:30:00', 90, 4),
('O período regencial foi uma fase de transição no Brasil após a abdicação de Dom Pedro I.', '2024-05-25 14:30:00', 91, 5),
('Um verbo intransitivo não requer objeto.', '2024-05-25 16:30:00', 92, 6);

-- Para 26 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é a Segunda Lei de Newton?', '2024-05-26 09:00:00', 1, 5, 1),
('Como calcular a energia cinética?', '2024-05-26 11:00:00', 1, 5, 2),
('Explique a Revolução Industrial.', '2024-05-26 13:00:00', 1, 4, 3);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('A Segunda Lei de Newton é F = ma.', '2024-05-26 09:30:00', 93, 4),
('Energia cinética é (1/2)mv².', '2024-05-26 11:30:00', 94, 5),
('A Revolução Industrial foi a transição para novos processos de fabricação.', '2024-05-26 13:30:00', 95, 1);

-- Para 27 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um ângulo reto?', '2024-05-27 10:00:00', 1, 1, 2),
('Como calcular a resistência elétrica?', '2024-05-27 12:00:00', 1, 5, 3),
('Explique o Iluminismo.', '2024-05-27 14:00:00', 1, 4, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um ângulo reto tem 90 graus.', '2024-05-27 10:30:00', 96, 4),
('Resistência elétrica é V/I.', '2024-05-27 12:30:00', 97, 2),
('O Iluminismo foi um movimento intelectual na Europa.', '2024-05-27 14:30:00', 98, 1);

-- Para 28 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é uma função quadrática?', '2024-05-28 10:00:00', 1, 1, 3),
('Como calcular a velocidade angular?', '2024-05-28 12:00:00', 1, 5, 4),
('Explique a Guerra Fria.', '2024-05-28 14:00:00', 1, 4, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Uma função quadrática é do tipo y = ax² + bx + c.', '2024-05-28 10:30:00', 99, 2),
('Velocidade angular é a variação do ângulo por unidade de tempo.', '2024-05-28 12:30:00', 100, 6),
('A Guerra Fria foi uma tensão política entre EUA e União Soviética.', '2024-05-28 14:30:00', 101, 4);

-- Para 29 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um vetor?', '2024-05-29 09:00:00', 1, 1, 1),
('Como calcular o trabalho realizado?', '2024-05-29 11:00:00', 1, 5, 2),
('Explique a Segunda Guerra Mundial.', '2024-05-29 13:00:00', 1, 4, 3),
('O que é um substantivo concreto?', '2024-05-29 15:00:00', 1, 2, 4),
('Como calcular a dilatação térmica?', '2024-05-29 17:00:00', 1, 5, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um vetor tem magnitude e direção.', '2024-05-29 09:30:00', 102, 4),
('Trabalho realizado é força vezes deslocamento.', '2024-05-29 11:30:00', 103, 6),
('A Segunda Guerra Mundial foi um conflito global de 1939 a 1945.', '2024-05-29 13:30:00', 104, 1),
('Substantivo concreto designa coisas tangíveis.', '2024-05-29 15:30:00', 105, 3),
('Dilatação térmica é a variação do comprimento de um material com a temperatura.', '2024-05-29 17:30:00', 106, 2);

-- Para 30 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um ângulo agudo?', '2024-05-30 09:00:00', 1, 1, 2),
('Como calcular a corrente elétrica?', '2024-05-30 11:00:00', 1, 5, 3),
('Explique a Revolução Gloriosa.', '2024-05-30 13:00:00', 1, 4, 4);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um ângulo agudo é menor que 90 graus.', '2024-05-30 09:30:00', 107, 1),
('Corrente elétrica é a quantidade de carga por unidade de tempo.', '2024-05-30 11:30:00', 108, 5),
('A Revolução Gloriosa foi a derrubada do rei James II na Inglaterra.', '2024-05-30 13:30:00', 109, 6);

-- Para 31 de Maio de 2024
INSERT INTO publicacao (conteudo, data_hora, tipo_publicacao_id, canal_id, usuario_id) VALUES
('O que é um número irracional?', '2024-05-31 10:00:00', 1, 1, 3),
('Como calcular a velocidade de propagação de uma onda?', '2024-05-31 12:00:00', 1, 5, 4),
('Explique a Guerra de Secessão.', '2024-05-31 14:00:00', 1, 4, 5);

INSERT INTO comentario (comentario, data_hora, publicacao_id, usuario_id) VALUES
('Um número irracional não pode ser expresso como uma fração.', '2024-05-31 10:30:00', 110, 4),
('Velocidade de propagação de uma onda é frequência vezes comprimento de onda.', '2024-05-31 12:30:00', 111, 1),
('A Guerra de Secessão foi um conflito civil nos EUA.', '2024-05-31 14:30:00', 112, 3);



INSERT INTO registro_login (registro_login, usuario_id) VALUES
-- Maio 1 a 10
('2024-05-01 08:15:00', 1),
('2024-05-01 18:45:00', 1),
('2024-05-02 10:30:00', 1),
('2024-05-02 15:20:00', 1),
('2024-05-03 09:00:00', 1),
('2024-05-03 11:10:00', 1),
('2024-05-04 14:50:00', 1),
('2024-05-04 16:30:00', 1),
('2024-05-07 08:20:00', 1),
('2024-05-08 12:35:00', 1),
('2024-05-09 14:00:00', 1),
('2024-05-10 11:25:00', 1),
-- Maio 11 a 20
('2024-05-11 09:15:00', 1),
('2024-05-12 08:45:00', 1),
('2024-05-12 14:30:00', 1),
('2024-05-13 10:50:00', 1),
('2024-05-13 17:35:00', 1),
('2024-05-15 11:30:00', 1),
('2024-05-16 12:15:00', 1),
('2024-05-17 08:20:00', 1),
('2024-05-17 15:50:00', 1),
('2024-05-18 14:40:00', 1),
('2024-05-19 10:05:00', 1),
('2024-05-19 16:25:00', 1),
-- Maio 21 a 31
('2024-05-21 13:55:00', 1),
('2024-05-21 17:40:00', 1),
('2024-05-22 10:45:00', 1),
('2024-05-24 08:20:00', 1),
('2024-05-24 14:00:00', 1),
('2024-05-25 15:45:00', 1),
('2024-05-26 11:10:00', 1),
('2024-05-26 13:35:00', 1),
('2024-05-27 10:05:00', 1),
('2024-05-27 19:00:00', 1),
('2024-05-29 12:40:00', 1),
('2024-05-30 08:05:00', 1),
('2024-05-30 18:00:00', 1),
('2024-05-31 07:30:00', 1),
('2024-05-31 11:50:00', 1);