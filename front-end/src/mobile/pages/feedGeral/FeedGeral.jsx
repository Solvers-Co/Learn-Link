import React, { useEffect, useState, useRef } from 'react';
import { useLocation } from 'react-router-dom';
import Styles from '../feedGeral/FeedGeral.module.css';
import api from "../../../api";
import Publicacao from '../../components/publicacao/Publicacao';
import Header from '../../components/headerAplicacao/Header';
import Modal from 'react-modal';
import Comentario from '../../components/comentario/Comentario';
import { toast } from 'react-toastify';

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
    const [isSearching, setIsSearching] = useState(false);
    const [searchKeyword, setSearchKeyword] = useState('');

    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [sortDirection, setSortDirection] = useState('desc'); // Estado para controlar a direção do sort
    const [isSortPopupOpen, setIsSortPopupOpen] = useState(false); // Para controlar a exibição do popup de ordenação
    const [textoComentario, setTextoComentario] = useState(''); // Estado para armazenar o comentário
    const [idPublicacaoAtual, setIdPublicacaoAtual] = useState(null); // Estado para armazenar o ID da publicação atual

    const [popupAbertoId, setPopupAbertoId] = useState(null);
    const observerRef = useRef();
    const location = useLocation();
    const canalId = location.state?.canalId;

    const toggleSortPopup = () => {
        setIsSortPopupOpen(prev => !prev);
    };

    const handleSortChange = (direction) => {
        if (direction !== sortDirection) {
            setSortDirection(direction);
            setPublicacoes([]); // Limpa a lista de publicações
            setPage(0); // Reseta para a primeira página ao mudar a ordenação
            setIsSortPopupOpen(false); // Fecha o popup ao escolher uma opção
        } else {
            setIsSortPopupOpen(false); // Apenas fecha o popup se a direção for a mesma
        }
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
                let url = '/publicacoes/publicacoes-mais-recentes-paginado'; // URL padrão

                if (isSearching && searchKeyword.trim() !== '') {
                    // Caso esteja pesquisando, não busca publicações padrão
                    url = `/publicacoes/buscar-palavra-chave-paginado?palavraChave=${encodeURIComponent(searchKeyword)}`; // Alterar conforme sua rota de pesquisa
                } else if (canalId) {
                    url = '/publicacoes/publicacoes-por-canal-paginado'; // Caso esteja filtrando por canal
                }

                const response = await api.get(url, { params });
                const publicacoesRecebidas = response?.data?.content || [];

                if (page === 0) {
                    setPublicacoes(publicacoesRecebidas); // Substitui as publicações quando está na primeira página
                } else {
                    setPublicacoes(prev => [...prev, ...publicacoesRecebidas]); // Concatena as próximas páginas
                }
                console.log(url, response.data);
                setTotalPages(response?.data.totalPages || 0);
            } catch (error) {
                console.error("Erro ao buscar publicações:", error);
            }
        };

        fetchPublicacoes();
    }, [page, canalId, sortDirection, isSearching, searchKeyword]); // Inclui isSearching na dependência



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

    const handleSearchResult = (searchValue) => {
        // Se o campo de pesquisa estiver vazio, reseta os estados de busca
        if (searchValue.trim() === '') {
            setIsSearching(false);
            setSearchKeyword('');
            setPublicacoes([]);  // Reseta a lista de publicações
            setPage(0); // Reinicia a paginação
            return;
        }

        // Se há algo para pesquisar, reinicia o estado de busca
        setIsSearching(true);
        setSearchResults([]); // Limpa os resultados anteriores
        setSearchKeyword(searchValue); // Armazena o valor pesquisado no estado
        setPublicacoes([]);
        setPage(0);
    };

    // Função para capturar o texto do comentário
    const handleInputComentario = (event) => {
        setTextoComentario(event.target.value);
    };

    // Função para enviar o comentário
    const enviarComentario = async () => {
        // Verificação do tamanho do texto
        if (!textoComentario.trim()) {
            toast.error("Digite um comentário antes de enviar!");
            return;
        }

        if (textoComentario.length < 3 || textoComentario.length > 500) {
            toast.error("O comentário deve ter entre 3 e 500 caracteres.");
            return;
        }

        try {
            const idUsuario = sessionStorage.getItem('userId');
            const response = await api.post(`publicacoes/${idPublicacaoAtual}/comentar`, {
                comentario: textoComentario,
                idUsuario,
            });

            console.log("Comentário enviado com sucesso:", response.data);

            // Limpa o campo de texto após enviar o comentário
            setTextoComentario('');

            // Atualiza os comentários após o envio
            listarComentarios(idPublicacaoAtual);

        } catch (error) {
            console.error("Erro ao enviar comentário:", error);
        }
    };


    const listarComentarios = async (id) => {
        try {
            const response = await api.get(`/comentarios/publicacao/${id}`);
            setComentarios(response.data);
            setShowComentarios(true);
            setIdPublicacaoAtual(id); // Define a publicação atual para usar no envio do comentário
            console.log("Comentários da publicação:", response.data);
        } catch (error) {
            console.error("Erro ao buscar comentários:", error);
        }
    };

    const closeComentariosModal = () => {
        setShowComentarios(false);
        setIdPublicacaoAtual(null); // Limpa a publicação atual quando fechar o modal
    };

    const togglePopup = (id) => {
        if (popupAbertoId === id) {
            setPopupAbertoId(null); // Fecha o popup se já estiver aberto
        } else {
            setPopupAbertoId(id); // Abre o popup para o ID correspondente
        }
    };

    const nomeUsuarioLogado = sessionStorage.getItem('nome');
    const publicacoesParaExibir = publicacoes;

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
                                comentarios={publicacao.quantidadeComentarios}
                                listarComentarios={listarComentarios}
                                togglePopup={togglePopup}
                                popupAbertoId={popupAbertoId}
                                idUsuarioQuePublicou={publicacao.usuario.id}
                                urlImagem={publicacao.urlImagem}
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
                    <p
                        onClick={() => handleSortChange('asc')}
                        className={sortDirection === 'asc' ? Styles.sortSelected : ''}
                    >
                        Mais antigas
                    </p>
                    <p
                        onClick={() => handleSortChange('desc')}
                        className={sortDirection === 'desc' ? Styles.sortSelected : ''}
                    >
                        Mais recentes
                    </p>
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
                                idPublicacao={comentario.publicacao.id}
                                idUsuarioQuePublicou={comentario.usuario.id}
                            />
                        ))
                    ) : (
                        <p className={Styles.textoSemComentarios}>Ainda não há comentários nessa publicação<br />Seja o primeiro!</p>
                    )}
                </div>
                <div className={Styles.postarComentario}>
                    <textarea
                        className={Styles.inputComentario}
                        placeholder='Digite aqui...'
                        value={textoComentario} // Conecta o valor ao estado
                        onChange={handleInputComentario} // Atualiza o estado quando o valor muda
                    />
                    <img
                        className={Styles.botaoComentar}
                        src={Enviar}
                        alt="Enviar"
                        onClick={enviarComentario} // Função que envia o comentário
                    />
                </div>
            </Modal>
        </>
    );
}

export default FeedGeral;
