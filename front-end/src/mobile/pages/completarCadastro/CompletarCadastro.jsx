import React, { useState } from "react";
import api from "../../../api";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import styles from "./CompletarCadastro.module.css";
import InputFormulario from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/botaoLoginCadastro/Botao";


const CompletarCadastro = () => {
    const navigate = useNavigate();

    const [idEspecialidade, setEspecialidade] = useState("");
    const [cep, setCep] = useState("");
    const [estado, setEstado] = useState("");
    const [cidade, setCidade] = useState("");
    const [bairro, setBairro] = useState("");

    const handleInputChange = (event, setStateFunction) => {
        const value = event.target.value;
        setStateFunction(value);

        // Só busca o endereço se for o campo de CEP
        if (setStateFunction === setCep && value.length === 8) {
            buscarEnderecoPorCep(value);
        }
    };

    const buscarEnderecoPorCep = async (cep) => {
        try {
            const response = await fetch(`https://brasilapi.com.br/api/cep/v1/${cep}`);
            const data = await response.json();
            console.log("Endereço encontrado:", data);

            // Atualiza os estados diretamente
            const estadoForm = document.getElementById("estado");
            const cidadeForm = document.getElementById("cidade");
            const bairroForm = document.getElementById("bairro");
            const estadoViaCep = data.state;
            const cidadeViaCep = data.city;
            const bairroViaCep = data.neighborhood;
            estadoForm.value = estadoViaCep;
            cidadeForm.value = cidadeViaCep;
            bairroForm.value = bairroViaCep;
            setEstado(estadoViaCep);
            setCidade(cidadeViaCep);
            setBairro(bairroViaCep);
        } catch (error) {
            console.error("Erro ao buscar endereço:", error);
        }
    };

    const handleSave = () => {
        const usuarioCompleto = {
            idUsuario: sessionStorage.getItem("userId"),
            idEspecialidade,
            endereco : {
                bairro,
                cidade,
                estado,
                cep
            }
        };
        console.log(usuarioCompleto);

        api.patch(`/usuarios/finalizar-cadastro`, usuarioCompleto)
            .then(() => {
                toast.success("Cadastro completo!");
                navigate("/feedGeral");
            })
            .catch(() => {
                toast.error("Ocorreu um erro ao salvar os dados, por favor, tente novamente.");
            });
    };

    return (
        <div className={styles.container}>
            <h1 className={styles.cadastroMobileTitulo}>Completar Cadastro</h1>
            <h3>Para usar a plataforma é necessário terminar o cadastro</h3>
            <div className={styles.divInputs}>
                {/* <InputFormulario placeHolder="Especialidade" value={especialidade} onChange={(e) => handleInputChange(e, setEspecialidade)} /> */}
                <select className={styles.select} value={idEspecialidade} name="especialidade" onChange={(e) => handleInputChange(e, setEspecialidade)}>
                    <option value="">Especialidade</option>
                    <option value="1">Matemática</option>
                    <option value="2">Português</option>
                    <option value="3">Biologia</option>
                    <option value="4">História</option>
                    <option value="5">Física</option>
                    <option value="6">Química</option>
                    <option value="7">Sociologia</option>
                    <option value="8">Geografia</option>
                    <option value="9">Inglês</option>
                    <option value="10">Filosofia</option>
                </select>
                <InputFormulario placeHolder="CEP" value={cep} onChange={(e) => handleInputChange(e, setCep)} />
                <InputFormulario id="estado" placeHolder="Estado" readOnly />
                <InputFormulario id="cidade" placeHolder="Cidade" readOnly />
                <InputFormulario id="bairro" placeHolder="Bairro" readOnly />

                <Botao funcao={handleSave} tipo="button" textoBotao="Finalizar" />
            </div> 
        </div>
    );
};

export default CompletarCadastro;
