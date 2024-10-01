import styles from './Notificacao.module.css';
import { generateInitials } from '../../utils/functions/GerarIniciais';
import { useState } from 'react';
import { toast } from 'react-toastify';
import api from "../../../api"

const Notificacao = ({ corDeFundo, id, corpo, nomeUsuarioGerador, vista }) => {
    const [idNotificacao, setId] = useState(id);
    const [conteudo, setConteudo] = useState(corpo);
    const [nomeUsuario, setNomeUsuarioGerador] = useState(nomeUsuarioGerador);
    const [visualizada, setVisusalizada] = useState(vista)
    const avatar = generateInitials(nomeUsuario);
    const backgroundColor = visualizada === 1 ? corDeFundo : 'white';

    const fetchVisualizarNotificacao = () =>{
        api.patch(`/notificacoes/visualizar-notificacao/${id}`).then(response =>{
            setVisusalizada(response.data.vista)
        }).catch(()=>{
            toast.error("Erro ao atualizar notificacão")
        })
    }


    return (
        <div className={styles.notificacao} onClick={fetchVisualizarNotificacao}>
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