import styles from './CardPerfil.module.css'

const CardPerfil = ({ conteudo, classificacao }) => {
    return (
        <div className={styles.card}>
            <h2 className={styles.conteudo}>{conteudo}</h2>
            <p className={styles.classificacao}>{classificacao}</p>
        </div>
    )
}

export default CardPerfil