import React, { useEffect, useState } from 'react';
import styles from './ListaUsuarios.module.css';
import Usuario from '../Usuario';
import api from "../../../../../src/api";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from "react-router-dom";
import Titulo from '../listaUsuarios/tituloAceitarUsuarios/Titulo';
import Dropdown from '../listaUsuarios/dropdown/Dropdown';


const ListaUsuarios = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [opcoes, setOpcoes] = useState(6);

    const fetchUsuarios = (status) => {
        let endpoint = '/usuarios/buscar-todos-os-usuarios'; // Padrão para carregar todos os usuários
        switch (status) {
            case '1':
                endpoint = '/usuarios/buscar-todos-os-usuarios';
                break;
            case '2':
                endpoint = '/usuarios/usuarios-ativos';
                break;
            case '3':
                endpoint = '/usuarios/usuarios-pendentes';
                break;
            case '4':
                endpoint = '/usuarios/usuarios-negados';
                break;
            default:
                break;
        }

        api.get(endpoint)
            .then((response) => {
                console.log(response);
                setUsuarios(response.data);
                toast.success("Usuários carregados com sucesso!");
            })
            .catch((error) => {
                console.error('erro', error);
            });
    };

    useEffect(() => {
        fetchUsuarios(opcoes); // Carrega os usuários de acordo com o valor inicial
    }, [opcoes]);

    useEffect(() => {
        fetchUsuarios('1'); // Carrega todos os usuários quando a página é carregada
    }, []);

    return (
        <>
            <div className={styles['listaUsuarios']}>
                <div className={styles['cabecalho']}>
                    <Titulo>Aceitar Usuários</Titulo>
                    <Dropdown
                        value={opcoes}
                        onChange={(value) => {
                            setOpcoes(value);
                        }}
                    />
                </div>
                <div className={styles['descricaoColunas']}>
                    <div className={styles['divNome']}>Nome</div>
                    <div className={styles['divCpf']}>CPF</div>
                    <div className={styles['divEmail']}>E-mail</div>
                    <div className={styles['divStatus']}>Status</div>
                </div>
                {usuarios.map((usuario) => (
                    <Usuario key={usuario.id} usuario={usuario} />
                ))}
            </div>
            <ToastContainer
                position="top-right"
                autoClose={1500}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="light"
                />
        </>
    );
};

export default ListaUsuarios;
