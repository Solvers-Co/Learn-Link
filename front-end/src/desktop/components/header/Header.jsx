import styles from './Header.module.css';
import stylesBotao from '../../../mobile/components/botoes/botaoLoginCadastro/Botao.module.css';
import Logo from '../../utils/assets/logo-branca.png';
import Botao from '../../../mobile/components/botoes/botaoLoginCadastro/Botao';
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate(); 

    const handleHome = () => {
        navigate('/homeDesktop'); 
    };

    const handleCadastro = () => {
        navigate('/cadastroDesktop'); 
    };

    const handleLogin = () => {
        navigate('/loginDesktop'); 
    };

    // Função customizada para scroll suave com duração controlada
    const scrollToSection = (sectionId) => {
        const target = document.getElementById(sectionId);
        if (!target) return;

        const targetPosition = target.getBoundingClientRect().top + window.scrollY;
        const startPosition = window.scrollY;
        const distance = targetPosition - startPosition;
        const duration = 1000; // Duração em milissegundos
        let startTime = null;

        const ease = (t, b, c, d) => {
            t /= d / 2;
            if (t < 1) return c / 2 * t * t + b;
            t--;
            return -c / 2 * (t * (t - 2) - 1) + b;
        };

        const animation = (currentTime) => {
            if (startTime === null) startTime = currentTime;
            const timeElapsed = currentTime - startTime;
            const run = ease(timeElapsed, startPosition, distance, duration);
            window.scrollTo(0, run);
            if (timeElapsed < duration) requestAnimationFrame(animation);
        };

        requestAnimationFrame(animation);
    };

    return (
        <header className={styles.header}>
            <img className={styles.logo} src={Logo} onClick={handleHome} alt="Logo" />
            <nav className={styles.secoes}>
                <a href="#home" onClick={() => scrollToSection('home')}>Home</a>
                <a href="#solucao" onClick={() => scrollToSection('solucao')}>Solução</a>
                <a href="#beneficios" onClick={() => scrollToSection('beneficios')}>Benefícios</a>
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
    );
}

export default Header;
