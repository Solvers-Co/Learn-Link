import React, { useState } from 'react';
import Styles from '../publicacao/Publicacao.module.css';
import api from '../../../api';
import { toast, ToastContainer } from 'react-toastify';

import Curtir from '../../utils/assets/Curtir.png';
import Comentar from '../../utils/assets/Comentario.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Usuario from '../../utils/assets/Usuario.png';
import Editar from '../../utils/assets/Editar.png';
import Deletar from '../../utils/assets/Deletar.png';
import Denunciar from '../../utils/assets/Deletar.png';

function formatDateTime(dateString) {
    const date = new Date(dateString);

    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear().toString().slice(-2);

    return `${hours}:${minutes} - ${day}/${month}/${year}`;
}

function deletarPublicacao(id) {
    api.delete(`/publicacoes/${id}`)
        .then(response => {
            console.log("Publicação deletada com sucesso:", response.data);
            toast.success("Publicação deletada com sucesso!");
        })
        .catch(error => {
            console.error("Ocorreu um erro ao deletar a publicação:", error);
        });
}

const Publicacao = ({ id, nome, materia, mensagem, horario, curtidas, comentarios, listarComentarios }) => {
    const [showPopup, setShowPopup] = useState(false);

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    return (
        <>
            <div className={Styles['container']}>

                <div className={Styles['header']}>
                    <div className={Styles['materiaBadge']}>{materia}</div>
                    <div className={Styles['menuVertical']} onClick={togglePopup}>
                        <img src={MenuVertical} alt="Menu" />
                    </div>
                </div>

                <div className={Styles['userInfo']}>
                    <img src={Usuario} alt="User" className={Styles['avatar']} />
                    <span className={Styles['nome']}>{nome}</span>
                </div>

                <div className={Styles['mensagem']}>{mensagem}</div>
                <div className={Styles['dataHora']}>{formatDateTime(horario)}</div>

                <div className={Styles['linha']}></div>

                <div className={Styles['footer']}>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{curtidas}</span>
                        <img src={Curtir} alt="Curtir" />
                        <span className={Styles['footerText']}>Curtir</span>
                    </div>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{comentarios}</span>
                        <img src={Comentar} alt="Comentar" />
                        <span className={Styles['footerText']} onClick={() => { listarComentarios(id); }}>Comentários</span>
                    </div>
                </div>

                {showPopup && (
                    <div className={Styles['popup']}>
                        {nomeUsuarioLogado === nome ? (
                            <>
                                <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); }}>
                                    <img src={Editar} alt="Editar" />
                                    Editar
                                </button>

                                <div className={Styles['linhaPopup']}></div>

                                <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); deletarPublicacao(id); }}>
                                    <img src={Deletar} alt="Deletar" />
                                    Excluir
                                </button>
                            </>
                        ) : (
                            <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); /* Lógica para denunciar */ }}>
                                <img src={Denunciar} alt="Denunciar" />
                                Denunciar
                            </button>
                        )}
                    </div>
                )}
                <ToastContainer
                    position="top-right"
                    autoClose={1000}
                    hideProgressBar={false}
                    newestOnTop={false}
                    closeOnClick
                    rtl={false}
                    pauseOnFocusLoss
                    draggable
                    pauseOnHover
                    theme="colored"
                />
            </div>
        </>
    );
}

export default Publicacao;
