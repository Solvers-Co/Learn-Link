import React from "react";
import { useNavigate, useLocation } from "react-router-dom"; // Importe o useLocation
import iconeDashboard from "../../../utils/assets/painel-de-controle.png";
import iconeAcesso from "../../../utils/assets/controle-de-acesso.png";
import iconeDenuncia from "../../../utils/assets/icone_denuncia.png";
import iconeSair from "../../../utils/assets/sair.png";
import fotoPerfil from "../../../utils/assets/perfil.png";
import styles from "./MenuLateral.module.css";

const MenuLateral = () => {
    const navigate = useNavigate(); // Crie uma instância do hook useNavigate
    const location = useLocation(); // Crie uma instância do hook useLocation

    const sair = () => {
        navigate("/homeDesktop"); // Redireciona para a rota de logout
    };

    const paginaAcesso = () => {
        navigate("/AceitarUsuarios"); // Redireciona para a rota de acesso
    };

    const paginaDashboard = () => {
        navigate("/Dashboard"); // Redireciona para a rota de dashboard
    };

    const paginaDenuncias = () => {
        navigate("/denuncias"); // Redireciona para a rota de denuncias
    };

    return (
        <div className={styles['menuLateralDashboard']}>
            <div className={styles['infoUsuario']}>
                <img src={fotoPerfil} alt="FotoDePerfil" className={styles['fotoPerfilMenu']} />
                <span className={styles['texto']}>Taina Maiara</span>
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
