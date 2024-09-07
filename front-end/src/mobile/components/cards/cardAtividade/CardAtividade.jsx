import { useState, useEffect, useRef } from 'react';
import styles from './CardAtividade.module.css';

const CardAtividade = () => {
    const [selectedIndex, setSelectedIndex] = useState(null); // Estado para armazenar o índice do quadrado clicado
    const cardRef = useRef(null); // Referência para o container dos quadrados

    // Função para calcular as últimas 30 datas
    const gerarUltimasDatas = (quantidade) => {
        const datas = [];
        const hoje = new Date();
        for (let i = quantidade - 1; i >= 0; i--) { // Começa do índice mais alto para a data mais recente
            const data = new Date();
            data.setDate(hoje.getDate() - i);
            datas.push(data.toLocaleDateString());
        }
        return datas;
    };

    const datas = gerarUltimasDatas(30);

    // Função para renderizar os quadrados com as datas
    const renderQuadrados = (quantidade, startIndex) => (
        Array.from({ length: quantidade }, (_, index) => {
            const currentIndex = startIndex + index;
            const isGray = currentIndex >= datas.length - 3; // Verifica se é um dos últimos 3 quadrados
            return (
                <div
                    key={index}
                    className={`${styles.quadrado} ${isGray ? styles.cinza : ''}`}
                    onClick={() => setSelectedIndex(currentIndex)} // Atualiza o índice ao clicar
                >
                    {selectedIndex === currentIndex && (
                        <div className={styles.title}>{datas[currentIndex]}</div>
                    )}
                </div>
            );
        })
    );

    // Função para lidar com cliques fora do card
    const handleClickOutside = (event) => {
        if (cardRef.current && !cardRef.current.contains(event.target)) {
            setSelectedIndex(null); // Oculta a data ao clicar fora
        }
    };

    // Adiciona e remove o listener de clique fora
    useEffect(() => {
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    return (
        <div ref={cardRef}>
            <div className={styles.header}>
                <span className={styles.titulo}>Atividade dos últimos 30 dias</span>
            </div>
            <div className={styles.card}>
                <div className={styles.primeiraLinha}>
                    {renderQuadrados(15, 0)}
                </div>
                <div className={styles.segundaLinha}>
                    {renderQuadrados(15, 15)}
                </div>
            </div>
        </div>
    );
};

export default CardAtividade;
