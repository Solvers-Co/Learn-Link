import styles from './CadastroFuncionarios.module.css';
import TelaCadastroFuncionarios from '../../components/cadastroFuncionarios/telaCadastroFuncionarios/TelaCadastroFuncionarios';
import MenuLateral from '../../components/dashboard/menuLateral/MenuLateral';
import Ranking from '../../components/dashboard/ranking/Ranking';

const CadastroFuncionarios = () => {
    return (
        <div className={styles.telaCadastroFuncionarios}>
            <MenuLateral />
            <TelaCadastroFuncionarios />
            <Ranking />
        </div>
    )
}

export default CadastroFuncionarios;