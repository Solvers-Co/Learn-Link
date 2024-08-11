import React, { useState, useEffect } from 'react';
import styles from './Header.module.css';
import stylesBotao from '../../../mobile/components/botoes/botaoLoginCadastro/Botao.module.css';
import Logo from '../../utils/assets/logo-branca.png';
import LogoScroll from '../../utils/assets/Logo_vermelha_fundo_branco-removebg-preview.png'; // Adicione uma nova logo para quando rolar
import Botao from '../../../mobile/components/botoes/botaoLoginCadastro/Botao';
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const [isScrolled, setIsScrolled] = useState(false);
    const [activeSection, setActiveSection] = useState('home');
    const navigate = useNavigate();

    const handleCadastro = () => {
        navigate('/cadastroDesktop');
    };

    const handleLogin = () => {
        navigate('/loginDesktop');
    };

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

    useEffect(() => {
        const handleScroll = () => {
            setIsScrolled(window.scrollY > 50); // Ajuste o valor conforme necessário

            // Atualiza a seção ativa com base na rolagem
            const sections = ['home', 'solucao', 'beneficios'];
            const scrollPosition = window.scrollY;

            sections.forEach(sectionId => {
                const section = document.getElementById(sectionId);
                if (section) {
                    const sectionTop = section.offsetTop;
                    const sectionHeight = section.clientHeight;
                    if (scrollPosition >= sectionTop - 50 && scrollPosition < sectionTop + sectionHeight - 50) {
                        setActiveSection(sectionId);
                    }
                }
            });
        };

        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, []);

    return (
        <header className={`${styles.header} ${isScrolled ? styles.scrolled : ''}`}>
            <img
                className={styles.logo}
                src={isScrolled ? LogoScroll : Logo}
                onClick={() => scrollToSection('home')}
                alt="Logo"
            />
            <nav className={styles.secoes}>
                <a
                    href="#home"
                    onClick={() => scrollToSection('home')}
                    className={activeSection === 'home' ? styles.active : ''}
                >
                    Home
                </a>
                <a
                    href="#solucao"
                    onClick={() => scrollToSection('solucao')}
                    className={activeSection === 'solucao' ? styles.active : ''}
                >
                    Solução
                </a>
                <a
                    href="#beneficios"
                    onClick={() => scrollToSection('beneficios')}
                    className={activeSection === 'beneficios' ? styles.active : ''}
                >
                    Benefícios
                </a>
            </nav>
            <div className={styles.botoes}>
                <Botao
                    className={`${isScrolled ? styles.botaoBrancoHeaderScroll : stylesBotao.botaoBrancoHeader}`}
                    textoBotao="Cadastro"
                    funcao={handleCadastro}
                />
                <Botao
                    className={`${isScrolled ? styles.botaoTransparenteHeaderScroll : stylesBotao.botaoTransparenteHeader}`}
                    textoBotao="Login"
                    funcao={handleLogin}
                />
            </div>
        </header>
    );
}

export default Header;
