import { useNavigate } from 'react-router-dom';  // Adicione esta linha
import { useMemo, useState } from 'react'; // Adicione esta linha
import styles from './MenuLateral.module.css';
import stylesBotao from '../botoes/botaoLoginCadastro/Botao.module.css';
import IconePerfil from '../../utils/assets/perfil-menu-lateral.png';
import IconeFeedGeral from '../../utils/assets/feed-geral-menu-lateral.png';
import IconeDoacoes from '../../utils/assets/doacoes-menu-lateral.png';
import IconeCanais from '../../utils/assets/canais-menu-lateral.png';
import IconeNotificacoes from '../../utils/assets/notificacoes-menu-lateral.png';
import IconeRanking from '../../utils/assets/ranking-menu-lateral.png';
import IconeSair from '../../utils/assets/sair-menu-lateral.png';
import OpcaoNavegacao from '../opcaoNavegacaoMenuInicial/OpcaoNavegacao';
import IconeX from '../../utils/assets/icone_x.svg';
import Linha from '../linha/Linha';
import Usuario from '../../utils/assets/Usuario.png'

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
        fontSize: '15px',
        width: '30px',
        height: '30px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSans", sans-serif',
        backgroundColor
    };

    return <div style={avatar}>{firstInitial + lastInitial}</div>;
}


const MenuLateral = ({ nome }) => {
    const [isVisible, setIsVisible] = useState(true); // Adiciona estado para visibilidade
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
        console.log('Nome de usuário não encontrado');
    }

    // Use useMemo com nomeFormatado
    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);

    const handlePerfil = () => {
        navigate('/perfil');
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

    const handleLogout = () => {
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
                    {avatar}
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

