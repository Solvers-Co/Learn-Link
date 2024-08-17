import React, { useEffect, useState } from 'react';
import Styles from '../publicacao/Publicacao.module.css';


import Curtir from '../../utils/assets/Curtir.png';
import Comentar from '../../utils/assets/Comentario.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Usuario from '../../utils/assets/Usuario.png';


// tenho a ideia de separar um select(endpoint) para as publicações e outro para os comentários 
// pq ai quando clicar no botão de comentar ele vai fazer um select para pegar os comentários da publicação especifica 
// (não sei como pegar o id de uma publicação especifica dps do map do feedGeral, mas to passando o id como prop para usar nessa página)

//isso precisa de uma grande refatorção no backend e banco

const Publicacao = ({ id, nome, materia, mensagem, curtidas, comentarios }) => {
    return (
        <div className={Styles['container']}>

            <div className={Styles['header']}>
                <div className={Styles['materiaBadge']}>{materia}</div>
                <div className={Styles['menuVertical']}><img src={MenuVertical} alt="" /></div>
            </div>

            <div className={Styles['userInfo']}>
                <img
                    src={Usuario} alt="User"
                    className={Styles['avatar']}
                />
                <span className={Styles['nome']}>{nome}</span>
            </div>

            <div className={Styles['mensagem']}>{mensagem}</div>

            <div className={Styles['linha']}></div>


            <div className={Styles['footer']}>

                <div className={Styles['footerItem']}>
                    <span className={Styles['numero']}>{curtidas}</span>
                    <img src={Curtir} alt="" />
                    <span className={Styles['footerText']}>Curtir</span>
                </div>

                <div className={Styles['footerItem']}>
                    <span className={Styles['numero']}>{comentarios}</span>
                    <img src={Comentar} alt="" />
                    <span className={Styles['footerText']}>Comentar</span>

                </div>
            </div>
        </div>
    );
};

export default Publicacao;
