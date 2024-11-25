import styles from './Notificacoes.module.css';
import Header from '../../components/headerAplicacao/Header'
import Notificacao from '../../components/notificacao/Notificacao';
import React, { useEffect, useState } from 'react';
import api from "../../../api";
import { toast } from 'react-toastify';
import Modal from 'react-modal';
import Publicacao from '../../components/publicacao/Publicacao';
import Comentario from '../../components/comentario/Comentario';
import Deletar from '../../utils/assets/Deletar.png';
import IconeX from '../../utils/assets/icone_x.svg';

const Notificacoes = () => {
    const [notificacoes, setNotificacoes] = useState([]);
    const [modalIsOpen, setIsOpen] = useState(false);
    const [publicacao, setPublicacao] = useState(null); // Estado para uma única publicação
    const [comentario, setComentarios] = useState(null); // Estado para um único comentário
    const [popupAbertoId, setPopupAbertoId] = useState(null);

    useEffect(() => {
        const fetchNotificacoes = () => {
            api.get(`/notificacoes/${sessionStorage.getItem("userId")}`).then(response => {
                setNotificacoes(response.data);
            }).catch(() => {
                toast.error("Erro ao carregar as notificações");
            });
        };
        fetchNotificacoes();
    }, []);

    function openModal() {
        setIsOpen(true);
    }

    function closeModal() {
        setIsOpen(false);
        setPublicacao(null); // Limpa a publicação ao fechar o modal
    }

    function deletarNotificacao(idUsuario) {
        api.delete(`/notificacoes/deletar-notificacoes/${idUsuario}`).then(response => {
            setNotificacoes([]);
            toast.success("Notificações deletadas com sucesso");
        }).catch(() => {
            toast.error("Erro ao deletar as notificações");
        });
    };

    const formatSubjectName = (name) => {
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
        return subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
    };

    const togglePopup = (id) => {
        setPopupAbertoId(popupAbertoId === id ? null : id);
    };

    const listarComentarios = async (id) => {
        try {
            const response = await api.get(`/comentarios/publicacao/${id}`);
            setComentarios(response.data);
        } catch (error) {
            console.error("Erro ao buscar comentários:", error);
        }
    };

    const carregarConteudoNotificacoes = async (idPublicacao, idComentario) => {
        try {
            // Carregar a publicação
            const responsePublicacao = await api.get(`/publicacoes/${idPublicacao}`);
            setPublicacao(responsePublicacao.data);
            openModal();

            // Verificar se existe um comentário para carregar
            if (idComentario) {
                const responseComentario = await api.get(`/comentarios/${idComentario}`);
                setComentarios(responseComentario.data);
            } else {
                // Limpar o estado do comentário caso não exista um
                setComentarios(null);
            }
        } catch (error) {
            console.error("Erro ao carregar dados:", error);
        }
    };


    const listarComentario = async (idComentario) => {
        try {
            const response = await api.get(`/comentarios/${idComentario}`);
            setComentarios(response.data);
        } catch (error) {
            console.error("Erro ao buscar comentários:", error);
        }
    };

    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    return (
        <>
            <Header />
            <div className={styles.notificacoes}>
                <div className={styles.cabecalho}>
                    <h1 className={styles.titulo}>Notificações</h1>
                    <img src={Deletar}  alt="Deletar" onClick={() => { deletarNotificacao(sessionStorage.getItem('userId')) }} />
                </div>
                <div className={styles.notificacao}>
                    {notificacoes.length > 0 ? (
                        notificacoes.map((notificacao, index) => (
                            <Notificacao
                                key={`${notificacao.id}-${index}`}
                                corDeFundo={'rgb(0, 0, 0, 0.05)'}
                                id={notificacao.id}
                                corpo={notificacao.corpo}
                                nomeUsuarioGerador={notificacao.nomeUsuarioGerador}
                                idUsuarioGerador={notificacao.idUsuarioGerador}
                                nomeUsuarioRecebedor={notificacao.nomeUsuarioRecebedor}
                                vista={notificacao.vista}
                                openModal={openModal}
                                carregarConteudoNotificacoes={() => carregarConteudoNotificacoes(notificacao.idPublicacao, notificacao.idComentario)}
                                idPublicacao={notificacao.idPublicacao}
                                idComentario={notificacao.idComentario}
                            // carregarComentario={() => listarComentario(notificacao.idComentario)}
                            />
                        ))
                    ) : (
                        <p>Nenhuma notificação encontrada</p>
                    )}
                </div>

                <Modal
                    isOpen={modalIsOpen}
                    className={styles.modal}
                    overlayClassName={styles.overlay}
                >
                    <div className={styles.modalContent}>
                        <div className={styles.cabecalhoModal}>
                            <img src={IconeX} className={styles.iconeFechar} alt="Fechar" onClick={() => { closeModal() }} />
                        </div>
                        <div className={styles.publicacao}>
                            {publicacao ? (
                                <Publicacao
                                    id={publicacao.id}
                                    nome={publicacao.usuario.nome}
                                    materia={formatSubjectName(publicacao.canal.nome)}
                                    mensagem={publicacao.conteudo}
                                    horario={publicacao.dataHora}
                                    curtidas={publicacao.reacoes.length}
                                    quemCurtiu={publicacao.reacoes.map(reacao => reacao.usuario.nome)}
                                    comentarios={publicacao.quantidadeComentarios}
                                    listarComentarios={() => listarComentarios(publicacao.id)}
                                    togglePopup={togglePopup}
                                    popupAbertoId={popupAbertoId}
                                    idUsuarioQuePublicou={publicacao.usuario.id}
                                    origem="perfil"
                                    urlImagem={publicacao.urlImagem}
                                />
                            ) : (
                                <p className={styles.textoSemPublicacoes}>Nenhuma publicação encontrada</p>
                            )}
                        </div>

                        <div className={styles.comentario}>
                            {comentario ? (
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
                            ) : (
                                null
                            )}
                        </div>

                    </div>
                </Modal>
            </div>
        </>
    );
}

export default Notificacoes;
