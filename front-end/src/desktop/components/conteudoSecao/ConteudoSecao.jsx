import styles from './ConteudoSecao.module.css';

const ConteudoSecao = ({ titulo, paragrafo, corTitulo, corParagrafo, corLinha, largura }) => {
    return (
        <div className={styles.conteudo} style={{ width: largura }}>
            <h2 className={styles.titulo} style={{ color: corTitulo, '--cor-linha': corLinha }}>
                {titulo}
            </h2>
            <p className={styles.paragrafo} style={{ color: corParagrafo }}>
                {paragrafo}
            </p>
        </div>
    );
};

export default ConteudoSecao;
