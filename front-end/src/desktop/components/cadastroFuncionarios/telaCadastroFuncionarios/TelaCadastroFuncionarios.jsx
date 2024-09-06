import styles from './TelaCadastroFuncionarios.module.css'
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import Input from '../input/Input';
import Warning from '../../../utils/assets/icone_warning.png'

const TelaCadastroFuncionarios = () => {
    const nomeUsuario = sessionStorage.getItem('nome')
    return (
        <div className={styles.telaCadastroFuncionarios}>
            <div className={styles.cabecalho}>
                <Titulo>Cadastro de Funcionários</Titulo>
            </div>

            <div className={styles.container}>
                <div className={styles.left}>
                    <form className={styles.form}>
                        <Input label="Nome" placeholder="Nome" />
                        <Input label="E-mail" placeholder="E-mail" />
                        <Input label="CPF" placeholder="CPF" />
                    </form>
                    <button className={styles.btn}>Cadastrar</button>
                    <div className={styles.warning}>
                        <img src={Warning}/>
                        <div className={styles.text}>A senha será gerada automaticamente e enviada ao e-mail informado, o usuário poderá alterar a senha posteriormente.</div>
                    </div>
                </div>
                <div className={styles.right}>
                    <div className={styles.card}>
                        <span className={styles.saudacao}>Olá, {nomeUsuario}!</span>
                        <p>
                            Nessa página você poderá cadastrar outros funcionários para que possam ter acesso à <span>dashboard</span> e às <span>páginas de controle de acesso e denúncias</span></p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default TelaCadastroFuncionarios;