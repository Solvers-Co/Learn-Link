
import React from "react";
import Cadastro from "./mobile/pages/cadastro/Cadastro";
import Login from "./mobile/pages/login/Login";
import CadastroDesktop from "./desktop/pages/cadastro/Cadastro";
import LoginDesktop from "./desktop/pages/login/Login";
import Dashboard from "./desktop/pages/dashboard/Dashboard";
import AceitarUsuarios from "./desktop/pages/aceitarUsuarios/AceitarUsuarios";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PaginaInicialDesktop from "./desktop/pages/paginaInicial/PaginaInicial";
import MenuInicial from "./mobile/components/menuInicial/MenuInicial";
import Header from "./mobile/components/header/Header";
import PaginaInicial from "./mobile/pages/paginaInicial/PaginaInicial";

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
                    <Route path="/aceitarUsuarios" element={<AceitarUsuarios />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}
export default Rotas;