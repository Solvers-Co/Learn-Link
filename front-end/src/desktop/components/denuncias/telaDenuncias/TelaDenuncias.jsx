import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';
import Publicacao from '../../../../mobile/components/publicacao/Publicacao';

const TelaDenuncias = () => {
    return (
        <div className={styles.telaDenuncias}>
            <div className={styles.cabecalho}>
                <Titulo>Denúncias</Titulo>
            </div>
            <div className={styles.cardsDenuncias}>
                <CardDenuncia />
                <CardDenuncia />
                <CardDenuncia />
                <CardDenuncia />
                <CardDenuncia />
                <CardDenuncia />
                <CardDenuncia />
            </div>
        </div>
    )
}

export default TelaDenuncias;