import React from 'react';
import styles from './Usuario.module.css';
import api from "../../../../src/api";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import emailjs from '@emailjs/browser';
import { memo } from 'react';

// Função utilitária para formatar CPF
const formatarCPF = (cpf) => {
    if (cpf.length === 11) {
        return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    }
    return cpf; // Retorna o CPF sem formatação se não tiver 11 dígitos
};

const Usuario = ({ usuario, fetchUsuarios, paginaAtual, statusAtual }) => {

    var statusAtualTipo = ""

    if (usuario.tipoStatus === "APROVADO") {
        statusAtualTipo = styles.statusAprovado;
        var statuSituacaoBtn = styles.ativarDslgBtn;
        var statusBtn = styles.desativarBtn;
        var descricao = "Desativar";
    } else if (usuario.tipoStatus === "PENDENTE") {
        statusAtualTipo = styles.statusPendente;
        var statusBtn = styles.negarBtn;
        var descricao = "Negar";
    }
    else {
        statusAtualTipo = styles.statusNegado;
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

                //Descomentar para enviar email ao alterar status
                // var mensagem = "";

                // if (response.data.tipoStatus.status === "APROVADO") {
                //     mensagem = "Parabéns! Seu acesso foi aprovado. Acesse o link abaixo para iniciar o seu acesso: http://localhost:3000/login";
                // } else if (response.data.tipoStatus.status === "PENDENTE") {
                //     mensagem = "Seu acesso foi redefinido como pendente. Entre em contato com o instituto se achar que isto é um erro.";
                // } else {
                //     mensagem = "Seu acesso foi negado. Entre em contato com o instituto se achar que isto é um erro.";
                // }

                // emailjs.send("service_juy8w7g", "template_lr7u1k4", {
                //     to_name: response.data.nome,
                //     message: mensagem,
                //     to_email: response.data.email,
                // }, "tZxktBF31MEVsj2aL")
                //     .then((emailResponse) => {
                //         console.log("Email enviado:", emailResponse.status, emailResponse.text);
                //         toast.success("Email enviado!");
                //     })
                //     .catch((emailError) => {
                //         console.log("Erro ao enviar o email:", emailError.text);
                //         toast.error("Erro ao enviar o e-mail. Tente novamente.");
                //     });

                fetchUsuarios(statusAtual, paginaAtual);
            })
            .catch(error => {
                toast.error('Erro ao alterar status!');
                console.error('Erro na requisição:', error);
            });
    };


    let nomeFormatado = 'Usuário Desconhecido'; // Valor padrão caso o nome não seja encontrado

    const nomes = usuario.nome.trim().split(' '); // Remove espaços em branco e divide a string em palavras
    const primeiroNome = nomes[0];
    const ultimoNome = nomes[nomes.length - 1];
    if (nomes.length === 1) {
        nomeFormatado = primeiroNome;
    } else {
        nomeFormatado = `${primeiroNome} ${ultimoNome}`;
    }

    if (nomeFormatado.length > 15) {
        nomeFormatado = `${nomeFormatado.slice(0, 13)}...`; // 13 para deixar espaço para os "..."
    }

    let emailFormatado = usuario.email; // Definido como o email original do usuário

    if (usuario.email.length > 23) {
        emailFormatado = `${usuario.email.slice(0, 20)}...`; // Corrigido para usar usuario.email
    }


    return (
        <div className={styles.userRow}>
            <div className={styles['divNome']}><span title={usuario.nome} className={styles.userName}>{nomeFormatado}</span></div>
            <div className={styles['divCpf']}><span className={styles.userCpf}>{formatarCPF(usuario.cpf)}</span></div>
            <div className={styles['divEmail']}><span title={usuario.email} className={styles.userEmail}>{emailFormatado}</span></div>
            <div className={styles['divStatus']}><div className={styles.userStatus}>
                <span className={statusAtualTipo}></span>
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
