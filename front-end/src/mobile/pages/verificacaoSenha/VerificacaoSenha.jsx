import React, { useState } from "react";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./VerfificacaoSenha.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";
import { useLocation } from 'react-router-dom';
import Header from "../../components/header/Header";

const VerificacaoSenha = () => {
    const navigate = useNavigate();

    const location = useLocation();
    const { codigoGerado } = location.state || {};
    const { idUsuario } = location.state || {};


    const [codigo, setCodigo] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }

    const handleSave = () => {
        console.log("Verificando o código:", codigo);
        console.log("Código gerado:", codigoGerado);
        if (codigo === codigoGerado) {
            toast.success("Código correto");
            navigate("/redefinirSenha", { state: { idUsuarioParam : idUsuario} });
        } else {
            toast.error("Código inválido");
        }
    };

    return (
        <>
            <Header />
            <div className={styles['container']}>
                <h1 className={styles['cadastroMobileTitulo']}>Recuperação de Senha</h1>
                <h3>Informe o código enviado</h3>
                <div className={styles['divInputs']}>
                    <InputFormulario placeHolder="Código" value={codigo} onChange={(e) => handleInputChange(e, setCodigo)} />
                    <Botao funcao={handleSave} tipo="button" textoBotao="Verificar" />
                </div>
            </div>
        </>
    )
};

export default VerificacaoSenha;