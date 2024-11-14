import React, { useState } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import styles from "./RedefinicaoSenhaDesktop.module.css";
import Card from "../../components/cards/cardFormularios/CardFormulario";
import Input from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/Botao";
import { useLocation } from 'react-router-dom';
import api from "../../../api";

const RedefinicaoSenhaDesktop = () => {
    const navigate = useNavigate();

    const [senha, setSenha] = useState("");
    const [confirmarSenha, setConfirmarSenha] = useState("");

    const location = useLocation();
    const { idUsuarioParam } = location.state || {};

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }


    const handleSave = () => {
        if (senha === confirmarSenha) {
            api.patch(`/usuarios/atualizar-senha/${idUsuarioParam}?senha=${senha}`)
                .then(response => {
                    toast.success("Senha redefinida com sucesso");
                    navigate("/loginDesktop");
                })
                .catch(error => {
                    toast.error("Erro ao redefinir a senha. Tente novamente");
                })
        } else {
            toast.error("As senhas devem ser iguais");
        }
    };

    return (
        <div className={styles['container']}>
            <Card altura="55vh">
                <a href="/homeDesktop" className={styles['imagemClicavel']}><div className={styles['imageContainer']}></div></a>
                <h1 className={styles['loginDesktopTitulo']}>Redefinição de Senha</h1>
                <h3 className={styles['tituloInput']}>Informe a sua nova senha</h3>
                <Input value={senha} onChange={(e) => handleInputChange(e, setSenha)} type="password" />
                <h3 className={styles['tituloInput']}>Confirmar nova senha</h3>
                <Input value={confirmarSenha} onChange={(e) => handleInputChange(e, setConfirmarSenha)} type="password" />
                <div className={styles['divBotao']}>
                    <Botao funcao={handleSave} textoBotao="Redefinir" />
                </div>
            </Card>
        </div >
    )
}

export default RedefinicaoSenhaDesktop;