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
    const [classificacao, setClassificacao] = useState([]);
    const [especialidade, setEspecialidade] = useState([]);
    const [contribuicoes, setContribuicoes] = useState([]);

    // Obtem o nome do usuário armazenado no sessionStorage
    const idUsuarioLogado = sessionStorage.getItem('userId') || 'N/A';
    const nomeUsuarioLogado = sessionStorage.getItem('nome') || 'Usuário Anônimo';
    const emailUsuarioLogado = sessionStorage.getItem('email') || 'E-mail Anônimo';

    let nomeFormatado = 'Usuário Desconhecido'; // Valor padrão caso o nome não seja encontrado

    if (nomeUsuarioLogado) {
        const nomes = nomeUsuarioLogado.trim().split(' '); // Remove espaços em branco e divide a string em palavras
        const primeiroNome = nomes[0];
        const ultimoNome = nomes[nomes.length - 1];
        if (nomes.length === 1) {
            nomeFormatado = primeiroNome;
        } else {
            nomeFormatado = `${primeiroNome} ${ultimoNome}`;
        }
    } else {
        console.log('Nome de usuário não encontrado');
    }

    // Use useMemo com nomeFormatado
    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);

    useEffect(() => {
        const fetchClassificacao = async () => {
            try {
                const response = await api.patch(`/usuarios/classificar-usuario/${idUsuarioLogado}`);
                if (response.data.classificacao.classificacao === 'JUNIOR') {
                    setClassificacao('Júnior');
                } else if (response.data.classificacao.classificacao === 'PLENO') {
                    setClassificacao('Pleno');
                } else if (response.data.classificacao.classificacao === 'SENIOR') {
                    setClassificacao('Sênior');
                } else if (response.data.classificacao.classificacao === 'ESPECIALISTA') {
                    setClassificacao('Especialista');
                }
            } catch (error) {
                console.error("Ocorreu um erro ao buscar os dados do usuário:", error);
                setClassificacao('Erro ao carregar');
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
    
    const formatSubjectName = (name) => {
        return subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
    };

    useEffect(() => {
        const fetchEspecialidade = async () => {
            try {
                const response = await api.get(`/usuarios/${idUsuarioLogado}`);
                setEspecialidade(formatSubjectName(response.data.especialidade.materia));
            } catch (error) {
                console.error("Ocorreu um erro ao buscar os dados do usuário:", error);
                setEspecialidade('Erro ao carregar');
            }
        };
        fetchEspecialidade();
    }, [idUsuarioLogado]);

    useEffect(() =>{
        const fetchContribuicoes = async () =>{
            try{
                const response = await api.get(`/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-do-usuario/${idUsuarioLogado}`)
                setContribuicoes(response.data.qtdReacoes);
                console.log(response)
                console.log(contribuicoes)
            }catch(error){
                console.error("Ocorreu um erro ao buscar os dados do usuário:", error)
                setContribuicoes('Erro ao carregar')
            }
        };
        fetchContribuicoes();
    },[idUsuarioLogado])


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
