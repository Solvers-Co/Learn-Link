import React from 'react';
import Styles from '../ranking/Ranking.module.css';
import Header from '../../components/headerAplicacao/Header';

import Primeiro from '../../utils/assets/ranking/Primeiro lugar.png';
import Segundo from '../../utils/assets/ranking/Segundo lugar.png';
import Terceiro from '../../utils/assets/ranking/Terceiro lugar.png';

const users = [
    { nome: 'Sofhia Utaka', imageUrl: 'url-da-imagem' },
    { nome: 'Otávio Walkovics', imageUrl: 'url-da-imagem' },
    { nome: 'Ana Júlia', imageUrl: 'url-da-imagem' },
    { nome: 'Kauan Cruz', imageUrl: 'url-da-imagem' },
    { nome: 'Simone Mendes', imageUrl: 'url-da-imagem' },
    { nome: 'Stacy Melody', imageUrl: 'url-da-imagem' },
    { nome: 'André Souza', imageUrl: 'url-da-imagem' },
    { nome: 'Melody Santos', imageUrl: 'url-da-imagem' },
    { nome: 'Daniel Carvalho', imageUrl: 'url-da-imagem' },
    { nome: 'Ariana Borges', imageUrl: 'url-da-imagem' },
];

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
        width: '35px',
        height: '35px',
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

                                    <span className={Styles.contribuicoes}>Contribuições: 10</span>
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