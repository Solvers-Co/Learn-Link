import Titulo from "../tituloDashboard/Titulo";
import DadosGrafico from "../dadosGrafico/DadosGrafico";
import DadosKpi from "../kpis/dados/DadosKpi";
import Dropdown from "../dropdown/Dropdown";
import React, { useEffect, useState } from "react";
import styles from "./Metricas.module.css";



function Metricas() {

    let [mes, setMes] = useState(5);

    function renderChart(mes) {
        return <DadosGrafico mes={mes}/>
    }
    function renderKpi(mes) {
        return <DadosKpi mes={mes}/>
    }

    useEffect(() => {
        renderChart()
        renderKpi()
    }, [mes, setMes])
    

    return(
        <div className={styles["metricas"]}>
            <div className={styles["cabecalho"]}>
                <Titulo>Visão Geral</Titulo>
                <Dropdown value={mes} onChange={(e) =>{ 
                    setMes(e.target.value)
                    console.log("valor do mês")
                    console.log(e.target.value)} 
                }/>
            </div>
            {renderKpi(mes)}
            
            {renderChart(mes)}
        </div>
    )
}

export default Metricas;