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
      console.log("Dados publicacao: ", publicacaoResponse.data);
      console.log("Dados comentario: ", comentarioResponse.data);
      setPublicacao(publicacaoResponse.data);
      setComentario(comentarioResponse.data);
    } catch (error) {
      console.error('Erro ao buscar dados:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [mes, ano]);

  return (
    <div>
      <GraficoLinhas data={quantidadePublicacao} data1={quantidadeComentario} />
    </div>
  );
}

export default DadosGrafico;
