import { useState, useEffect, useRef } from 'react';
import styles from './CardSolucao.module.css';

const CardSolucao = ({ titulo, paragrafo, showButton, largura, altura, imagem }) => {
    const [mostrarImagem, setMostrarImagem] = useState(false);
    const cardRef = useRef(null);

    const handleClick = () => {
        setMostrarImagem(true);
    };

    const handleClickOutside = (event) => {
        if (cardRef.current && !cardRef.current.contains(event.target)) {
            setMostrarImagem(false);
        }
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    return (
        <div 
            ref={cardRef} 
            className={styles.card} 
            style={{ width: largura, height: altura }}
        >
            {mostrarImagem && imagem ? (
                <img src={imagem} alt="Imagem do Card" className={styles.imagem} />
            ) : (
                <div className={styles.conteudoCard}>
                    <h3 className={styles.titulo}>{titulo}</h3>
                    <p className={styles.paragrafo}>{paragrafo}</p>
                    {showButton && <button onClick={handleClick} className={styles.botao}>Veja mais</button>}
                </div>
            )}
        </div>
    );
};

export default CardSolucao;
