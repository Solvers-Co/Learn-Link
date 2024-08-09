import React, { useState } from 'react';
import styles from './Dropdown.module.css';

const Dropdown = (props) => {
  const [selectedValue, setSelectedValue] = useState('');

  const handleDropdownChange = (event) => {
    const value = event.target.value;
    setSelectedValue(value);
    if (props.onChange) {
      props.onChange(value);
    }
  };

  return (
    <div>
      <select value={selectedValue} onChange={handleDropdownChange} className={styles["btnDropdown"]}>
        <option value="6">Selecione o Status</option>
        <option value="1">Todos</option>
        <option value="2">Ativos</option>
        <option value="3">Pendentes</option>
        <option value="4">Negados</option>
      </select>
      {/* <p>Mês selecionado: {selectedValue}</p> */}
    </div>
  );
};

export default Dropdown;
