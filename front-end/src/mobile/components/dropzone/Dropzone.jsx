import React, { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import styles from './Dropzone.module.css';
import api from '../../../api';
import { toast } from 'react-toastify'; // Usando react-toastify para exibir os toasts

function toBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result.split(",")[1]);
        reader.onerror = (error) => reject(error);
    });
}

const Dropzone = ({ origem }) => {
    const [uploadedImages, setUploadedImages] = useState([]);
    const [message, setMessage] = useState('');
    const [imagem, setImagem] = useState(null); // Mantém a última imagem carregada

    const handleImageChange = async (file) => {
        const base64Image = await toBase64(file);
        setImagem(base64Image); // Atualiza o estado com a imagem em base64
    };

    const onDrop = useCallback(acceptedFiles => {
        acceptedFiles.forEach(file => {
            handleImageChange(file); // Converte a imagem para base64
            setUploadedImages(prevState => [...prevState, URL.createObjectURL(file)]);
        });
    }, []);

    const { getRootProps, getInputProps } = useDropzone({ onDrop });

    // Função para salvar a imagem no servidor
    const handleSaveImage = async () => {
        if (!imagem) {
            toast.error("Nenhuma imagem anexada"); // Exibe toast de erro
            return;
        }

        const url = `/upload-foto-perfil/${sessionStorage.getItem("userId")}`;
        try {
            const response = await api.patch(url, { imagem });
            if (response.status === 200) {
                toast.success('Imagem salva com sucesso!'); // Exibe toast de sucesso
            } else {
                toast.error('Falha ao salvar a imagem.'); // Exibe toast de falha
            }
        } catch (error) {
            toast.error('Erro ao conectar com o servidor.'); // Exibe toast de erro
            console.error(error);
        }
    };

    return (
        <div className={styles['image-upload-container']}>
            <h1>Upload de Imagens</h1>
            <div {...getRootProps()} className={styles['dropzone']}>
                <input {...getInputProps()} />
                <p>Arraste e solte suas imagens aqui, ou clique para selecionar</p>
            </div>
            {message && <p className={styles['message']}>{message}</p>}

            <div className={styles['uploaded-images']}>
                {uploadedImages.length > 0 && <h2>Imagens enviadas:</h2>}
                {uploadedImages.map((image, index) => (
                    <img key={index} src={image} alt={`Uploaded ${index}`} />
                ))}
            </div>

            <button onClick={handleSaveImage} className={styles['save-button']}>Salvar Imagem</button>
        </div>
    );
};

export default Dropzone;
