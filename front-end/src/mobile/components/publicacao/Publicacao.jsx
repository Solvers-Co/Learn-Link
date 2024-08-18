import React, { useState } from 'react';
import Styles from '../publicacao/Publicacao.module.css';

import Curtir from '../../utils/assets/Curtir.png';
import Comentar from '../../utils/assets/Comentario.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Usuario from '../../utils/assets/Usuario.png';
import Editar from '../../utils/assets/Editar.png';
import Deletar from '../../utils/assets/Deletar.png';

const Publicacao = ({ id, nome, materia, mensagem, curtidas, comentarios, onEdit, onDelete }) => {
    const [showPopup, setShowPopup] = useState(false);

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    return (
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
                    <span className={Styles['footerText']}>Comentar</span>
                </div>
            </div>

            {showPopup && (
                <div className={Styles['popup']}>
                    <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); onEdit(id); }}>
                        <img src={Editar} alt="" />
                        Editar
                    </button>
                    
                    <div className={Styles['linhaPopup']}></div>

                    <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); onDelete(id); }}>
                        <img src={Deletar} alt="" />
                        Excluir
                    </button>
                </div>
            )}
        </div>
    );
};

export default Publicacao;
