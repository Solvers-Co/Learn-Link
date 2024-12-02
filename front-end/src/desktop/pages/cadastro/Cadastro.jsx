import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import styles from "./Cadastro.module.css";
import Card from "../../components/cards/cardFormularios/CardFormulario";
import Input from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/Botao";
import emailjs from '@emailjs/browser';
import InputMask from "react-input-mask";

const Cadastro = () => {
    const navigate = useNavigate();

    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [cpf, setCpf] = useState("");
    const [senha, setSenha] = useState("");
    const [confirmaSenha, setConfirmaSenha] = useState(""); // Definindo o estado para confirmação da senha

    const handleInputChange = (event, setStateFunction) => {
        setStateFunction(event.target.value);
    }

    const handleCpfChange = (event) => {
        const rawCpf = event.target.value.replace(/\D/g, ""); // Remove tudo que não é número
        setCpf(rawCpf);
    };

    const handleSave = () => {
        const usuarioNovo = {
            nome,
            email,
            cpf,
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
            errorMessages.push("CPF deve ter 11 dígitos" + cpf);
        } else if (/[^0-9]/.test(cpf)) {
            errorMessages.push("CPF deve conter apenas números");
        } else if (cpf.trim() !== cpf) {
            errorMessages.push("CPF não deve conter espaços em branco no final");
        }

        // Verificações da senha
        if (!senha) {
            errorMessages.push("Senha não preenchida");
        } else {
            if (senha.length < 6) {
                errorMessages.push("Senha deve ter pelo menos 6 caracteres");
            }
            if (senha.length > 20) {
                errorMessages.push("Senha deve ter no máximo 20 caracteres");
            }
            if (!/[!@#$%^&*(),.?":{}|<>]/.test(senha)) {
                errorMessages.push("Senha deve conter pelo menos um caractere especial");
            }
            if (!/\d/.test(senha)) {
                errorMessages.push("Senha deve conter pelo menos um número");
            }
            if (!/[A-Z]/.test(senha)) {
                errorMessages.push("Senha deve conter pelo menos uma letra maiúscula");
            }
            if (!/[a-z]/.test(senha)) {
                errorMessages.push("Senha deve conter pelo menos uma letra minúscula");
            }
            if (senha.trim() !== senha) {
                errorMessages.push("Senha não deve conter espaços em branco no final");
            }
        }

        if (!confirmaSenha) {
            errorMessages.push("Confirmação de senha não preenchida");
        } else if (senha !== confirmaSenha) {
            errorMessages.push("Senhas não coincidem");
        } else if (confirmaSenha.trim() !== confirmaSenha) {
            errorMessages.push("Confirmação de senha não deve conter espaços em branco no final");
        }

        if (errorMessages.length > 0) {
            errorMessages.forEach(msg => toast.error(msg));
        } else {
            api.post(`/usuarios`, usuarioNovo).then(() => {
                emailjs.send("service_juy8w7g", "template_lr7u1k4", {
                    to_name: nome,
                    message: "\nSeja bem vindo ao sistema LearnLink! \nEstamos felizes em ter você conosco 😊 \nEm breve você receberá um e-mail informando seu status de acesso!",
                    to_email: email,
                }, "tZxktBF31MEVsj2aL")
                    .then((emailResponse) => {
                        toast.success("Email enviado!");
                    })
                    .catch((emailError) => {
                        toast.error("Erro ao enviar o e-mail. Tente novamente.");
                    });
                toast.success("Usuário cadastrado!");
                navigate("/loginDesktop");
            }).catch(() => {
                toast.error("Ocorreu um erro ao salvar os dados, por favor, tente novamente.");
            });
        }
    };

    return (
        <div className={styles['container']}>
            <Card altura="72vh">
                <a href="/homeDesktop" className={styles['imagemClicavel']}><div className={styles['imageContainer']} placeholder="Email"></div></a>
                <h1 className={styles['cadastroDesktopTitulo']}>CADASTRO</h1>
                <h3 className={styles['tituloInput']}>Nome</h3>
                <Input value={nome} onChange={(e) => handleInputChange(e, setNome)} placeholder="Nome" />
                <h3 className={styles['tituloInput']}>E-mail</h3>
                <Input value={email} onChange={(e) => handleInputChange(e, setEmail)} placeholder="E-mail" />
                <h3 className={styles['tituloInput']}>CPF</h3>
                <InputMask className={styles.inputMask} mask={"999.999.999-99"} maskChar={null} placeholder="CPF" value={cpf} onChange={handleCpfChange}></InputMask>
                <h3 className={styles['tituloInput']}>Senha</h3>
                <Input value={senha} type="password" onChange={(e) => handleInputChange(e, setSenha)} placeholder="Senha" />
                <h3 className={styles['tituloInput']}>Confirmar Senha</h3>
                <Input value={confirmaSenha} type="password" onChange={(e) => handleInputChange(e, setConfirmaSenha)} placeholder="Confirmação de Senha" />
                <div className={styles['divBotao']}>
                    <Botao funcao={handleSave} tipo="button" textoBotao="Cadastrar" />
                </div>
                <h3 className={styles['rodape']}>Não possui uma conta? <a href="loginDesktop" className={styles['direcionaCadastro']}>Login</a></h3>
            </Card>
        </div>
    );
}



export default Cadastro;
