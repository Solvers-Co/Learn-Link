import { useState, useEffect, useRef } from 'react';
import styles from './CardAtividade.module.css';
import api from "../../../../api";

const CardAtividade = ({idUsuario}) => {
    const [selectedIndex, setSelectedIndex] = useState(null); // Estado para armazenar o índice do quadrado clicado
    const cardRef = useRef(null); // Referência para o container dos quadrados
    const [datasRetornadas, setDatasRetornadas] = useState([]);

    // Função para calcular as últimas 30 datas
    useEffect(() => {
        const fetchAtividade = async () => {
            try {
                const response = await api.get(`registro-logins/${idUsuario}`);
                const registros = response.data.map(item => {
                    // Converte LocalDateTime para o formato "DD/MM/YYYY"
                    const data = new Date(item.registroLogin); // Converte a string da API em um objeto Date
                    return data.toLocaleDateString(); // Converte para o formato DD/MM/YYYY
                });
                setDatasRetornadas(registros); // Armazena as datas no estado
            } catch (error) {
                console.error("Erro ao buscar dados da API", error);
            }
        };
        fetchAtividade();
    }, [idUsuario]);

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
            const isGray = datasRetornadas.includes(datas[currentIndex]); // Verifica se é um dos últimos 3 quadrados
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
            
            <div className={styles.card}>
            <div className={styles.header}>
                <span className={styles.titulo}>Atividade nos últimos 30 dias</span>
            </div>
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
