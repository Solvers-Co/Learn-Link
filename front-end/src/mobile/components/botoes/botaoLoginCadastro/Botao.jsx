import React from "react";
import styles from './Botao.module.css'

function Botao({ funcao, tipo, className, textoBotao }) {
    return (
        <button 
            onClick={funcao} 
            type={tipo} 
            className={`${styles.botaoComum} ${className}`}
        >
            {textoBotao}
        </button>
    );
}

export default Botao;
