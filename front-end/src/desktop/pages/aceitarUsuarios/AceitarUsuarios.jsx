import ListaUsuarios from '../../components/aceitarUsuarios/listaUsuarios/ListaUsuarios.jsx';
import styles from '../aceitarUsuarios/AceitarUsuarios.module.css';
import MenuLateral from '../../components/dashboard/menuLateral/MenuLateral';
import Ranking from '../../components/dashboard/ranking/Ranking';


function AceitarUsuarios() {
    return (
        <div className={styles["telaAceitarUsuarios"]}>
            <MenuLateral />
            <ListaUsuarios />
            <Ranking />
        </div>
    );
}

export default AceitarUsuarios;