import React, { useEffect, useState } from "react";
import api from '../../../../api'; // Certifique-se de que o caminho está correto
import Styles from "./Ranking.module.css";
import Primeiro from '../../../../mobile/utils/assets/ranking/Primeiro lugar.png';
import Segundo from '../../../../mobile/utils/assets/ranking/Segundo lugar.png';
import Terceiro from '../../../../mobile/utils/assets/ranking/Terceiro lugar.png';
// import { generateInitials } from '../../../utils/';

function generateInitials(name) {
    const nameParts = name.trim().split(' ');
    const firstInitial = nameParts[0].charAt(0).toUpperCase();
    const lastInitial = nameParts[nameParts.length - 1].charAt(0).toUpperCase();

    const pastelColors = [
        '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
        '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
        '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    ];

    const randomIndex = Math.floor(Math.random() * pastelColors.length);
    const backgroundColor = pastelColors[randomIndex];

    const avatarStyle = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        width: '32px',
        height: '32px',
        marginRight: '12px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSansExtraBold", sans-serif',
        backgroundColor
    };

    return <div style={avatarStyle}>{firstInitial + lastInitial}</div>;
}

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
