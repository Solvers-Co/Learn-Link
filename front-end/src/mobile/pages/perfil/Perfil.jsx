import styles from './Perfil.module.css';
import Header from '../../components/headerAplicacao/Header';
import CardPerfil from '../../components/cards/cardPerfil/CardPerfil';
import React, { useMemo, useState, useEffect } from 'react';
import CardAtividade from '../../components/cards/cardAtividade/CardAtividade';
import api from "../../../api";

function generateInitials(name) {
    if (!name) {
        return <div style={{ color: 'red' }}>N/A</div>;
    }

    const nameParts = name.trim().split(' ');
    const firstInitial = nameParts[0].charAt(0).toUpperCase();
    const lastInitial = nameParts.length > 1 ? nameParts[nameParts.length - 1].charAt(0).toUpperCase() : '';

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
    const [classificacao, setClassificacao] = useState('Iniciante');
    const [especialidade, setEspecialidade] = useState('Desconhecido');
    const [contribuicoes, setContribuicoes] = useState(0);

    const idUsuarioLogado = sessionStorage.getItem('userId') || 'N/A';
    const nomeUsuarioLogado = sessionStorage.getItem('nome') || 'Usuário Anônimo';
    const emailUsuarioLogado = sessionStorage.getItem('email') || 'E-mail Anônimo';

    const nomeFormatado = useMemo(() => {
        if (!nomeUsuarioLogado) return 'Usuário Desconhecido';
        const nomes = nomeUsuarioLogado.trim().split(' ');
        return nomes.length === 1 ? nomes[0] : `${nomes[0]} ${nomes[nomes.length - 1]}`;
    }, [nomeUsuarioLogado]);

    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);

    useEffect(() => {
        const fetchClassificacao = async () => {
            try {
                const response = await api.patch(`/usuarios/classificar-usuario/${idUsuarioLogado}`);
                const nivel = response.data.classificacao.classificacao;
                const classificacoes = {
                    'JUNIOR': 'Júnior',
                    'PLENO': 'Pleno',
                    'SENIOR': 'Sênior',
                    'ESPECIALISTA': 'Especialista'
                };
                setClassificacao(classificacoes[nivel] || 'Iniciante');
            } catch (error) {
                console.error("Erro ao carregar classificação:", error);
                setClassificacao('Iniciante');
            }
        };
        fetchClassificacao();
    }, [idUsuarioLogado]);

    const subjectNameMap = {
        'MATEMATICA': 'Matemática',
        'HISTORIA': 'História',
        'GEOGRAFIA': 'Geografia',
        'QUIMICA': 'Química',
        'PORTUGUES': 'Português',
        'FISICA': 'Física',
        'BIOLOGIA': 'Biologia',
        'INGLES': 'Inglês',
        'FILOSOFIA': 'Filosofia',
        'SOCIOLOGIA': 'Sociologia',
    };

    const formatSubjectName = (name) => subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();

    useEffect(() => {
        const fetchEspecialidade = async () => {
            try {
                const response = await api.get(`/usuarios/${idUsuarioLogado}`);
                setEspecialidade(formatSubjectName(response.data.especialidade.materia));
            } catch (error) {
                console.error("Erro ao carregar especialidade:", error);
                setEspecialidade('Erro ao carregar');
            }
        };
        fetchEspecialidade();
    }, [idUsuarioLogado]);

    useEffect(() => {
        const fetchContribuicoes = async () => {
            try {
                const response = await api.get(`/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-do-usuario/${idUsuarioLogado}`);
                const qtdReacoes = response.data.qtdReacoes;
                setContribuicoes(qtdReacoes >= 1 ? qtdReacoes : 0);
                if (qtdReacoes < 1) setClassificacao('Iniciante');
            } catch (error) {
                console.error("Erro ao carregar contribuições:", error);
                setContribuicoes(0);
                setClassificacao('Iniciante');
            }
        };
        fetchContribuicoes();
    }, [idUsuarioLogado]);

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
                    <CardPerfil conteudo={classificacao} classificacao="Classificação" />
                    <CardPerfil conteudo={contribuicoes} classificacao="Contribuições" />
                    <CardPerfil conteudo={especialidade} classificacao="Especialidade" />
                </div>
                <div className={styles.atividade}>
                    <CardAtividade />
                </div>
            </div>
        </>
    );
}

export default Perfil;
