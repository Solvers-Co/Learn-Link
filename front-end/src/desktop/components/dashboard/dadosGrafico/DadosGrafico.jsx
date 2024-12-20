import React, { useEffect, useState } from "react";
import GraficoLinhas from "../graficoLinhas/GraficoLinhas";
import api from "../../../../api";

function DadosGrafico({ mes }) {
  const [quantidadePublicacao, setPublicacao] = useState([]);
  const [quantidadeComentario, setComentario] = useState([]);
  const ano = new Date().getFullYear();

  const fetchData = async () => {
    try {
      const [publicacaoResponse, comentarioResponse] = await Promise.all([
        api.get(`/publicacoes/quantidade-por-dia`, { params: { mes, ano } }),
        api.get(`/comentarios/quantidade-comentarios-por-dia-mes`, { params: { mes, ano } })
      ]);
      setPublicacao(publicacaoResponse.data);
      setComentario(comentarioResponse.data);
    } catch (error) {
      console.error("Erro ao buscar dados:", error);
    }
  };

  useEffect(() => {
    fetchData();
    const interval = setInterval(fetchData, 3000); // Atualiza a cada 3 segundos

    return () => clearInterval(interval);
  }, [mes, ano]); // Atualiza sempre que `mes` ou `ano` mudarem

  return (
    <div>
      <GraficoLinhas data={quantidadePublicacao} data1={quantidadeComentario} />
    </div>
  );
}

export default DadosGrafico;
