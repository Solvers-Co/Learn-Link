import styles from './Header.module.css'
import stylesBotao from '../../../mobile/components/botoes/botaoLoginCadastro/Botao.module.css'
import Logo from '../../utils/assets/logo-branca.png'
import { useRef } from 'react';
import Botao from '../../../mobile/components/botoes/botaoLoginCadastro/Botao';
import { useNavigate } from 'react-router-dom';

const Header = () => {

    const navigate = useNavigate(); 

    const handleHome = () => {
        navigate('/'); 
    };

    const handleCadastro = () => {
        navigate('/cadastroDesktop'); 
    };

    const handleLogin = () => {
        navigate('/loginDesktop'); 
    };

    const homeRef = useRef(null);
    const solucaoRef = useRef(null);
    const beneficiosRef = useRef(null);

    const scrollToSection = (ref) => {
        if (ref.current) {
            ref.current.scrollIntoView({ behavior: 'smooth' });
        }
    };

    return (
        <header className={styles.header}>
            <img className={styles.logo} src={Logo} onClick={handleHome}/>
            <nav className={styles.secoes}>
                <a href="#home" onClick={(e) => { e.preventDefault(); scrollToSection(homeRef); }}>Home</a>
                <a href="#solucao" onClick={(e) => { e.preventDefault(); scrollToSection(solucaoRef); }}>Solução</a>
                <a href="#beneficios" onClick={(e) => { e.preventDefault(); scrollToSection(beneficiosRef); }}>Benefícios</a>
            </nav>
            <div className={styles.botoes}>
                <Botao
                    className={stylesBotao.botaoBrancoHeader}
                    textoBotao="Cadastro"
                    funcao={handleCadastro}  
                /> 
                <Botao
                    className={stylesBotao.botaoTransparenteHeader}
                    textoBotao="Login"
                    funcao={handleLogin}  

                />
            </div>
        </header>
    )
}

export default Header;