import React, { useEffect, useState } from "react";
import api from "../../../../../api";
import Kpi from "../kpi/Kpi";
import styles from "../../metricas/Metricas.module.css";

function DadosKpi({ mes }) {
    const [canalComMaiorQtdPublicacoes, setCanalComMaiorQtdPublicacoes] = useState(null); // Inicializa como null para indicar ausência de dados
    const [temDados, setTemDados] = useState(true); // Estado para verificar se há dados
    const ano = new Date().getFullYear();

    const fetchData = async () => {
        try {
            const response = await api.get(`/publicacoes/canal-com-maior-numero-de-publicacoes`, { params: { mes, ano } });

            if (response.data && response.data.canal) { // Verifica se há dados válidos
                setCanalComMaiorQtdPublicacoes(response.data);
                setTemDados(true); // Há dados
            } else {
                setCanalComMaiorQtdPublicacoes(null); // Define como null para indicar ausência de dados
                setTemDados(false); // Não há dados
            }
        } catch (error) {
            console.error('Erro ao buscar canal com maior qtd de publicações:', error);
            setCanalComMaiorQtdPublicacoes(null); // Em caso de erro, define como null
            setTemDados(false); // Em caso de erro, não há dados
        }
    };

    useEffect(() => {
        fetchData();
    }, [mes, ano]); // Atualiza os dados sempre que o mês ou ano mudar

    return (
        <div>
            <div className={styles["kpis"]}>
                {temDados && canalComMaiorQtdPublicacoes ? (
                    <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi={canalComMaiorQtdPublicacoes.canal} />
                ) : (
                    <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi={"---"} />
                )}
                <Kpi TituloKpi="Média de usuários ativos" ResultadoKpi="126" />
            </div>
        </div>
    );
}

export default DadosKpi;
