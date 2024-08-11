import { useNavigate } from 'react-router-dom';  // Adicione esta linha
import { useState } from 'react'; // Adicione esta linha
import styles from './MenuInicial.module.css';
import stylesBotao from '../botoes/botaoLoginCadastro/Botao.module.css';
import IconeHome from '../../utils/assets/icone_home.svg';
import IconeSolucoes from '../../utils/assets/icone_solucoes.svg';
import IconeBeneficios from '../../utils/assets/icone_beneficios.svg';
import OpcaoNavegacao from '../opcaoNavegacaoMenuInicial/OpcaoNavegacao';
import IconeX from '../../utils/assets/icone_x.svg';
import Linha from '../linha/Linha';
import Botao from '../botoes/botaoLoginCadastro/Botao';

const MenuInicial = () => {
    const [isVisible, setIsVisible] = useState(true); // Adiciona estado para visibilidade
    const navigate = useNavigate(); 

    const handleCadastro = () => {
        navigate('/cadastro'); 
    };

    const handleLogin = () => {
        navigate('/login'); 
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
                <div className={styles.navegacaoSite}>
                    <OpcaoNavegacao icone={IconeHome} nomeSecao="Home" />
                    <OpcaoNavegacao icone={IconeSolucoes} nomeSecao="Soluções" />
                    <OpcaoNavegacao icone={IconeBeneficios} nomeSecao="Benefícios" />
                </div>
                <Linha />
                <div className={styles.tituloBotoes}>
                    <p className={styles.titulo}>FAÇA PARTE</p>
                    <Botao
                        className={stylesBotao.botaoVermelhoMenuInicial}
                        textoBotao="Cadastro"
                        funcao={handleCadastro}  
                    />
                    <Botao
                        className={stylesBotao.botaoBrancoMenuInicial}
                        textoBotao="Login" 
                        funcao={handleLogin} 
                        largura="60%"
                        altura="40px"
                    />
                </div>
            </div>
        </div>
    );
}

export default MenuInicial;
