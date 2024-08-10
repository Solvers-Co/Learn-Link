import Beneficios from "../beneficios/Beneficios";
import Home from "../home/Home";
import Solucao from "../solucao/Solucao";
import styles from './PaginaInicial.module.css'

const PaginaInicial = () => {
    return (
        <div className={styles.paginaInicial}>
            <Home />
            <Solucao />
            <Beneficios />
        </div>
    )
}

export default PaginaInicial;