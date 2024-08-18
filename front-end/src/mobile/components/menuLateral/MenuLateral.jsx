import { useNavigate } from 'react-router-dom';  // Adicione esta linha
import { useState } from 'react'; // Adicione esta linha
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

const MenuLateral = ({ nome }) => {
    const [isVisible, setIsVisible] = useState(true); // Adiciona estado para visibilidade
    const navigate = useNavigate();

    const handlePerfil = () => {
        navigate('/perfil');
    };

    const handleFeedGeral = () => {
        navigate('/feedGeral');
    };

    const handleDoacoes = () => {
        navigate('/doacoes');
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
                    <img
                        src={Usuario} alt="User"
                        className={styles.avatar}
                    />
                    <span className={styles.nome}>{nome}Sofhia Utaka</span>
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
                        icone={IconeDoacoes}
                        nomeSecao="Doações"
                        fonte='"Nunito Sans", sans-serif'
                        onClick={handleDoacoes}
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
