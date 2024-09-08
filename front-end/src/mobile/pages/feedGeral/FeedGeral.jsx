import React, { useEffect, useState, useRef } from 'react';
import { useLocation } from 'react-router-dom'; 
import Styles from '../feedGeral/FeedGeral.module.css';
import api from "../../../api";
import Publicacao from '../../components/publicacao/Publicacao';
import Header from '../../components/headerAplicacao/Header';
import Modal from 'react-modal';
import Comentario from '../../components/comentario/Comentario';
import fechar from '../../utils/assets/icone_x.svg';
import Filtro from '../../utils/assets/Filtro direcao.png';
import Enviar from '../../utils/assets/Enviar.png';
import BotaoFazerPublicacao from '../../components/botoes/botaoFazerPublicacao/BotaoFazerPublicacao';

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
    'DOACOES': 'Doações',
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
    const [sortDirection, setSortDirection] = useState('desc'); // Estado para controlar a direção do sort
    const [isSortPopupOpen, setIsSortPopupOpen] = useState(false); // Para controlar a exibição do popup de ordenação

    const [popupAbertoId, setPopupAbertoId] = useState(null);
    const observerRef = useRef();
    const location = useLocation();
    const canalId = location.state?.canalId;

    const toggleSortPopup = () => {
        setIsSortPopupOpen(prev => !prev);
    };

    const handleSortChange = (direction) => {
        setSortDirection(direction);
        setPage(0);
        setPublicacoes([]); // Limpa a lista para evitar duplicações ao trocar a ordenação
        setIsSortPopupOpen(false); // Fecha o popup ao escolher uma opção
    };

    useEffect(() => {
        const fetchPublicacoes = async () => {
            try {
                const params = {
                    page,
                    size: 20,
                    sortDirection,
                    ...(canalId && { canalId }),
                };
                const url = canalId ? '/publicacoes/publicacoes-por-canal-paginado' : '/publicacoes/publicacoes-mais-recentes-paginado';
                const response = await api.get(url, { params });
                const publicacoesRecebidas = response?.data?.content || [];
                
                setPublicacoes(prev => [...prev, ...publicacoesRecebidas]);
                setTotalPages(response?.data.totalPages || 0);
                
                console.log("Publicacoes recebidas:", response.data.content);
            } catch (error) {
                console.error("Erro ao buscar publicações:", error);
            }
        };

        fetchPublicacoes();
    }, [page, canalId, sortDirection]);

    useEffect(() => {
        const observer = new IntersectionObserver(([entry]) => {
            if (entry.isIntersecting && page < totalPages - 1) {
                setPage(prevPage => prevPage + 1);
            }
        }, {
            root: null,
            rootMargin: '20px',
            threshold: 1.0
        });

        if (observerRef.current) {
            observer.observe(observerRef.current);
        }

        return () => {
            if (observerRef.current) {
                observer.unobserve(observerRef.current);
            }
        };
    }, [totalPages]);

    const handleSearchResult = (results) => {
        setSearchResults(results);
    };

    const listarComentarios = async (id) => {
        try {
            const response = await api.get(`/comentarios/publicacao/${id}`);
            setComentarios(response.data);
            setShowComentarios(true);
        } catch (error) {
            console.error("Erro ao buscar comentários:", error);
        }
    };

    const closeComentariosModal = () => {
        setShowComentarios(false);
    };

    const togglePopup = (id) => {
        if (popupAbertoId === id) {
            setPopupAbertoId(null); // Fecha o popup se já estiver aberto
        } else {
            setPopupAbertoId(id); // Abre o popup para o ID correspondente
        }
    };

    const nomeUsuarioLogado = sessionStorage.getItem('nome');
    const publicacoesParaExibir = searchResults || publicacoes;

    return (
        <>
            <Header onSearchResult={handleSearchResult} />
            <div className={Styles.feedGeral}>
                <div className={Styles.publicarFiltro}>
                    <BotaoFazerPublicacao />
                    <img src={Filtro} alt="Filtro" onClick={toggleSortPopup} />
                </div>
                <div className={Styles.publicacoes}>
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
                                togglePopup={togglePopup}
                                popupAbertoId={popupAbertoId}
                            />
                        ))
                    ) : (
                        <p className={Styles.textoSemPublicacoes}>Nenhuma publicação encontrada</p>
                    )}
                    <div ref={observerRef} />
                </div>
            </div>

            <Modal
                isOpen={isSortPopupOpen}
                onRequestClose={toggleSortPopup}
                className={Styles.sortPopup}
                overlayClassName={Styles.overlay}
            >
                <div className={Styles.sortOptions}>
                    <span>Ordenação</span>
                    <p onClick={() => handleSortChange('asc')}>Mais antigas</p>
                    <p onClick={() => handleSortChange('desc')}>Mais recentes</p>
                </div>
            </Modal>

            <Modal
                isOpen={showComentarios}
                onRequestClose={closeComentariosModal}
                className={Styles.comentariosModal}
                overlayClassName={Styles.comentariosOverlay}
            >
                <div className={Styles.fechar}><img src={fechar} alt="Fechar" onClick={closeComentariosModal} /></div>
                <div className={Styles.listaComentarios}>
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
                        <p className={Styles.textoSemComentarios}>Ainda não há comentários nessa publicação<br />Seja o primeiro!</p>
                    )}
                </div>
                <div className={Styles.postarComentario}>
                    <textarea className={Styles.inputComentario} placeholder='Digite aqui...' />
                    <img className={Styles.botaoComentar} src={Enviar} alt="Enviar" />
                </div>
            </Modal>
        </>
    );
}

export default FeedGeral;
