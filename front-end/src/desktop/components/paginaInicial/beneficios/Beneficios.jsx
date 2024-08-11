import CardBeneficio from '../../cards/cardBeneficio/CardBeneficio'
import ConteudoSecao from '../../../components/conteudoSecao/ConteudoSecao'
import styles from './Beneficios.module.css'
import Imagem from '../../../utils/assets/piramide-aprendizagem.png'

const Beneficios = () => {
    return (
        <div className={styles.beneficios}>
            <div className={styles.conteudo}>
                <ConteudoSecao
                    tamanhoFonteTitulo="32px"
                    tamanhoFonteParagrafo="18px"
                    largura="55vw"
                    altura="55vh"
                    corTitulo="#FFFFFF"
                    corLinha="#FFFFFF"
                    titulo="O benefício de ajudar"
                    corParagrafo="#FFFFFF"
                    paragrafo="Além de esclarecer suas dúvidas com outros usuários do Learn Link, você também pode compartilhar seu conhecimento, ajudando os outros e colaborando para o seu aprendizado!"
                />
            </div>
            <div className={styles.cards}>
                <CardBeneficio
                    largura="19vw"
                    altura="31vh" 
                    titulo="Reforço do conhecimento"
                    paragrafo="Ao ensinar, você revisa e aprofunda o seu próprio conhecimento sobre o assunto, solidificando e ampliando o seu entendimento."
                />
                <CardBeneficio 
                    largura="23vw"
                    altura="40vh" 
                    titulo="Conhece a pirâmide de aprendizagem?"
                    paragrafo="Você pode aprender mais ainda quando ensina outras pessoas."
                    showButton={true}
                    imagem={Imagem}
                />
                <CardBeneficio 
                    largura="19vw"
                    altura="31vh" 
                    titulo="Identificação das lacunas"
                    paragrafo="Ensinar ajuda a identificar áreas onde seu conhecimento pode estar incompleto ou onde você precisa de uma compreensão mais profunda."
                />
            </div>

        </div>
    )
}

export default Beneficios;