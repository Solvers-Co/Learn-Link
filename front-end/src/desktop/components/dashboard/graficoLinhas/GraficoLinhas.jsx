import React from "react";
import Chart from "react-apexcharts";
import styles from "./GraficoLinhas.module.css";

// Função para obter os dias do mês selecionado
const getDaysInMonth = (year, month) => {
    const date = new Date(year, month, 1);
    const days = [];
    while (date.getMonth() === month) {
        days.push(date.getDate());
        date.setDate(date.getDate() + 1);
    }
    return days;
};

// Função para preencher dados com zero para dias ausentes
const fillMissingDays = (data, daysInMonth, dateKey, quantityKey) => {
    const dataMap = data.reduce((acc, item) => {
        const date = new Date(item[dateKey]);
        const day = date.getDate() + 1; // Ajusta para adicionar um dia
        acc[day] = item[quantityKey];
        return acc;
    }, {});

    // Adiciona zero para dias ausentes
    return daysInMonth.map(day => dataMap[day] || 0);
};

const GraficoLinhas = ({ data: dadosPublicacoes, data1: dadosComentarios }) => {
    const isDataEmpty = dadosPublicacoes.length === 0 && dadosComentarios.length === 0;

    // Pega o mês e o ano do primeiro item, ou usa o mês atual como fallback
    const firstDate = dadosPublicacoes.length > 0 ? new Date(dadosPublicacoes[0].dataPublicacao) : new Date();
    const year = firstDate.getFullYear();
    const month = firstDate.getMonth();

    // Gera os dias do mês para o eixo x
    const daysInMonth = getDaysInMonth(year, month);

    const options = {
        chart: {
            id: "basic-line",
            toolbar: { show: true },
            zoom: { enabled: true },
            animations: {
                enabled: true,
                easing: 'easeinout',
                speed: 800,
            },
            background: '#f4f4f4',
            dropShadow: { enabled: false },
        },
        xaxis: {
            categories: isDataEmpty ? [""] : daysInMonth,
            labels: {
                style: {
                    fontSize: '12px',
                    fontFamily: 'Nunito Sans, sans-serif',
                    colors: ['#333'],
                },
            },
            title: {
                text: 'Dias do Mês',
                style: {
                    fontSize: '14px',
                    fontFamily: 'Nunito Sans, sans-serif',
                    color: '#333',
                },
            },
        },
        yaxis: {
            labels: {
                style: {
                    fontSize: '12px',
                    fontFamily: 'Nunito Sans, sans-serif',
                    colors: ['#333'],
                },
            },
            title: {
                text: 'Quantidade',
                style: {
                    fontSize: '14px',
                    fontFamily: 'Nunito Sans, sans-serif',
                    color: '#333',
                },
            },
        },
        colors: ['#1f77b4', '#ff7f0e'],
        legend: {
            position: 'top',
            horizontalAlign: 'left',
            fontFamily: 'Nunito Sans, sans-serif',
            fontWeight: 600,
            fontSize: '14px',
            markers: {
                width: 10,
                height: 10,
                radius: 12,
            },
        },
        title: {
            text: 'Quantidade de Publicações e Comentários por Mês',
            align: 'left',
            margin: 10,
            style: {
                fontSize: '20px',
                fontWeight: 'bold',
                color: '#333',
            },
        },
        stroke: {
            curve: 'smooth',
            width: 3,
        },
        markers: {
            size: 5,
            strokeColors: '#fff',
            strokeWidth: 2,
            hover: { size: 7 },
        },
        dataLabels: { enabled: false },
        grid: {
            borderColor: '#e7e7e7',
            strokeDashArray: 5,
        },
        tooltip: {
            theme: 'dark',
            x: { show: true },
        },
    };

    const series = isDataEmpty
        ? [
              { name: "Quantidade de Publicações", data: daysInMonth.map(() => 0) },
              { name: "Quantidade de Comentários", data: daysInMonth.map(() => 0) },
          ]
        : [
              {
                  name: "Quantidade de Publicações",
                  data: fillMissingDays(dadosPublicacoes, daysInMonth, 'dataPublicacao', 'quantidadePublicacoes'),
              },
              {
                  name: "Quantidade de Comentários",
                  data: fillMissingDays(dadosComentarios, daysInMonth, 'dataComentario', 'quantidadeComentarios'),
              },
          ];

    return (
        <div className={styles.grafico}>
            {isDataEmpty ? (
                <div className={styles.noData}>
                    <p>Não há dados para o mês selecionado</p>
                </div>
            ) : (
                <div className={styles.chart}>
                    <Chart options={options} series={series} type="line" width="100%" height="450" />
                </div>
            )}
        </div>
    );
};

export default GraficoLinhas;
