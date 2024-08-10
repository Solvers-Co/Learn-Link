import styles from './CardSolucao.module.css'

const CardSolucao = ({ titulo, paragrafo, imagem }) => {
    return (
        <div className={styles.card}>
            <div className={styles.conteudoCard}>
                <h2 className={styles.titulo}>
                    {titulo}
                </h2>
                <p className={styles.paragrafo}>
                    {paragrafo}
                </p>
            </div>
            <div>
                <img src={imagem}/>
            </div>
        </div>

    )
}

export default CardSolucao;