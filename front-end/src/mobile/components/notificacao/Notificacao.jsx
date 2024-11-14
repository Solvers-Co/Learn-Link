import styles from './Notificacao.module.css';
import { generateInitials } from '../../utils/functions/GerarIniciais';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import api from "../../../api"

const Notificacao = ({ corDeFundo, id, corpo, nomeUsuarioGerador, vista, idUsuarioGerador, openModal, carregarConteudoNotificacoes, idPublicacao, idComentario }) => {
    const [idNotificacao, setId] = useState(id);
    const [conteudo, setConteudo] = useState(corpo);
    const [nomeUsuario, setNomeUsuarioGerador] = useState(nomeUsuarioGerador);
    const [visualizada, setVisusalizada] = useState(vista)
    const [srcImagemPerfil, setSrcImagemPerfil] = useState('')
    const avatar = generateInitials(nomeUsuario);
    const backgroundColor = visualizada === 1 ? corDeFundo : 'white';

    const fetchVisualizarNotificacao = () => {
        api.patch(`/notificacoes/visualizar-notificacao/${id}`).then(response => {
            setVisusalizada(response.data.vista)
        }).catch(() => {
            toast.error("Erro ao atualizar notificacão")
        })
    }

    useEffect(() => {
        async function buscarImagemPerfil() {
            try {
                const response = await api.get(`usuarios/buscar-imagem-perfil/${idUsuarioGerador}`);
                setSrcImagemPerfil(response.data)
                // setSrcImagemPerfil("https://s3-learnlink.s3.us-east-1.amazonaws.com/WIN_20240909_09_30_09_Pro.jpg")
            } catch (error) {
                toast.error("Erro ao buscar imagem de perfil")
            }
        }
        buscarImagemPerfil();
    }, [])

    return (
        <div className={styles.notificacao} onClick={() => { fetchVisualizarNotificacao(); openModal(); carregarConteudoNotificacoes(idPublicacao, idComentario) }}>
            <div className={styles.notificacaoItem} style={{ backgroundColor }}>
                <div className={styles.infos}>
                    <div className={styles.userInfo}>
                        {srcImagemPerfil ? (
                            <img className={styles.imagemPerfil} src={srcImagemPerfil} alt="Imagem de Perfil" />
                        ) : (
                            <span className={styles.avatar}>{avatar}</span>
                        )}
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