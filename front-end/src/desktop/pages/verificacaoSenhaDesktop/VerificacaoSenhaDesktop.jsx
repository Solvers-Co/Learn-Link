import React, { useState } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import styles from "./VerificacaoSenhaDesktop.module.css";
import Card from "../../components/cards/cardFormularios/CardFormulario";
import Input from "../../components/inputs/inputFormularios/InputFormulario";
import Botao from "../../components/botoes/Botao";
import { useLocation } from 'react-router-dom';

const VerificacaoSenhaDesktop = () => {
    const navigate = useNavigate();

    const [codigo, setCodigo] = useState("");

    const location = useLocation();
    const { codigoGerado } = location.state || {};
    const { idUsuario } = location.state || {};

    const handleInputChange = (event, setStateFunction) => { // Função para manipular as mudanças nos inputs
        setStateFunction(event.target.value); // Atualiza o estado correspondente
    }


    const handleSave = () => {
        console.log("Verificando o código:", codigo);
        console.log("Código gerado:", codigoGerado);
        if (codigo === codigoGerado) {
            toast.success("Código correto");
            navigate("/redefinirSenhaDesktop", { state: { idUsuarioParam: idUsuario } });
        } else {
            toast.error("Código inválido");
        }
    };

    return (
        <div className={styles['container']}>
            <Card altura="45vh">
                <a href="/homeDesktop" className={styles['imagemClicavel']}><div className={styles['imageContainer']}></div></a>
                <h1 className={styles['loginDesktopTitulo']}>Verificação</h1>
                <h3 className={styles['tituloInput']}>Informe o código enviado</h3>
                <Input value={codigo} onChange={(e) => handleInputChange(e, setCodigo)} />
                <div className={styles['divBotao']}>
                    <Botao funcao={handleSave} textoBotao="Verificar" />
                </div>
            </Card>
        </div >
    )
}

export default VerificacaoSenhaDesktop;