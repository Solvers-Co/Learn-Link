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

// import Comentario from '../comentario/Comentario';
// import listarComentarios from '../../pages/feedGeral/FeedGeral';

function formatDateTime(dateString) {
    const date = new Date(dateString);
  
    // Extrair hora e minutos
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
  
    // Extrair dia, mês e ano
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear().toString().slice(-2); // Pega os últimos dois dígitos do ano
  
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

const Publicacao = ({ id, nome, materia, mensagem, horario, curtidas, comentarios, onComentariosClick, listarComentarios }) => {
    const [showPopup, setShowPopup] = useState(false);
    

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

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
                    <span className={Styles['footerText']} onClick={() => {listarComentarios(id); onComentariosClick();}}>Comentarios</span>
                </div>
            </div>

            {showPopup && (
                <div className={Styles['popup']}>
                    <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); }}>
                        <img src={Editar} alt="" />
                        Editar
                    </button>

                    <div className={Styles['linhaPopup']}></div>

                    <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); deletarPublicacao(id); }}>
                        <img src={Deletar} alt="" />
                        Excluir
                    </button>

                    <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); /* Lógica para denunciar */ }}>
                        <img src={Denunciar} alt="" />
                        Denunciar
                    </button>
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
                theme="light"
            />

        </div>        
        </>
    );
};

export default Publicacao;
