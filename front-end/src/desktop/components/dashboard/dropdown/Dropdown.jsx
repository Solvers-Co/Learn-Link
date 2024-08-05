import React, { useState } from 'react';
import styles from './Dropdown.module.css';

const Dropdown = (props) => {
  const [selectedValue, setSelectedValue] = useState('');

  const handleDropdownChange = (event) => {
    setSelectedValue(event.target.value);
  };

  return (
    <div>
      <select value={selectedValue} onChange={props.onChange && handleDropdownChange} className={styles["btnDropdown"]}>
        <option value="6">Selecione um mês</option>
        <option value="1">Janeiro</option>
        <option value="2">Fevereiro</option>
        <option value="3">Março</option>
        <option value="4">Abril</option>
        <option value="5">Maio</option>
        <option value="6">Junho</option>
        <option value="7">Julho</option>
        <option value="8">Agosto</option>
        <option value="9">Setembro</option>
        <option value="10">Outubro</option>
        <option value="11">Novembro</option>
        <option value="12">Dezembro</option>
      </select>
      {/* <p>Mês selecionado: {selectedValue}</p> */}
    </div>
  );
};

export default Dropdown;


/*
import * as React from 'react';
import styles from './Dropdown.module.css';

const Dropdown = (props) => {

  const date = new Date();
  const month = date.toLocaleString('default', { month: 'long' });

  //console.log(month);

  const handleMenuOne = () => {
    console.log('clicked one');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('janeiroOpt').value + ' ▼';
  };

  const handleMenuTwo = () => {
    // do something
    console.log('clicked two');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('fevereiroOpt').value + ' ▼';
  };

  const handleMenuThree = () => {
    // do something
    console.log('clicked three');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('marcoOpt').value + ' ▼';
  };

  const handleMenuFour = () => {
    // do something
    console.log('clicked four');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('abrilOpt').value + ' ▼';
  };

  const handleMenuFive = () => {
    // do something
    console.log('clicked four');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('maioOpt').value + ' ▼';
  };

  const handleMenuSix = () => {
    // do something
    console.log('clicked six');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('junhoOpt').value + ' ▼';
  };

  const handleMenuSeven = () => {
    // do something
    console.log('clicked seven');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('julhoOpt').value + ' ▼';
  };

  const handleMenuEight = () => {
    // do something
    console.log('clicked eight');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('agostoOpt').value + ' ▼';
  };

  const handleMenuNine = () => {
    // do something
    console.log('clicked nine');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('setembroOpt').value + ' ▼';
  };

  const handleMenuTen = () => {
    // do something
    console.log('clicked ten');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('outubroOpt').value + ' ▼';
  };

  const handleMenuEleven = () => {
    // do something
    console.log('clicked eleven');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('novembroOpt').value + ' ▼';
  };

  const handleMenuTwelve = () => {
    // do something
    console.log('clicked twelve');
    document.getElementById('dropdownButton').innerHTML = document.getElementById('dezembroOpt').value + ' ▼';
  };

  setTimeout(function() {

    switch(month) {
      case 'janeiro':
        document.getElementById('dropdownButton').innerHTML = 'Janeiro ▼';
        break;
      case 'fevereiro':
        document.getElementById('dropdownButton').innerHTML = 'Fevereiro ▼';
        break;
      case 'março':
        document.getElementById('dropdownButton').innerHTML = 'Março ▼';
        break;
      case 'abril':
        document.getElementById('dropdownButton').innerHTML = 'Abril ▼';
        break;
      case 'maio':
        document.getElementById('dropdownButton').innerHTML = 'Maio ▼';
        break;
      case 'junho':
        document.getElementById('dropdownButton').innerHTML = 'Junho ▼';
        break;
      case 'julho':
        document.getElementById('dropdownButton').innerHTML = 'Julho ▼';
        break;
      case 'agosto':
        document.getElementById('dropdownButton').innerHTML = 'Agosto ▼';
        break;
      case 'setembro':
        document.getElementById('dropdownButton').innerHTML = 'Setembro ▼';
        break;
      case 'outubro':
        document.getElementById('dropdownButton').innerHTML = 'Outubro ▼';
        break;
      case 'novembro':
        document.getElementById('dropdownButton').innerHTML = 'Novembro ▼';
        break;
      case 'dezembro':
        document.getElementById('dropdownButton').innerHTML = 'Dezembro ▼';
        break;
      default:
        document.getElementById('dropdownButton').innerHTML = 'Mês desconhecido ▼';
        break;
    }
  }, 200)

  return (
    <Dropdownn
      trigger={<button id='dropdownButton' value='Dropdown' className={styles["btnDropdown"]}></button>}
      menu={[
        <button  id='janeiroOpt' value="Janeiro" onClick={props.onClick}>Janeiro</button>,
        <button onClick={handleMenuTwo} id='fevereiroOpt' value='Fevereiro'>Fevereiro</button>,
        <button onClick={handleMenuThree} id='marcoOpt' value='Março'>Março</button>,
        <button onClick={handleMenuFour} id='abrilOpt' value='Abril'>Abril</button>,
        <button onClick={handleMenuFive} id='maioOpt' value='Maio'>Maio</button>,
        <button onClick={handleMenuSix} id='junhoOpt' value='Junho'>Junho</button>,
        <button onClick={handleMenuSeven} id='julhoOpt' value='Julho'>Julho</button>,
        <button onClick={handleMenuEight} id='agostoOpt' value='Agosto'>Agosto</button>,
        <button onClick={handleMenuNine} id='setembroOpt' value='Setembro'>Setembro</button>,
        <button onClick={handleMenuTen} id='outubroOpt' value='Outubro'>Outubro</button>,
        <button onClick={handleMenuEleven} id='novembroOpt' value='Novembro'>Novembro</button>,
        <button onClick={handleMenuTwelve} id='dezembroOpt' value='Dezembro'>Dezembro</button>,
      ]}
    />
  );
};

const Dropdownn = ({ trigger, menu }) => {
  const [open, setOpen] = React.useState(false);

  const handleOpen = () => {
    setOpen(!open);
  };

  return (
    <div className={styles["dropdown"]}>
      {React.cloneElement(trigger, {
        onClick: handleOpen,
      })}
      {open ? (
        <ul className={styles["menu"]}>
          {menu.map((menuItem, index) => (
            <li key={index} className={styles["menu-item"]}>
              {React.cloneElement(menuItem, {
                onClick: () => {
                  menuItem.props.onClick();
                  setOpen(false);  // Fechar o menu após clicar em um item
                },
              })}
            </li>
          ))}
        </ul>
      ) : null}
    </div>
  );
};

export default Dropdown;
*/