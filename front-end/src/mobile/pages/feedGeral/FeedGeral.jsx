import React, { useEffect, useState } from 'react';
import Styles from '../feedGeral/FeedGeral.module.css';
import api from "../../../api";
import Publicacao from '../../components/publicacao/Publicacao';
import Header from '../../components/headerAplicacao/Header';

import Comentario from '../../components/comentario/Comentario';
import fechar from '../../utils/assets/icone_x.svg';

const subjectNameMap = {
    'MATEMATICA': 'Matemática',
    'HISTORIA': 'História',
    'GEOGRAFIA': 'Geografia',
    'QUIMICA': 'Química',
    'PORTUGUES': 'Português',
    'FISICA': 'Física',
    'BIOLOGIA': 'Biologia',
    'INGLES': 'Inglês',
    'FILOSOFIA': 'Filosofia',
    'SOCIOLOGIA': 'Sociologia',
};

const formatSubjectName = (name) => {
    return subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
};

const FeedGeral = () => {
    const [publicacoes, setPublicacoes] = useState([]);
    const [showComentarios, setShowComentarios] = useState(false);
    const [comentariosPublicacao, setComentarios] = useState([]);
    const [searchResults, setSearchResults] = useState(null); // armazena resultados da busca

    useEffect(() => {
        if (!searchResults) { // Apenas faz a requisição se não houver a busca
            api.get('/publicacoes/publicacoes-mais-recentes')
                .then(response => {
                    console.log("Dados recebidos:", response.data);
                    setPublicacoes(response.data);
                })
                .catch(error => {
                    console.error("Ocorreu um erro ao buscar os dados:", error);
                });
        }
    }, [searchResults]);

    const handleSearchResult = (results) => {
        setSearchResults(results);
    };

    const listarComentarios = (id) => {
        api.get(`/comentarios/publicacao/${id}`)
            .then(response => {
                console.log("Comentários recebidos:", response.data);
                setComentarios(response.data);
            })
            .catch(error => {
                console.error("Ocorreu um erro ao buscar os comentários:", error);
            });
    };

    const toggleComentarios = () => {
        setShowComentarios(!showComentarios);
    };

    const publicacoesParaExibir = searchResults || publicacoes;

    return (
        <>
            <Header onSearchResult={handleSearchResult} />
            <div className={Styles['feedGeral']}>
                {!showComentarios && (
                    <div className={Styles['publicacoes']}>
                        {publicacoesParaExibir.map((publicacao) => (
                            <Publicacao
                                key={publicacao.id}
                                id={publicacao.id}
                                nome={publicacao.usuario.nome}
                                materia={formatSubjectName(publicacao.canal.nome)}
                                mensagem={publicacao.conteudo}
                                horario={publicacao.dataHora}
                                curtidas={5}
                                comentarios={publicacao.comentarios.length}
                                onComentariosClick={toggleComentarios} // Passa a função para o componente Publicacao
                                listarComentarios={listarComentarios}
                            />
                        ))}
                    </div>
                )}
            </div>

            {showComentarios && (
                <div className={Styles["comentarios"]}>
                    <div className={Styles["fechar"]}><img src={fechar} alt="icone fechar" onClick={toggleComentarios} /></div>
                    <div className={Styles["listaComentarios"]}>
                        {comentariosPublicacao.length > 0 ? (
                            comentariosPublicacao.map((comentario) => (
                                <Comentario
                                    key={comentario.id}
                                    id={comentario.id}
                                    nome={comentario.usuario.nome}
                                    mensagem={comentario.comentario}
                                    horario={comentario.dataHora}
                                    curtidas={comentario.reacoes.length}
                                />
                            ))
                        ): (
                            <p className={Styles["textoSemComentarios"]}>Ainda não há comentarios nessa publicação <br/> Seja o primeiro!</p>
                        )}
                    </div>
                </div>
            )}
        </>
    );
}

export default FeedGeral;
