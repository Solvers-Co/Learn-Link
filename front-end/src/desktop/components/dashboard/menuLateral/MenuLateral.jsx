import React from "react";
import iconeDashboard from "../../../utils/assets/painel-de-controle.png";
import iconeAcesso from "../../../utils/assets/controle-de-acesso.png";
import iconeSair from "../../../utils/assets/sair.png";
import fotoPerfil from "../../../utils/assets/perfil.png";
import styles from "./MenuLateral.module.css";

const MenuLateral = () =>{
    return(
        <div className={styles['menuLateralDashboard']}>
            <div className={styles['infoUsuario']}>
                <img src={fotoPerfil} alt="FotoDePerfil" className={styles['fotoPerfilMenu']}></img>
                <p>Taina Maiara</p>
            </div>
            <div className={styles['paginasDashboard']}>
                <p className={styles['infoPaginasDashboard']}>Dashboards</p>
                <div className={styles['paginaDaDashboard']}>
                    <img src={iconeDashboard} className={styles['iconePaginaDaDashboard']} alt="IconePaginaDashboard"></img>
                    Visão Geral
                </div>

                <p className={styles['infoPaginasPaginas']}>Páginas</p>
                <div className={styles['paginaPaginas']}>
                    <img src={iconeAcesso} className={styles['iconePaginaPaginas']} alt="IconePaginaAcesso"></img>
                    Controle de Acesso
                </div>

                <hr className={styles['divisaoSair']}/>

                <div className={styles['btnSair']}>
                    <img src={iconeSair} className={styles['iconePaginaPaginas']} alt="IconeSair"></img>
                    Sair
                </div>
                
            </div>
        </div>
    )
};

export default MenuLateral;