import styles from './CardDenuncia.module.css';
import { useMemo, useState } from 'react';

import IconeDenuncia from '../../../utils/assets/icone_denuncia_vermelho.png'
import MenuVertical from '../../../../mobile/utils/assets/MenuVertical.png';
import Excluir from '../../../../mobile/utils/assets/Deletar.png'
import Ignorar from '../../../utils/assets/icone_ignorar.png'

const CardDenuncia = ({ nome, mensagem }) => {
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [showConfirmationIgnore, setShowConfirmationIgnore] = useState(false);

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };
    const avatar = useMemo(() => generateInitials(nome), [nome]);

    function generateInitials(name) {
        if (!name) return null;

        const nameParts = name.trim().split(' ');
        const firstInitial = nameParts[0].charAt(0).toUpperCase();
        const lastInitial = nameParts[nameParts.length - 1].charAt(0).toUpperCase();

        const pastelColors = [
            '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
            '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
            '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
            '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
        ];

        const randomIndex = Math.floor(Math.random() * pastelColors.length);
        const backgroundColor = pastelColors[randomIndex];

        const avatar = {
            borderRadius: '50%',
            border: '1px solid rgba(0, 0, 0, .3)',
            width: '35px',
            height: '35px',
            marginRight: '12px',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            fontFamily: '"NunitoSansExtraBold", sans-serif',
            backgroundColor
        };

        return <div style={avatar}>{firstInitial + lastInitial}</div>
    }

    return (
        <div className={styles.card}>
            <div className={styles.header}>
                <div className={styles.menuVertical} onClick={togglePopup}>
                    <img src={MenuVertical} alt="Menu" />
                </div>
            </div>
            <div className={styles.userInfo}>
                {avatar}
                <div className={styles.avatar}></div>
                <span className={styles.nome}>
                    ...{nome}
                </span>
            </div>
            <div className={styles.conteudo}>
                {mensagem}
            </div>
            <div className={styles.denuncia}>
                <img src={IconeDenuncia} className={styles.iconeDenuncia} alt='Ícone de denúncia' />
                <div className={styles.denuncias}>
                    <span className={styles.quantidadeDenuncias}>X</span>
                    <p>denúncias</p>
                </div>
            </div>

            {showPopup && (
                <div className={styles.popup}>
                    <div className={styles.opcao} onClick={() => { setShowPopup(false); setShowConfirmationIgnore(true); }}>
                        <img src={Ignorar} alt="Ignorar" />
                        <span>Ignorar</span>
                    </div>

                    <div className={styles.linhaPopup}></div>

                    <div className={styles.opcao} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                        <img src={Excluir} alt="Excluir" />
                        <span>Excluir</span>
                    </div>
                </div>
            )}

            {showConfirmationIgnore && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h3>Deseja mesmo ignorar esta denúncia?</h3>
                        <p>Ela sairá da lista de publicações e comentários denunciados</p>
                        <button className={styles.confirmButton} >Sim</button>
                        <button className={styles.cancelButton} onClick={() => setShowConfirmationIgnore(false)}>Cancelar</button>
                    </div>
                </div>
            )}

            {showConfirmation && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        <h3>Confirmar Exclusão</h3>
                        <p>Tem certeza de que deseja excluir esta denúncia?</p>
                        <button className={styles.confirmButton} >Sim</button>
                        <button className={styles.cancelButton} onClick={() => setShowConfirmation(false)}>Cancelar</button>
                    </div>
                </div>
            )}

        </div>
    )
}

export default CardDenuncia;