import { useNavigate } from 'react-router-dom';
import { useEffect, useMemo, useState } from 'react';
import styles from './MenuLateral.module.css';
import IconePerfil from '../../utils/assets/perfil-menu-lateral.png';
import IconeFeedGeral from '../../utils/assets/feed-geral-menu-lateral.png';
import IconeCanais from '../../utils/assets/canais-menu-lateral.png';
import IconeNotificacoes from '../../utils/assets/notificacoes-menu-lateral.png';
import IconeRanking from '../../utils/assets/ranking-menu-lateral.png';
import IconeLivros from '../../utils/assets/livros-menu-lateral.png';
import IconeSair from '../../utils/assets/sair-menu-lateral.png';
import OpcaoNavegacao from '../opcaoNavegacaoMenuInicial/OpcaoNavegacao';
import IconeX from '../../utils/assets/icone_x.svg';
import Linha from '../linha/Linha';
import { generateInitials } from '../../utils/functions/GerarIniciais';
import api from "../../../api";
import { toast } from 'react-toastify';

const MenuLateral = ({ nome }) => {
    const [isVisible, setIsVisible] = useState(true); // Adiciona estado para visibilidade
    const [srcImagemPerfil, setSrcImagemPerfil] = useState('')
    const navigate = useNavigate();

    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    // Mova a declaração de nomeFormatado para fora do bloco if
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
        toast.error('Nome de usuário não encontrado');
    }
    useEffect(() => {
        async function buscarImagemPerfil() {
            try {
                const response = await api.get(`usuarios/buscar-imagem-perfil/${sessionStorage.getItem("userId")}`);
                setSrcImagemPerfil(response.data)
                // setSrcImagemPerfil("https://s3-learnlink.s3.us-east-1.amazonaws.com/WIN_20240909_09_30_09_Pro.jpg")
            } catch (error) {
                toast.error("Erro ao buscar imagem de perfil")
            }
        }
        buscarImagemPerfil();
    }, [])


    // Use useMemo com nomeFormatado
    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);
    const handlePerfil = () => {
        navigate(`/perfil/${sessionStorage.getItem('userId')}`);
        window.location.reload();
    };

    const handleFeedGeral = () => {
        navigate('/feedGeral');
        window.location.reload();
    };

    const handleCanais = () => {
        navigate('/canais');
    };

    const handleNotificacoes = () => {
        navigate('/notificacoes');
    };

    const handleRanking = () => {
        navigate('/ranking');
    };

    const handleLivros = () => {
        navigate('/livrosFuvest');
    };

    const handleLogout = () => {
        sessionStorage.clear();
        navigate('/');
    };

    const handleClose = () => {
        setIsVisible(false); // Oculta o menu
    };

    if (!isVisible) return null; // Retorna null se o menu não estiver visível

    return (
        <div className={styles.container}>
            <div className={styles.ladoEsquerdo}></div>

            <div className={styles.menuInicial}>
                <img src={IconeX} className={styles.cancelar} onClick={handleClose} alt="Fechar Menu" />
                <div className={styles.userInfo}>
                    {srcImagemPerfil ? (
                        <img className={styles.imagemPerfil} src={srcImagemPerfil} alt="Imagem de Perfil" />
                    ) : (
                        <span className={styles.avatar}>{avatar}</span>
                    )}
                    <span className={styles.nome}>{nomeFormatado}</span>
                </div>
                <Linha />
                <div className={styles.navegacaoSite}>
                    <OpcaoNavegacao
                        icone={IconePerfil}
                        nomeSecao="Perfil"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handlePerfil}
                    />
                    <OpcaoNavegacao
                        icone={IconeFeedGeral}
                        nomeSecao="Feed Geral"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleFeedGeral}
                    />
                    <OpcaoNavegacao
                        icone={IconeCanais}
                        nomeSecao="Canais"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleCanais}
                    />
                    <OpcaoNavegacao
                        icone={IconeNotificacoes}
                        nomeSecao="Notificações"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleNotificacoes}
                    />
                    <OpcaoNavegacao
                        icone={IconeRanking}
                        nomeSecao="Ranking"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleRanking}
                    />
                    <OpcaoNavegacao
                        icone={IconeLivros}
                        nomeSecao="Livros Fuvest"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleLivros}
                    />

                </div>
                <Linha />
                <div className={styles.logout}>
                    <OpcaoNavegacao
                        icone={IconeSair}
                        nomeSecao="Sair"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleLogout}
                    />
                </div>
            </div>
        </div>
    );
}

export default MenuLateral;
