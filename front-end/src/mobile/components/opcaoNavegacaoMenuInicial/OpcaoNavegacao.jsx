import styles from './OpcaoNavegacao.module.css'

const OpcaoNavegacao = ({ icone, nomeSecao, onClick, fonte }) => {
    return (
        <div onClick={onClick} className={styles.opcaoNavegacao}>
            <img src={icone} alt={`${nomeSecao} ícone`} />
            <p style={{ fontFamily:fonte }}>{nomeSecao}</p>
        </div>
    )
}

export default OpcaoNavegacao;