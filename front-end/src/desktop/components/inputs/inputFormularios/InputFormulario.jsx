import React from "react";
import styles from "./InputFormulario.module.css";

const Input = (props) =>{
    return(
        <>
            <input className={styles['input']} onChange={props.onChange} type={props.type} placeholder={props.placeholder}/>
        </>
    )
}

export default Input;