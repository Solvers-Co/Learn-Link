import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Beneficios from '../../components/paginaInicial/beneficios/Beneficios';
import Home from '../../components/paginaInicial/home/Home';
import Solucao from '../../components/paginaInicial/solucoes/Solucao';
import styles from './PaginaInicial.module.css';
import Header from '../../components/header/Header';
import Footer from "../../components/footer/Footer";

const PaginaInicial = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const screenWidth = window.innerWidth;

    if (screenWidth > 768) {
      // Se for maior que 768px, é considerado desktop
      navigate('/homeDesktop');
    } else {
      // Se for menor ou igual a 768px, é considerado mobile
      navigate('/');
    }
  }, [navigate]);

  return (
    <div>
      <Header />
      <div id="home">
        <Home />
      </div>
      <div id="solucoes">
        <Solucao />
      </div>
      <div id="beneficios">
        <Beneficios />
      </div>
      <div id="footer">
        <Footer />
      </div>
    </div>
  );
}

export default PaginaInicial;
