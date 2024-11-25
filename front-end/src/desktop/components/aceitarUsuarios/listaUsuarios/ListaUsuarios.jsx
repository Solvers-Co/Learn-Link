import React, { useEffect, useState } from 'react';
import styles from './ListaUsuarios.module.css';
import Usuario from '../Usuario';
import api from "../../../../../src/api";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Titulo from '../listaUsuarios/tituloAceitarUsuarios/Titulo';
import Dropdown from '../listaUsuarios/dropdown/Dropdown';

const ListaUsuarios = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [opcoes, setOpcoes] = useState('1');
    const [pagina, setPagina] = useState(0);
    const [itensPorPagina, setItensPorPagina] = useState(7);
    const [totalPaginas, setTotalPaginas] = useState(0);
    const [origemChamada, setOrigemChamada] = useState('');
    const idUsuarioSs = parseInt(sessionStorage.getItem('userId'));

    const fetchUsuarios = (status, paginaAtual = pagina, itens = itensPorPagina) => {
        // Se a origem é o dropdown, reseta para a primeira página
        if (origemChamada === 'dropdown') {
            paginaAtual = 0;
        }

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
                const novaPagina = Math.min(paginaAtual, response.data.totalPages - 1);
                setPagina(novaPagina);
                setUsuarios(response.data.content || []);
                setTotalPaginas(response.data.totalPages);
            })
            .catch((error) => {
                console.error('erro', error);
            });
    };

    // Função para alterar o status de cada usuário
    const alterarStatus = (id, novoStatus) => {
        return api.patch(`/usuarios/${id}/status/${novoStatus}?idUsuarioRequisicao=${idUsuarioSs}`)
            .then(response => {
                console.log(`${sessionStorage.getItem('userID')} Usuário ${id} alterado com sucesso!`);
                return response.data;
            })
            .catch(error => {
                console.error(`Erro ao alterar status do usuário ${id}:`, error);
            });
    };

    // Função para aceitar todos os usuários pendentes
    const aceitarTodosPendentes = async () => {
        try {
            // 1. Buscar a primeira página para obter o total de páginas
            const response = await api.get('/usuarios/buscar-usuarios-pendentes-paginado?pagina=0&itens=7');
            const totalPaginas = response.data.totalPages;
            let idsPendentes = [];

            // 2. Percorrer todas as páginas e coletar os IDs dos usuários pendentes
            for (let pagina = 0; pagina < totalPaginas; pagina++) {
                const res = await api.get(`/usuarios/buscar-usuarios-pendentes-paginado?pagina=${pagina}&itens=7`);
                const usuariosPendentes = res.data.content || [];
                idsPendentes = [...idsPendentes, ...usuariosPendentes.map(usuario => usuario.id)];
            }

            // 3. Atualizar status de cada usuário pendente
            const promises = idsPendentes.map(id => alterarStatus(id, 2));

            // 4. Esperar que todas as requisições sejam concluídas
            await Promise.all(promises);

            toast.success('Todos os ' + idsPendentes.length + ' usuários pendentes foram aprovados!');
            // Atualiza a lista de usuários após aceitar todos
            fetchUsuarios('1');
            console.log('Todos os ' + idsPendentes.length + ' usuários pendentes foram aprovados!');
        } catch (error) {
            console.error('Erro ao aceitar todos os usuários pendentes:', error);
            toast.error('Ocorreu um erro ao aceitar os usuários.');
        }
    };

    useEffect(() => {
        fetchUsuarios(opcoes, pagina, itensPorPagina);
    }, [opcoes, pagina, itensPorPagina, totalPaginas]);

    return (
        <>
            <div className={styles.listaUsuarios}>
                <div className={styles.cabecalho}>
                    <Titulo>Aceitar Usuários</Titulo>
                    <div className={styles.interacao}>
                        <button className={styles.btnAtivarTodos} onClick={aceitarTodosPendentes}>
                            Ativar <span className={styles.bold}>todos</span> os usuários pendentes
                        </button>
                        <Dropdown
                            value={opcoes}
                            onChange={(value) => {
                                setOpcoes(value);
                                setOrigemChamada('dropdown'); // Marca que a origem é o dropdown
                            }}
                        />
                    </div>
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
                                onClick={() => {
                                    setPagina(pagina - 1);
                                    setOrigemChamada('paginacao');
                                }}
                                disabled={pagina === 0}
                                className={styles.btnPaginacao}
                            >
                                Anterior
                            </button>
                            <span className={styles.spanNumeroPaginas}>{pagina + 1} de {totalPaginas}</span>
                            <button
                                onClick={() => {
                                    setPagina(pagina + 1);
                                    setOrigemChamada('paginacao');
                                }}
                                disabled={pagina + 1 === totalPaginas}
                                className={styles.btnPaginacao}
                            >
                                Próximo
                            </button>
                        </div>
                    </>
                ) : (
                    <p className={styles['notFound']}>Nenhum usuário encontrado.</p>
                )}
            </div>
        </>
    );
};

export default ListaUsuarios;
