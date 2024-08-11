import React from 'react';
import styles from './Footer.module.css'; // Ensure to style the footer correctly

function Footer() {

    return (
        <footer className={styles['footer']}>

            <div className={styles['direitos']}>
                <p>Copyright &copy; 2024 Learn Link</p>
                <p>Todos os direitos reservados</p>
            </div>

        </footer>
    );
}

export default Footer;
