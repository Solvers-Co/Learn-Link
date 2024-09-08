import Titulo from '../../dashboard/tituloDashboard/Titulo';
import CardDenuncia from '../card/CardDenuncia';
import styles from './TelaDenuncias.module.css';

const users = [
    { nome: 'Pedro José', conteudo: 'Essa merd# desse profº passando essa atividade', qtdDenuncias: 7, imageUrl: 'url-da-imagem' },
    { nome: 'Antônio João', conteudo: 'Odiei a nova profª, vocês gostaram dela?', qtdDenuncias: 5, imageUrl: 'url-da-imagem' },
    { nome: 'Virgínia Souza', conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', qtdDenuncias: 5, imageUrl: 'url-da-imagem' },
    { nome: 'Felipe Albuquerque', conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', qtdDenuncias: 12, imageUrl: 'url-da-imagem' },
    { nome: 'Paula Rosa Santos', conteudo: 'Lorem ipsum', qtdDenuncias: 1, imageUrl: 'url-da-imagem' },
    { nome: 'Rosa Magalhães', conteudo: 'Lorem ipsum dolor sit amet', qtdDenuncias: 20, imageUrl: 'url-da-imagem' },
    { nome: 'Christian Ferreira', conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', qtdDenuncias: 11, imageUrl: 'url-da-imagem' },
    { nome: 'Marcela de Castro', conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', qtdDenuncias: 16, imageUrl: 'url-da-imagem' },
    { nome: 'Filipa Rodrigues', conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', qtdDenuncias: 5, imageUrl: 'url-da-imagem' },
];

const TelaDenuncias = () => {
    return (
        <div className={styles.telaDenuncias}>
            <div className={styles.cabecalho}>
                <Titulo>Denúncias</Titulo>
            </div>
            <div className={styles.cardsDenuncias}>
            {users.map((user, index) => (
                    <CardDenuncia key={index} user={user} />
            ))}
            </div>
        </div>
    )
}

export default TelaDenuncias;