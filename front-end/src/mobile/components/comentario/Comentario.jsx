import React, { useState, useMemo } from 'react';
import Styles from '../comentario/Comentario.module.css';
import api from '../../../api';
import { toast } from 'react-toastify';
import { generateInitials } from '../../utils/functions/GerarIniciais';
import { useNavigate } from "react-router-dom";

import Curtir from '../../utils/assets/Curtir.png';
import Curtido from '../../utils/assets/Curtido.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Editar from '../../utils/assets/Editar.png';
import Deletar from '../../utils/assets/Deletar.png';
import Denunciar from '../../utils/assets/Denuncia.png';

function formatTimeAgo(dateString) {
    const date = new Date(dateString);
    const now = new Date();
    const diffInSeconds = Math.floor((now - date) / 1000);

    const units = [
        { singular: 'ano', plural: 'anos', seconds: 31536000 },
        { singular: 'mês', plural: 'meses', seconds: 2592000 },
        { singular: 'dia', plural: 'dias', seconds: 86400 },
        { singular: 'hora', plural: 'horas', seconds: 3600 },
        { singular: 'minuto', plural: 'minutos', seconds: 60 },
        { singular: 'segundo', plural: 'segundos', seconds: 1 },
    ];

    for (let unit of units) {
        const interval = Math.floor(diffInSeconds / unit.seconds);
        if (interval >= 1) {
            return `há ${interval} ${interval > 1 ? unit.plural : unit.singular}`;
        }
    }
    return 'agora mesmo';
}

function gerarNotificacao(corpo,usuarioGeradorId, usuarioRecebedorId){
    const notificacao = {
        corpo,
        usuarioGeradorId,
        usuarioRecebedorId
    }
    api.post("/notificacoes", notificacao).then(response =>{
        console.log(response.data)
    }).catch(() =>{
        toast.error("Erro ao gerar notificacao")
    })
}

function reagirComentario(idComentario, idReacao, tipoReacao, idUsuario, curtida, setCurtida, setCurtidas, idUsuarioQuePublicou) {
    if (curtida) {
        // Se o usuário já curtiu, remove a curtida
        api.delete(`/comentarios/${idComentario}/reagir`, { data: { tipoReacao, idUsuario } })
            .then(response => {
                console.log("Reação removida com sucesso:", response.data);
                setCurtida(false);
                setCurtidas(prevCurtidas => prevCurtidas - 1); // Decrementa o contador de curtidas
            })
            .catch(error => {
                console.error("Ocorreu um erro ao remover a reação:", error);
            });
    } else {
        // Se o usuário não curtiu, adiciona a curtida
        api.post(`/comentarios/${idComentario}/reagir`, { tipoReacao, idUsuario })
            .then(response => {
                console.log("Reação registrada com sucesso:", response.data);
                setCurtida(true);
                setCurtidas(prevCurtidas => prevCurtidas + 1); // Incrementa o contador de curtidas
                gerarNotificacao(" curtiu o seu comentário", idUsuario, idUsuarioQuePublicou)
            })
            .catch(error => {
                console.error("Ocorreu um erro ao reagir ao comentário:", error);
            });
    }
}

function deletarComentario(id) {
    api.delete(`/comentarios/${id}`)
        .then(response => {
            console.log("Comentário deletado com sucesso:", response.data);
        })
        .catch(error => {
            console.error("Ocorreu um erro ao deletar o comentário:", error);
        });
}

function denunciarComentario(idComentario, idUsuario) {
    const denunciaData = {
        idUsuario: idUsuario,
    };

    api.post(`/comentarios/${idComentario}/denunciar`, denunciaData)
        .then(response => {
            console.log("Comentário denunciado com sucesso:", response.data);
            toast.success("Comentário denunciado com sucesso!");
        })
        .catch(error => {
            if (error.response && error.response.data && error.response.data.message) {
                toast.error(`Erro ao denunciar: ${error.response.data.message}`);
            } else {
                toast.error("Erro ao denunciar o comentário.");
            }
            // console.error("Ocorreu um erro ao denunciar o comentário:", error);
        });
}

const Comentario = ({ quemCurtiu, id, nome, mensagem, horario, curtidas, idReacao, nomePublicacao, idPublicacao, idUsuarioQuePublicou, emailDeQuemPublicou }) => {
    const [curtida, setCurtida] = useState(quemCurtiu.includes(sessionStorage.getItem('nome')));
    const [numCurtidas, setCurtidas] = useState(curtidas);
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [showDenunciaModal, setShowDenunciaModal] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);
    const [novoComentario, setNovoComentario] = useState(mensagem);

    const navigate = useNavigate();

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    const confirmarDelecao = () => {
        deletarComentario(id);
        setShowConfirmation(false);
    };

    const confirmarDenuncia = () => {
        denunciarComentario(id, idUsuario);
        setShowDenunciaModal(false);
    };

    const confirmarEdicao = () => {
        if (novoComentario.trim() === '') {
            toast.error("O comentário não pode estar em branco.");
            return;
        }
        if (novoComentario.length <= 3) {
            toast.error("O comentário deve ter mais de 3 caracteres.");
            return;
        }
        if (novoComentario.length >= 500) {
            toast.error("O comentário deve ter menos de 500 caracteres.");
            return;
        }

        api.patch(`/comentarios/${id}?comentarioAlterar=${encodeURIComponent(novoComentario)}`)
            .then(response => {
                console.log("Comentário editado com sucesso:", response.data);
                toast.success("Comentário editado com sucesso!");
                setShowEditModal(false);
            })
            .catch(error => {
                console.error("Ocorreu um erro ao editar o comentário:", error.response ? error.response.data : error.message);
                toast.error("Erro ao editar o comentário.");
            });
    };

    const visualizarPerfil = (id) => {
        console.log(id)
        navigate(`/perfil/${id}`)
    }


    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    // Obtem o id do usuário armazenado no sessionStorage
    const idUsuario = sessionStorage.getItem('userId');

    // Gere o avatar com base no nome
    const avatar = useMemo(() => generateInitials(nome), [nome]);

    return (
        <>
            <div className={Styles['comentarioContainer']}>
                <div className={Styles['comentarioUserInfo']}>
                    <div className={Styles["userComentario"]} onClick={() => { visualizarPerfil(idUsuarioQuePublicou) }}>
                        {avatar}
                        <span className={Styles['comentarioNome']}>{nome}</span>
                    </div>
                    <div className={Styles['menuVertical']} onClick={togglePopup}>
                        <img src={MenuVertical} alt="Menu" />
                    </div>

                    {showPopup && (
                        <div className={Styles['popup']}>
                            {nomeUsuarioLogado === nomePublicacao ? (
                                <>
                                    {nomeUsuarioLogado === nome ? (
                                        <>
                                            <div className={Styles['opcao']} onClick={() => { setShowPopup(false); setShowEditModal(true); }}>
                                                <img src={Editar} alt="Editar" />
                                                <span>Editar</span>
                                            </div>
                                            <div className={Styles['linhaPopup']}></div>
                                        </>
                                    ) : null}

                                    <div className={Styles['opcao']} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                                        <img src={Deletar} alt="Deletar" />
                                        <span>Excluir</span>
                                    </div>
                                </>
                            ) : (
                                <div className={Styles['popupButtonDenunciar']} onClick={() => { setShowPopup(false); setShowDenunciaModal(true); }}>
                                    <img src={Denunciar} alt="Denunciar" />
                                    <span>Denunciar</span>
                                </div>
                            )}
                        </div>
                    )}

                    {showEditModal && (
                        <div className={Styles['modalOverlay']}>
                            <div className={Styles['modalContent']}>
                                <h3>Editar Comentário</h3>
                                <textarea
                                    value={novoComentario}
                                    onChange={(e) => setNovoComentario(e.target.value)}
                                />
                                <br />
                                <button className={Styles['confirmButton']} onClick={confirmarEdicao}>Confirmar</button>
                                <button className={Styles['cancelButton']} onClick={() => setShowEditModal(false)}>Cancelar</button>
                            </div>
                        </div>
                    )}

                    {showConfirmation && (
                        <div className={Styles['modalOverlay']}>
                            <div className={Styles['modalContent']}>
                                <h3>Confirmar Exclusão</h3>
                                <p>Tem certeza de que deseja excluir este comentário?</p>
                                <button className={Styles['confirmButton']} onClick={confirmarDelecao}>Sim</button>
                                <button className={Styles['cancelButton']} onClick={() => setShowConfirmation(false)}>Cancelar</button>
                            </div>
                        </div>
                    )}

                    {showDenunciaModal && (
                        <div className={Styles['modalOverlay']}>
                            <div className={Styles['modalContent']}>
                                <h3>Denunciar Comentário</h3>
                                <p>Tem certeza de que deseja denunciar este comentário?</p>
                                <button className={Styles['confirmButton']} onClick={confirmarDenuncia}>Sim</button>
                                <button className={Styles['cancelButton']} onClick={() => setShowDenunciaModal(false)}>Cancelar</button>
                            </div>
                        </div>
                    )}
                </div>

                <div className={Styles['comentarioMensagem']}>{mensagem}</div>

                <div className={Styles["footerComentario"]}>
                    <div className={Styles["horarioPublicacao"]}>{formatTimeAgo(horario)}</div>
                    <div className={Styles["curtir"]}>
                        <span className={Styles['numero']}>{numCurtidas}</span>
                        <img
                            src={curtida ? Curtido : Curtir}
                            alt="Curtir"
                            onClick={() => { reagirComentario(id, idReacao, "CURTIDA", sessionStorage.getItem('userId'), curtida, setCurtida, setCurtidas, idUsuarioQuePublicou) }}
                        />
                    </div>
                </div>
            </div>
            <div className={Styles['linha']}></div>
        </>
    );
};

export default Comentario;