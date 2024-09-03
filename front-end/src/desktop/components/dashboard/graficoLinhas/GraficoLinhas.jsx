import React from "react";
import Chart from "react-apexcharts";
import styles from "./GraficoLinhas.module.css";

const GraficoLinhas = ({ data, data1 }) => {
    const options = {
        chart: {
            id: "basic-line",
        },
        xaxis: {
            categories: data.map(item => item.dataPublicacao),
        },
        colors: ['#000000', '#808080'],
        legend: {
            position: 'top',
            horizontalAlign: 'left',
            fontFamily: 'Nunito Sans, sans-serif',
            fontWeight: 700,
            fontSize: '14px',
        },
        title: {
            text: 'Quantidade de Publicações e Comentários por mês',
            align: 'left',
        },
        stroke: {
            curve: 'smooth',
        },
        markers: {
            size: 3,
        },
        dataLabels: {
            enabled: false,
        },
    };

    const series = [
        {
            name: "Quantidade de Publicações",
            data: data.map(item => item.quantidadePublicacoes || 0),
        },
        {
            name: "Quantidade de Comentários",
            data: data1.map(item => item.quantidadeComentarios || 0),
        },
    ];

    return (
        <div className={styles.grafico}>
            <div className={styles.chart}>
                <Chart
                    options={options}
                    series={series}
                    type="line"
                    width="100%"
                    height="450"
                />
            </div>
        </div>
    );
};

export default GraficoLinhas;
