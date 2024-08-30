import React from 'react';
import styles from './InputFormulario.module.css';

const InputFormulario = (props) => {
    return (
        <>
            <input id={props.id} className={styles['input']} placeholder={props.placeHolder} type={props.tipo} maxLength={props.maxLength} onChange={props.onChange} readOnly={props.readOnly} />
        </>
    );
};

export default InputFormulario;