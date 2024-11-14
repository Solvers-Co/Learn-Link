import React, { useEffect, useState } from "react";
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
    const dataMap = Array.isArray(data)
        ? data.reduce((acc, item) => {
              const date = new Date(item[dateKey]);
              const day = date.getDate();
              acc[day] = item[quantityKey];
              return acc;
          }, {})
        : {};

    return daysInMonth.map((day) => dataMap[day] || 0);
};

const GraficoLinhas = ({ data: initialPublicacoes, data1: initialComentarios }) => {
    const isDataEmpty = initialPublicacoes.length === 0 && initialComentarios.length === 0;

    // Obtém o mês e o ano do primeiro item, ou usa o mês atual
    const firstDate = initialPublicacoes.length > 0 ? new Date(initialPublicacoes[0].dataPublicacao) : new Date();
    const year = firstDate.getFullYear();
    const month = firstDate.getMonth();
    const daysInMonth = getDaysInMonth(year, month);

    // Configurações do gráfico
    const options = {
        chart: {
            id: "basic-line",
            toolbar: { show: true },
            zoom: { enabled: true },
            animations: { enabled: true, easing: "easeinout", speed: 800 },
            background: "#f4f4f4",
            dropShadow: { enabled: false },
        },
        xaxis: {
            categories: isDataEmpty ? [""] : daysInMonth,
            labels: { style: { fontSize: "12px", colors: ["#333"] } },
            title: { text: "Dias do Mês", style: { fontSize: "14px", color: "#333" } },
        },
        yaxis: {
            labels: { style: { fontSize: "12px", colors: ["#333"] } },
            title: { text: "Quantidade", style: { fontSize: "14px", color: "#333" } },
        },
        colors: ["#1f77b4", "#ff7f0e"],
        legend: { position: "top", horizontalAlign: "left" },
        title: {
            text: "Quantidade de Publicações e Comentários por Mês",
            align: "left",
            margin: 10,
            style: { fontSize: "20px", fontWeight: "bold", color: "#333" },
        },
        stroke: { curve: "smooth", width: 3 },
        markers: { size: 5, strokeColors: "#fff", strokeWidth: 2, hover: { size: 7 } },
        dataLabels: { enabled: false },
        grid: { borderColor: "#e7e7e7", strokeDashArray: 5 },
        tooltip: { theme: "dark", x: { show: true } },
    };

    const series = isDataEmpty
        ? [
              { name: "Quantidade de Publicações", data: daysInMonth.map(() => 0) },
              { name: "Quantidade de Comentários", data: daysInMonth.map(() => 0) },
          ]
        : [
              {
                  name: "Quantidade de Publicações",
                  data: fillMissingDays(initialPublicacoes, daysInMonth, "dataPublicacao", "quantidadePublicacoes"),
              },
              {
                  name: "Quantidade de Comentários",
                  data: fillMissingDays(initialComentarios, daysInMonth, "dataComentario", "quantidadeComentarios"),
              },
          ];

    // Força a re-renderização do gráfico ao trocar o mês
    const chartKey = `${month}-${year}-${initialPublicacoes.length}-${initialComentarios.length}`;

    return (
        <div className={styles.grafico}>
            {isDataEmpty ? (
                <div className={styles.noData}>
                    <p>Não há dados para o mês selecionado</p>
                </div>
            ) : (
                <div className={styles.chart}>
                    <Chart key={chartKey} options={options} series={series} type="line" width="100%" height="450" />
                </div>
            )}
        </div>
    );
};

export default GraficoLinhas;
