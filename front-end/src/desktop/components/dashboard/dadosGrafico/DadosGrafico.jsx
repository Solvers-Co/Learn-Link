// DadosGrafico.js
import React, { useEffect, useState } from "react";
import GraficoLinhas from "../graficoLinhas/GraficoLinhas";
import api from "../../../../api";


function DadosGrafico(props) {
  const [quantidadePublicacao, setPublicacao] = useState([], []);
  const [quantidadeComentario, setComentario] = useState([], []);
  let [mes, setMes] = useState(props.mes)
  const ano = 2024;

  const fetchData = async () => {
    console.log("mes")
    console.log(mes)
    
    let response1

    api.get(`/publicacoes/quantidade-publicacoes-por-dia-mes`, {params:{mes, ano}}).then((response) => {
      setPublicacao(response.data);
      console.log("oie")
      console.log(mes)
      console.log(response.data)
      console.log(quantidadePublicacao)

    }).catch((error) => {
      console.error('Erro ao buscar dados das publicações:', error);
    });

    api.get(`/comentarios/quantidade-comentarios-por-dia-mes`, {params:{mes, ano}}).then((response) => {
      setComentario(response.data);
      console.log("olá")
      console.log(response)
      console.log(quantidadeComentario);
    }).catch((error) => {
      console.error('Erro ao buscar dados dos comentários:', error);
    });
  };
  
 useEffect(() => {
        console.log(props)
        fetchData();
      }, [mes, ano]);

  return (
    <div>
      <GraficoLinhas data={quantidadePublicacao} data1={quantidadeComentario}
      />
    </div>
  );
}

export default DadosGrafico;
