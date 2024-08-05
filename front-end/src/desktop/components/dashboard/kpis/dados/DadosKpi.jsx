
import React, { useEffect, useState } from "react";
import api from "../../../../../api";
import Kpi from "../kpi/Kpi";
import styles from "../../metricas/Metricas.module.css";

function DadosKpi(props) {
    const [canalComMaiorQtdPublicacoes, setCanalComMaiorQtdPublicacoes] = useState([], []);
    // const [quantidadeComentario, setComentario] = useState([], []);
    let [mes, setMes] = useState(props.mes)
    const ano = 2024;

    const fetchData = async () => {
        console.log("mes dados kpi")
        console.log(mes)

        // let response1

        api.get(`/publicacoes/canal-com-maior-numero-de-publicacoes`, { params: { mes, ano } }).then((response) => {
            setCanalComMaiorQtdPublicacoes(response.data);
            console.log("oie")
            console.log(mes)
            console.log(response.data)
            console.log(canalComMaiorQtdPublicacoes)

        }).catch((error) => {
            console.error('Erro ao buscar canal com maior qtd de publicações:', error);
        });

        // api.get(`/comentarios/quantidade-comentarios-por-dia-mes`, { params: { mes, ano } }).then((response) => {
        //     setComentario(response.data);
        //     console.log("olá")
        //     console.log(response)
        //     console.log(quantidadeComentario);
        // }).catch((error) => {
        //     console.error('Erro ao buscar dados dos comentários:', error);
        // });
    };

    useEffect(() => {
        console.log(props)
        fetchData();
    }, [mes, ano]);

    return (
        <div>
            <div className={styles["kpis"]}>
                <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi={canalComMaiorQtdPublicacoes.canal} />
                <Kpi TituloKpi="Média de usuários ativos" ResultadoKpi="126" />
            </div>
        </div>
    );
}

export default DadosKpi;
