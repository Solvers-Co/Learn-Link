import React, { useState, useMemo } from 'react';
import Styles from '../comentario/Comentario.module.css';
import api from '../../../api';

import Curtir from '../../utils/assets/Curtir.png';
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

function deletarComentario(id) {
    api.delete(`/comentarios/${id}`)
        .then(response => {
            console.log("Comentário deletado com sucesso:", response.data);
        })
        .catch(error => {
            console.error("Ocorreu um erro ao deletar o comentário:", error);
        });
}

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

const Comentario = ({ id, nome, mensagem, horario, curtidas, nomePublicacao }) => {
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    const confirmarDelecao = () => {
        deletarComentario(id);
        setShowConfirmation(false);
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
                                        <div className={Styles['opcao']} onClick={() => { setShowPopup(false); }}>
                                            <img src={Editar} alt="Editar" />
                                            <span>Editar</span>
                                        </div>
                                    ) : null}

                                    <div className={Styles['linhaPopup']}></div>

                                    <div className={Styles['opcao']} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                                        <img src={Deletar} alt="Deletar" />
                                        <span>Excluir</span>
                                    </div>
                                </>
                            ) : (
                                <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); /* Lógica para denunciar */ }}>
                                    <img src={Denunciar} alt="Denunciar" />
                                    Denunciar
                                </button>
                            )}
                        </div>
                    )}

                    {showConfirmation && (
                        <div className={Styles['modalOverlay']}>
                            <div className={Styles['modalContent']}>
                                <h3>Confirmar Exclusão</h3>
                                <p>Tem certeza de que deseja excluir este comentario?</p>
                                <button className={Styles['confirmButton']} onClick={confirmarDelecao}>Sim</button>
                                <button className={Styles['cancelButton']} onClick={() => setShowConfirmation(false)}>Cancelar</button>
                            </div>
                        </div>
                    )}
                </div>

                <div className={Styles['comentarioMensagem']}>{mensagem}</div>

                <div className={Styles["footerComentario"]}>
                    <div className={Styles["horarioPublicacao"]}>{formatTimeAgo(horario)}</div>
                    <div className={Styles["curtir"]}>
                        <span className={Styles['numero']}>{curtidas}</span>
                        <img src={Curtir} alt="Curtir" /></div>
                </div>
            </div>
            <div className={Styles['linha']}></div>
        </>
    );
}

export default Comentario;
