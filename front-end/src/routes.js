
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
import LivrosFuvest from "./mobile/pages/livrosFuvest/LivrosFuvest";
import RecuperacaoSenhaDesktop from "./desktop/pages/recuperarSenhaDesktop/RecuperarSenhaDesktop";
import VerificacaoSenhaDesktop from "./desktop/pages/verificacaoSenhaDesktop/VerificacaoSenhaDesktop";
import RedefinicaoSenhaDesktop from "./desktop/pages/redefinicaoSenhaDesktop/RedefinicaoSenhaDesktop";
import CadastroFuncionarios from "./desktop/pages/cadastroFuncionarios/CadastroFuncionarios";
import ProtectedLayout from "./ProtectedLayout";

function Rotas() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/homeDesktop" element={<PaginaInicialDesktop />} />
                    <Route path="/" element={<PaginaInicial />} />  
                    <Route path="/login" element={<Login />} />
                    <Route path="/loginDesktop" element={<LoginDesktop />} />
                    <Route path="/cadastro" element={<Cadastro />} />
                    <Route path="/cadastroDesktop" element={<CadastroDesktop />} />
                    <Route element={<ProtectedLayout />}>
                        <Route path="/recuperarSenhaDesktop" element={<RecuperacaoSenhaDesktop />} />
                        <Route path="/verificarSenhaDesktop" element={<VerificacaoSenhaDesktop />} />
                        <Route path="/redefinirSenhaDesktop" element={<RedefinicaoSenhaDesktop />} />
                        <Route path="/dashboard" element={<Dashboard />} />
                        <Route path="/denuncias" element={<Denuncias />} />
                        <Route path="/cadastroFuncionarios" element={<CadastroFuncionarios />} />
                        <Route path="/aceitarUsuarios" element={<AceitarUsuarios />} />
                        <Route path="/perfil/:idUsuario" element={<Perfil />} />
                        <Route path="/canais" element={<Canais />} />
                        <Route path="/notificacoes" element={<Notificacoes />} />
                        <Route path="/completarCadastro" element={<CompletarCadastro />} />
                        <Route path="/recuperarSenha" element={<RecuperacaoSenha />} />
                        <Route path="/verificarSenha" element={<VerificacaoSenha />} />
                        <Route path="/redefinirSenha" element={<RedefinicaoSenha />} />
                        <Route path="/feedGeral" element={<FeedGeral />} />
                        <Route path="/ranking" element={<Ranking />} />
                        <Route path="/livrosFuvest" element={<LivrosFuvest />} />
                    </Route>    
                 </Routes>
            </BrowserRouter>
        </>
    );
}
export default Rotas;