import React from 'react';
import styles from './Usuario.module.css';

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
            <button className={`${styles.btn} ${statuSituacaoBtn}`}>Ativar</button>
            <button className={`${styles.btn} ${statusBtn}`}>{descricao}</button>
        </div>
    );
};

export default Usuario;
