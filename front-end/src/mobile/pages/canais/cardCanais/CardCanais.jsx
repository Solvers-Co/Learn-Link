import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../cardCanais/CardCanais.module.css';

const Card = ({ nomeMateria, image, buttonText, unansweredCount }) => {
    const navigate = useNavigate();

    const idMap = {
        "MATEMATICA": 1,
        "PORTUGUES": 2,
        "BIOLOGIA": 3,
        "HISTORIA": 4,
        "FISICA": 5,
        "QUIMICA": 6,
        "SOCIOLOGIA": 7,
        "GEOGRAFIA": 8,
        "INGLES": 9,
        "FILOSOFIA": 10
    };

    function atribuirId(nomeMateria) {
        return idMap[nomeMateria] || null; // Retorna o ID ou null se a matéria não for encontrada
    }

    const handleClick = () => {
        var canalId = atribuirId(nomeMateria);
        navigate('/feedGeral', { state: { canalId } });
    };

    return (
        <div className={styles['card-container']}>
            <div className={styles['card-superior']}>
                <div className={styles['imagem']}>
                    <img src={image} alt="Ícone da disciplina" />
                </div>

                <button className={styles['card-button']} onClick={handleClick}>
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
