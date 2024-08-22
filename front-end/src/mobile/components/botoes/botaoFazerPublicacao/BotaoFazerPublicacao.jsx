import React, { useState } from "react";
import styles from "./BotaoFazerPublicacao.module.css";
import editarIcone from "../../../utils/assets/Editar.png";
import Popup from "../../popup/Popup";

function BotaoFazerPublicacao() {
    const [isPopupOpen, setPopupOpen] = useState(false);

    const abrirPopup = () => {
        setPopupOpen(true);
    };

    const fecharPopup = () => {
        setPopupOpen(false);
    };

    return (
        <>
            <button
                className={styles["botaoFazerPublicacao"]}
                onClick={abrirPopup}
            >
                <img src={editarIcone} alt="Editar" />
                Faça uma publicação
            </button>

            {isPopupOpen && <Popup fecharPopup={fecharPopup} />}
        </>
    );
}

export default BotaoFazerPublicacao;