import CardBeneficio from '../../components/cards/cardBeneficio/CardBeneficio';
import ConteudoSecao from '../../components/conteudoSecao/ConteudoSecao';
import styles from './Beneficios.module.css'

const Beneficios = () => {
    return (
        <div className={styles.beneficios}>
            <div className={styles.conteudo}>
                <ConteudoSecao
                    largura="55vw"
                    corTitulo="#FFFFFF"
                    corLinha="#FFFFFF"
                    titulo="O benefício de ajudar"
                    corParagrafo="#FFFFFF"
                    paragrafo="Além de esclarecer suas dúvidas com outros usuários do Learn Link, você também pode compartilhar seu conhecimento, ajudando os outros e colaborando para o seu aprendizado!"
                />
            </div>
            <div className={styles.cards}>
                <CardBeneficio 
                    titulo="Reforço do conhecimento"
                    paragrafo="Ao ensinar, você revisa e aprofunda o seu próprio conhecimento sobre o assunto, solidificando e ampliando o seu entendimento."
                />
                <CardBeneficio 
                    titulo="Conhece a pirâmide de aprendizagem?"
                    paragrafo="Você pode aprender mais ainda quando ensina outras pessoas."
                />
                <CardBeneficio 
                    titulo="Identificação das lacunas"
                    paragrafo="Ensinar ajuda a identificar áreas onde seu conhecimento pode estar incompleto ou onde você precisa de uma compreensão mais profunda."
                />
            </div>

        </div>
    )
}

export default Beneficios;