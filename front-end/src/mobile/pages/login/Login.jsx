import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./Login.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";
import Header from "../../components/header/Header";
import { ToastContainer } from "react-toastify";

const Login = () => {

    sessionStorage.nome = "";
    sessionStorage.situacao = "";
    sessionStorage.userId = "";
    sessionStorage.token = "";

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [showConfirmation, setShowConfirmation] = useState(false);

    const handleInputChange = (event, setStateFunction) => {
        setStateFunction(event.target.value);
    }

    const handleClick = async () => {
        const usuario = {
            email,
            senha
        };

        try {
            const response = await api.post(`/usuarios/login`, usuario);
            const sessionData = response.data.token;
            const sessionUserId = response.data.userId;
            const sessionUserName = response.data.nome;
            const sessionUserEmail = response.data.email;

            const situacaoUsuario = await buscarSituacaoUser(sessionUserId);

            if (situacaoUsuario === 'PENDENTE' || situacaoUsuario === 'NEGADO') {
                setShowConfirmation(true);
            } else {
                sessionStorage.setItem('token', sessionData);
                sessionStorage.setItem('userId', sessionUserId);
                sessionStorage.setItem('nome', sessionUserName);

            sessionStorage.setItem('email', sessionUserEmail)
                if(endereco === null){
                    toast.success("Login Efetuado com sucesso!");
                    navigate("/completarCadastro");
                } else{
                    toast.success("Login Efetuado com sucesso!");
                    navigate("/feedGeral");
                }
            }
        } catch (error) {
            toast.error("Ocorreu um erro ao salvar os dados, por favor, tente novamente.");
        }
    };

    var endereco;

    const buscarSituacaoUser = async (id) => {
        try {
            const response = await api.get(`/usuarios/${id}`);
            const situacao = response.data.tipoStatus.status;
            sessionStorage.setItem('situacao', situacao);
            console.log(response.data);
            endereco = response.data.endereco;
            return situacao;
        } catch (error) {
            console.error("Erro ao buscar situação do usuário:", error);
            return null; // ou algum valor padrão
        }
    }

    return (
        <>
            <Header />
            <div className={styles['container']}>
                <h1 className={styles['loginMobileTitulo']}>Login</h1>
                <h3>Bem-Vindo de volta</h3>
                <div className={styles['divInputs']}>
                    <InputFormulario placeHolder="Email" value={email} onChange={(e) => handleInputChange(e, setEmail)} />
                    <InputFormulario placeHolder="Senha" value={senha} tipo="password" onChange={(e) => handleInputChange(e, setSenha)} />
                </div>
                <a href=""><h2>Esqueceu sua senha?</h2></a>
                <div className={styles['divBotao']}>
                    <Botao funcao={handleClick} textoBotao="Login" />
                </div>

            </div>

            {showConfirmation && (
                <div className={styles['modalOverlay']}>
                    <div className={styles['modalContent']}>
                        <h3>Falta pouco!</h3>
                        <p>Para acessar a plataforma, é necessário que um administrador aprove seu cadastro. Em breve, você receberá um e-mail confirmando a aprovação.</p>
                        <button className={styles['confirmButton']} onClick={() => {setShowConfirmation(false); window.location.reload();}}>Ok</button>
                    </div>
                </div>
            )}

            <ToastContainer
                position="top-right"
                autoClose={1000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="colored"
            />
        </>
    )
};

export default Login;