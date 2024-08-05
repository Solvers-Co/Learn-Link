import React from 'react';
import styles from './TituloKpi.module.css';

const TituloKpi = (props) => {
    return (
        <h5 className={styles.tituloKpi}>
            {props.children}    
        </h5>
    );
};

export default TituloKpi;
