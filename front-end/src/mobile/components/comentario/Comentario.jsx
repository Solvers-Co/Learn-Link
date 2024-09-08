import React, { useState, useMemo } from 'react';
import Styles from '../comentario/Comentario.module.css';
import api from '../../../api';

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


function reagirComentario(idComentario, idReacao, tipoReacao, idUsuario, curtida, setCurtida, setCurtidas) {
    if (curtida) {
        // Se o usuário já curtiu, remove a curtida
        api.delete(`/comentarios/${idComentario}/reagir/${idReacao}`)
            .then(response => {
                console.log("Reação removida com sucesso:", response.data);
                setCurtida(false);
                setCurtidas(prevCurtidas => prevCurtidas - 1); // Decrementa o contador de curtidas
            })
            .catch(error => {
                console.log(idReacao);
                console.error("Ocorreu um erro ao remover a reação:", error);
            });
    } else {
        // Se o usuário não curtiu, adiciona a curtida
        api.post(`/comentarios/${idComentario}/reagir`, { tipoReacao, idUsuario })
            .then(response => {
                console.log("Reação registrada com sucesso:", response.data);
                setCurtida(true);
                setCurtidas(prevCurtidas => prevCurtidas + 1); // Incrementa o contador de curtidas
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

function denunciarComentario() {
    console.log("A logica de denunciar comentarios ainda não foi implementada.")

    // Enviar a denúncia para o backend
    // api.post(`/comentarios/${id}/denunciar`, { motivo: motivoDenuncia, idUsuario: sessionStorage.getItem('userId') })
    //     .then(response => {
    //         console.log("Denúncia enviada com sucesso:", response.data);
    //         setShowDenunciaModal(false);
    //     })
    //     .catch(error => {
    //         console.error("Erro ao enviar a denúncia:", error);
    //     });
};

function generateInitials(name) {
    const nameParts = name.trim().split(' ');
    const firstInitial = nameParts[0].charAt(0).toUpperCase();
    const lastInitial = nameParts[nameParts.length - 1].charAt(0).toUpperCase();

    const pastelColors = [
        '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
        '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
        '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    ];

    const randomIndex = Math.floor(Math.random() * pastelColors.length);
    const backgroundColor = pastelColors[randomIndex];

    const avatar = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        width: '35px',
        height: '35px',
        marginRight: '12px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSansExtraBold", sans-serif',
        backgroundColor
    };

    return <div style={avatar}>{firstInitial + lastInitial}</div>;
}

const Comentario = ({ quemCurtiu, id, nome, mensagem, horario, curtidas, idReacao, nomePublicacao }) => {
    const [curtida, setCurtida] = useState(quemCurtiu.includes(sessionStorage.getItem('nome')));
    const [numCurtidas, setCurtidas] = useState(curtidas);
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [showDenunciaModal, setShowDenunciaModal] = useState(false);
    const [motivoDenuncia, setMotivoDenuncia] = useState('');

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    const confirmarDelecao = () => {
        deletarComentario(id);
        setShowConfirmation(false);
    };

    const confirmarDenuncia = () => {
        denunciarComentario();
        setShowDenunciaModal(false);
    };

    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    // Gere o avatar com base no nome
    const avatar = useMemo(() => generateInitials(nome), [nome]);

    return (
        <>
            <div className={Styles['comentarioContainer']}>
                <div className={Styles['comentarioUserInfo']}>
                    <div className={Styles["userComentario"]}>
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
                                            <div className={Styles['opcao']} onClick={() => { setShowPopup(false); }}>
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
                            onClick={() => { reagirComentario(id, idReacao, "CURTIDA", sessionStorage.getItem('userId'), curtida, setCurtida, setCurtidas) }}
                        />
                    </div>
                </div>
            </div>
            <div className={Styles['linha']}></div>
        </>
    );
};

export default Comentario;