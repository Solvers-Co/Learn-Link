import styles from './OpcaoNavegacao.module.css'

const OpcaoNavegacao = ({ icone, nomeSecao, onClick }) => {
    return (
        <div onClick={onClick} className={styles.opcaoNavegacao}>
            <img src={icone} alt={`${nomeSecao} ícone`} />
            <p>{nomeSecao}</p>
        </div>
    )
}

export default OpcaoNavegacao;