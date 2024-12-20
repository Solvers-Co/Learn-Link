import React, { useEffect, useState } from 'react';
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';
import api from '../../../../api';
import Tooltip from '../../../../mobile/components/tooltip/Tooltip';
import Download from '../../../utils/assets/icone_download.png';
import IA from '../../../utils/assets/icone_robo.png';
import { toast } from 'react-toastify';

const TelaDenuncias = () => {
    const [denuncias, setDenuncias] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [tipoDenuncia, setTipoDenuncia] = useState('publicacao');
    const [modoSelecao, setModoSelecao] = useState(false);
    const [showModal, setShowModal] = useState(false); // Estado para controlar a exibição do modal
    const [ordenadoPorIa, setOrdenadoPorIa] = useState(false); // Estado para controlar a ordenação por IA

    // Listas de IDs selecionados para cada tipo
    const [publicacoesSelecionadas, setPublicacoesSelecionadas] = useState([]);
    const [comentariosSelecionados, setComentariosSelecionados] = useState([]);

    const fetchDenuncias = async () => {
        setLoading(true);
        try {
            const endpoint = tipoDenuncia === 'publicacao'
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

    const fetchDenunciasPorIa = async () => {
        if (ordenadoPorIa) {
            toast.info('As denúncias já estão ordenadas por IA');
            // setLoading(false);
            return;
        }
        setLoading(true);
        try {
            const endpoint = tipoDenuncia === 'publicacao'
                ? '/publicacoes/denuncias/ordenadasPorIa'
                : '/comentarios/denuncias/ordenadasPorIa';
            const response = await api.get(endpoint);
            setDenuncias(response.data);
            setOrdenadoPorIa(true);
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
        setPublicacoesSelecionadas([]);
        setComentariosSelecionados([]);
        setOrdenadoPorIa(false);
    };

    const toggleModoSelecao = () => setModoSelecao(!modoSelecao);

    const toggleSelecao = (id, tipo) => {
        if (tipo === 'publicacao') {
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

    const removerDenunciasSelecionadas = async () => {
        try {
            if (publicacoesSelecionadas.length === 0 && comentariosSelecionados.length === 0) {
                toast.error('Nenhum item foi selecionado');
                return;
            }

            let countPublicacoes = 0;
            let countComentarios = 0;

            for (const id of publicacoesSelecionadas) {
                await api.delete(`/publicacoes/${id}`);
                countPublicacoes++;
            }

            for (const id of comentariosSelecionados) {
                await api.delete(`/comentarios/${id}`);
                countComentarios++;
            }

            await fetchDenuncias();
            setPublicacoesSelecionadas([]);
            setComentariosSelecionados([]);

            toast.success(`${countPublicacoes} ${countPublicacoes === 1 ? 'publicação' : 'publicações'} Deletada(s) e ${countComentarios} ${countComentarios === 1 ? 'comentário' : 'comentários'} Deletado(s).`);
        } catch (error) {
            console.error('Erro ao remover denúncias:', error);
            toast.error('Erro ao remover denúncias.');
        }
    };

    const gerarRelatorio = async (formato, tipo) => {
        try {
            const endpoint = `/publicacoes/denuncias/${formato}?tipo=${tipo}`;
            const response = await api.get(endpoint, { responseType: 'blob' });

            const blob = new Blob([response.data], { type: `text/${formato}` });
            const downloadUrl = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = downloadUrl;
            link.setAttribute('download', `denuncias.${formato}`);
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);

            setShowModal(false); // Fechar o modal após o download
        } catch (error) {
            console.error(`Falha ao gerar o relatório ${formato}`, error);
            toast.error('Erro ao gerar o relatório.');
        }
    };



    return (
        <div className={styles.telaDenuncias}>
            <div className={styles.cabecalho}>
                <div className={styles.titulo}>
                    <Titulo>Denúncias</Titulo>
                    <div className={styles.tooltip}>
                        <Tooltip txt="Ao ignorar uma denúncia, ela continuará na plataforma. Ao excluir a denúncia, a publicação/comentário será apagado da plataforma." />
                    </div>
                    <img
                        src={Download}
                        alt="Download"
                        className={styles.download}
                        onClick={() => setShowModal(prev => !prev)} // Alterna entre abrir e fechar o modal
                        title="Exportação de relatórios"
                    />
                    <button onClick={fetchDenunciasPorIa} className={styles.btnOrdenarPorIa}>
                        Moderador Virtual
                        <div className={styles.imgBtn}>
                            <img src={IA} alt="IA" className={styles.imgRobo} />
                        </div>
                    </button>
                    <div className={styles.tooltip}>
                        <Tooltip txt={"O Moderador Virtual ajuda a destacar as publicações ou comentários que podem ser nocivos. A ordenação é feita por IA, podendo haver erros."} />
                    </div>
                </div>
                
                {showModal && (
                    <div className={styles.modal}>
                        <div className={styles.modalContent}>
                            <h3>Escolha o formato do relatório</h3>
                            <div className={styles.buttons}>
                                <button onClick={() => gerarRelatorio('csv', tipoDenuncia)} className={styles.relatButton}>CSV</button>
                                <button onClick={() => gerarRelatorio('txt', tipoDenuncia)} className={styles.relatButton}>TXT</button>
                                <button onClick={() => gerarRelatorio('json', tipoDenuncia)} className={styles.relatButton}>JSON</button>
                                <button onClick={() => gerarRelatorio('xml', tipoDenuncia)} className={styles.relatButton}>XML</button>
                                <button onClick={() => setShowModal(false)} className={styles.relatButtonRed}>Cancelar</button>
                            </div>
                        </div>
                    </div>
                )}
                {modoSelecao && (
                    <button className={styles.btnRemover} onClick={removerDenunciasSelecionadas}>
                        Remover Denúncias Selecionadas
                    </button>
                )}
                <div className={styles.interacao}>
                    <button className={styles.btnSelecionar} onClick={toggleModoSelecao}>
                        {modoSelecao ? 'Cancelar' : 'Selecionar'}
                    </button>
                    <select
                        value={tipoDenuncia}
                        onChange={handleTipoDenunciaChange}
                        className={styles.dropdown}
                    >
                        <option value="publicacao">Publicações</option>
                        <option value="comentario">Comentários</option>
                    </select>
                </div>
            </div>

            {loading ? (
                <div className={styles.msgTelaCarregando}>Carregando...</div>
            ) : error ? (
                <div className={styles.msgTelaErro}>{error}</div>
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
                                    toggleSelecao={toggleSelecao}
                                    isSelected={
                                        tipoDenuncia === 'publicacao'
                                            ? publicacoesSelecionadas.includes(denuncia.publicacao?.id)
                                            : comentariosSelecionados.includes(denuncia.comentario?.id)
                                    }
                                    carregarDenuncias={fetchDenuncias}
                                    classificacao={denuncia?.classificacao}
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
