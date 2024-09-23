import React, { useEffect, useState } from 'react';
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';
import api from '../../../../api';

const TelaDenuncias = () => {
    const [denuncias, setDenuncias] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [tipoDenuncia, setTipoDenuncia] = useState('publicacoes');
    const [modoSelecao, setModoSelecao] = useState(false);

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

    const toggleModoSelecao = () => setModoSelecao(!modoSelecao); 

    return (
        <div className={styles.telaDenuncias}>
            <div className={styles.cabecalho}>
                <Titulo>Denúncias</Titulo>
                <div className={styles.interacao}>
                    <button className={styles.btnSelecionar} onClick={toggleModoSelecao}>
                        {modoSelecao ? 'Cancelar' : 'Selecionar'}
                    </button>
                    <select
                        value={tipoDenuncia}
                        onChange={handleTipoDenunciaChange}
                        className={styles.dropdown}
                    >
                        <option value="publicacoes">Publicações</option>
                        <option value="comentarios">Comentários</option>
                    </select>
                </div>
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
                                    tipo={tipoDenuncia}
                                    modoSelecao={modoSelecao}
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

