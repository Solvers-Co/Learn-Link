import React, { useEffect, useState } from 'react';
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';
import api from '../../../../api';
import Tooltip from '../../../../mobile/components/tooltip/Tooltip';

const TelaDenuncias = () => {
    const [denuncias, setDenuncias] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [tipoDenuncia, setTipoDenuncia] = useState('publicacoes');

    const fetchDenuncias = async () => {
        setLoading(true);
        try {
            const endpoint = tipoDenuncia === 'publicacoes'
                ? '/publicacoes/denuncias'
                : '/comentarios/denuncias';
            const response = await api.get(endpoint);
            setDenuncias(response.data);
        } catch (err) {
            setError('Não foi possível carregar as denúncias');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchDenuncias();
    }, [tipoDenuncia]);

    const handleTipoDenunciaChange = (e) => setTipoDenuncia(e.target.value);

    return (
        <div className={styles.telaDenuncias}>
            <div className={styles.cabecalho}>
                <div className={styles.titulo}>
                    <Titulo>Denúncias</Titulo>
                    <div className={styles.tooltip}>
                        <Tooltip txt="Ao ignorar uma denúncia, ela continuará na plataforma. 
                        Ao excluir a denúncia, a publicação/comentário será apagado da plataforma." />
                    </div>
                </div>
                <select
                    value={tipoDenuncia}
                    onChange={handleTipoDenunciaChange}
                    className={styles.dropdown}
                >
                    <option value="publicacoes">Publicações</option>
                    <option value="comentarios">Comentários</option>
                </select>
            </div>
            {loading ? (
                <div>Carregando...</div>
            ) : error ? (
                <div>{error}</div>
            ) : (
                    <div className={styles.cardContainer}>
                <div className={styles.cardsDenuncias}>
                        
                    {denuncias.length === 0 ? (
                        <div className={styles.semDenuncias}>Não há denúncias para exibir</div>
                    ) : (
                        denuncias.map((denuncia, index) => (
                            <CardDenuncia
                            key={index}
                            idItem={denuncia.publicacao?.id || denuncia.comentario?.id}
                            item={denuncia.publicacao || denuncia.comentario}
                            quantidadeDenuncias={denuncia.quantidadeDenuncias}
                            tipo={tipoDenuncia} // Passa o tipo de denúncia
                            />
                        ))
                    )}
                    </div>
                </div>
            )}
        </div>
    );
};

export default TelaDenuncias;
