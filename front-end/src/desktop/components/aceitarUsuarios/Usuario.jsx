import React from 'react';
import styles from './Usuario.module.css';

const Usuario = () => {
    return (
        <div className={styles.userRow}>
            <span className={styles.userCpf}>123.456.789-01</span>
            <span className={styles.userName}>Sofhia Utaka</span>
            <span className={styles.userEmail}>sofhia.utaka@mafalda.com</span>
            <div className={styles.userStatus}>
                <span className={styles.statusIndicator}></span>
                <span className={styles.userStatus}>Pendente</span>
            </div>
            <button className={`${styles.btn} ${styles.activateBtn}`}>Ativar</button>
            <button className={`${styles.btn} ${styles.denyBtn}`}>Negar</button>
        </div>
    );
};

export default Usuario;
