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
            if (response.data.tipoUsuario.tipoUsuario === "ADMIN") {
                const sessionData = response.data.token;
                const sessionUserId = response.data.userId;
                const sessionUserName = response.data.nome;
                const sessionUserTipo = response.data.tipoUsuario.tipoUsuario;
                const sessionUserEmail = response.data.email;

                sessionStorage.setItem('token', sessionData)
                sessionStorage.setItem('userId', sessionUserId)
                sessionStorage.setItem('nome', sessionUserName)
                sessionStorage.setItem('tipo', sessionUserTipo)
                sessionStorage.setItem('email', sessionUserEmail)
                toast.success("Login Efetuado com sucesso!");
                navigate("/dashboard");
            } else {
                console.log(response.data);
                toast.warning("Você não é um administrador.");
            }
        }).catch(() => {
            const errorMessages = [];

            if (!email) {
                errorMessages.push("Email não preenchido");
            } else if (!email.includes("@")) {
                errorMessages.push("E-mail ou senha incorretos");
            }

            // Verificações da senha
            if (!senha) {
                errorMessages.push("Senha não preenchida");
            } else {
                errorMessages.push("E-mail ou senha incorretos");
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
                <Input value={senha} onChange={(e) => handleInputChange(e, setSenha)} type="password" />
                <div className={styles['divBotao']}>
                    <Botao funcao={handleClick} textoBotao="Login" />
                </div>
                <div className={styles['rodapeContainer']}>
                    <h3 className={styles['rodape']}>Não possui uma conta? <a href="cadastroDesktop" className={styles['direcionaCadastro']}>Cadastre-se</a></h3>
                    <h3 className={styles['rodape']}><a href="recuperarSenhaDesktop" className={styles['direcionaRecuperarSenha']}>Esqueceu a senha?</a></h3>
                </div>
            </Card>
        </div >
    )
}

export default Login;