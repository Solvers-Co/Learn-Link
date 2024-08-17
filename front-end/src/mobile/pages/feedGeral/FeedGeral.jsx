import React, { useEffect, useState } from 'react';
import Styles from '../feedGeral/FeedGeral.module.css';
import api from "../../../api";
import Publicacao from '../../components/publicacao/Publicacao';

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

const FeedGeral = ({ }) => {
    const [publicacoes, setPublicacoes] = useState([]);

    useEffect(() => {
        api.get('/publicacoes/publicacoes-mais-recentes')
            .then(response => {
                console.log("Dados recebidos:", response.data);
                setPublicacoes(response.data); // Atualize o estado com os dados recebidos
            })
            .catch(error => {
                console.error("Ocorreu um erro ao buscar os dados:", error);
            });
    }, []);

    return (
        <>
            <div className={Styles['feedGeral']}>
                {publicacoes.map((publicacao) => (
                    <Publicacao
                        key={publicacao.id} //tem q passar uma key p cada item (assim, o react consegue identificar cada item de forma única)
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