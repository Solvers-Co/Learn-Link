import styles from './CardAtividade.module.css';

const CardAtividade = () => {
    const renderQuadrados = (quantidade) => (
        Array.from({ length: quantidade }, (_, index) => (
            <div key={index} className={styles.quadrado}></div>
        ))
    );

    return (
        <div>
            <div className={styles.header}>
                <span className={styles.titulo}>Atividade dos últimos 30 dias</span>
            </div>
            <div className={styles.card}>
                <div className={styles.primeiraLinha}>
                    {renderQuadrados(15)}
                </div>
                <div className={styles.segundaLinha}>
                    {renderQuadrados(15)}
                </div>
            </div>
        </div>
    );
};

export default CardAtividade;
