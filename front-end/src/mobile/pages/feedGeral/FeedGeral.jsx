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
        // tem algum erro relacionado ao retorno do componente com key mas nn sei oq é (acho que isso é o de menos): https://react.dev/learn/rendering-lists#keeping-list-items-in-order-with-key
        <>
            <div className={Styles['feedGeral']}>
                {publicacoes.map((publicacao) => (
                    <Publicacao
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