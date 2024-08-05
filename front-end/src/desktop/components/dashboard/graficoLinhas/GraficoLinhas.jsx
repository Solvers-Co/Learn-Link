import React, { Component } from "react";
import Chart from "react-apexcharts";
import styles from "./GraficoLinhas.module.css"


class GraficoLinhas extends Component {
    constructor(props) {
        super(props);

        console.log(props)

        this.state = {
            options: {
                chart: {
                    id: "basic-line"
                },
                xaxis: {
                    categories: props.data.map(item => item[0]||[])
                },
                colors: ['#000000', '#808080'],
                legend: {
                    position: 'top',
                    horizontalAlign: 'left',
                    fontFamily: 'Nunito Sans, sans-serif',
                    fontWeight: 700,
                    fontSize: '14px'
                }
            },
            series: [
                {
                    name: "Quantidade de Publicações",
                    data: props.data.map(item => item[1]||[])
                },
                // 30, 40, 45, 50, 49, 60, 70, 91, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 182, 157, 134, 150, 150, 160, 150, 180, 177
                {
                    name: "Quantidade de Comentários",
                    data: props.data1.map(item => item[1]||[])
                    // 20, 30, 35, 30, 45, 50, 55, 52, 68, 70, 67, 75, 75, 77, 80, 75, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150
                }
            ]
        };
    }

    render() {
        return (
            <div className={styles.grafico}>
                {console.log(this.props.data)}
                <div className={styles["chart"]} >
                    <div className="row">
                        <div className={styles["mixed-chart"]}>
                            <Chart
                                options={this.state.options}
                                series={this.state.series}
                                type="line"
                                width="100%"
                                height="300"
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default GraficoLinhas;
