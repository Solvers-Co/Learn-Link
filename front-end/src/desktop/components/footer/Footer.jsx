import React, { useState, useEffect } from 'react';
import styles from './Footer.module.css'; // Ensure to style the footer correctly
import Logo from '../../utils/assets/Logo vermelha fundo branco.png';

function Footer() {

    const [isScrolled, setIsScrolled] = useState(false);
    const [activeSection, setActiveSection] = useState('home');

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
        <footer className={styles['footer']}>
            <div className={styles['logo']}>
                <img src={Logo} alt="Learn Link Logo" className={styles['footer-logo']} onClick={() => scrollToSection('home')}/>
            </div>

            <div className={styles['direitos']}>
                <p>Copyright &copy; 2024 Learn Link</p>
                <p>Todos os direitos reservados</p>
            </div>

            <div className={styles['linha']}></div>

            <div className={styles['paginas']}>
                <ul>
                    <p className={styles['learnLink']}>Learn Link</p>
                    <li onClick={() => scrollToSection('home')}>Home</li>
                    <li onClick={() => scrollToSection('solucao')}>Solução</li>
                    <li onClick={() => scrollToSection('beneficios')}>Benefícios</li>
                </ul>
            </div>
        </footer>
    );
}

export default Footer;
