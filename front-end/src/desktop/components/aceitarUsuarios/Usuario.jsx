import React from 'react';
import styles from './Usuario.module.css';
import api from "../../../../src/api";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Usuario = ({ usuario }) => {

    var statusAtual = ""

    if (usuario.tipoStatus === "APROVADO") {
        statusAtual = styles.statusAprovado;
        var statuSituacaoBtn = styles.ativarDslgBtn;
        var statusBtn = styles.desativarBtn;
        var descricao = "Desativar";
    } else if (usuario.tipoStatus === "PENDENTE") {
        statusAtual = styles.statusPendente;
        var statusBtn = styles.negarBtn;
        var descricao = "Negar";
    }
    else {
        statusAtual = styles.statusNegado;
        var statuSituacaoBtn = styles.ativarBtn;
        var statusBtn = styles.negarDslgBtn;
        var descricao = "Negar";
    }

    function definirStatus() {
        if (usuario.tipoStatus === "APROVADO") {
            alterarStatus(usuario.id, 1);
        } else {
            alterarStatus(usuario.id, 3);
        }
    }

    const alterarStatus = (id, novoStatus) => {
        api.patch(`/usuarios/${id}/status/${novoStatus}`)
            .then(response => {
                toast.success('Status alterado com sucesso!');
                console.log('Resposta da API:', response.data);
                // Aqui você pode adicionar lógica para atualizar a interface, se necessário
            })
            .catch(error => {
                toast.error('Erro ao alterar status!');
                console.error('Erro na requisição:', error);
            });
    };

    return (
        <div className={styles.userRow}>
            <div className={styles['divNome']}><span className={styles.userCpf}>{usuario.nome}</span></div>
            <div className={styles['divCpf']}><span className={styles.userName}>{usuario.cpf}</span></div>
            <div className={styles['divEmail']}><span className={styles.userEmail}>{usuario.email}</span></div>
            <div className={styles['divStatus']}><div className={styles.userStatus}>
                <span className={statusAtual}></span>
                <span className={styles.userStatus}>{usuario.tipoStatus}</span>
            </div>
            </div>
            <button className={`${styles.btn} ${statuSituacaoBtn}`}
                onClick={() => alterarStatus(usuario.id, 2)}>Ativar</button>

            <button className={`${styles.btn} ${statusBtn}`}
                onClick={() => definirStatus()}>{descricao}</button>
        </div>
    );
};

export default Usuario;
