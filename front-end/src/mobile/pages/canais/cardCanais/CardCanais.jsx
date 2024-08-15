import React from 'react';
import styles from '../cardCanais/CardCanais.module.css'


const Card = ({ image, buttonText, unansweredCount }) => {
    return (
        <div className={styles['card-container']}>
            <div className={styles['card-superior']}>
                <div className={styles['imagem']}>
                    <img src={image} alt="Ícone da disciplina" />
                </div>

                <button className={styles['card-button']}>
                    {buttonText}
                </button>
            </div>

            <hr className={styles['card-divider']} />

            <div className={styles['card-footer']}>
                <span>Publicações sem resposta:</span>
                <span>{unansweredCount}</span>
            </div>
        </div>
    );
};

export default Card;
