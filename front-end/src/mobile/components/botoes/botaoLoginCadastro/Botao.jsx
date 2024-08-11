import React from "react";
import styles from './Botao.module.css'

function Botao({ funcao, tipo, className, textoBotao, largura, altura, tamanhoLetra }) {
    return (
        <button 
            onClick={funcao} 
            type={tipo} 
            style={{ width: largura, height: altura, fontSize: tamanhoLetra }}       
            className={`${styles.botaoComum} ${className}`
        }
        >
            {textoBotao}
        </button>
    );
}

export default Botao;
