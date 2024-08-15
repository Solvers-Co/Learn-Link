import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./VerfificacaoSenha.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";

const VerificacaoSenha = () => {
    const navigate = useNavigate();

    const [codigo, setCodigo] = useState("");

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }
    
    const handleSave = () => {
        const usuarioNovo = { // Cria um objeto com os dados do formulário
            codigo
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
            <h1 className={styles['cadastroMobileTitulo']}>Recuperação de Senha</h1>
            <h3>Informe o código enviado</h3>
            <div className={styles['divInputs']}>
                <InputFormulario  placeHolder="Código" value={codigo} onChange={(e) => handleInputChange(e, setCodigo)}/>
                <Botao funcao={handleSave} tipo="button" textoBotao="Verificar" />
            </div>
        </div>
    )
};

export default VerificacaoSenha;