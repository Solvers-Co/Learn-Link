import styles from './Perfil.module.css';
import Header from '../../components/headerAplicacao/Header';
import CardPerfil from '../../components/cards/cardPerfil/CardPerfil';
import React, { useMemo, useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import CardAtividade from '../../components/cards/cardAtividade/CardAtividade';
import api from "../../../api";

// generateInitials diferente do padrão
function generateInitials(name) {
    if (!name) return <div style={{ color: 'red' }}>N/A</div>;

    const nameParts = name.trim().split(' ');
    const initials = nameParts[0].charAt(0).toUpperCase() + (nameParts.length > 1 ? nameParts[nameParts.length - 1].charAt(0).toUpperCase() : '');

    const pastelColors = [
        '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
        '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
        '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    ];

    const backgroundColor = pastelColors[Math.floor(Math.random() * pastelColors.length)];

    const avatarStyle = {
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

    return <div style={avatarStyle}>{initials}</div>;
}

const Perfil = () => {
    const { idUsuario } = useParams(); // Pegando o id do usuário da URL
    const [nome, setNome] = useState('Usuário Desconhecido');
    const [email, setEmail] = useState('E-mail Desconhecido');
    const [classificacao, setClassificacao] = useState('Iniciante');
    const [especialidade, setEspecialidade] = useState('Desconhecido');
    const [contribuicoes, setContribuicoes] = useState(0);

    const idUsuarioLogado = idUsuario;

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const response = await api.get(`/usuarios/${idUsuario}`);
                const usuario = response.data;
                setNome(usuario.nome || 'Usuário Desconhecido');
                setEmail(usuario.email || 'E-mail Desconhecido');
                setEspecialidade(formatSubjectName(usuario.especialidade?.materia) || 'Desconhecido');
            } catch (error) {
                console.error("Erro ao carregar dados do usuário:", error);
            }
        };
        fetchUsuario();
    }, [idUsuario]);

    const nomeFormatado = useMemo(() => {
        const nomes = nome.trim().split(' ');
        return nomes.length === 1 ? nomes[0] : `${nomes[0]} ${nomes[nomes.length - 1]}`;
    }, [nome]);

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
            }
        };
        fetchClassificacao();
    }, [idUsuarioLogado]);

    const formatSubjectName = (name) => {
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
        return subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
    };

    useEffect(() => {
        const fetchContribuicoes = async () => {
            try {
                const response = await api.get(`/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-do-usuario/${idUsuarioLogado}`);
                const qtdReacoes = response.data.qtdReacoes;
                setContribuicoes(qtdReacoes >= 1 ? qtdReacoes : 0);
                if (qtdReacoes < 1) setClassificacao('Iniciante');
            } catch (error) {
                console.error("Erro ao carregar contribuições:", error);
            }
        };
        fetchContribuicoes();
    }, [idUsuarioLogado]);

    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);

    return (
        <>
            <Header />
            <div className={styles.perfil}>
                <div className={styles.corFundo}>
                    <div className={styles.userInfo}>
                        {avatar}
                        <span className={styles.nome}>{nome}</span>
                        <span className={styles.email}>{email}</span>
                    </div>
                </div>
                <div className={styles.cards}>
                    <CardPerfil conteudo={classificacao} classificacao="Classificação" />
                    <CardPerfil conteudo={contribuicoes} classificacao="Contribuições" />
                    <CardPerfil conteudo={especialidade} classificacao="Especialidade" />
                </div>
                <div className={styles.atividade}>
                    <CardAtividade idUsuario={idUsuarioLogado} />
                </div>
            </div>
        </>
    );
};

export default Perfil;
