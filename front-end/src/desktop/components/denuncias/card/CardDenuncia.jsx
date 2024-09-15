import React, { useState, useMemo } from 'react';
import styles from './CardDenuncia.module.css';
import api from '../../../../api';
import { toast } from 'react-toastify';
// import { generateInitials } from '../../../utils/functions/GerarIniciais';


import IconeDenuncia from '../../../utils/assets/icone_denuncia_vermelho.png';
import MenuVertical from '../../../../mobile/utils/assets/MenuVertical.png';
import Excluir from '../../../../mobile/utils/assets/Deletar.png';
import Ignorar from '../../../utils/assets/icone_ignorar.png';

function generateInitials(name) {
    if (!name) return '';

    const nameParts = name.trim().split(' ');
    const firstInitial = nameParts[0].charAt(0).toUpperCase();
    const lastInitial = nameParts.length > 1 ? nameParts[nameParts.length - 1].charAt(0).toUpperCase() : '';

    return `${firstInitial}${lastInitial}`;
}

function ignorarDenuncia(id, tipo) {
    const endpoint = tipo === 'publicacoes' ? `/publicacoes/${id}/remover-denuncias` : `/comentarios/${id}/remover-denuncias`;
    api.delete(endpoint)
        .then(response => {
            toast.success("Denúncia ignorada com sucesso!");
            window.location.reload();
        })
        .catch(error => {
            console.error("Erro ao ignorar denúncia:", error);
        });
}

function deletarItem(id, tipo) {
    const endpoint = tipo === 'publicacoes' ? `/publicacoes/${id}` : `/comentarios/${id}`;
    api.delete(endpoint)
        .then(response => {
            toast.success(`${tipo === 'publicacoes' ? 'Publicação' : 'Comentário'} deletado(a) com sucesso!`);
            window.location.reload();
        })
        .catch(error => {
            console.error(`Erro ao deletar ${tipo === 'publicacoes' ? 'publicação' : 'comentário'}:`, error);
        });
}

const CardDenuncia = ({ idItem, item, quantidadeDenuncias, tipo }) => {
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [showConfirmationIgnore, setShowConfirmationIgnore] = useState(false);

    const togglePopup = () => setShowPopup(!showPopup);

    const avatarColor = useMemo(() => {
        const pastelColors = [
            '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        ];
        const randomIndex = Math.floor(Math.random() * pastelColors.length);
        return pastelColors[randomIndex];
    }, []);

    const avatarInitials = useMemo(() => generateInitials(item.usuario.nome), [item.usuario.nome]);

    return (
        <div className={styles.card}>
            <div className={styles.header}>
                <div className={styles.menuVertical} onClick={togglePopup}>
                    <img src={MenuVertical} alt="Menu" />
                </div>
            </div>
            <div className={styles.userInfo}>
                <div
                    style={{
                        borderRadius: '50%',
                        border: '1px solid rgba(0, 0, 0, .3)',
                        width: '35px',
                        height: '35px',
                        marginRight: '12px',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        fontFamily: '"NunitoSansExtraBold", sans-serif',
                        backgroundColor: avatarColor
                    }}
                >
                    {avatarInitials}
                </div>
                <span className={styles.nome}>{item.usuario.nome}</span>
            </div>
            <div className={styles.conteudo}>
                {item.conteudo || item.comentario}
            </div>
            <div className={styles.denuncia}>
                <img src={IconeDenuncia} className={styles.iconeDenuncia} alt='Ícone de denúncia' />
                <div className={styles.denuncias}>
                    <span className={styles.quantidadeDenuncias}>{quantidadeDenuncias}</span>
                    <p>denúncias</p>
                </div>
            </div>

            {showPopup && (
                <div className={styles.popup}>
                    <div className={styles.opcao} onClick={() => { setShowPopup(false); setShowConfirmationIgnore(true); }}>
                        <img src={Ignorar} alt="Ignorar" />
                        <span>Ignorar</span>
                    </div>
                    <div className={styles.opcao} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                        <img src={Excluir} alt="Excluir" />
                        <span>Excluir</span>
                    </div>
                </div>
            )}

            {showConfirmationIgnore && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h3>Deseja ignorar esta denúncia?</h3>
                        <button className={styles.confirmButton} onClick={() => { ignorarDenuncia(idItem, tipo); setShowConfirmationIgnore(false); }}>Sim</button>
                        <button className={styles.cancelButton} onClick={() => setShowConfirmationIgnore(false)}>Cancelar</button>
                    </div>
                </div>
            )}

            {showConfirmation && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h3>Confirmar Exclusão</h3>
                        <p>Tem certeza de que deseja excluir esta {tipo === 'publicacoes' ? 'publicação' : 'comentário'}?</p>
                        <button className={styles.confirmButton} onClick={() => { deletarItem(idItem, tipo); setShowConfirmation(false); }}>Sim</button>
                        <button className={styles.cancelButton} onClick={() => setShowConfirmation(false)}>Cancelar</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default CardDenuncia;
