import MenuLateral from '../../components/dashboard/menuLateral/MenuLateral';
import Ranking from '../../components/dashboard/ranking/Ranking';
import TelaDenuncias from '../../components/denuncias/telaDenuncias/TelaDenuncias';
import styles from './Denuncias.module.css'

const Denuncias = ( ) => {
    return (
        <div className={styles.telaDenuncias}>
            <MenuLateral />
            <TelaDenuncias />
            <Ranking />
        </div>
    )
}

export default Denuncias;