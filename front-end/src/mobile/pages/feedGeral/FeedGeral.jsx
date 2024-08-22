import React, { useEffect, useState } from 'react';
import Styles from '../feedGeral/FeedGeral.module.css';
import api from "../../../api";
import Publicacao from '../../components/publicacao/Publicacao';
import Header from '../../components/headerAplicacao/Header';
import Modal from 'react-modal';
import Comentario from '../../components/comentario/Comentario';
import fechar from '../../utils/assets/icone_x.svg';
import BotaoFazerPublicacao from '../../components/botoes/botaoFazerPublicacao/BotaoFazerPublicacao';
import Filtro from '../../utils/assets/Filtro.png';

import Enviar from '../../utils/assets/Enviar.png';


Modal.setAppElement('#root'); // Necessário para acessibilidade

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
                setShowComentarios(true); // Abre o modal ao listar os comentários
            })
            .catch(error => {
                console.error("Ocorreu um erro ao buscar os comentários:", error);
            });
    };

    const closeComentariosModal = () => {
        setShowComentarios(false); // Fecha o modal
    };

    const publicacoesParaExibir = searchResults || publicacoes;

    return (
        <>
            <Header onSearchResult={handleSearchResult} />
            <div className={Styles['feedGeral']}>
                <div className={Styles['publicarFiltro']}>
                    <BotaoFazerPublicacao/>
                    <img src={Filtro}></img>
                </div>
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
                            listarComentarios={listarComentarios}
                        />
                    ))}
                </div>
            </div>

            <Modal
                isOpen={showComentarios}
                onRequestClose={closeComentariosModal}
                className={Styles['comentariosModal']}
                overlayClassName={Styles['comentariosOverlay']}
            >
                <div className={Styles["fechar"]}><img src={fechar} alt="icone fechar" onClick={closeComentariosModal} /></div>
                <div className={Styles["listaComentarios"]}>
                    {comentariosPublicacao.length > 0 ? (
                        comentariosPublicacao?.map((comentario) => (
                            <Comentario
                                key={comentario.id}
                                id={comentario.id}
                                nome={comentario.usuario.nome}
                                mensagem={comentario.comentario}
                                horario={comentario.dataHora}
                                curtidas={comentario.reacoes.length}
                                nomePublicacao={comentario.publicacao.usuario.nome}
                            />
                        ))
                    ) : (
                        <p className={Styles["textoSemComentarios"]}>Ainda não há comentários nessa publicação <br /> Seja o primeiro!</p>
                    )}
                </div>
                <div className={Styles["postarComentario"]}>
                    <textarea className={Styles["inputComentario"]} type="text" placeholder='Digite aqui...'/>
                    <img className={Styles["botaoComentar"]} src={Enviar} alt="Curtir" />
                </div>
            </Modal>
        </>
    );
}

export default FeedGeral;
