import ResultadoKpi from "../resultadoKpi/ResultadoKpi";
import TituloKpi from "../tituloKpi/TituloKpi";
import styles from './Kpi.module.css';

function Kpi(props) {
    return(
        <div className={styles.kpi}>
            <TituloKpi>{props.TituloKpi}</TituloKpi>
            <ResultadoKpi>{props.ResultadoKpi}</ResultadoKpi>
        </div>
    )
}

export default Kpi;