import React from 'react';
import styles from './InputFormulario.module.css';

const InputFormulario = (props) =>{
    return (
        <>
            <input className={styles['input']} placeholder={props.placeHolder} type={props.tipo} onChange={props.onChange}/>
        </>
    );
};

export default InputFormulario;