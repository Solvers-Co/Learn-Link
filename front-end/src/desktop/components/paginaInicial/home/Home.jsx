import Botao from "../../../../mobile/components/header/Header"
// import Header from "../../components/header/Header";
import styles from './Home.module.css'
import stylesBotao from '../../../../mobile/components/botoes/botaoLoginCadastro/Botao.module.css'
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate(); 

    const handleCadastro = () => {
        navigate('/cadastroDesktop'); 
    };

    return (
        <div className={styles.home}>
            {/* <Header /> */}
            <div className={styles.conteudo}>
                <h1 className={styles.titulo}>Learn Link</h1>
                <p className={styles.subTitulo}>Criada com o intuito de promover a colaboração entre alunos, Learn Link é uma plataforma completa e fácil de se usar.</p>
                <Botao 
                    className={stylesBotao.botaoHomeInteresse}
                    textoBotao="Tenho interesse"
                    funcao={handleCadastro}
                />
            </div>
        </div>
    )
}

export default Home;