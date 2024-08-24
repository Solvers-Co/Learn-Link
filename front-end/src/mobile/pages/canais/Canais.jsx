import React, { useEffect, useState } from 'react';
import Styles from '../canais/Canais.module.css';
import api from "../../../api";
// import Header from '../../components/header/Header';
import Card from './cardCanais/CardCanais';

// Import dos ícones necessários
import mathIcon from '../../utils/assets/canais/Matematica.png';
import portuguesIcon from '../../utils/assets/canais/Portugues.png';
import biologiaIcon from '../../utils/assets/canais/Biologia.png';
import HistoryIcon from '../../utils/assets/canais/Historia.png';
import fisicaIcon from '../../utils/assets/canais/Fisica.png';
import quimicaIcon from '../../utils/assets/canais/Quimica.png';
import sociologiaIcon from '../../utils/assets/canais/Sociologia.png';
import GeografiaIcon from '../../utils/assets/canais/Geografia.png';
import inglesIcon from '../../utils/assets/canais/Ingles.png';
import filosofiaIcon from '../../utils/assets/canais/Filosofia.png';
import notFoundIcon from '../../utils/assets/canais/NotFound.png';


// Mapeamento de ícones para matérias
const iconMap = {
    'MATEMATICA': mathIcon,
    'HISTORIA': HistoryIcon,
    'GEOGRAFIA': GeografiaIcon,
    'QUIMICA': quimicaIcon,
    'PORTUGUES': portuguesIcon,
    'FISICA': fisicaIcon,
    'BIOLOGIA': biologiaIcon,
    'INGLES': inglesIcon,
    'FILOSOFIA': filosofiaIcon,
    'SOCIOLOGIA': sociologiaIcon,
};

const subjectNameMap = {
    'MATEMATICA': 'Matemática',
    'HISTORIA': 'História',
    'GEOGRAFIA': 'Geografia',
    'QUIMICA': 'Química',
    'PORTUGUES': 'Português',
    'FISICA': 'Física',
    'BIOLOGIA': 'Biologia',
    'INGLES': 'Inglês',
    'FILOSOFIA': 'Filosofia',
    'SOCIOLOGIA': 'Sociologia', 
};

// Função para formatar o nome das matérias com acento
const formatSubjectName = (name) => {
    return subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
};

const Canais = () => {
    const [cardsData, setCardsData] = useState([]);

    useEffect(() => {
        api.get('/qtd-materias-nao-respondidas')
            .then(response => {
                console.log("Dados recebidos:", response.data);
                setCardsData(response.data); // Atualize o estado com os dados recebidos
            })
            .catch(error => {
                console.error("Erro ao buscar os dados:", error);
            });
    }, []);

    return (
        //header  errado 
        // <Header />
        <>
        <div className={Styles['telaCanais']}>
            <div className={Styles['titulo']}>Canais</div>
            {cardsData.map((item) => (
                <Card
                    key={item.nomeMateria}
                    nomeMateria={item.nomeMateria}
                    image={iconMap[item.nomeMateria] || notFoundIcon} // Use um ícone padrão se não encontrar um ícone correspondente
                    buttonText={formatSubjectName(item.nomeMateria)} // Formate o nome da matéria
                    unansweredCount={item.qtdPublicacoesNaoRespondidas}
                />
            ))}
        </div>
        </>
    );
};

export default Canais;
