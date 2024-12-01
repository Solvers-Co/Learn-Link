import React, { useEffect, useState } from "react";
import api from "../../../api";
import Styles from "./LivrosFuvest.module.css";
import Header from "../../components/headerAplicacao/Header";
import Tooltip from "../../components/tooltip/Tooltip";


const LivrosFuvest = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        api
            .get("/livros/listar")
            .then((response) => {
                setBooks(response.data);
            })
            .catch((error) => {
                console.error("Erro ao buscar livros:", error);
            });
    }, []);

    const livros2025 = books.filter((book) => book.anoVestibular === 2025);
    const livros2026 = books.filter((book) => book.anoVestibular === 2026);

    return (
        <>
            <Header />
            <div className={Styles.livrosContainer}>
                <div className={Styles.info}>
                    <h1 className={Styles.titulo}>Livros Fuvest</h1>
                    <div className={Styles.tooltip}>
                        <Tooltip txt="Lista de leituras obrigatórias da Fuvest" />
                    </div>
                </div>

                <div className={Styles.livrosPorAno}>
                    <h2 className={Styles.subtitulo}>Vestibular 2025</h2>
                    <div className={Styles.livros}>
                        {livros2025.map((book, index) => (
                            <div key={index} className={Styles.livroItem}>
                                <div className={Styles.infos}>
                                    <h3 className={Styles.tituloLivro}>{book.titulo}</h3>
                                    <p className={Styles.autor}><span className={Styles.bold}>Autor:</span> {book.autor}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                <div className={Styles.livrosPorAno}>
                    <h2 className={Styles.subtitulo}>Vestibular 2026</h2>
                    <div className={Styles.livros}>
                        {livros2026.map((book, index) => (
                            <div key={index} className={Styles.livroItem}>
                                <div className={Styles.infos}>
                                    <h3 className={Styles.tituloLivro}>{book.titulo}</h3>
                                    <p className={Styles.autor}><span className={Styles.bold}>Autor:</span> {book.autor}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </>
    );
};

export default LivrosFuvest;
