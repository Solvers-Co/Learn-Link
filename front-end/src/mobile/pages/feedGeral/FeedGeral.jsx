import React, { useEffect, useState, useRef } from 'react';
import { useLocation } from 'react-router-dom'; // Importar useLocation
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

Modal.setAppElement('#root');

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
    const [searchResults, setSearchResults] = useState(null);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const observer = useRef();

    const location = useLocation(); // Obter o state da navegação
    const canalId = location.state?.canalId; // Verificar se canalId foi passado

    useEffect(() => {
        const fetchPublicacoes = async () => {
            try {
                let url = '/publicacoes/publicacoes-mais-recentes-paginado';
                const params = {
                    page: page,
                    size: 20,
                };

                if (canalId) {
                    url = '/publicacoes/publicacoes-por-canal-paginado';
                    params.canalId = canalId;
                }

                const response = await api.get(url, { params });

                const publicacoesRecebidas = response?.data?.content || []; // Garante que será um array

                setPublicacoes(prevPublicacoes => [...prevPublicacoes, ...publicacoesRecebidas]);
                setTotalPages(response?.data.totalPages || 0); // Garante que totalPages será um número

                console.log("Dados recebidos:", response?.data);
            } catch (error) {
                console.error("Ocorreu um erro ao buscar os dados:", error);
            }
        };


        fetchPublicacoes();
    }, [page, canalId]);

    useEffect(() => {
        if (observer.current) {
            const handleObserver = ([entry]) => {
                if (entry.isIntersecting && page < totalPages - 1) {
                    setPage(prevPage => prevPage + 1);
                }
            };

            const options = {
                root: null,
                rootMargin: '20px',
                threshold: 1.0
            };

            const observerInstance = new IntersectionObserver(handleObserver, options);
            observerInstance.observe(observer.current);

            return () => observerInstance.disconnect();
        }
    }, [page, totalPages]);

    const handleSearchResult = (results) => {
        setSearchResults(results);
    };

    const listarComentarios = (id) => {
        api.get(`/comentarios/publicacao/${id}`)
            .then(response => {
                console.log("Comentários recebidos com sucesso:", response.data);
                setComentarios(response.data);
                setShowComentarios(true);
            })
            .catch(error => {
                console.error("Ocorreu um erro ao buscar os comentários:", error);
            });
    };

    const closeComentariosModal = () => {
        setShowComentarios(false);
    };

    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    const publicacoesParaExibir = searchResults || publicacoes;

    return (
        <>
            <Header onSearchResult={handleSearchResult} />
            <div className={Styles['feedGeral']}>
                <div className={Styles['publicarFiltro']}>
                    <BotaoFazerPublicacao />
                    <img src={Filtro} alt="Filtro" />
                </div>
                <div className={Styles['publicacoes']}>
                    {publicacoesParaExibir.length > 0 ? (
                        publicacoesParaExibir.map((publicacao, index) => (
                            <Publicacao
                                key={`${publicacao.id}-${index}`}
                                id={publicacao.id}
                                nome={publicacao.usuario.nome}
                                materia={formatSubjectName(publicacao.canal.nome)}
                                mensagem={publicacao.conteudo}
                                horario={publicacao.dataHora}
                                curtidas={publicacao.reacoes.length}
                                quemCurtiu={publicacao.reacoes.map(reacao => reacao.usuario.nome)}
                                comentarios={publicacao.comentarios.length}
                                listarComentarios={listarComentarios}
                            />
                        ))
                    ) : (
                        <p className={Styles["textoSemPublicacoes"]}>Nenhuma publicação encontrada</p>
                    )}
                    <div ref={observer} />
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
                        comentariosPublicacao.map((comentario) => (
                            <Comentario
                                key={comentario.id}
                                id={comentario.id}
                                nome={comentario.usuario.nome}
                                mensagem={comentario.comentario}
                                horario={comentario.dataHora}
                                quemCurtiu={comentario.reacoes.map(reacao => reacao.usuario.nome)}
                                idReacao={comentario.reacoes.find(reacao => reacao.usuario.nome === nomeUsuarioLogado)?.id}
                                curtidas={comentario.reacoes.length}
                                nomePublicacao={comentario.publicacao.usuario.nome}
                            />
                        ))
                    ) : (
                        <p className={Styles["textoSemComentarios"]}>Ainda não há comentários nessa publicação <br /> Seja o primeiro!</p>
                    )}
                </div>
                <div className={Styles["postarComentario"]}>
                    <textarea className={Styles["inputComentario"]} type="text" placeholder='Digite aqui...' />
                    <img className={Styles["botaoComentar"]} src={Enviar} alt="Curtir" />
                </div>
            </Modal>
        </>
    );
}

export default FeedGeral;
