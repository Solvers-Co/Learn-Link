import React, { useState } from "react";
import Titulo from "../tituloDashboard/Titulo";
import DadosGrafico from "../dadosGrafico/DadosGrafico";
import DadosKpi from "../kpis/dados/DadosKpi";
import Dropdown from "../dropdown/Dropdown";
import styles from "./Metricas.module.css";

function Metricas() {
    const [mes, setMes] = useState(new Date().getMonth() + 1); // Mês inicial

    const handleDropdownChange = (mesSelecionado) => {
        setMes(Number(mesSelecionado.target.value)); // Atualiza o mês com o valor selecionado
    };

    return (
        <div className={styles.metricas}>
            <header className={styles.cabecalho}>
                <Titulo>Visão Geral</Titulo>
                <Dropdown
                    value={mes} // Passa o valor atual do mês
                    onChange={handleDropdownChange} // Atualiza o mês no estado
                />
            </header>
            <DadosKpi mes={mes} />
            <DadosGrafico mes={mes} />
        </div>
    );
}

export default Metricas;
