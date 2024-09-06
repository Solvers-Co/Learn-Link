
import React from "react";
import Cadastro from "./mobile/pages/cadastro/Cadastro";
import Login from "./mobile/pages/login/Login";
import Canais from "./mobile/pages/canais/Canais";
import CadastroDesktop from "./desktop/pages/cadastro/Cadastro";
import LoginDesktop from "./desktop/pages/login/Login";
import Dashboard from "./desktop/pages/dashboard/Dashboard";
import AceitarUsuarios from "./desktop/pages/aceitarUsuarios/AceitarUsuarios";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PaginaInicialDesktop from "./desktop/pages/paginaInicial/PaginaInicial";
import MenuInicial from "./mobile/components/menuInicial/MenuInicial";
import Header from "./mobile/components/header/Header";
import PaginaInicial from "./mobile/pages/paginaInicial/PaginaInicial";
import CompletarCadastro from "./mobile/pages/completarCadastro/CompletarCadastro";
import RecuperacaoSenha from "./mobile/pages/recuperacaoSenha/RecuperacaoSenha";
import VerificacaoSenha from "./mobile/pages/verificacaoSenha/VerificacaoSenha";
import RedefinicaoSenha from "./mobile/pages/redefinicaoSenha/RedefinicaoSenha";
import FeedGeral from "./mobile/pages/feedGeral/FeedGeral";
import Perfil from "./mobile/pages/perfil/Perfil";
import Ranking from "./mobile/pages/ranking/Ranking";
import Denuncias from "./desktop/pages/denuncias/Denuncias";
import Notificacoes from "./mobile/pages/notificacoes/Notificacoes";
import CadastroFuncionarios from "./desktop/pages/cadastroFuncionarios/CadastroFuncionarios";

function Rotas() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<PaginaInicial />} />
                    <Route path="/homeDesktop" element={<PaginaInicialDesktop />} />
                    <Route path="/cadastro" element={<Cadastro />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/cadastroDesktop" element={<CadastroDesktop />} />
                    <Route path="/loginDesktop" element={<LoginDesktop />} />
                    <Route path="/dashboard" element={<Dashboard />} />
                    <Route path="/denuncias" element={<Denuncias />} />
                    <Route path="/cadastroFuncionarios" element={<CadastroFuncionarios />} />
                    <Route path="/aceitarUsuarios" element={<AceitarUsuarios />} />
                    <Route path="/perfil" element={<Perfil />} />
                    <Route path="/canais" element={<Canais />} />
                    <Route path="/notificacoes" element={<Notificacoes />} />
                    <Route path="/completarCadastro" element={<CompletarCadastro />} />
                    <Route path="/recuperarSenha" element={<RecuperacaoSenha />} />
                    <Route path="/verificarSenha" element={<VerificacaoSenha />} />
                    <Route path="/redefinirSenha" element={<RedefinicaoSenha />} />
                    <Route path="/feedGeral" element={<FeedGeral />} />
                    <Route path="/ranking" element={<Ranking />} />
                 </Routes>
            </BrowserRouter>
        </>
    );
}
export default Rotas;