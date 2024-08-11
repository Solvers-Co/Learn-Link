import styles from './Solucao.module.css'
import ConteudoSecao from '../../../components/conteudoSecao/ConteudoSecao';
import CardSolucao from '../../../components/cards/cardSolucao/CardSolucao';
import Solucao1 from '../../../utils/assets/card-solucao-1.png'
import Solucao2 from '../../../utils/assets/card-solucao-2.png'

const Solucao = () => {
    return (
        <div className={styles.solucao}>
            <div className={styles.conteudo}>
                <ConteudoSecao
                    tamanhoFonteTitulo="32px"
                    tamanhoFonteParagrafo="18px"
                    largura="50vw"
                    altura="55vh"
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
                    imagem={Solucao1}
                />
                <CardSolucao 
                    titulo="Contribuição"
                    paragrafo="Contribuir com o aprendizado através do Learn Link não só fortalece seu conhecimento, mas também permite compartilhar descobertas e ajudar outros alunos a enfrentar desafios acadêmicos."
                    imagem={Solucao2}
                />
            </div>
        </div>
    )
}

export default Solucao;