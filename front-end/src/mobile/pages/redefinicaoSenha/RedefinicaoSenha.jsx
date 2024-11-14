import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./RedefinicaoSenha.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";
import Header from "../../components/header/Header";
import { useLocation } from 'react-router-dom';

const RedefinicaoSenha = () => {
    const navigate = useNavigate();

    const location = useLocation();
    const { idUsuarioParam } = location.state || {};

    const [senha, setSenha] = useState("");
    const [confirmarSenha, setConfirmarSenha] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }

    const handleSave = () => {
        if (senha === confirmarSenha) {
            api.patch(`/usuarios/atualizar-senha/${idUsuarioParam}?senha=${senha}`)
            .then(response => {
                toast.success("Senha redefinida com sucesso");
                navigate("/login");
            })
            .catch(error => {
                toast.error("Erro ao redefinir a senha. Tente novamente");
            })
        } else{
            toast.error("As senhas devem ser iguais");
        }
    };

    return (
        <>
            <Header />
            <div className={styles['container']}>
                <h1 className={styles['cadastroMobileTitulo']}>Redefinição <br />de Senha</h1>
                <h3>Informe sua nova senha</h3>
                <div className={styles['divInputs']}>
                    <InputFormulario placeHolder="Nova Senha" value={senha} tipo="password" onChange={(e) => handleInputChange(e, setSenha)} />
                    <InputFormulario placeHolder="Confirmar Nova Senha" tipo="password" value={confirmarSenha} onChange={(e) => handleInputChange(e, setConfirmarSenha)} />
                    <Botao funcao={handleSave} tipo="button" textoBotao="Redefinir" />
                </div>
            </div>
        </>
    )
};

export default RedefinicaoSenha;