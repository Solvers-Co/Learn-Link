import React, { useState, useMemo } from 'react';
import styles from './CardDenuncia.module.css';
import api from '../../../../api';
import { toast } from 'react-toastify';
import { generateInitials } from '../../../../mobile/utils/functions/GerarIniciais';

import IconeDenuncia from '../../../utils/assets/icone_denuncia_vermelho.png';
import MenuVertical from '../../../../mobile/utils/assets/MenuVertical.png';
import Excluir from '../../../../mobile/utils/assets/Deletar.png';
import Ignorar from '../../../utils/assets/icone_ignorar.png';

function ignorarDenuncia(id, tipo, carregarDenuncias) {
    const endpoint = tipo === 'publicacao' ? `/publicacoes/${id}/remover-denuncias` : `/comentarios/${id}/remover-denuncias`;
    api.delete(endpoint)
        .then(response => {
            toast.success("Denúncia ignorada com sucesso!");
            carregarDenuncias();
        })
        .catch(error => {
            console.error("Erro ao ignorar denúncia:", error);
        });
}

function deletarItem(id, tipo, carregarDenuncias) {
    const endpoint = tipo === 'publicacao' ? `/publicacoes/${id}` : `/comentarios/${id}`;
    api.delete(endpoint)
        .then(response => {
            toast.success(`${tipo === 'publicacoes' ? 'Publicação' : 'Comentário'} deletado(a) com sucesso!`);
            carregarDenuncias()
        })
        .catch(error => {
            console.error(`Erro ao deletar ${tipo === 'publicacoes' ? 'publicação' : 'comentário'}:`, error);
        });
}

const CardDenuncia = ({ idItem, item, quantidadeDenuncias, tipo, modoSelecao, toggleSelecao, isSelected, carregarDenuncias, classificacao }) => {
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [showConfirmationIgnore, setShowConfirmationIgnore] = useState(false);

    const togglePopup = () => setShowPopup(!showPopup);

    const avatar = useMemo(() => generateInitials(item.usuario.nome), [item.usuario.nome]);

    return (
        <div className={styles.card} style={classificacao === 'Nocivo' ? { border: '1px solid red' } : { border: 'none' }}>
            <div className={styles.header}>
                {modoSelecao && (
                    <div className={styles.selecao} onClick={() => toggleSelecao(idItem, tipo)}>
                        <div className={isSelected ? styles.bolinhaPreenchida : styles.bolinhaVazia}></div>
                    </div>
                )}
                <div className={styles.menuVertical} onClick={togglePopup}>
                    <img src={MenuVertical} alt="Menu" />
                </div>
            </div>
            <div className={styles.userInfo}>
                {avatar}
                <span className={styles.nome}>{item.usuario.nome}</span>
            </div>
            <div className={styles.conteudo}>
                {item.conteudo || item.comentario}
            </div>
            <div className={styles.denuncia}>
                <img src={IconeDenuncia} className={styles.iconeDenuncia} alt='Ícone de denúncia' />
                <div className={styles.denuncias}>
                    <span className={styles.quantidadeDenuncias}>{quantidadeDenuncias}</span>
                    {quantidadeDenuncias === 1 ? (
                        <p>denúncia</p>
                    ) : (
                        <p>denúncias</p>
                    )}
                </div>
            </div>

            {showPopup && (
                <div className={styles.popup}>
                    <div className={styles.opcao} onClick={() => { setShowPopup(false); setShowConfirmationIgnore(true); }}>
                        <img src={Ignorar} alt="Ignorar denúncia" />
                        <span>Ignorar</span>
                    </div>
                    <div className={styles.opcao} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                        <img src={Excluir} alt="Deletar" />
                        <span>Deletar</span>
                    </div>
                </div>
            )}

            {showConfirmationIgnore && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h3>Deseja ignorar esta denúncia?</h3>
                        <button className={styles.confirmButton} onClick={() => { ignorarDenuncia(idItem, tipo, carregarDenuncias); setShowConfirmationIgnore(false); }}>Sim</button>
                        <button className={styles.cancelButton} onClick={() => setShowConfirmationIgnore(false)}>Cancelar</button>
                    </div>
                </div>
            )}

            {showConfirmation && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h3>Confirmar Exclusão</h3>
                        <p>Tem certeza de que deseja excluir esta {tipo === 'publicacoes' ? 'publicação' : 'comentário'}?</p>
                        <button className={styles.confirmButton} onClick={() => { deletarItem(idItem, tipo, carregarDenuncias); setShowConfirmation(false); }}>Sim</button>
                        <button className={styles.cancelButton} onClick={() => setShowConfirmation(false)}>Cancelar</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default CardDenuncia;
