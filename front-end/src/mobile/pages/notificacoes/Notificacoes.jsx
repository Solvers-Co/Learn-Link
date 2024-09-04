import styles from './Notificacoes.module.css';
import Header from '../../components/headerAplicacao/Header'
import Notificacao from '../../components/notificacao/Notificacao';


const Notificacoes = () => {
    return (
        <>
        <Header />
        <div className={styles.notificacoes}>
            <h1 className={styles.titulo}>Notificações</h1>
            <div className={styles.notificacao}>
                <Notificacao corDeFundo={'rgb(0, 0, 0, 0.05)'}/>
            </div>
        </div>
        </>
    )
}

export default Notificacoes;