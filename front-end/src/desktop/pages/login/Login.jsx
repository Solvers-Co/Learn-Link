import React, { useState } from "react";
import api from "../../../api";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import styles from "./Login.module.css";
import Card from "../../components/cards/cardFormularios/CardFormulario";
import Input from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/Botao";

const Login = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }

    const handleClick = () => {
        const usuario = { // Cria um objeto com os dados do formulário
            email,
            senha
        };

        api.post(`/usuarios/login`, usuario).then(response => {
            const sessionData = response.data.token;
            const sessionUserId = response.data.userId;

            sessionStorage.setItem('token', sessionData)
            sessionStorage.setItem('userId', sessionUserId)
            toast.success("Login Efetuado com sucesso!"); // Exibe uma mensagem de sucesso
            navigate("/loginDesktop"); // Redireciona para a página de músicas
        }).catch(() => {
            const errorMessages = [];

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
            if (errorMessages.length > 0) {
                errorMessages.forEach(msg => toast.error(msg)); // Exibe cada mensagem de erro individualmente
            } else {
                toast.error("E-mail ou senha incorretos"); // Mensagem de erro genérica
            }
        });
    };

    return (
        <div className={styles['container']}>
            <Card altura="55vh">
                <a href="/homeDesktop" className={styles['imagemClicavel']}><div className={styles['imageContainer']}></div></a>
                <h1 className={styles['loginDesktopTitulo']}>Bem-Vindo de Volta!</h1>
                <h3 className={styles['tituloInput']}>E-mail</h3>
                <Input value={email} onChange={(e) => handleInputChange(e, setEmail)} />
                <h3 className={styles['tituloInput']}>Senha</h3>
                <Input value={senha} onChange={(e) => handleInputChange(e, setSenha)} />
                <div className={styles['divBotao']}>
                    <Botao funcao={handleClick} textoBotao="Login" />
                </div>
                <h3 className={styles['rodape']}>Não possui uma conta? <a href="cadastroDesktop" className={styles['direcionaCadastro']}>Cadastre-se</a></h3>
            </Card>
            <ToastContainer />
        </div >
    )
}

export default Login;