import styles from './Perfil.module.css';
import Header from '../../components/headerAplicacao/Header';
import CardPerfil from '../../components/cards/cardPerfil/CardPerfil';
import React, { useMemo } from 'react';
import CardAtividade from '../../components/cards/cardAtividade/CardAtividade';

function generateInitials(name) {
    if (!name) {
        // Se o nome não estiver definido, retorne um valor padrão
        return <div style={{ color: 'red' }}>N/A</div>;
    }

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

    const avatar = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        fontSize: '40px',
        width: '100px',
        height: '100px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSans", sans-serif',
        boxShadow: '2px 2px 16px rgba(0, 0, 0, 0.25)',
        backgroundColor
    };

    return <div style={avatar}>{firstInitial + lastInitial}</div>;
}

const Perfil = () => {
    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome') || 'Usuário Anônimo';
    const emailUsuarioLogado = sessionStorage.getItem('email') || 'E-mail Anônimo';

    // Memorize o avatar gerado com base no nome
    const avatar = useMemo(() => generateInitials(nomeUsuarioLogado), [nomeUsuarioLogado]);

    return (
        <>
            <Header />
            <div className={styles.perfil}>
                <div className={styles.corFundo}>
                    <div className={styles.userInfo}>
                        {avatar}
                        <span className={styles.nome}>{nomeUsuarioLogado}</span>
                        <span className={styles.email}>{emailUsuarioLogado}</span>
                    </div>
                </div>
                <div className={styles.cards}>
                    <CardPerfil conteudo="Especialista" classificacao="Classificação" />
                    <CardPerfil conteudo="300" classificacao="Contribuições" />
                    <CardPerfil conteudo="Matemática" classificacao="Especialidade" />
                </div>
                <div className={styles.atividade}>
                    <CardAtividade />
                </div>
            </div>
        </>
    );
}

export default Perfil;
