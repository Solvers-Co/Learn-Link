import React from "react";
import styles from "./Botao.module.css";

function Botao(props){
    return(
        <button onClick={props.funcao} type={props.tipo} className={styles['botaoVermelho']}>{props.textoBotao}</button>    
    )
}

export default Botao;