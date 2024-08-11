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
        <option value="6" className={styles['texto']}>Selecione um Status</option>
        <option value="1" className={styles['texto']}>Todos</option>
        <option value="2" className={styles['texto']}>Ativos</option>
        <option value="3" className={styles['texto']}>Pendentes</option>
        <option value="4" className={styles['texto']}>Negados</option>
      </select>
    </div>
  );
};

export default Dropdown;
