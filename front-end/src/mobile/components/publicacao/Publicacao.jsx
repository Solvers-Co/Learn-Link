import React, { useState, useMemo } from 'react';
import Styles from '../publicacao/Publicacao.module.css';
import api from '../../../api';
import { toast, ToastContainer } from 'react-toastify';
import Modal from 'react-modal';
import 'react-toastify/dist/ReactToastify.css';
import StylesModal from '../../components/botoes/botaoFazerPublicacao/BotaoFazerPublicacao.module.css'

import Curtir from '../../utils/assets/Curtir.png';
import Curtido from '../../utils/assets/Curtido.png';
import Comentar from '../../utils/assets/Comentario.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Editar from '../../utils/assets/Editar.png';
import Deletar from '../../utils/assets/Deletar.png';
import Denunciar from '../../utils/assets/Denuncia.png';
import Fechar from '../../utils/assets/icone_x.svg'

function formatDateTime(dateString) {
    const date = new Date(dateString);
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear().toString().slice(-2);
    return `${hours}:${minutes} - ${day}/${month}/${year}`;
}

function reagirPublicacao(idPublicacao, tipoReacao, idUsuario, curtida, setCurtida, setCurtidas) {

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
    console.log("A logica de denunciar publicação ainda não foi implementada.");
    // api.post(`/publicacoes/${idPublicacao}/denunciar`)
    //     .then(response => {
    //         console.log("Publicação denunciada com sucesso:", response.data);
    //         toast.success("Publicação denunciada com sucesso!");
    //     })
    //     .catch(error => {
    //         console.error("Ocorreu um erro ao denunciar a publicação:", error);
    //         toast.error("Erro ao denunciar a publicação.");
    //     });
}


function generateInitials(name) {
    const nameParts = name.trim().split(' ');
    const firstInitial = nameParts[0].charAt(0).toUpperCase();
    const lastInitial = nameParts[nameParts.length - 1].charAt(0).toUpperCase();

    const pastelColors = [
        '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
        '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
        '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    ];

    const randomIndex = Math.floor(Math.random() * pastelColors.length);
    const backgroundColor = pastelColors[randomIndex];

    const avatar = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        width: '35px',
        height: '35px',
        marginRight: '12px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSansExtraBold", sans-serif',
        backgroundColor
    };

    return <div style={avatar}>{firstInitial + lastInitial}</div>
}

const Publicacao = ({ quemCurtiu, id, nome, materia, mensagem, horario, curtidas, comentarios, listarComentarios }) => {
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [curtida, setCurtida] = useState(quemCurtiu.includes(sessionStorage.getItem('nome')));
    const [numCurtidas, setCurtidas] = useState(curtidas); // Estado para o número de curtidas
    const [showEditar, setShowEditar] = useState(false);
    const [novoConteudo, setNovoConteudo] = useState(mensagem);
    const [novaMateria, setNovaMateria] = useState(materia);
    const [textoPublicacao, setTextoPublicacao] = useState("");
    const maxCaracteres = 255;
    const [showDenunciaModal, setShowDenunciaModal] = useState(false);
    // const [motivoDenuncia, setMotivoDenuncia] = useState("");

    const togglePopup = () => {
        setShowPopup(!showPopup);
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

    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome');
    const idUsuarioLogado = sessionStorage.getItem('userId');

    let nomeFormatado = 'Usuário Desconhecido'; // Valor padrão caso o nome não seja encontrado

    if (nomeUsuarioLogado) {
        const nomes = nomeUsuarioLogado.trim().split(' '); 
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
                    <div className={Styles['menuVertical']} onClick={togglePopup}>
                        <img src={MenuVertical} alt="Menu" />
                    </div>
                </div>

                <div className={Styles['userInfo']}>
                    {avatar}
                    <span className={Styles['nome']}>{nome}</span>
                </div>

                <div className={Styles['mensagem']}>{mensagem}</div>
                <div className={Styles['dataHora']}>{formatDateTime(horario)}</div>


                <div className={Styles['linha']}></div>

                <div className={Styles['footer']}>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{numCurtidas}</span>
                        <img
                            src={curtida ? Curtido : Curtir}
                            alt="Curtir"
                            onClick={() => { reagirPublicacao(id, "CURTIDA", idUsuarioLogado, curtida, setCurtida, setCurtidas) }}
                        />
                        <span className={Styles['footerText']}>Curtir</span>
                    </div>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{comentarios}</span>
                        <img src={Comentar} alt="Comentar" />
                        <span className={Styles['footerText']} onClick={() => listarComentarios(id)}>Comentários</span>
                    </div>
                </div>

                {showPopup && (
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

                        <div className={StylesModal["footerPublicar"]}>
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

                <ToastContainer
                    position="top-right"
                    autoClose={1000}
                    hideProgressBar={false}
                    newestOnTop={false}
                    closeOnClick
                    rtl={false}
                    pauseOnFocusLoss
                    draggable
                    pauseOnHover
                    theme="colored"
                />
            </div>
        </>
    );
}

export default Publicacao;
