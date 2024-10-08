import Beneficios from "../../components/paginaInicial/beneficios/Beneficios";
import Home from "../../components/paginaInicial/home/Home";
import Solucao from "../../components/paginaInicial/solucao/Solucao";
import styles from './PaginaInicial.module.css'
import Header from '../../components/header/Header'
import Footer from "../../components/footer/Footer";

const PaginaInicial = () => {
    return (
        <div className={styles.paginaInicial}>
            <Header />
            <div id="home">
                <Home />
            </div>
            <div id="solucao">
                <Solucao />
            </div>
            <div id="beneficios">
                <Beneficios />
            </div>
            <div id="footer">
                <Footer />
            </div>
        </div>
    )
}

export default PaginaInicial;