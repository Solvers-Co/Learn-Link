import styles from './ConteudoSecao.module.css';

const ConteudoSecao = ({ titulo, paragrafo, corTitulo, corParagrafo, corLinha, largura, altura, tamanhoFonteTitulo, tamanhoFonteParagrafo }) => {
    return (
        <div className={styles.conteudo} style={{ width: largura, height: altura }}>
            <h2 className={styles.titulo} style={{ color: corTitulo, '--cor-linha': corLinha, fontSize: tamanhoFonteTitulo }}>
                {titulo}
            </h2>
            <p className={styles.paragrafo} style={{ color: corParagrafo, fontSize: tamanhoFonteParagrafo }}>
                {paragrafo}
            </p>
        </div>
    );
};

export default ConteudoSecao;
