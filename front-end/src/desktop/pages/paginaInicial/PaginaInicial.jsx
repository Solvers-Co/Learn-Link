import Beneficios from "../../components/paginaInicial/beneficios/Beneficios";
import Home from "../../components/paginaInicial/home/Home";
import Solucao from "../../components/paginaInicial/solucao/Solucao";
import styles from './PaginaInicial.module.css'
import Header from '../../components/header/Header'

const PaginaInicial = () => {
    return (
        <div className={styles.paginaInicial}>
            <Header />
            <Home />
            <Solucao />
            <Beneficios />
        </div>
    )
}

export default PaginaInicial;