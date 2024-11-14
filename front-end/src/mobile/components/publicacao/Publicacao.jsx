import React, { useEffect, useState, useMemo } from 'react';
import Styles from '../publicacao/Publicacao.module.css';
import api from '../../../api';
import { toast } from 'react-toastify';
import Modal from 'react-modal';
import { useNavigate } from "react-router-dom";
import StylesModal from '../../components/botoes/botaoFazerPublicacao/BotaoFazerPublicacao.module.css'
import { generateInitials } from '../../utils/functions/GerarIniciais';

import Curtir from '../../utils/assets/Curtir.png';
import Curtido from '../../utils/assets/Curtido.png';
import Comentar from '../../utils/assets/Comentario.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Editar from '../../utils/assets/Editar.png';
import Deletar from '../../utils/assets/Deletar.png';
import Denunciar from '../../utils/assets/Denuncia.png';
import Fechar from '../../utils/assets/icone_x.svg';
import IconeImagem from '../../utils/assets/icone_imagem.png';
import Dropzone from '../dropzone/Dropzone';

function formatDateTime(dateString) {
    const date = new Date(dateString);
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear().toString().slice(-2);
    return `${hours}:${minutes} - ${day}/${month}/${year}`;
}

function gerarNotificacao(corpo, usuarioGeradorId, usuarioRecebedorId) {
    const notificacao = {
        corpo,
        usuarioGeradorId,
        usuarioRecebedorId
    }
    api.post("/notificacoes", notificacao).then(response => {
        console.log(response.data)
    }).catch(() => {
        toast.error("Erro ao gerar notificacao")
    })
}
function reagirPublicacao(idPublicacao, tipoReacao, idUsuario, curtida, setCurtida, setCurtidas, idUsuarioQuePublicou) {

    if (curtida) {
        // Se o usuário já curtiu, remove a curtida
        api.delete(`/publicacoes/${idPublicacao}/remover-reacao`, { data: { tipoReacao, idUsuario } })
            .then(response => {
                console.log("Reação removida com sucesso:", response.data);
                // toast.success("Reação removida com sucesso!");
                setCurtida(false);
                setCurtidas(prevCurtidas => prevCurtidas - 1); // Decrementa o contador de curtidas
            })
            .catch(error => {
                console.error("Ocorreu um erro ao remover a reação:", error);
                // toast.error("Erro ao remover a reação.");
            });
    } else {
        // Se o usuário não curtiu, adiciona a curtida
        api.post(`/publicacoes/${idPublicacao}/reagir`, { tipoReacao, idUsuario })
            .then(response => {
                console.log("Reação registrada com sucesso:", response.data);
                // toast.success("Reação registrada com sucesso!");
                setCurtida(true);
                setCurtidas(prevCurtidas => prevCurtidas + 1); // Incrementa o contador de curtidas
                gerarNotificacao(" curtiu a sua publicação", idUsuario, idUsuarioQuePublicou)
            })
            .catch(error => {
                console.error("Ocorreu um erro ao reagir à publicação:", error);
                // toast.error("Erro ao reagir à publicação.");
            });
    }
}

function deletarPublicacao(id) {
    api.delete(`/publicacoes/${id}`)
        .then(response => {
            console.log("Publicação deletada com sucesso:", response.data);
            toast.success("Publicação deletada com sucesso!");
        })
        .catch(error => {
            console.error("Ocorreu um erro ao deletar a publicação:", error);
        });
}

function editarPublicacao(id, novoConteudo, novoCanal) {
    api.patch(`/publicacoes/${id}/conteudo?novoConteudo=${encodeURIComponent(novoConteudo)}&novoCanal=${encodeURIComponent(novoCanal)}`)
        .then(response => {
            console.log("Publicação editada com sucesso:", response.data);
            toast.success("Publicação editada com sucesso!");
            window.location.reload();
        })
        .catch(error => {
            console.error("Erro ao editar a publicação:", error.response?.data || error.message);
        });
}


function denunciarPublicacao(idPublicacao, idUsuario) {
    const denunciaData = {
        idUsuario: idUsuario,
    };

    api.post(`/publicacoes/${idPublicacao}/denunciar`, denunciaData)
        .then(response => {
            console.log("Publicação denunciada com sucesso:", response.data);
            toast.success("Publicação denunciada com sucesso!");
        })
        .catch(error => {
            if (error.response && error.response.data && error.response.data.message) {
                toast.error(`Erro ao denunciar: ${error.response.data.message}`);
            } else {
                toast.error("Erro ao denunciar a publicação.");
            }
            // console.error("Ocorreu um erro ao denunciar a publicação:", error);
        });
}

const Publicacao = ({ quemCurtiu, id, nome, materia, mensagem, horario, curtidas, comentarios, listarComentarios, togglePopup, popupAbertoId, idUsuarioQuePublicou, origem, listarComentariosPerfil, urlImagem }) => {
    const [showPopup, setShowPopup] = useState(null);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [curtida, setCurtida] = useState(quemCurtiu.includes(sessionStorage.getItem('nome')));
    const [numCurtidas, setCurtidas] = useState(curtidas); // Estado para o número de curtidas
    const [showEditar, setShowEditar] = useState(false);
    const [novoConteudo, setNovoConteudo] = useState(mensagem);
    const [novaMateria, setNovaMateria] = useState(materia);
    const [textoPublicacao, setTextoPublicacao] = useState("");
    const maxCaracteres = 255;
    const [showDenunciaModal, setShowDenunciaModal] = useState(false);
    const [srcImagem, setSrcImagem] = useState(urlImagem);
    const [srcImagemPerfil, setSrcImagemPerfil] = useState('')
    const [showPopupModal, setShowPopupModal] = useState(false);
    // const [motivoDenuncia, setMotivoDenuncia] = useState("");

    const navigate = useNavigate();

    const handleTogglePopup = () => {
        togglePopup(id);
    };

    const confirmarDelecao = () => {
        deletarPublicacao(id);
        setShowConfirmation(false);
        window.location.reload();
    };

    const abrirEditarModal = () => {
        console.log("Abrindo modal");
        console.log("Mensagem atual:", mensagem);
        console.log("Matéria atual:", materia);
        setNovoConteudo(mensagem); // Define o novo conteúdo com o valor atual
        setNovaMateria(materia);
        setShowPopup(false);
        setShowEditar(true);
    };

    const closeEditarModal = () => {
        setShowEditar(false);
    };

    const confirmarEdicao = () => {
        editarPublicacao(id, novoConteudo, novaMateria);
        closeEditarModal();
    };

    const abrirDenunciaModal = () => {
        setShowPopup(false);
        setShowDenunciaModal(true);
    };

    const closeDenunciaModal = () => {
        setShowDenunciaModal(false);
    };

    const confirmarDenuncia = () => {
        denunciarPublicacao(id, idUsuarioLogado);
        closeDenunciaModal();
    };

    const visualizarPerfil = (id) => {
        console.log(id)
        navigate(`/perfil/${id}`)
    }

    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome');
    const idUsuarioLogado = sessionStorage.getItem('userId');

    let nomeFormatado = 'Usuário Desconhecido'; // Valor padrão caso o nome não seja encontrado

    if (nome) {
        const nomes = nome.trim().split(' ');
        const primeiroNome = nomes[0];
        const ultimoNome = nomes[nomes.length - 1];
        if (nomes.length === 1) {
            nomeFormatado = primeiroNome;
        } else {
            nomeFormatado = `${primeiroNome} ${ultimoNome}`;
        }
    } else {
        console.log('Nome de usuário não encontrado');
    }

    // Use useMemo com nomeFormatado
    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);
    useEffect(() => {
        async function buscarImagemPerfil() {
            try {
                const response = await api.get(`usuarios/buscar-imagem-perfil/${idUsuarioQuePublicou}`);
                console.log(response.data)
                setSrcImagemPerfil(response.data)
            } catch (error) {
                console.log(error)
            }
        }
        buscarImagemPerfil();
    }, [])

    // Memorize o avatar gerado com base no nome
    const handleChange = (e) => {
        const value = e.target.value;
        if (value.length <= maxCaracteres) {
            setTextoPublicacao(value);
        }
    };
    return (
        <>
            <div className={Styles['container']}>

                <div className={Styles['header']}>
                    <div className={Styles['materiaBadge']}>{materia}</div>
                    <div className={Styles['menuVertical']} onClick={handleTogglePopup}>
                        <img src={MenuVertical} alt="Menu" />
                    </div>
                </div>
                <div className={Styles['userInfo']}>
                    {srcImagemPerfil ? (
                        <img className={Styles['imagemPerfil']} src={srcImagemPerfil} alt="Imagem de Perfil" />
                    ) : (
                        <span className={Styles['avatar']}>{avatar}</span>
                    )}
                    <span className={Styles['nome']} onClick={() => { visualizarPerfil(idUsuarioQuePublicou) }}>{nome}</span>
                </div>

                <div className={Styles['mensagem']}>{mensagem}</div>
                {srcImagem != null ? <img className={Styles['imagemPublicacao']} src={srcImagem}></img> : <div style={{ border: 'none' }}></div>}
                <div className={Styles['dataHora']}>{formatDateTime(horario)}</div>


                <div className={Styles['linha']}></div>

                <div className={Styles['footer']}>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{numCurtidas}</span>
                        <img
                            src={curtida ? Curtido : Curtir}
                            alt="Curtir"
                            onClick={() => { reagirPublicacao(id, "CURTIDA", idUsuarioLogado, curtida, setCurtida, setCurtidas, idUsuarioQuePublicou) }}
                        />
                        <span className={Styles['footerText']}>Curtir</span>
                    </div>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{comentarios}</span>
                        <img src={Comentar} alt="Comentar" />
                        <span className={Styles['footerText']} onClick={origem === "perfil" ? (() => listarComentariosPerfil(id)) : (() => listarComentarios(id))}>Comentários</span>
                    </div>
                </div>

                {popupAbertoId === id && (
                    <div className={Styles['popup']}>
                        {nomeUsuarioLogado === nome ? (
                            <>
                                <div className={Styles['opcao']} onClick={abrirEditarModal}>
                                    <img src={Editar} alt="Editar" />
                                    <span>Editar</span>
                                </div>

                                <div className={Styles['linhaPopup']}></div>

                                <div className={Styles['opcao']} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                                    <img src={Deletar} alt="Deletar" />
                                    <span>Excluir</span>
                                </div>
                            </>
                        ) : (
                            <div className={Styles['opcao']} onClick={() => { setShowPopup(false); abrirDenunciaModal(true) }}>
                                <img src={Denunciar} alt="Denunciar" />
                                <span>Denunciar</span>
                            </div>
                        )}
                    </div>
                )}

                {showEditar && (
                    <Modal
                        isOpen={showEditar}
                        onRequestClose={closeEditarModal}
                        className={StylesModal['publicarModal']}
                        overlayClassName={StylesModal['publicarOverlay']}
                    >
                        <div className={StylesModal["headerPublicar"]}>
                            <img src={Fechar} alt="icone fechar" onClick={closeEditarModal} />
                            <button className={StylesModal["botaoPostar"]} onClick={confirmarEdicao}>Editar</button>
                        </div>

                        <div className={StylesModal["conteudoPublicacao"]}>
                            <div className={StylesModal["pessoaConteudo"]} >
                                {avatar}
                                <textarea
                                    className={StylesModal["textoPublicacao"]}
                                    placeholder="Digite aqui..."
                                    value={novoConteudo}
                                    onChange={(e) => setNovoConteudo(e.target.value) && handleChange}
                                />
                            </div>
                            <div className={StylesModal["contadorCaracteres"]}>
                                {textoPublicacao.length} / {maxCaracteres}
                            </div>
                        </div>
                        {showPopupModal && (
                            <div className={Styles["blur"]}>
                                <div className={Styles["popup-modal"]}>
                                    <div className={Styles["popupContent"]}>
                                        <div className={Styles["iconeFechar"]}>
                                            <img
                                                src={Fechar}
                                                onClick={() => setShowPopupModal(false)}
                                            />
                                        </div>
                                        <div className={Styles.dropzone}>
                                            <Dropzone origem="publicacoes" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        )}
                        <div className={StylesModal["footerPublicar"]}>
                            <div className={Styles["divCanal"]}>
                                <span className={StylesModal["hashtag"]}>#</span>
                                <select
                                    name="materias"
                                    id="materias"
                                    className={StylesModal["opcoesMaterias"]}
                                    value={novaMateria}
                                    onChange={(e) => { console.log("Valor selecionado:", e.target.value); setNovaMateria(e.target.value) }}
                                >
                                    <option value="portugues">Português</option>
                                    <option value="matematica">Matemática</option>
                                    <option value="biologia">Biologia</option>
                                    <option value="quimica">Química</option>
                                    <option value="fisica">Física</option>
                                    <option value="historia">História</option>
                                    <option value="geografia">Geografia</option>
                                    <option value="filosofia">Filosofia</option>
                                    <option value="sociologia">Sociologia</option>
                                    <option value="ingles">Inglês</option>
                                    <option value="doacoes">Doações</option>
                                </select>
                            </div>
                            <div className={Styles["divImagem"]}>
                                <img
                                    src={IconeImagem}
                                    className={Styles["iconeImagem"]}
                                    onClick={() => setShowPopupModal(true)}
                                />
                            </div>
                        </div>
                    </Modal>
                )}

                {showConfirmation && (
                    <div className={Styles['modalOverlay']}>
                        <div className={Styles['modalContent']}>
                            <h3>Confirmar Exclusão</h3>
                            <p>Tem certeza de que deseja excluir esta publicação?</p>
                            <button className={Styles['confirmButton']} onClick={confirmarDelecao}>Sim</button>
                            <button className={Styles['cancelButton']} onClick={() => setShowConfirmation(false)}>Cancelar</button>
                        </div>
                    </div>
                )}

                {showDenunciaModal && (
                    <div className={Styles['modalOverlay']}>
                        <div className={Styles['modalContent']}>
                            <h3>Denunciar Publicação</h3>
                            <p>Tem certeza de que deseja denunciar esta publicação?</p>
                            <button className={Styles['confirmButton']} onClick={confirmarDenuncia}>Sim</button>
                            <button className={Styles['cancelButton']} onClick={() => closeDenunciaModal(false)}>Cancelar</button>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}

export default Publicacao;
