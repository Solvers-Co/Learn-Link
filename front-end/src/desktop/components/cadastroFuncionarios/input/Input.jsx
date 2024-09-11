import styles from './Input.module.css'

const Input = ({ label, placeholder, onChange }) => {
    return (
        <div className={styles.container}>
            <label className={styles.label}>{label}</label>
            <input className={styles.input} placeholder={placeholder} onChange={onChange}/>
        </div>
    )
}

export default Input;