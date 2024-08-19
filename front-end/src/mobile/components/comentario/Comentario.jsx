import React, { useState } from 'react';
import Styles from '../comentario/Comentario.module.css';
// import api from '../../../api';

import Usuario from '../../utils/assets/Usuario.png';
import Curtir from '../../utils/assets/Curtir.png';

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

const Comentario = ({ id, nome, mensagem, horario }) => {
    return (
        <>
            <div className={Styles['comentarioContainer']}>
                <div className={Styles['comentarioUserInfo']}>
                    <img src={Usuario} alt="User" className={Styles['avatar']} />
                    <span className={Styles['comentarioNome']}>{nome}</span>
                </div>

                <div className={Styles['comentarioMensagem']}>{mensagem}</div>

                <div className={Styles["footerComentario"]}>
                    <div className={Styles["horarioPublicacao"]}>{formatTimeAgo(horario)}</div>
                    <div className={Styles["curtir"]}><img src={Curtir} alt="Curtir" /></div>
                </div>
            </div>
            <div className={Styles['linha']}></div>
        </>
    );
}
export default Comentario;