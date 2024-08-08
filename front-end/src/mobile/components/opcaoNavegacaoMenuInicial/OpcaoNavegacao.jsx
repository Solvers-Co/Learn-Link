import styles from './OpcaoNavegacao.module.css'

const OpcaoNavegacao = (props) => {
    return (
                <div className={styles.opcaoNavegacao}>
                    <img src={props.icone} />
                    <p>{props.nomeSecao}</p>
                </div>
    )
}

export default OpcaoNavegacao;