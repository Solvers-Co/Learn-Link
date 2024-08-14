import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./CompletarCadastro.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";

const CompletarCadastro = () => {
    const navigate = useNavigate();

    const [especialidade, setEspecialidade] = useState("");
    const [cep, setCep] = useState("");
    const [estado, setEstado] = useState("");
    const [cidade, setCidade] = useState("");
    const [bairro, setBairro] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }
    
    const handleSave = () => {
        const usuarioNovo = { // Cria um objeto com os dados do formulário
            especialidade,
            cep,
            estado,
            cidade,
            bairro
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
        <div className={styles['container']}>
            <h1 className={styles['cadastroMobileTitulo']}>Completar Cadastro</h1>
            <h3>Para usar a plataforma é necessário terminar o cadastro</h3>
            <div className={styles['divInputs']}>
                <InputFormulario  placeHolder="Especialidade" value={especialidade} onChange={(e) => handleInputChange(e, setEspecialidade)}/>
                <InputFormulario placeHolder="CEP" value={cep} onChange={(e) => handleInputChange(e, setCep)}/>
                <InputFormulario placeHolder="Estado" value={estado} onChange={(e) => handleInputChange(e, setEstado)}/>
                <InputFormulario placeHolder="Cidade" value={cidade} onChange={(e) => handleInputChange(e, setCidade)}/>
                <InputFormulario placeHolder="Bairro" value={bairro} onChange={(e) => handleInputChange(e, setBairro)}/>
                <Botao funcao={handleSave} tipo="button" textoBotao="Finalizar" />
            </div>
        </div>
    )
};

export default CompletarCadastro;