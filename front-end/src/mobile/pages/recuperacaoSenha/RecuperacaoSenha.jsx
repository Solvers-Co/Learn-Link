import React, { useState } from "react";
import api from "../../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import styles from "./RecuperacaoSenha.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";
import emailjs from '@emailjs/browser';
import Header from "../../components/header/Header";

const RecuperacaoSenha = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }

    function gerarCodigoAleatorio() {
        const caracteres = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        let codigo = '';
        for (let i = 0; i < 6; i++) {
            const randomIndex = Math.floor(Math.random() * caracteres.length);
            codigo += caracteres[randomIndex];
        }
        return codigo;
    }


    const handleSave = () => {
        api.get(`/usuarios/buscarEmail/${email}`)
            .then(response => {
                // Verifica se encontrou o usuário
                if (response.status === 200) {
                    const codigo = gerarCodigoAleatorio();
    
                    emailjs.send("service_juy8w7g", "template_lr7u1k4", {
                        to_name: response.data.nome,
                        message: "Segue o código para recuperação: " + codigo,
                        to_email: email,
                    }, "tZxktBF31MEVsj2aL")
                        .then((emailResponse) => {
                            console.log("Email enviado:", emailResponse.status, emailResponse.text);
                            toast.success("Email enviado!");
                            navigate("/verificarSenha", { state: { codigoGerado: codigo, idUsuario : response.data.id} });
                        })
                        .catch((emailError) => {
                            console.log("Erro ao enviar o email:", emailError.text);
                            toast.error("Erro ao enviar o e-mail. Tente novamente.");
                        });
                } else {
                    toast.error("E-mail não encontrado. Verifique e tente novamente.");
                }
            })
            .catch(error => {
                // Verifique se o erro é um 404 (Not Found)
                if (error.response && error.response.status === 404) {
                    console.log("E-mail não encontrado. Verifique e tente novamente.");
                    toast.error("E-mail não encontrado. Verifique e tente novamente.");
                } else {
                    toast.error("Erro ao buscar e-mail. Tente novamente.");
                }
                console.log("Erro ao buscar e-mail:", error);
            });
    };


    return (
        <>
            <Header />
            <div className={styles['container']}>
                <h1 className={styles['cadastroMobileTitulo']}>Recuperação de Senha</h1>
                <h3>Informe seu e-mail</h3>
                <div className={styles['divInputs']}>
                    <InputFormulario placeHolder="Email" value={email} onChange={(e) => handleInputChange(e, setEmail)} />
                    <Botao funcao={handleSave} tipo="button" textoBotao="Enviar" />
                </div>
            </div>
        </>
    )
};

export default RecuperacaoSenha;