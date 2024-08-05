
import React from "react";
import Cadastro from "./mobile/pages/cadastro/Cadastro";
import Login from "./mobile/pages/login/Login";
import CadastroDesktop from "./desktop/pages/cadastro/Cadastro";
import LoginDesktop from "./desktop/pages/login/Login";
import Dashboard from "./desktop/pages/dashboard/Dashboard";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function Rotas() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    {/* <Route path="/" element={<Home />} /> */}
                    <Route path="/cadastro" element={<Cadastro />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/cadastroDesktop" element={<CadastroDesktop />} />
                    <Route path="/loginDesktop" element={<LoginDesktop />} />
                    <Route path="/dashboard" element={<Dashboard />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}
export default Rotas;