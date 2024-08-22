import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./Cadastro.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";
import Header from "../../components/header/Header"

const Cadastro = () => {
    const navigate = useNavigate();

    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [cpf, setCpf] = useState("");
    const [senha, setSenha] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }
    
    const handleSave = () => {
        const usuarioNovo = { // Cria um objeto com os dados do formulário
            nome,
            email,
            cpf,
            senha
        };
        console.log(usuarioNovo)
        
        api.post(`/usuarios`, usuarioNovo).then(() => {
            toast.success("Usuário cadastrado!"); // Exibe uma mensagem de sucesso
            navigate("/login"); // Redireciona para a página de músicas
        }).catch(() => {
            toast.error("Ocorreu um erro ao salvar os dados, por favor, tente novamente."); // Exibe uma mensagem de erro se a requisição falhar
        });
    };
    
    return (
        <>
        <Header />
        <div className={styles['container']}>
            <h1 className={styles['cadastroMobileTitulo']}>Cadastro</h1>
            <div className={styles['divInputs']}>
                <InputFormulario  placeHolder="Nome" value={nome} onChange={(e) => handleInputChange(e, setNome)}/>
                <InputFormulario placeHolder="Email" value={email} onChange={(e) => handleInputChange(e, setEmail)}/>
                <InputFormulario placeHolder="CPF" value={cpf} onChange={(e) => handleInputChange(e, setCpf)}/>
                <InputFormulario placeHolder="Senha" value={senha} onChange={(e) => handleInputChange(e, setSenha)}/>
                <InputFormulario placeHolder="Confirmar senha"/>
                <Botao funcao={handleSave} tipo="button" textoBotao="Cadastrar" />
            </div>
        </div>
        </>      
    )
};

export default Cadastro;