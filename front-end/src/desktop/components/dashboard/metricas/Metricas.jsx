import React, { useState } from "react";
import Titulo from "../tituloDashboard/Titulo";
import DadosGrafico from "../dadosGrafico/DadosGrafico";
import DadosKpi from "../kpis/dados/DadosKpi";
import Dropdown from "../dropdown/Dropdown";
import styles from "./Metricas.module.css";

function Metricas() {
    const [mes, setMes] = useState(8);

    const handleDropdownChange = (e) => {
        setMes(Number(e.target.value)); // Garante que o valor seja um número
    };

    return (
        <div className={styles.metricas}>
            <header className={styles.cabecalho}>
                <Titulo>Visão Geral</Titulo>
                <Dropdown
                    value={mes}
                    onChange={handleDropdownChange}
                />
            </header>
            <DadosKpi mes={mes} />
            <DadosGrafico mes={mes} />
        </div>
    );
}

export default Metricas;
