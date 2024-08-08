import React from 'react';
import styles from './ListaUsuarios.module.css';
import Usuario from '../Usuario';

const ListaUsuarios = () => {
    return (
        <div className={styles["listaUsuarios"]}>
            <Usuario />
            <Usuario />
        </div>
    );
};

export default ListaUsuarios;