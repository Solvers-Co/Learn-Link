import styles from './Notificacoes.module.css';
import Header from '../../components/headerAplicacao/Header'
import Notificacao from '../../components/notificacao/Notificacao';
import React, { useEffect ,useState} from 'react';
import api from "../../../api";
import { toast } from 'react-toastify';



const Notificacoes = () => {
    const [notificacoes, setNotificacoes] = useState([]);

    useEffect(() => {
        const fetchNotificacoes = () =>{
            api.get(`/notificacoes/${sessionStorage.getItem("userId")}`).then(response =>{
                setNotificacoes(response.data)
            }).catch(() =>{
                toast.error("Erro ao carregar as notificações")
            })
        };
        fetchNotificacoes();
    },[sessionStorage.getItem("userId")])

    const notificacoesParaExibir = notificacoes;
    return (
        <>
        <Header />
        <div className={styles.notificacoes}>
            <h1 className={styles.titulo}>Notificações</h1>
            <div className={styles.notificacao}>
                {notificacoesParaExibir.length > 0 ? (
                    notificacoesParaExibir.map((notificacao, index) => (
                        <Notificacao 
                            key={`${notificacao.id}-${index}`}
                            corDeFundo={'rgb(0, 0, 0, 0.05)'}
                            id={notificacao.id}
                            corpo={notificacao.corpo}
                            nomeUsuarioGerador={notificacao.nomeUsuarioGerador}
                            nomeUsuarioRecebedor={notificacao.nomeUsuarioRecebedor}
                            vista={notificacao.vista}
                        />
                    ))

                ): (
                    <p>Nenhuma notificação encontrada</p>
                )}
                
            </div>
        </div>
        </>
    )
}

export default Notificacoes;