import React from 'react';
import styles from './Titulo.module.css';

const Titulo = (props) => {
    return (
        <h1 className={styles.titulo}>
            {props.children}
        </h1>
    );
};

export default Titulo;
