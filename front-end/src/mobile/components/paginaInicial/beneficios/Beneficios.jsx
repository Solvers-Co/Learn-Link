import ConteudoSecao from '../../../../desktop/components/conteudoSecao/ConteudoSecao';
import CardSolucao from '../../cards/cardSolucao/CardSolucao';
import styles from './Beneficios.module.css'
import Imagem from '../../../../desktop/utils/assets/piramide-aprendizagem.png'

const Beneficios = () => {
    return (
        <div className={styles.beneficios}>
            <div className={styles.conteudo}>
                <ConteudoSecao
                    tamanhoFonteTitulo="20px"
                    tamanhoFonteParagrafo="16px"
                    largura="80vw"
                    altura="32vh"
                    corTitulo="#FFFFFF"
                    corLinha="#FFFFFF"
                    titulo="O benefício de ajudar"
                    corParagrafo="#FFFFFF"
                    paragrafo="Além de esclarecer suas dúvidas com outros usuários do Learn Link, você também pode compartilhar seu conhecimento, ajudando os outros e colaborando para o seu aprendizado!"
                />
            </div>
            <div className={styles.cards}>
                <CardSolucao
                    largura="65vw"
                    altura="20vh" 
                    titulo="Reforço do conhecimento"
                    paragrafo="Ao ensinar, você revisa e aprofunda o seu próprio conhecimento sobre o assunto, solidificando e ampliando o seu entendimento."
                />
                <CardSolucao 
                    largura="65vw"
                    altura="20vh" 
                    titulo="Conhece a pirâmide de aprendizagem?"
                    paragrafo="Você pode aprender mais ainda quando ensina outras pessoas."
                    showButton={true}
                    imagem={Imagem}
                />
                <CardSolucao 
                    largura="65vw"
                    altura="20vh" 
                    titulo="Identificação das lacunas"
                    paragrafo="Ensinar ajuda a identificar áreas onde seu conhecimento pode estar incompleto ou onde você precisa de uma compreensão mais profunda."
                />
            </div>

        </div>
    )
}

export default Beneficios;