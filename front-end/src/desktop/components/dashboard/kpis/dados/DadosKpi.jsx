import React, { useEffect, useState } from "react";
import api from "../../../../../api";
import Kpi from "../kpi/Kpi";
import styles from "../../metricas/Metricas.module.css";

function DadosKpi({ mes }) {
    const [canalComMaiorQtdPublicacoes, setCanalComMaiorQtdPublicacoes] = useState(null); // Estado para o canal
    const [mediaUsuariosAtivos, setMediaUsuariosAtivos] = useState(null); // Estado para a média de usuários ativos
    const [temDadosCanal, setTemDadosCanal] = useState(true); // Verifica se há dados para o canal
    const [temDadosUsuarios, setTemDadosUsuarios] = useState(true); // Verifica se há dados para os usuários ativos
    const ano = new Date().getFullYear();

    const fetchData = async () => {
        try {
            // Requisição para o canal com maior quantidade de publicações
            const canalResponse = await api.get(`/publicacoes/canal-com-maior-numero-de-publicacoes`, { params: { mes, ano } });

            if (canalResponse.data && canalResponse.data.canal) {
                setCanalComMaiorQtdPublicacoes(canalResponse.data);
                setTemDadosCanal(true);
            } else {
                setCanalComMaiorQtdPublicacoes(null);
                setTemDadosCanal(false);
            }

            // Requisição para a média de usuários ativos
            const usuariosResponse = await api.get(`/media-usuarios-ativos/mes`);
            if (usuariosResponse.data && usuariosResponse.data.length > 0) {
                setMediaUsuariosAtivos(usuariosResponse.data[0].usuariosAtivos); // Acessa o valor da média
                setTemDadosUsuarios(true);
            } else {
                setMediaUsuariosAtivos(null);
                setTemDadosUsuarios(false);
            }
        } catch (error) {
            console.error('Erro ao buscar dados:', error);
            setCanalComMaiorQtdPublicacoes(null);
            setTemDadosCanal(false);
            setMediaUsuariosAtivos(null);
            setTemDadosUsuarios(false);
        }
    };

    useEffect(() => {
        fetchData();
    }, [mes, ano]);

    return (
        <div>
            <div className={styles["kpis"]}>
                {temDadosCanal && canalComMaiorQtdPublicacoes ? (
                    <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi={canalComMaiorQtdPublicacoes.canal} />
                ) : (
                    <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi={"---"} />
                )}

                {temDadosUsuarios && mediaUsuariosAtivos !== null ? (
                    <Kpi TituloKpi="Média de usuários ativos" ResultadoKpi={mediaUsuariosAtivos} />
                ) : (
                    <Kpi TituloKpi="Média de usuários ativos" ResultadoKpi={"---"} />
                )}
            </div>
        </div>
    );
}

export default DadosKpi;
