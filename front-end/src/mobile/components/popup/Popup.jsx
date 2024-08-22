import React from "react";
import styles from "./Popup.module.css";

function Popup({ fecharPopup }) {
    return (
        <div className={styles["popupOverlay"]}>
            <div className={styles["popup"]}>
                <button className={styles["closeButton"]} onClick={fecharPopup}>
                    &times;
                </button>
                <div className={styles["popupContent"]}>
                    <h2>Título da Publicação</h2>
                    <p>Conteúdo do popup ou formulário vai aqui.</p>
                </div>
            </div>
        </div>
    );
}

export default Popup;