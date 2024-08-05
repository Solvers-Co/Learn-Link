import React from "react";
import fotoPerfilHomem from "../../../utils/assets/perfilHomem.png";
import fotoPerfilMulher from "../../../utils/assets/perfilMulher.png";
import logoLearnLink from "../../../utils/assets/logo.png";
import styles from "./Ranking.module.css";

const Ranking = () =>{
    return(
        <div className={styles['ranking']}>
            <h1>Ranking</h1>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilMulher} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Sofhia Utaka</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilHomem} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Otávio Walkovics</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilMulher} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Ana Júlia</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilHomem} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Kauan Cruz</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilMulher} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Simone Mendes</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilMulher} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Stacy Melody</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilMulher} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Victoria Lima</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilHomem} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Carlos Henrique</p>
            </div>

            <div className={styles['classificacoes']}>
                <img src={fotoPerfilHomem} className={styles['fotoPerfilRanking']} alt="foto"></img>
                <p>Thiago Nogueira</p>
            </div>

            <img src={logoLearnLink} className={styles['logoLearnLink']} alt="foto"></img>
        </div>
    )
}

export default Ranking;