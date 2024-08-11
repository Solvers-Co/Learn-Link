import React from "react";
import styles from "./CardFormulario.module.css";

const Card = ({ children, altura }) =>{
    return(
        
        <div style={{ height: altura }} className={styles['card']}>
            {children}
        </div>

    )
}

export default Card;