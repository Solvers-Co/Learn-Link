import React, { useEffect, useState } from 'react';
import Styles from '../feedGeral/FeedGeral.module.css';
import api from "../../../api";
import Publicacao from '../../components/publicacao/Publicacao';
import Header from '../../components/headerAplicacao/Header';

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

    const publicacoesParaExibir = searchResults || publicacoes;

    return (
        <>
            <Header onSearchResult={handleSearchResult} />
            <div className={Styles['feedGeral']}>
                {publicacoesParaExibir.map((publicacao) => (
                    <Publicacao
                        key={publicacao.id}
                        id={publicacao.id}
                        nome={publicacao.usuario.nome}
                        materia={formatSubjectName(publicacao.canal.nome)}
                        mensagem={publicacao.conteudo}
                        curtidas={5}
                        comentarios={2}
                    />
                ))}
            </div>
        </>
    );
}

export default FeedGeral;
