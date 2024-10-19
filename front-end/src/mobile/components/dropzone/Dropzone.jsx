import React, { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import styles from './Dropzone.module.css';
import api from '../../../api';
import { toast } from 'react-toastify'; // Usando react-toastify para exibir os toasts

const Dropzone = ({ origem }) => {
    const [uploadedImages, setUploadedImages] = useState([]);
    const [imagem, setImagem] = useState(null); // Mantém a última imagem carregada

    // Função para ler a imagem e convertê-la para bytes
    const handleImageChange = (file) => {
        if (file) {
            const reader = new FileReader();
            reader.readAsArrayBuffer(file);  // Isso vai disparar o 'onloadend'

            reader.onloadend = () => {
                const imageBytes = reader.result;
                console.log("Imagem lida como ArrayBuffer:", imageBytes);

                setImagem(imageBytes); // Salva os bytes da imagem no estado
            };

            reader.onerror = () => {
                toast.error("Erro ao ler o arquivo");
            };
        }
    };

    const fetchImagem = async (imageBytes) => {
        const payload = {
            imagemBytes: Array.from(new Uint8Array(imageBytes))
        };

        try {
            const response = await api.patch(`/usuarios/upload-foto-perfil/${sessionStorage.getItem("userId")}`, payload);
            if (response.status === 200) {
                toast.success('Imagem salva com sucesso!');
            } else {
                toast.error('Falha ao salvar a imagem.');
            }
        } catch (error) {
            toast.error('Erro ao conectar com o servidor.');
            console.error(error);
        }
    };

    const onDrop = useCallback((acceptedFiles) => {
        acceptedFiles.forEach(file => {
            handleImageChange(file); // Lê o arquivo selecionado e o converte para ArrayBuffer
            setUploadedImages(prevState => [...prevState, URL.createObjectURL(file)]); // Exibe a imagem para o usuário
        });
    }, []);

    const { getRootProps, getInputProps } = useDropzone({ onDrop });

    const handleSaveImage = () => {
        if (!imagem) {
            toast.error("Nenhuma imagem anexada");
            return;
        }

        fetchImagem(imagem); // Chama a função para salvar a imagem no servidor
    };

    return (
        <div className={styles['image-upload-container']}>
            <h1>Upload de Imagens</h1>
            <div {...getRootProps()} className={styles['dropzone']}>
                <input {...getInputProps()} />
                <p>Arraste e solte suas imagens aqui, ou clique para selecionar</p>
            </div>

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