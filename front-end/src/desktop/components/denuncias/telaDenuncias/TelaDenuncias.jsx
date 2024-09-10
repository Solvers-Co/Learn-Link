import React, { useEffect, useState } from 'react';
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';
import api from '../../../../api'; // Certifique-se de que este caminho está correto

const TelaDenuncias = () => {
    const [denuncias, setDenuncias] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchDenuncias = async () => {
            try {
                const response = await api.get('/publicacoes/denuncias'); // Fazer a requisição à API
                setDenuncias(response.data);
            } catch (err) {
                setError('Não foi possível carregar as denúncias');
            } finally {
                setLoading(false);
            }
        };

        fetchDenuncias();
    }, []);

    if (loading) return <div>Carregando...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div className={styles.telaDenuncias}>
            <div className={styles.cabecalho}>
                <Titulo>Denúncias</Titulo>
            </div>
            <div className={styles.cardsDenuncias}>
                {denuncias.length === 0 ? (
                    <div>Não há denúncias para exibir.</div>
                ) : (
                    denuncias.map((denuncia, index) => (
                        <CardDenuncia
                            key={index}
                            idPublicacao={denuncia.publicacao.id}
                            publicacao={denuncia.publicacao}
                            quantidadeDenuncias={denuncia.quantidadeDenuncias}
                        />
                    ))
                )}
            </div>
        </div>
    );
};

export default TelaDenuncias;
