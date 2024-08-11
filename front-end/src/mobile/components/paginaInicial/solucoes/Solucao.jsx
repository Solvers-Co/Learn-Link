import styles from './Solucao.module.css'
import ConteudoSecao from '../../../../desktop/components/conteudoSecao/ConteudoSecao';
import CardSolucao from '../../cards/cardSolucao/CardSolucao';


const Solucao = () => {
    return (
        <div className={styles.solucao}>
            <div className={styles.conteudo}>
                <ConteudoSecao
                    tamanhoFonteTitulo="20px"
                    tamanhoFonteParagrafo="18px"
                    largura="80vw"
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
                    paragrafo="Utilize o Learn Link para explorar uma ampla gama de assuntos que podem ajudá-lo a superar suas dúvidas e aprofundar seu entendimento em diferentes áreas do conhecimento."
                />
                <CardSolucao 
                    titulo="Contribuição"
                    paragrafo="Contribuir com o aprendizado através do Learn Link não só fortalece seu conhecimento, mas também permite compartilhar descobertas e ajudar outros alunos a enfrentar desafios acadêmicos."
                />
            </div>
        </div>
    )
}

export default Solucao;