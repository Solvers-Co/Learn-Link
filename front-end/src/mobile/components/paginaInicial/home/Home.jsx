import Botao from "../../botoes/botaoLoginCadastro/Botao"
import styles from './Home.module.css'
import stylesBotao from '../../botoes/botaoLoginCadastro/Botao.module.css'
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";

const Home = () => {
    const navigate = useNavigate(); 
    const location = useLocation();

    useEffect(() => {
        if (location.state && location.state.sectionId) {
            const section = document.getElementById(location.state.sectionId);
            if (section) {
                section.scrollIntoView({ behavior: 'smooth' });
            }
        }
    }, [location]);

    const handleCadastro = () => {
        navigate('/cadastro'); 
    };

    return (
        <div className={styles.home}>
            <div className={styles.conteudo}>
                <h1 className={styles.titulo}>Learn Link</h1>
                <p className={styles.subTitulo}>Criada com o intuito de promover a colaboração entre alunos, Learn Link é uma plataforma completa e fácil de se usar.</p>
                <Botao 
                    className={stylesBotao.botaoHomeInteresse}
                    textoBotao="Tenho interesse"
                    funcao={handleCadastro}
                    largura="40vw"
                    altura="5vh"
                    tamanhoLetra="16px"
                />
            </div>
        </div>
    )
}

export default Home;