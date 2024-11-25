import React, { useState } from "react";
import api from "../../../api";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import styles from "./RecuperarSenhaDesktop.module.css";
import Card from "../../components/cards/cardFormularios/CardFormulario";
import Input from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/Botao";
import emailjs from '@emailjs/browser';

const RecuperarSenhaDesktop = () => {
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
                        message: "\nSegue o código para recuperação de senha: " + codigo + "\n\nCaso não tenha solicitado, ignore este e-mail.",
                        to_email: email,
                    }, "tZxktBF31MEVsj2aL")
                        .then((emailResponse) => {
                            toast.success("Email enviado!");
                            navigate("/verificarSenhaDesktop", { state: { codigoGerado: codigo, idUsuario: response.data.id } });
                        })
                        .catch((emailError) => {
                            toast.error("Erro ao enviar o e-mail. Tente novamente.");
                        });
                } else {
                    toast.error("E-mail não encontrado. Verifique e tente novamente.");
                }
            })
            .catch(error => {
                // Verifique se o erro é um 404 (Not Found)
                if (error.response && error.response.status === 404) {
                    toast.error("E-mail não encontrado. Verifique e tente novamente.");
                } else {
                    toast.error("Erro ao buscar e-mail. Tente novamente.");
                }
            });
    };

    return (
        <div className={styles['container']}>
            <Card altura="45vh">
                <a href="/homeDesktop" className={styles['imagemClicavel']}><div className={styles['imageContainer']}></div></a>
                <h1 className={styles['loginDesktopTitulo']}>Recuperação de Senha</h1>
                <h3 className={styles['tituloInput']}>E-mail</h3>
                <Input value={email} onChange={(e) => handleInputChange(e, setEmail)} />
                <div className={styles['divBotao']}>
                    <Botao funcao={handleSave} textoBotao="Enviar" />
                </div>
            </Card>
        </div >
    )
}

export default RecuperarSenhaDesktop;