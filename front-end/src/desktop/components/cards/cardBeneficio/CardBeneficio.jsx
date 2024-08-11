import { useState } from 'react';
import styles from './CardBeneficio.module.css';

const CardBeneficio = ({ titulo, paragrafo, showButton, largura, altura, imagem }) => {
    const [mostrarImagem, setMostrarImagem] = useState(false);

    const handleClick = () => {
        setMostrarImagem(true);
    };

    const handleMouseLeave = () => {
        setMostrarImagem(false);
    };

    return (
        <div 
            className={styles.card} 
            style={{ width: largura, height: altura }}
            onMouseLeave={handleMouseLeave}
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

export default CardBeneficio;
