import React, { useState, useMemo } from 'react';
import Styles from '../publicacao/Publicacao.module.css';
import api from '../../../api';
import { toast, ToastContainer } from 'react-toastify';
import Modal from 'react-modal';
import 'react-toastify/dist/ReactToastify.css';

import Curtir from '../../utils/assets/Curtir.png';
import Comentar from '../../utils/assets/Comentario.png';
import MenuVertical from '../../utils/assets/MenuVertical.png';
import Editar from '../../utils/assets/Editar.png';
import Deletar from '../../utils/assets/Deletar.png';
import Denunciar from '../../utils/assets/Deletar.png';
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

function EditarPublicacao(id) {
    const [showEditar, setshowEditar] = useState(false);

    const closeEditarModal = () => {
        setshowEditar(false); // Fecha o modal
    };

    api.patch(`/publicacoes/${id}/conteudo`)
        .then(response => {
            <Modal
                isOpen={showEditar}
                onRequestClose={closeEditarModal}
                className={Styles['publicarModal']}
                overlayClassName={Styles['publicarOverlay']}
            >
                <div className={Styles["headerPublicar"]}>
                    <img src={Fechar} alt="icone fechar" onClick={closeEditarModal} />
                    <button className={Styles["botaoPostar"]}>Editar</button>
                </div>

                <div className={Styles["conteudoPublicacao"]}>
                    {generateInitials(sessionStorage.nome)}
                    <textarea className={Styles["textoPublicacao"]} placeholder="Digite aqui..."></textarea>
                </div>


                <div className={Styles["footerPublicar"]}>
                    <span className={Styles["hashtag"]}>#</span>
                    <select name="materias" id="materias" className={Styles["opcoesMaterias"]}>
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
                    </select>
                </div>

            </Modal>


            console.log("Publicação editada com sucesso:", response.data);
            toast.success("Publicação editada com sucesso!");
        })
        .catch(error => {
            console.error("Ocorreu um erro ao editar a publicação:", error);
        });
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

const Publicacao = ({ id, nome, materia, mensagem, horario, curtidas, comentarios, listarComentarios }) => {
    const [showPopup, setShowPopup] = useState(false);
    const [showConfirmation, setShowConfirmation] = useState(false);

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    const confirmarDelecao = () => {
        deletarPublicacao(id);
        setShowConfirmation(false);
        window.location.reload();
    };

    // Obtem o nome do usuário armazenado no sessionStorage
    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    // Memorize o avatar gerado com base no nome
    const avatar = useMemo(() => generateInitials(nome), [nome]);

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
                        <span className={Styles['numero']}>{curtidas}</span>
                        <img src={Curtir} alt="Curtir" />
                        <span className={Styles['footerText']}>Curtir</span>
                    </div>
                    <div className={Styles['footerItem']}>
                        <span className={Styles['numero']}>{comentarios}</span>
                        <img src={Comentar} alt="Comentar" />
                        <span className={Styles['footerText']} onClick={() => { listarComentarios(id); }}>Comentários</span>
                    </div>
                </div>

                {showPopup && (
                    <div className={Styles['popup']}>
                        {nomeUsuarioLogado === nome ? (
                            <>
                                <button className={Styles['popupButton']} onClick={() => { setShowPopup(false); setShowEditar(true); }}>
                                    <div className={Styles['opcao']}>
                                        <img src={Editar} alt="Editar" />
                                        <span>Editar</span>
                                    </div>
                                </button>

                                <div className={Styles['linhaPopup']}></div>

                                <div className={Styles['opcao']} onClick={() => { setShowPopup(false); setShowConfirmation(true); }}>
                                    <img src={Deletar} alt="Deletar" />
                                    <span>Excluir</span>
                                </div>
                            </>
                        ) : (
                            <div className={Styles['opcao']} onClick={() => { setShowPopup(false); /* Lógica para denunciar */ }}>
                                <img src={Denunciar} alt="Denunciar" />
                                <span>Denunciar</span>
                            </div>
                        )}
                    </div>
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
