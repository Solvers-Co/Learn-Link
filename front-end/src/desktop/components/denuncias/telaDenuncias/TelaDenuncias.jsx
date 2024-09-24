import React, { useEffect, useState } from 'react';
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';
import api from '../../../../api';
import { toast } from 'react-toastify';

const TelaDenuncias = () => {
    const [denuncias, setDenuncias] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [tipoDenuncia, setTipoDenuncia] = useState('publicacoes');
    const [modoSelecao, setModoSelecao] = useState(false);

    // Listas de IDs selecionados para cada tipo
    const [publicacoesSelecionadas, setPublicacoesSelecionadas] = useState([]);
    const [comentariosSelecionados, setComentariosSelecionados] = useState([]);

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

    const handleTipoDenunciaChange = (e) => {
        setTipoDenuncia(e.target.value);
        // Resetar as listas de seleção quando trocar o tipo
        setPublicacoesSelecionadas([]);
        setComentariosSelecionados([]);
    };

    const toggleModoSelecao = () => setModoSelecao(!modoSelecao);

    // Adicionar ou remover itens selecionados
    const toggleSelecao = (id, tipo) => {
        if (tipo === 'publicacoes') {
            setPublicacoesSelecionadas(prev =>
                prev.includes(id)
                    ? prev.filter(item => item !== id)
                    : [...prev, id]
            );
        } else {
            setComentariosSelecionados(prev =>
                prev.includes(id)
                    ? prev.filter(item => item !== id)
                    : [...prev, id]
            );
        }
    };

    // Função para remover denúncias de itens selecionados
    const removerDenunciasSelecionadas = async () => {
        try {
            let countPublicacoes = 0;
            let countComentarios = 0;

            // Remover denúncias de publicações selecionadas
            for (const id of publicacoesSelecionadas) {
                await api.delete(`/publicacoes/${id}/remover-denuncias`);
                countPublicacoes++;
            }

            // Remover denúncias de comentários selecionados
            for (const id of comentariosSelecionados) {
                await api.delete(`/comentarios/${id}/remover-denuncias`);
                countComentarios++;
            }

            // Após a remoção, recarregar as denúncias
            await fetchDenuncias();

            // Limpar as listas de selecionados
            setPublicacoesSelecionadas([]);
            setComentariosSelecionados([]);

            // Exibir um toast com a quantidade de denúncias removidas
            toast.success(`${countPublicacoes} ${countPublicacoes === 1 ? 'publicação' : 'publicações'} denunciada(s) removida(s) e ${countComentarios} ${countComentarios === 1 ? 'comentário' : 'comentários'} denunciado(s) removido(s).`);

        } catch (error) {
            console.error('Erro ao remover denúncias:', error);
            toast.error('Erro ao remover denúncias.');
        }
    };


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
                    {modoSelecao && (
                        <button className={styles.btnRemover} onClick={removerDenunciasSelecionadas}>
                            Remover Denúncias Selecionadas
                        </button>
                    )}
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
                                    toggleSelecao={toggleSelecao} // Passa a função para alternar a seleção
                                    isSelected={
                                        tipoDenuncia === 'publicacoes'
                                            ? publicacoesSelecionadas.includes(denuncia.publicacao?.id)
                                            : comentariosSelecionados.includes(denuncia.comentario?.id)
                                    }
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
