import styles from './CardBeneficio.module.css'

const CardBeneficio = ({ titulo, paragrafo, showButton, onClick, largura, altura }) => {
    return (
      <div className={styles.card} style={{ width: largura, height: altura }}>
        <h3 className={styles.titulo}>{titulo}</h3>
        <p className={styles.paragrafo}>{paragrafo}</p>
        {showButton && <button onClick={onClick} className={styles.botao}>Veja mais</button>}
      </div>
    );
  };

export default CardBeneficio;