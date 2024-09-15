import styles from './Notificacao.module.css';
import { generateInitials } from '../../utils/functions/GerarIniciais';

const users = [
    { nome: 'Ana Luísa Moreira', acao: 'respondeu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'Ana Luísa Moreira', acao: 'curtiu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'José Filipe da Silva', acao: 'respondeu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'Ângela Arcanjo', acao: 'curtiu seu comentário', imageUrl: 'url-da-imagem' },
    { nome: 'Sophie Antunes', acao: 'respondeu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'Sophie Antunes', acao: 'curtiu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'Pedro Henrique', acao: 'respondeu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'Fred Fernandes', acao: 'curtiu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'João Vitor Pereira', acao: 'respondeu sua publicação', imageUrl: 'url-da-imagem' },
    { nome: 'Vitor Carvalho', acao: 'respondeu sua publicação', imageUrl: 'url-da-imagem' },
];

const Notificacao = ({ corDeFundo }) => {
    return (
        <div className={styles.notificacao}>
            {users.map((user, index) => {
                const avatar = generateInitials(user.nome);
                const backgroundColor = index === 0 ? corDeFundo : 'white';
                return (
                    <div key={index} className={styles.notificacaoItem} style={{ backgroundColor }}>
                        <div className={styles.infos}>
                            <div className={styles.avatar}>
                                {avatar}
                            </div>
                            <div className={styles.texto}>
                                <span className={styles.nome}>{user.nome}</span>
                                <span className={styles.acao}>{user.acao}</span>
                            </div>
                        </div>
                    </div>
                );
            })}
        </div>
    )
}

export default Notificacao;