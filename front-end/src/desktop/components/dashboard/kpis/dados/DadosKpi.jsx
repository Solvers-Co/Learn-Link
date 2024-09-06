
import React, { useEffect, useState } from "react";
import api from "../../../../../api";
import Kpi from "../kpi/Kpi";
import styles from "../../metricas/Metricas.module.css";

function DadosKpi(props) {
    const [canalComMaiorQtdPublicacoes, setCanalComMaiorQtdPublicacoes] = useState([], []);
    // const [quantidadeComentario, setComentario] = useState([], []);
    let [mes, setMes] = useState(props.mes)
    const ano = new Date().getFullYear();

    const fetchData = async () => {
        console.log("mes dados kpi", mes)

        api.get(`/publicacoes/canal-com-maior-numero-de-publicacoes`, { params: { mes, ano } }).then((response) => {
            setCanalComMaiorQtdPublicacoes(response.data);

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
