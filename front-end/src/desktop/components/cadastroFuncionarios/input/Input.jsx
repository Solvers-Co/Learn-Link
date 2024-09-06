import styles from './Input.module.css'

const Input = ({ label, placeholder }) => {
    return (
        <div className={styles.container}>
            <label className={styles.label}>{label}</label>
            <input className={styles.input} placeholder={placeholder} />
        </div>
    )
}

export default Input;