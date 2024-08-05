import React from 'react';
import styles from './ResultadoKpi.module.css';

const ResultadoKpi = (props) => {
    return (
        <h5 className={styles.ResultadoKpi}>
            {props.children}    
        </h5>
    );
};

export default ResultadoKpi;
