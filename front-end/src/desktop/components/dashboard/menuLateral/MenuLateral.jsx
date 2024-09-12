import React, { useState, useMemo, useEffect } from 'react';
import { useNavigate, useLocation } from "react-router-dom";
import iconeDashboard from "../../../utils/assets/painel-de-controle.png";
import iconeAcesso from "../../../utils/assets/controle-de-acesso.png";
import iconeDenuncia from "../../../utils/assets/icone_denuncia.png";
import iconeSair from "../../../utils/assets/sair.png";
import iconeCadastroFuncionarios from "../../../utils/assets/icone_cadastro_func.png";
import styles from "./MenuLateral.module.css";

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

    const avatarStyle = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        width: '32px',
        height: '32px',
        marginRight: '12px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSansExtraBold", sans-serif',
        backgroundColor
    };

    return <div style={avatarStyle}>{firstInitial + lastInitial}</div>;
}

const MenuLateral = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const nome = "Taina Maiara";

    const sair = () => {
        navigate("/homeDesktop");
    };

    const paginaAcesso = () => {
        navigate("/AceitarUsuarios");
    };

    const paginaDashboard = () => {
        navigate("/Dashboard");
    };

    const paginaDenuncias = () => {
        navigate("/denuncias");
    };

    const paginaCadastroFuncionarios = () => {
        navigate("/cadastroFuncionarios");
    };

    const avatar = useMemo(() => generateInitials(sessionStorage.getItem("nome")), [sessionStorage.getItem("nome")]);

    const validarContaAdm = () => {
        const elementoCadastro = document.getElementById("cadastroFuncionarios");
        if (sessionStorage.getItem("email") !== "adm@adm.com" && elementoCadastro) {
            elementoCadastro.style.display = "none";
        }
    };

    useEffect(() => {
        validarContaAdm();
    }, []); // useEffect para garantir que a função seja executada após o componente ser montado

    return (
        <div className={styles['menuLateralDashboard']}>
            <div className={styles['infoUsuario']}>
                {avatar}
                <span className={styles['texto']}>{sessionStorage.getItem("nome")}</span>
            </div>
            <div className={`${styles['paginasDashboard']}`} >
                <p className={styles['infoPaginasDashboard']}>Dashboards</p>
                <div className={`${styles['paginaDaDashboard']} ${styles['cursorPointer']} ${location.pathname === '/Dashboard' ? styles['paginaAtiva'] : ''}`}
                    onClick={paginaDashboard}
                >
                    <img
                        src={iconeDashboard}
                        className={styles['iconePaginaDaDashboard']}
                        alt="IconePaginaDashboard"
                    />
                    Visão Geral
                </div>
                <div className={styles['paginas']}>
                    <div>
                        <p className={styles['infoPaginasPaginas']}>Páginas</p>
                    </div>
                    <div className={styles['secaoPaginas']}>
                        <div className={`${styles['paginaPaginas']} ${styles['cursorPointer']} ${location.pathname === '/AceitarUsuarios' ? styles['paginaAtiva'] : ''}`}
                            onClick={paginaAcesso}
                        >
                            <img
                                src={iconeAcesso}
                                className={styles['iconePaginaPaginas']}
                                alt="IconePaginaAcesso"
                            />
                            Controle de Acesso
                        </div>

                        <div
                            className={`${styles['paginaPaginas']} ${styles['cursorPointer']} ${location.pathname === '/denuncias' ? styles['paginaAtiva'] : ''
                                }`}
                            onClick={paginaDenuncias}
                        >
                            <img
                                src={iconeDenuncia}
                                className={styles['iconePaginaPaginas']}
                                alt="IconePaginaDenuncias"
                            />
                            Denúncias
                        </div>
                        <div
                            id="cadastroFuncionarios" className={`${styles['paginaPaginas']} ${styles['cursorPointer']} ${location.pathname === '/cadastroFuncionario' ? styles['paginaAtiva'] : ''
                                }`}
                            onClick={paginaCadastroFuncionarios}
                        >
                            <img
                                src={iconeCadastroFuncionarios}
                                className={styles['iconePaginaPaginas']}
                                alt="IconePaginaDenuncias"
                            />
                            Cadastro
                        </div>
                    </div>
                </div>

                <hr className={styles['divisaoSair']} />

                <div
                    className={`${styles['btnSair']} ${styles['cursorPointer']}`}
                    onClick={sair}
                >
                    <img
                        src={iconeSair}
                        className={styles['iconePaginaPaginas']}
                        alt="IconeSair"
                    />
                    <span className={styles['texto']}>Sair</span>
                </div>
            </div>
        </div>
    );
};

export default MenuLateral;
