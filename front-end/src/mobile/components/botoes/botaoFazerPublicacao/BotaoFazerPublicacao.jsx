import React, { useState, useMemo } from "react";
import { toast } from 'react-toastify';
import api from "../../../../api";
import Styles from "./BotaoFazerPublicacao.module.css";
import Modal from 'react-modal';
import { generateInitials } from '../../../utils/functions/GerarIniciais';

import publicarIcone from "../../../utils/assets/Publicar.png";
import fechar from '../../../utils/assets/icone_x.svg';

function BotaoFazerPublicacao() {
    const [showComentarios, setShowComentarios] = useState(false);
    const [textoPublicacao, setTextoPublicacao] = useState("");
    const [materia, setMateria] = useState("");
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

    const fazerPublicacao = () => {
        const publicacao = {
            conteudo : textoPublicacao,
            idTipoPublicacao: 1,
            idUsuario : sessionStorage.userId,
            idCanal : materia
        };
        console.log(publicacao);

        api.post(`/publicacoes`, publicacao)
            .then(() => {
                toast.success("Publicação realizada!");
                window.location.reload();
            })
            .catch(() => {
                toast.error("Ocorreu um erro ao realizar a publicação, por favor, tente novamente.");
            });
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
                    <button className={Styles["botaoPostar"]} onClick={() => fazerPublicacao()}>Postar</button>
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
                    <select name="materias" id="materias" className={Styles["opcoesMaterias"]} onChange={(e) => setMateria(e.target.value)}>
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
                        <option value="11">Doações</option>
                    </select>
                </div>

            </Modal>
        </>
    );
}

export default BotaoFazerPublicacao;
