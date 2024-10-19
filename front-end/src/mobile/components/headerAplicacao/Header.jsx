import React, { useState, useEffect, useRef } from 'react';
import styles from './Header.module.css';
import Logo from '../../utils/assets/logo_vermelha_fundo_branco.png';
import IconePesquisar from '../../utils/assets/icone_pesquisar.svg';
import IconeMenu from '../../utils/assets/icone_menu_hamburguer.svg';
import { useNavigate, useLocation } from 'react-router-dom'; // Importa useLocation
import MenuLateral from '../menuLateral/MenuLateral';

function Header({ onSearchResult }) {
    const [searchVisible, setSearchVisible] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const [menuVisible, setMenuVisible] = useState(false);
    const headerRef = useRef(null);
    const menuIconRef = useRef(null);
    const menuRef = useRef(null);
    const navigate = useNavigate();
    const location = useLocation(); // Pega a localização atual

    const handleFeedGeral = () => {
        navigate('/feedGeral');
        window.location.reload();
    };

    const handleSearchClick = () => {
        if (searchVisible) {
            onSearchResult(searchValue); // Passa o valor da busca para FeedGeral
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
            (!menuRef.current || !menuRef.current.contains(event.target))
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

    // Verifica se a rota atual permite a pesquisa
    const isSearchAllowed = location.pathname === '/feedGeral'; // Permite apenas na página 'feedGeral'

    return (
        <>
            <div className={styles.header} ref={headerRef}>
                <div className={styles.logo}>
                    <img src={Logo} alt='Logo Vermelha' onClick={handleFeedGeral} />
                </div>
                <div className={styles.pesquisar}>
                    {searchVisible && isSearchAllowed && ( // Exibe o campo de pesquisa apenas se for permitido
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
                        onClick={isSearchAllowed ? handleSearchClick : null} // Bloqueia o clique se não for permitido
                        className={styles.searchIcon}
                        style={{ opacity: isSearchAllowed ? 1 : 0.5 }} // Reduz a opacidade se bloqueado
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
