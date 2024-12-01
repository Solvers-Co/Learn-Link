import React, { useEffect, useState } from "react";
import Kpi from "../kpi/Kpi";
import styles from "../../metricas/Metricas.module.css";
import api from "../../../../../api";

function DadosKpi({ mes }) {
    const [canalComMaiorQtdPublicacoes, setCanalComMaiorQtdPublicacoes] = useState(null);
    const [mediaUsuariosAtivos, setMediaUsuariosAtivos] = useState(null);
    const [temDadosCanal, setTemDadosCanal] = useState(true);
    const [temDadosUsuarios, setTemDadosUsuarios] = useState(true);
    const ano = new Date().getFullYear();

    const fetchData = async () => {
        try {

            try {
                const canalResponse = await api.get(`/publicacoes/canal-com-maior-numero-de-publicacoes`, { params: { mes, ano } });
                if (canalResponse.data && canalResponse.data.canal) {
                    if (canalResponse.data.canal == 'MATEMATICA') {
                        canalResponse.data.canal = 'Matemática';
                    } else if (canalResponse.data.canal == 'HISTORIA') {
                        canalResponse.data.canal = 'História';
                    } else if (canalResponse.data.canal == 'GEOGRAFIA') {
                        canalResponse.data.canal = 'Geografia';
                    } else if (canalResponse.data.canal == 'QUIMICA') {
                        canalResponse.data.canal = 'Química';
                    } else if (canalResponse.data.canal == 'PORTUGUES') {
                        canalResponse.data.canal = 'Português';
                    } else if (canalResponse.data.canal == 'FISICA') {
                        canalResponse.data.canal = 'Física';
                    } else if (canalResponse.data.canal == 'BIOLOGIA') {
                        canalResponse.data.canal = 'Biologia';
                    } else if (canalResponse.data.canal == 'INGLES') {
                        canalResponse.data.canal = 'Inglês';
                    } else if (canalResponse.data.canal == 'FILOSOFIA') {
                        canalResponse.data.canal = 'Filosofia';
                    } else if (canalResponse.data.canal == 'SOCIOLOGIA') {
                        canalResponse.data.canal = 'Sociologia';
                    } else if (canalResponse.data.canal == 'DOACOES') {
                        canalResponse.data.canal = 'Doações';
                    }
                    setCanalComMaiorQtdPublicacoes(canalResponse.data);
                    setTemDadosCanal(true);
                } else {
                    setCanalComMaiorQtdPublicacoes(null);
                    setTemDadosCanal(false);
                }
            } catch (error) {
                console.error("Erro ao buscar dados de canal com publicação:", error);
                setTemDadosCanal(false);
            }
            

            const usuariosResponse = await api.get(`/media-usuarios-ativos/mes`, { params: { mes, ano } });
            if (usuariosResponse.data && usuariosResponse.data.length > 0) {
                setMediaUsuariosAtivos(usuariosResponse.data[0].usuariosAtivos);
                console.log(usuariosResponse.data[0].usuariosAtivos);
                setTemDadosUsuarios(true);
                console.log(usuariosResponse.data[0].usuariosAtivos)
            } else {
                setMediaUsuariosAtivos(null);
                setTemDadosUsuarios(false);
            }
        } catch (error) {
            console.error("Erro ao buscar dados:", error);
            setTemDadosCanal(false);
            setTemDadosUsuarios(false);
        }
    };

    useEffect(() => {
        console.log("DadosKpi recebeu o mês:", mes);
        fetchData();
        const interval = setInterval(fetchData, 30000); // Atualiza a cada 3 segundos

        return () => clearInterval(interval);
    }, [mes, ano]);

    return (
        <div className={styles["kpis"]}>
            {temDadosCanal && canalComMaiorQtdPublicacoes ? (
                <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi={canalComMaiorQtdPublicacoes.canal} />
            ) : (
                <Kpi TituloKpi="Canal com maior quantidade de publicações" ResultadoKpi="---" />
            )}

            {temDadosUsuarios && mediaUsuariosAtivos !== null ? (
                <Kpi TituloKpi="Média de usuários ativos" ResultadoKpi={mediaUsuariosAtivos} />
            ) : (
                <Kpi TituloKpi="Média de usuários ativos" ResultadoKpi="---" />
            )}
        </div>
    );
}

export default DadosKpi;
