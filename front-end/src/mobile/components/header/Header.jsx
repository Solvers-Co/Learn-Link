import React, { useState, useEffect, useRef } from 'react';
import styles from './Header.module.css';
import Logo from '../../utils/assets/logo_vermelha_fundo_branco.png';
import IconePesquisar from '../../utils/assets/icone_pesquisar.svg';
import IconeMenu from '../../utils/assets/icone_menu_hamburguer.svg';
import MenuInicial from '../menuInicial/MenuInicial'; // Importe o MenuInicial

function Header() {
    const [searchVisible, setSearchVisible] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const [menuVisible, setMenuVisible] = useState(false); // Estado para controlar a visibilidade do menu
    const headerRef = useRef(null);

    const handleSearchClick = () => {
        if (searchVisible && searchValue) {
            // Realizar a pesquisa aqui
            console.log('Pesquisar por:', searchValue);
        } else {
            setSearchVisible(!searchVisible);
        }
    };

    const handleInputChange = (event) => {
        setSearchValue(event.target.value);
    };

    const handleClickOutside = (event) => {
        if (headerRef.current && !headerRef.current.contains(event.target)) {
            setSearchVisible(false);
        }
    };

    const handleMenuClick = () => {
        setMenuVisible(!menuVisible); // Alterna a visibilidade do menu
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
                    <img src={Logo} alt='Logo Vermelha' />
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
                    />
                </div>
            </div>
            {menuVisible && <MenuInicial />} {/* Renderize o MenuInicial baseado no estado */}
        </>
    );
}

export default Header;
