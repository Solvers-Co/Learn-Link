import MenuLateral from '../../components/dashboard/menuLateral/MenuLateral';
import Ranking from '../../components/dashboard/ranking/Ranking';
import styles from './Denuncias.module.css'

const Denuncias = ( ) => {
    return (
        <div className={styles.telaDenuncias}>
            <MenuLateral />
            <Ranking />
        </div>
    )
}

export default Denuncias;