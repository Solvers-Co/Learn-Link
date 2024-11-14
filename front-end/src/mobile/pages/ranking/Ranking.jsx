import React, { useEffect, useState } from 'react';
import api from '../../../api';
import Styles from '../ranking/Ranking.module.css';
import Header from '../../components/headerAplicacao/Header';
import { generateInitials } from '../../utils/functions/GerarIniciais';
import { useNavigate } from "react-router-dom";

import Primeiro from '../../utils/assets/ranking/Primeiro lugar.png';
import Segundo from '../../utils/assets/ranking/Segundo lugar.png';
import Terceiro from '../../utils/assets/ranking/Terceiro lugar.png';

const Ranking = () => {
    const [users, setUsers] = useState([]);
    const [imagensPerfil, setImagensPerfil] = useState({});

    const navigate = useNavigate();

    const visualizarPerfil = (id) => {
        navigate(`/perfil/${id}`)
    }

    useEffect(() => {
        api.get('/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-de-todos-usuarios')
            .then((response) => {
                setUsers(response.data);
            })
            .catch((error) => {
                console.error('Erro ao buscar dados do ranking:', error);
            });
    }, []);

    useEffect(() => {
        const buscarImagensPerfil = async () => {
            const novasImagensPerfil = {};
            await Promise.all(users.map(async (user) => {
                try {
                    const response = await api.get(`usuarios/buscar-imagem-perfil/${user.usuarioId}`);
                    novasImagensPerfil[user.usuarioId] = response.data;
                } catch (error) {
                    console.error('Erro ao buscar imagem de perfil para usuário', user.usuarioId, error);
                }
            }));
            setImagensPerfil(novasImagensPerfil);
        };

        if (users.length > 0) {
            buscarImagensPerfil();
        }
    }, [users]);

    return (
        <>
            <Header />
            <div className={Styles.rankingContainer}>
                <h1 className={Styles.titulo}>Ranking</h1>
                <div className={Styles.ranking}>
                    {users.map((user, index) => {
                        const avatar = generateInitials(user.nome);
                        const srcImagemPerfil = imagensPerfil[user.usuarioId];

                        return (
                            <div key={index} className={Styles.rankingItem}>
                                <div className={Styles.infos}>
                                    <div className={Styles.header}>
                                        {srcImagemPerfil ? (
                                            <img className={Styles.imagemPerfil} src={srcImagemPerfil} alt="Imagem de Perfil" />
                                        ) : (
                                            <span className={Styles.avatar}>{avatar}</span>
                                        )}
                                        <span className={Styles.nome} onClick={() => { visualizarPerfil(user.usuarioId) }}>{user.nome}</span>
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
