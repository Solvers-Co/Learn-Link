import React, { useEffect, useState } from 'react';
import api from '../../../api';
import Styles from '../ranking/Ranking.module.css';
import Header from '../../components/headerAplicacao/Header';
import { generateInitials } from '../../utils/functions/GerarIniciais';

import Primeiro from '../../utils/assets/ranking/Primeiro lugar.png';
import Segundo from '../../utils/assets/ranking/Segundo lugar.png';
import Terceiro from '../../utils/assets/ranking/Terceiro lugar.png';

const Ranking = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        api.get('/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-de-todos-usuarios')
            .then((response) => {
                setUsers(response.data);
                console.log('Dados do ranking:', response.data);
            })
            .catch((error) => {
                console.error('Erro ao buscar dados do ranking:', error);
            });
    }, []);

    return (
        <>
            <Header />
            <div className={Styles.rankingContainer}>
                <h1 className={Styles.titulo}>Ranking</h1>
                <div className={Styles.ranking}>
                    {users.map((user, index) => {
                        const avatar = generateInitials(user.nome);

                        return (
                            <div key={index} className={Styles.rankingItem}>
                                <div className={Styles.infos}>
                                    <div className={Styles.header}>
                                        {avatar}
                                        <span className={Styles.nome}>{user.nome}</span>
                                    </div>

                                    <span className={Styles.contribuicoes}>Contribuições: {user.qtdReacoes}</span>
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
            </div>
        </>
    );
};

export default Ranking;
