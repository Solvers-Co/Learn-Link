import React, { useState, useEffect, useRef } from 'react';
import styles from './Header.module.css';
import Logo from '../../utils/assets/logo_vermelha_fundo_branco.png';
import IconePesquisar from '../../utils/assets/icone_pesquisar.svg';
import IconeMenu from '../../utils/assets/icone_menu_hamburguer.svg';
import MenuInicial from '../menuInicial/MenuInicial';
import { useNavigate } from 'react-router-dom';
import api from "../../../api";
import MenuLateral from '../menuLateral/MenuLateral';

function Header() {
    const [searchVisible, setSearchVisible] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const [menuVisible, setMenuVisible] = useState(false);
    const headerRef = useRef(null);
    const menuIconRef = useRef(null);
    const menuRef = useRef(null); // Ref para o MenuInicial
    const navigate = useNavigate();
    const [busca, setBusca] = useState([]);

    const handleHome = () => {
        navigate('/');
    };

    const handleSearchClick = () => {
        if (searchVisible && searchValue) {
            // Realiza a busca usando o searchValue
            api.get(`/publicacoes/buscar-palavra-chave?palavraChave=${encodeURIComponent(searchValue)}`)
                .then(response => {
                    console.log('Resultado da busca:', response.data);
                    setBusca(response.data); // Atualize o estado com os resultados da busca
                })
                .catch(error => {
                    if (error.response) {
                        const { status } = error.response;
                        if (status === 204) {
                            console.log("Nenhuma publicação encontrada.");
                        } else if (status === 400) {
                            console.error("Palavra chave inválida.");
                        } else {
                            console.error("Erro ao realizar a busca:", error);
                        }
                    } else {
                        console.error("Erro ao realizar a busca:", error);
                    }
                });
        } else {
            setSearchVisible(!searchVisible);
        }
    };

    const handleInputChange = (event) => {
        setSearchValue(event.target.value);
    };

    const handleClickOutside = (event) => {
        if (
            headerRef.current &&
            !headerRef.current.contains(event.target) &&
            (!menuIconRef.current || !menuIconRef.current.contains(event.target)) &&
            (!menuRef.current || !menuRef.current.contains(event.target)) // Verifica se o clique foi fora do MenuInicial
        ) {
            setSearchVisible(false);
            setMenuVisible(false);
        }
    };

    const handleMenuClick = () => {
        setMenuVisible(!menuVisible);
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, []);

    return (
        <>
            <div className={styles.header} ref={headerRef}>
                <div className={styles.logo}>
                    <img src={Logo} alt='Logo Vermelha' onClick={handleHome}/>
                </div>
                <div className={styles.pesquisar}>
                    {searchVisible && (
                        <input
                            type="text"
                            value={searchValue}
                            onChange={handleInputChange}
                            className={styles.searchInput}
                            placeholder="Pesquisar..."
                        />
                    )}
                    <img
                        src={IconePesquisar}
                        alt='Ícone pesquisar'
                        onClick={handleSearchClick}
                        className={styles.searchIcon}
                    />
                </div>
                <div className={styles.menu}>
                    <img
                        src={IconeMenu}
                        alt='Menu hamburguer'
                        onClick={handleMenuClick}
                        ref={menuIconRef}
                    />
                </div>
            </div>
            {menuVisible && (
                <div ref={menuRef}>
                    <MenuLateral />
                </div>
            )}
        </>
    );
}

export default Header;
