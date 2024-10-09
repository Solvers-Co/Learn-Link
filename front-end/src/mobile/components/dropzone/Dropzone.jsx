import React, { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import styles from '.Dropzone.module.css';
import api from '../../../api';
import { Await } from 'react-router-dom';

function toBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result.split(",")[1]);
        reader.onerror = (error) => reject(error);
    });
}

const Dropzone = (origem) => {
  const [uploadedImages, setUploadedImages] = useState([]);
  const [message, setMessage] = useState('');

    const handleImageChange = async (file) => {
        let url = `/upload-foto-perfil/${sessionStorage.getItem("userId")}`
        if (file) {
            const base64Image = await toBase64(file);
            setImagem(base64Image);

            const response = await api.patch(`${url}`, base64Image);
        }
    };

  // Função para lidar com o upload
  const onDrop = useCallback(acceptedFiles => {
    const formData = new FormData();
    formData.append('file', acceptedFiles[0]);

    // Simulando upload (pode ser ajustado para seu backend)
    fetch('http://seu-backend.com/upload', {
      method: 'POST',
      body: formData,
    })
    .then(response => {
      if (response.ok) {
        setUploadedImages([...uploadedImages, URL.createObjectURL(acceptedFiles[0])]);
        setMessage('Upload realizado com sucesso!');
      } else {
        setMessage('Falha no upload.');
      }
    })
    .catch(() => setMessage('Erro ao conectar com o servidor.'));
  }, [uploadedImages]);

  const { getRootProps, getInputProps } = useDropzone({ onDrop });

  return (
    <div className="image-upload-container">
      <h1>Upload de Imagens</h1>
      <div {...getRootProps()} className="dropzone">
        <input {...getInputProps()} />
        <p>Arraste e solte suas imagens aqui, ou clique para selecionar</p>
      </div>
      {message && <p className="message">{message}</p>}
      
      <div className="uploaded-images">
        {uploadedImages.length > 0 && <h2>Imagens enviadas:</h2>}
        {uploadedImages.map((image, index) => (
          <img key={index} src={image} alt={`Uploaded ${index}`} />
        ))}
      </div>
    </div>
  );
};

export default Dropzone;