import styles from './Input.module.css'

const Input = ({ label, placeholder, onChange, value }) => {
    return (
        <div className={styles.container}>
            <label className={styles.label}>{label}</label>
            <input className={styles.input} placeholder={placeholder} value={value}
            onChange={onChange}/>
        </div>
    )
}

export default Input;