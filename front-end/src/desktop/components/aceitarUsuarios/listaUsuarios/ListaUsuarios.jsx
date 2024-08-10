import React, { useEffect, useState } from 'react';
import styles from './ListaUsuarios.module.css';
import Usuario from '../Usuario';
import api from "../../../../../src/api";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Titulo from '../listaUsuarios/tituloAceitarUsuarios/Titulo';
import Dropdown from '../listaUsuarios/dropdown/Dropdown';

const ListaUsuarios = () => {
    const [usuarios, setUsuarios] = useState([]);  // Garantindo que seja um array vazio por padrão
    const [opcoes, setOpcoes] = useState('1'); // Valor inicial como string '1'
    const [pagina, setPagina] = useState(0);
    const [itensPorPagina, setItensPorPagina] = useState(7);
    const [totalPaginas, setTotalPaginas] = useState(0);

    const fetchUsuarios = (status, paginaAtual = pagina, itens = itensPorPagina) => {
        let endpoint = '';
        switch (status) {
            case '1':
                endpoint = '/usuarios/buscar-todos-os-usuarios-paginado';
                break;
            case '2':
                endpoint = '/usuarios/buscar-usuarios-ativos-paginado';
                break;
            case '3':
                endpoint = '/usuarios/buscar-usuarios-pendentes-paginado';
                break;
            case '4':
                endpoint = '/usuarios/buscar-usuarios-negados-paginado';
                break;
            default:
                break;
        }

        api.get(`${endpoint}?pagina=${paginaAtual}&itens=${itens}`)
            .then((response) => {
                console.log(response);
                setUsuarios(response.data.content || []);  // Garantindo que seja um array
                setTotalPaginas(response.data.totalPages);
            })
            .catch((error) => {
                console.error('erro', error);
            });
    };
  
    
    useEffect(() => {
       
        fetchUsuarios(opcoes, pagina, itensPorPagina);
    }, [opcoes, pagina, itensPorPagina, totalPaginas]);
    

    return (
        <>
            <div className={styles.listaUsuarios}>
                <div className={styles.cabecalho}>
                    <Titulo>Aceitar Usuários</Titulo>
                    <Dropdown
                        value={opcoes}
                        onChange={(value) => {
                            setOpcoes(value);
                        }}
                    />
                </div>
                <div className={styles.descricaoColunas}>
                    <div className={styles.divNome}>Nome</div>
                    <div className={styles.divCpf}>CPF</div>
                    <div className={styles.divEmail}>E-mail</div>
                    <div className={styles.divStatus}>Status</div>
                </div>

                {usuarios && usuarios.length > 0 ? (
                    <>
                        {usuarios.map((usuario) => (
                            <Usuario
                                key={usuario.id}
                                usuario={usuario}
                                fetchUsuarios={fetchUsuarios}
                                paginaAtual={pagina}
                                statusAtual={opcoes}
                            />
                        ))}
                        <div className={styles.paginacao}>
                            <button
                                onClick={() => setPagina(pagina - 1)}
                                disabled={pagina === 0}
                                className={styles.btnPaginacao}
                            >
                                Anterior
                            </button>
                            <span className={styles.spanNumeroPaginas}>{pagina + 1} de {totalPaginas}</span>
                            <button
                                onClick={() => setPagina(pagina + 1)}
                                disabled={pagina + 1 === totalPaginas}
                                className={styles.btnPaginacao}
                            >
                                Próximo
                            </button>
                        </div>
                    </>
                ) : (
                    <p>Nenhum usuário encontrado.</p>
                )}
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
