import React from "react";
import Chart from "react-apexcharts";
import styles from "./GraficoLinhas.module.css";

// Função para obter os dias do mês selecionado
const getDaysInMonth = (year, month) => {
    const date = new Date(year, month, 1);
    const days = [];
    while (date.getMonth() === month) {
        days.push(new Date(date).getDate());
        date.setDate(date.getDate() + 1);
    }
    return days;
};

const GraficoLinhas = ({ data, data1 }) => {
    const isDataEmpty = data.length === 0 || data1.length === 0;

    // Pega o mês e o ano do primeiro item, ou usa o mês atual como fallback
    const firstDate = data.length > 0 ? new Date(data[0].dataPublicacao) : new Date();
    const year = firstDate.getFullYear();
    const month = firstDate.getMonth();

    // Gera os dias do mês para o eixo x
    const daysInMonth = getDaysInMonth(year, month);

    const options = {
        chart: {
            id: "basic-line",
            toolbar: {
                show: true,
            },
            zoom: {
                enabled: true,
            },
            animations: {
                enabled: true,
                easing: 'easeinout',
                speed: 800,
            },
            background: '#f4f4f4',
            dropShadow: {
                enabled: false,
            },
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
            hover: {
                size: 7,
            },
        },
        dataLabels: {
            enabled: false,
        },
        grid: {
            borderColor: '#e7e7e7',
            strokeDashArray: 5,
        },
        tooltip: {
            theme: 'dark',
            x: {
                show: true,
            },
        },
    };

    const series = isDataEmpty
        ? [
              {
                  name: "Quantidade de Publicações",
                  data: [0],
              },
              {
                  name: "Quantidade de Comentários",
                  data: [0],
              },
          ]
        : [
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
