import styles from './Notificacao.module.css';

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

function generateInitials(name) {
    const nameParts = name.trim().split(' ');
    const firstInitial = nameParts[0].charAt(0).toUpperCase();
    const lastInitial = nameParts[nameParts.length - 1].charAt(0).toUpperCase();

    const pastelColors = [
        '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
        '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
        '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    ];

    const randomIndex = Math.floor(Math.random() * pastelColors.length);
    const backgroundColor = pastelColors[randomIndex];

    const avatarStyle = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        width: '35px',
        height: '35px',
        marginRight: '12px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSansExtraBold", sans-serif',
        backgroundColor
    };

    return <div style={avatarStyle}>{firstInitial + lastInitial}</div>;
}

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