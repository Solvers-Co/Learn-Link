import { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom'; 
import { useState } from 'react'; 
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
    const [isVisible, setIsVisible] = useState(true);
    const [sectionToScroll, setSectionToScroll] = useState(null); // Guarda a seção a ser rolada
    const navigate = useNavigate(); 
    const location = useLocation(); // Para detectar mudanças na URL

    const handleCadastro = () => {
        navigate('/cadastro'); 
    };

    const handleLogin = () => {
        navigate('/login'); 
    };

    const scrollToSection = (sectionId) => {
        if (location.pathname !== '/') {
            navigate('/', { state: { sectionId } });
        } else {
            const section = document.getElementById(sectionId);
            if (section) {
                section.scrollIntoView({ behavior: 'smooth' });
            }
        }
    };

    useEffect(() => {
        if (location.pathname === '/' && sectionToScroll) {
            scrollToSection(sectionToScroll);
            setSectionToScroll(null); // Reseta a seção após a rolagem
        }
    }, [location.pathname]);

    const handleClose = () => {
        setIsVisible(false); 
    };

    if (!isVisible) return null;

    return (
        <div className={styles.container}>
            <div className={styles.ladoEsquerdo}></div>

            <div className={styles.menuInicial}>
                <img src={IconeX} className={styles.cancelar} onClick={handleClose} alt="Fechar Menu" />
                <div className={styles.navegacaoSite}>
                    <OpcaoNavegacao 
                        icone={IconeHome} 
                        nomeSecao="Home" 
                        onClick={() => scrollToSection('home')} 
                    />
                    <OpcaoNavegacao 
                        icone={IconeSolucoes} 
                        nomeSecao="Soluções" 
                        onClick={() => scrollToSection('solucoes')} 
                    />
                    <OpcaoNavegacao 
                        icone={IconeBeneficios} 
                        nomeSecao="Benefícios" 
                        onClick={() => scrollToSection('beneficios')} 
                    />
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
