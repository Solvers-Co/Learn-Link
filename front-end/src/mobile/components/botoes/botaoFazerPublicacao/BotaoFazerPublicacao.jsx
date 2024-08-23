import React, { useState, useMemo } from "react";
import Styles from "./BotaoFazerPublicacao.module.css";
import publicarIcone from "../../../utils/assets/Publicar.png";
import Modal from 'react-modal';
import fechar from '../../../utils/assets/icone_x.svg';

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

function BotaoFazerPublicacao() {
    const [showComentarios, setShowComentarios] = useState(false);
    const [textoPublicacao, setTextoPublicacao] = useState("");
    const maxCaracteres = 255;

    const nomeUsuario = sessionStorage.getItem('nome');

    // Memoriza o avatar gerado para o usuário
    const avatar = useMemo(() => generateInitials(nomeUsuario), [nomeUsuario]);

    const closeComentariosModal = () => {
        setShowComentarios(false); // Fecha o modal
    };

    const handleChange = (e) => {
        const value = e.target.value;
        if (value.length <= maxCaracteres) {
            setTextoPublicacao(value);
        }
    };

    return (
        <>
            <button
                className={Styles["botaoFazerPublicacao"]}
                onClick={() => { setShowComentarios(true); }}
            >
                <img src={publicarIcone} alt="Editar" />
                Faça uma publicação
            </button>

            <Modal
                isOpen={showComentarios}
                onRequestClose={closeComentariosModal}
                className={Styles['publicarModal']}
                overlayClassName={Styles['publicarOverlay']}
            >
                <div className={Styles["headerPublicar"]}>
                    <img src={fechar} alt="icone fechar" onClick={closeComentariosModal} />
                    <button className={Styles["botaoPostar"]}>Postar</button>
                </div>

                <div className={Styles["conteudoPublicacao"]}>
                    <div className={Styles["pessoaConteudo"]} >
                        {avatar}
                        <textarea
                            className={Styles["textoPublicacao"]}
                            placeholder="Digite aqui..."
                            value={textoPublicacao}
                            onChange={handleChange}
                        />
                    </div>
                    <div className={Styles["contadorCaracteres"]}>
                        {textoPublicacao.length} / {maxCaracteres}
                    </div>
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
        </>
    );
}

export default BotaoFazerPublicacao;
