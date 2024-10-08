import styles from './Solucao.module.css'
import ConteudoSecao from '../../../../desktop/components/conteudoSecao/ConteudoSecao';
import CardSolucao from '../../cards/cardSolucao/CardSolucao';


const Solucao = () => {
    return (
        <div className={styles.solucao}>
            <div className={styles.conteudo}>
                <ConteudoSecao
                    tamanhoFonteTitulo="20px"
                    tamanhoFonteParagrafo="16px"
                    largura="78vw"
                    altura="50vh"
                    corTitulo="#A80301"
                    corLinha="#A80301"
                    titulo="Da incerteza à compreensão"
                    corParagrafo="black"
                    paragrafo="Use o Learn Link para sanar as suas dúvidas e poder ajudar outros alunos com o conhecimento que você possui."
                />
            </div>
            <div className={styles.cards}>
                <CardSolucao 
                    titulo="Explore matérias variadas"
                    paragrafo="Explore o Learn Link para aprofundar seu conhecimento em diversos assuntos e sanar dúvidas."
                />
                <CardSolucao 
                    titulo="Contribuição"
                    paragrafo="Contribuir com o aprendizado fortalece seu conhecimento e permite ajudar outros alunos."
                />
            </div>
        </div>
    )
}

export default Solucao;