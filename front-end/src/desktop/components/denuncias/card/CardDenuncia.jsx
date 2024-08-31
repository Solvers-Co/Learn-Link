import styles from './CardDenuncia.module.css';
import MenuVertical from '../../../../mobile/utils/assets/MenuVertical.png';
import { useState } from 'react';
import IconeDenuncia from '../../../utils/assets/icone_denuncia_vermelho.png'

const CardDenuncia = () => {
    const [showPopup, setShowPopup] = useState(false);
    const togglePopup = () => {
        setShowPopup(!showPopup);
    };
    // const avatar = useMemo(() => generateInitials(nome), [nome]);

    // function generateInitials(name) {
    //     const nameParts = name.trim().split(' ');
    //     const firstInitial = nameParts[0].charAt(0).toUpperCase();
    //     const lastInitial = nameParts[nameParts.length - 1].charAt(0).toUpperCase();

    //     const pastelColors = [
    //         '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
    //         '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
    //         '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
    //         '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    //     ];

    //     const randomIndex = Math.floor(Math.random() * pastelColors.length);
    //     const backgroundColor = pastelColors[randomIndex];

    //     const avatar = {
    //         borderRadius: '50%',
    //         border: '1px solid rgba(0, 0, 0, .3)',
    //         width: '35px',
    //         height: '35px',
    //         marginRight: '12px',
    //         display: 'flex',
    //         justifyContent: 'center',
    //         alignItems: 'center',
    //         fontFamily: '"NunitoSansExtraBold", sans-serif',
    //         backgroundColor
    //     };

    //     return <div style={avatar}>{firstInitial + lastInitial}</div>
    // }

    return (
        <div className={styles.card}>
            <div className={styles.header}>
                <div className={styles.menuVertical} onClick={togglePopup}>
                    <img src={MenuVertical} alt="Menu" />
                </div>
            </div>
            <div className={styles.userInfo}>
                {/* {avatar} */}
                {/* temporário */}
                <div className={styles.avatar}></div>
                <span className={styles.nome}>
                    Nome{/* {nome} */}
                </span>
            </div>
            <div className={styles.conteudo}>
            aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaa aaaaaaaaaaa aaaaaaaaaa aaaaaaaaaaa aaaaaaaaaaa aaaaaaaaaaa aaaaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaa
                {/* {mensagem} */}
            </div>
            <div className={styles.denuncia}>
                <img src={IconeDenuncia} className={styles.iconeDenuncia} alt='Ícone de denúncia' />
                <div className={styles.denuncias}>
                    <span className={styles.quantidadeDenuncias}>X</span>
                    <p>denúncias</p>
                </div>
            </div>
        </div>
    )
}

export default CardDenuncia;