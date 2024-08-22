import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./Login.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";
import Header from "../../components/header/Header";

const Login = () => {
    const navigate = useNavigate();

    const[email, setEmail] = useState("");
    const[senha, setSenha] = useState("");

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
            const sessionUserName = response.data.nome;

            sessionStorage.setItem('token', sessionData)
            sessionStorage.setItem('userId', sessionUserId)
            sessionStorage.setItem('nome', sessionUserName)
            
            toast.success("Login Efetuado com sucesso!"); // Exibe uma mensagem de sucesso
            navigate("/feedGeral"); // Redireciona para a página de músicas
        }).catch(() => {
            toast.error("Ocorreu um erro ao salvar os dados, por favor, tente novamente."); // Exibe uma mensagem de erro se a requisição falhar
        });
    };

    return (
        <>
            <Header />
            <div className={styles['container']}>
                <h1 className={styles['loginMobileTitulo']}>Login</h1>
                <h3>Bem-Vindo de volta</h3>
                <div className={styles['divInputs']}>
                    <InputFormulario  placeHolder="Email" value={email} onChange={(e) => handleInputChange(e, setEmail)}/>
                    <InputFormulario placeHolder="Senha" value={senha} onChange={(e) => handleInputChange(e, setSenha)}/>
                </div>
                <a href=""><h2>Esqueceu sua senha?</h2></a>
                <div className={styles['divBotao']}>
                    <Botao funcao={handleClick} textoBotao="Login" />
                </div>

            </div>
        </>
    )
};

export default Login;