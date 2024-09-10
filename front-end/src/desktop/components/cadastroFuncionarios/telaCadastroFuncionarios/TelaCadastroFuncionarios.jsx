import React, { useState } from "react";
import api from "../../../../api";
import styles from './TelaCadastroFuncionarios.module.css'
import Titulo from '../../dashboard/tituloDashboard/Titulo';
import Input from '../input/Input';
import Warning from '../../../utils/assets/icone_warning.png';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import emailjs from '@emailjs/browser';

const TelaCadastroFuncionarios = () => {
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [cpf, setCpf] = useState("");

    const handleInputChange = (event, setStateFunction) => { 
        setStateFunction(event.target.value); 
    }    

    const handleSave = () => {

        const senha = gerarCodigoAleatorio();
        const tipoUsuario = "ADMIN";

        const usuarioNovo = { 
            nome,
            cpf,
            email,
            senha
        };

        const errorMessages = [];

        if (!nome) {
            errorMessages.push("Nome não preenchido");
        } else if (nome.length < 3) {
            errorMessages.push("Nome deve ter pelo menos 3 caracteres");
        } else if (nome.length > 45) {
            errorMessages.push("Nome deve ter no máximo 45 caracteres");
        } else if (/[^a-zA-Z\s]/.test(nome)) {
            errorMessages.push("Nome não pode conter caracteres especiais");
        } else if (nome.trim() !== nome) {
            errorMessages.push("Nome não deve conter espaços em branco no final");
        }

        if (!email) {
            errorMessages.push("Email não preenchido");
        } else if (!email.includes("@")) {
            errorMessages.push("Email deve conter @");
        } else if (!email.includes(".") || email.indexOf("@") > email.lastIndexOf(".")) {
            errorMessages.push("Email deve conter um domínio válido");
        } else if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
            errorMessages.push("Email inválido");
        } else if (email.trim() !== email) {
            errorMessages.push("Email não deve conter espaços em branco no final");
        }

        // Verificações do CPF
        if (!cpf) {
            errorMessages.push("CPF não preenchido");
        } else if (cpf.length < 11) {
            errorMessages.push("CPF deve ter 11 dígitos");
        } else if (cpf.length > 11) {
            errorMessages.push("CPF deve ter 11 dígitos");
        } else if (/[^0-9]/.test(cpf)) {
            errorMessages.push("CPF deve conter apenas números");
        } else if (cpf.trim() !== cpf) {
            errorMessages.push("CPF não deve conter espaços em branco no final");
        }

        if (errorMessages.length > 0) {
            errorMessages.forEach(msg => toast.error(msg));
        } else {
            console.log(usuarioNovo)
            api.post(`/usuarios/adm`, usuarioNovo).then(() => {
    
                    emailjs.send("service_juy8w7g", "template_lr7u1k4", {
                        to_name: nome,
                        message: "Você foi cadastrado(a) no sistema LearLink. A sua senha de acesso é: " + senha + "\nRecomendamos que altere esta senha no primeiro acesso. Obrigado!",
                        to_email: email,
                    }, "tZxktBF31MEVsj2aL")
                        .then((emailResponse) => {
                            console.log("Email enviado:", emailResponse.status, emailResponse.text);
                            toast.success("Email enviado!");
                        })
                        .catch((emailError) => {
                            console.log("Erro ao enviar o email:", emailError.text);
                            toast.error("Erro ao enviar o e-mail. Tente novamente.");
                        });
                toast.success("Usuário cadastrado!");
            }).catch(() => {
                toast.error("Ocorreu um erro ao salvar os dados, por favor, tente novamente.");
            });
        }
    };

    const nomeUsuario = sessionStorage.getItem('nome')

    function gerarCodigoAleatorio() {
        const caracteres = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@_#$%&*';
        let codigo = '';
        for (let i = 0; i < 9; i++) {
            const randomIndex = Math.floor(Math.random() * caracteres.length);
            codigo += caracteres[randomIndex];
        }
        return codigo;
    }

    return (
        <div className={styles.telaCadastroFuncionarios}>
            <div className={styles.cabecalho}>
                <Titulo>Cadastro de Funcionários</Titulo>
            </div>

            <div className={styles.container}>
                <div className={styles.left}>
                    <form className={styles.form}>
                        <Input label="Nome" placeholder="Nome" value={nome} onChange={e => handleInputChange(e, setNome)}/>
                        <Input label="E-mail" placeholder="E-mail" value={email} onChange={e => handleInputChange(e, setEmail)}/>
                        <Input label="CPF" placeholder="CPF" value={cpf} onChange={e => handleInputChange(e, setCpf)}/>
                    </form>
                    <button className={styles.btn} onClick={handleSave}>Cadastrar</button>
                    <div className={styles.warning}>
                        <img src={Warning}/>
                        <div className={styles.text}>A senha será gerada automaticamente e enviada ao e-mail informado, o usuário poderá alterar a senha posteriormente.</div>
                    </div>
                </div>
                <div className={styles.right}>
                    <div className={styles.card}>
                        <span className={styles.saudacao}>Olá, {nomeUsuario}!</span>
                        <p>
                            Nessa página você poderá cadastrar outros funcionários para que possam ter acesso à <span>dashboard</span> e às <span>páginas de controle de acesso e denúncias</span></p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default TelaCadastroFuncionarios;