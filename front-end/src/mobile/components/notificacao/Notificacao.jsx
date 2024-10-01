import styles from './Notificacao.module.css';
import { generateInitials } from '../../utils/functions/GerarIniciais';
import { useState } from 'react';

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

const Notificacao = ({ corDeFundo, id, corpo, nomeUsuarioGerador }) => {
    const [backgroundColor] = useState(corDeFundo)
    const [idNotificacao, setId] = useState(id);
    const [conteudo, setConteudo] = useState(corpo);
    const [nomeUsuario, setNomeUsuarioGerador] = useState(nomeUsuarioGerador);
    const avatar = generateInitials(nomeUsuario);
    return (
        <div className={styles.notificacao}>
                <div className={styles.notificacaoItem} style={{ backgroundColor }}>
                    <div className={styles.infos}>
                        <div className={styles.avatar}>
                            {avatar}
                        </div>
                        <div className={styles.texto}>
                            <span className={styles.nome}>{nomeUsuario}</span>
                            <span className={styles.acao}>{conteudo}</span>
                        </div>
                    </div>
                </div>
        </div>
    )
}

export default Notificacao;