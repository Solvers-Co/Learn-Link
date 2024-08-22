import React, { useState, useEffect, useRef } from 'react';
import styles from './Header.module.css';
import Logo from '../../utils/assets/logo_vermelha_fundo_branco.png';
import IconePesquisar from '../../utils/assets/icone_pesquisar.svg';
import IconeMenu from '../../utils/assets/icone_menu_hamburguer.svg';
import { useNavigate } from 'react-router-dom';
import api from "../../../api";
import MenuLateral from '../menuLateral/MenuLateral';

function Header({ onSearchResult }) { // Recebe a função onSearchResult como prop
    const [searchVisible, setSearchVisible] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const [menuVisible, setMenuVisible] = useState(false);
    const headerRef = useRef(null);
    const menuIconRef = useRef(null);
    const menuRef = useRef(null); 
    const navigate = useNavigate();

    const handleFeedGeral = () => {
        navigate('/feedGeral');
    };

    const handleSearchClick = () => {
        // verifica se a barra de busca tá visível e se tem txt no campo
        if (searchVisible && searchValue) {
            // encodeURIComponent: garantir q qualquer caractere especial seja corretamente codificado na URL
            api.get(`/publicacoes/buscar-palavra-chave?palavraChave=${encodeURIComponent(searchValue)}`)
                .then(response => {
                    console.log('Resultado da busca:', response.data);
                    onSearchResult(response.data); // Passa os resultados da busca para o FeedGeral
                })
                .catch(error => {
                    if (error.response) {
                        const { status } = error.response;
                        if (status === 204) {
                            console.log("Nenhuma publicação encontrada.");
                            onSearchResult([]); // Passa um array vazio se nada for encontrado
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

    // sempre q algo é digitado no campo, o valor é capturado e atualizado no seachValue
    const handleInputChange = (event) => {
        setSearchValue(event.target.value);
    };

    const handleClickOutside = (event) => {
        if (
            headerRef.current &&
            !headerRef.current.contains(event.target) &&
            (!menuIconRef.current || !menuIconRef.current.contains(event.target)) &&
            (!menuRef.current || !menuRef.current.contains(event.target))
        ) {
            setSearchVisible(false);
            setMenuVisible(false);
        }
    };

    const handleMenuClick = () => {
        setMenuVisible(!menuVisible);
    };

    // qnd clica fora da barra de busca, ela fecha
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
                    <img src={Logo} alt='Logo Vermelha' onClick={handleFeedGeral}/>
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
