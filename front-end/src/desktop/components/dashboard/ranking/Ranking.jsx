import React, { useEffect, useState } from "react";
import api from '../../../../api'; 
import { generateInitials } from '../../../../mobile/utils/functions/GerarIniciais';
import Styles from "./Ranking.module.css";
import Primeiro from '../../../../mobile/utils/assets/ranking/Primeiro lugar.png';
import Segundo from '../../../../mobile/utils/assets/ranking/Segundo lugar.png';
import Terceiro from '../../../../mobile/utils/assets/ranking/Terceiro lugar.png';

const Ranking = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Fazendo a requisição para buscar os usuários
        api.get('/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-de-todos-usuarios')
            .then(response => {
                setUsers(response.data); // Atualiza o estado com os dados da API
                setLoading(false);
            })
            .catch(error => {
                console.error('Erro ao buscar o ranking:', error);
                setLoading(false); // Mesmo em caso de erro, setamos o loading para falso
            });
    }, []);

    if (loading) {
        return <p>Carregando...</p>; // Exibe uma mensagem de carregamento enquanto a requisição é processada
    }

    return (
        <>
            <div className={Styles.ranking}>
                <h1 className={Styles.titulo}>Ranking</h1>
                {users.map((user, index) => {
                    const avatar = generateInitials(user.nome);
                    return (
                        <div key={index} className={Styles.rankingItem}>
                            <div className={Styles.infos}>
                                <div className={Styles.header}>
                                    {avatar}
                                    <span className={Styles.nome}>{user.nome}</span>
                                </div>

                                <span className={Styles.contribuicoes}>Contribuições: {user.qtdReacoes}</span> {/* Usando o valor da API */}
                            </div>
                            <div className={Styles.posicao}>
                                {index === 0 && (
                                    <img src={Primeiro} alt="Gold Medal" className={Styles.medalIcon} />
                                )}
                                {index === 1 && (
                                    <img src={Segundo} alt="Silver Medal" className={Styles.medalIcon} />
                                )}
                                {index === 2 && (
                                    <img src={Terceiro} alt="Bronze Medal" className={Styles.medalIcon} />
                                )}
                                {index > 2 && (
                                    <span>{index + 1}</span>
                                )}
                            </div>
                        </div>
                    );
                })}
            </div>
        </>
    );
};

export default Ranking;
