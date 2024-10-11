import React, { useEffect, useState } from "react";
import api from "../../../api";
import Styles from "../livrosFuvest/livrosFuvest.module.css";
import Header from "../../components/headerAplicacao/Header";


const LivrosFuvest = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        api
            .get("/livros/listar")
            .then((response) => {
                setBooks(response.data);
                console.log("Livros:", response.data);
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
                <h1 className={Styles.titulo}>Livros Fuvest</h1>

                <div className={Styles.livrosPorAno}>
                    <h2 className={Styles.subtitulo}>Vestibular 2025</h2>
                    <div className={Styles.livros}>
                        {livros2025.map((book, index) => (
                            <div key={index} className={Styles.livroItem}>
                                <div className={Styles.infos}>
                                    <h3 className={Styles.tituloLivro}>{book.titulo}</h3>
                                    <p className={Styles.autor}>Autor: {book.autor}</p>
                                    <p className={Styles.anoVestibular}>
                                        Ano Vestibular: {book.anoVestibular}
                                    </p>
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
                                    <p className={Styles.autor}>Autor: {book.autor}</p>
                                    <p className={Styles.anoVestibular}>
                                        Ano Vestibular: {book.anoVestibular}
                                    </p>
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
