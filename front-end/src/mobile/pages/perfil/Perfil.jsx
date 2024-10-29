import styles from './Perfil.module.css';
import Header from '../../components/headerAplicacao/Header';
import CardPerfil from '../../components/cards/cardPerfil/CardPerfil';
import React, { useMemo, useState, useEffect } from 'react';
import { useParams } from "react-router-dom";
import CardAtividade from '../../components/cards/cardAtividade/CardAtividade';
import api from "../../../api";
import Modal from 'react-modal';
import Tooltip from '../../components/tooltip/Tooltip';
import { toast } from 'react-toastify';
import Publicacao from '../../components/publicacao/Publicacao';
import Comentario from '../../components/comentario/Comentario';

import Enviar from '../../utils/assets/Enviar.png';
import fechar from '../../utils/assets/icone_x.svg';
import Lapis from '../../utils/assets/icone_lapis.png';

import Dropzone from '../../components/dropzone/Dropzone';

// generateInitials diferente do padrão
function generateInitials(name) {
    if (!name) return <div style={{ color: 'red' }}>N/A</div>;

    const nameParts = name.trim().split(' ');
    const initials = nameParts[0].charAt(0).toUpperCase() + (nameParts.length > 1 ? nameParts[nameParts.length - 1].charAt(0).toUpperCase() : '');

    const pastelColors = [
        '#FFB3BA', '#FFDFBA', '#FFFFBA', '#BAFFC9', '#BAE1FF',
        '#FFB3B3', '#FFCCB3', '#FFFFCC', '#CCFFCC', '#CCE5FF',
        '#FFC3A0', '#FFEDCC', '#FFFFE0', '#E0FFCC', '#CCE0FF',
        '#FFC4C4', '#FFE1C4', '#FFFFD1', '#D1FFD1', '#D1E8FF'
    ];

    const backgroundColor = pastelColors[Math.floor(Math.random() * pastelColors.length)];

    const avatarStyle = {
        borderRadius: '50%',
        border: '1px solid rgba(0, 0, 0, .3)',
        fontSize: '40px',
        width: '100px',
        height: '100px',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontFamily: '"NunitoSans", sans-serif',
        boxShadow: '2px 2px 16px rgba(0, 0, 0, 0.25)',
        backgroundColor
    };

    return <div style={avatarStyle}>{initials}</div>;
}



const Perfil = () => {
    const { idUsuario } = useParams(); // Pegando o id do usuário da URL
    const [nome, setNome] = useState('Usuário Desconhecido');
    const [email, setEmail] = useState('E-mail Desconhecido');
    const [classificacao, setClassificacao] = useState('Iniciante');
    const [especialidade, setEspecialidade] = useState('Desconhecido');
    const [contribuicoes, setContribuicoes] = useState(0);
    const [publicacoes, setPublicacoes] = useState([]);
    const [publicacoesCarregadas, setPublicacoesCarregadas] = useState(false); // Estado para controlar o clique no botão
    const [srcImagemPerfil, setSrcImagemPerfil] = useState('')
    const [showComentarios, setShowComentarios] = useState(false);
    const [comentariosPublicacao, setComentarios] = useState([]);
    const [idPublicacaoAtual, setIdPublicacaoAtual] = useState(null); // Estado para armazenar o ID da publicação atual
    const [textoComentario, setTextoComentario] = useState(''); // Estado para armazenar o comentário

    const [popupAbertoId, setPopupAbertoId] = useState(null);

    const [imagem, setImagem] = useState("")

    const idUsuarioLogado = idUsuario;
    const nomeUsuarioLogado = sessionStorage.getItem('nome');

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const response = await api.get(`/usuarios/${idUsuario}`);
                const usuario = response.data;
                setNome(usuario.nome || 'Usuário Desconhecido');
                setEmail(usuario.email || 'E-mail Desconhecido');
                setEspecialidade(formatSubjectName(usuario.especialidade?.materia) || 'Desconhecido');
            } catch (error) {
                console.error("Erro ao carregar dados do usuário:", error);
            }
        };
        fetchUsuario();
    }, [idUsuario]);

    const nomeFormatado = useMemo(() => {
        const nomes = nome.trim().split(' ');
        return nomes.length === 1 ? nomes[0] : `${nomes[0]} ${nomes[nomes.length - 1]}`;
    }, [nome]);

    useEffect(() => {
        const fetchClassificacao = async () => {
            try {
                const response = await api.patch(`/usuarios/classificar-usuario/${idUsuarioLogado}`);
                const nivel = response.data.classificacao.classificacao;
                const classificacoes = {
                    'JUNIOR': 'Júnior',
                    'PLENO': 'Pleno',
                    'SENIOR': 'Sênior',
                    'ESPECIALISTA': 'Especialista'
                };
                setClassificacao(classificacoes[nivel] || 'Iniciante');
            } catch (error) {
                console.error("Erro ao carregar classificação:", error);
            }
        };
        fetchClassificacao();
    }, [idUsuarioLogado]);

    const formatSubjectName = (name) => {
        const subjectNameMap = {
            'MATEMATICA': 'Matemática',
            'HISTORIA': 'História',
            'GEOGRAFIA': 'Geografia',
            'QUIMICA': 'Química',
            'PORTUGUES': 'Português',
            'FISICA': 'Física',
            'BIOLOGIA': 'Biologia',
            'INGLES': 'Inglês',
            'FILOSOFIA': 'Filosofia',
            'SOCIOLOGIA': 'Sociologia',
        };
        return subjectNameMap[name] || name.charAt(0).toUpperCase() + name.slice(1).toLowerCase();
    };

    useEffect(() => {
        const fetchContribuicoes = async () => {
            try {
                const response = await api.get(`/qtd-reacoes-comentario-usuarios/buscar-nivel-de-classificacao-do-usuario/${idUsuarioLogado}`);
                const qtdReacoes = response.data.qtdReacoes;
                setContribuicoes(qtdReacoes >= 1 ? qtdReacoes : 0);
                if (qtdReacoes < 1) setClassificacao('Iniciante');
            } catch (error) {
                console.error("Erro ao carregar contribuições:", error);
            }
        };
        fetchContribuicoes();
    }, [idUsuarioLogado]);

    useEffect(() => {
        async function buscarImagemPerfil() {
            try {
                const response = await api.get(`usuarios/buscar-imagem-perfil/${sessionStorage.getItem("userId")}`);
                console.log(response.data)
                setSrcImagemPerfil(response.data)
                // setSrcImagemPerfil("https://s3-learnlink.s3.us-east-1.amazonaws.com/WIN_20240909_09_30_09_Pro.jpg")
            } catch (error) {
                console.log(error)
            }
        }
        buscarImagemPerfil();
    }, [])

    const fetchPublicacoes = async () => {
        try {
            const response = await api.get(`/publicacoes/usuario/${idUsuarioLogado}`);
            setPublicacoes(response?.data);
            setPublicacoesCarregadas(true); // Atualiza o estado quando as publicações são carregadas
        } catch (error) {
            console.error("Erro ao carregar publicações:", error);
        }
    };

    const listarComentarios = async (id) => {
        try {
            const response = await api.get(`/comentarios/publicacao/${id}`);
            setComentarios(response.data);
            setShowComentarios(true);
            setIdPublicacaoAtual(id); // Define a publicação atual para usar no envio do comentário
            console.log("Comentários da publicação:", response.data);
        } catch (error) {
            console.error("Erro ao buscar comentários:", error);
        }
    };

    // Função para enviar o comentário
    const enviarComentario = async () => {
        // Verificação do tamanho do texto
        if (!textoComentario.trim()) {
            toast.error("Digite um comentário antes de enviar!");
            return;
        }

        if (textoComentario.length < 3 || textoComentario.length > 500) {
            toast.error("O comentário deve ter entre 3 e 500 caracteres.");
            return;
        }

        try {
            const idUsuario = sessionStorage.getItem('userId');
            const response = await api.post(`publicacoes/${idPublicacaoAtual}/comentar`, {
                comentario: textoComentario,
                idUsuario,
            });

            console.log("Comentário enviado com sucesso:", response.data);

            // Limpa o campo de texto após enviar o comentário
            setTextoComentario('');

            // Atualiza os comentários após o envio
            listarComentarios(idPublicacaoAtual);

        } catch (error) {
            console.error("Erro ao enviar comentário:", error);
        }
    };


    const closeComentariosModal = () => {
        setShowComentarios(false);
        setIdPublicacaoAtual(null); // Limpa a publicação atual quando fechar o modal
    };

    // Função para capturar o texto do comentário
    const handleInputComentario = (event) => {
        setTextoComentario(event.target.value);
    };

    const togglePopup = (id) => {
        if (popupAbertoId === id) {
            setPopupAbertoId(null); // Fecha o popup se já estiver aberto
        } else {
            setPopupAbertoId(id); // Abre o popup para o ID correspondente
        }
    };


    const avatar = useMemo(() => generateInitials(nomeFormatado), [nomeFormatado]);

    const [showPopup, setShowPopup] = useState(false);

    return (
        <>
            <Header />
            <div className={styles.perfil}>
                <div className={styles.corFundo}>
                    <div className={styles.userInfo}>
                        <div className={styles.user}>
                            {srcImagemPerfil ? (
                                <img className={styles.imagemPerfil} src={srcImagemPerfil} alt="Imagem de Perfil" />
                            ) : (
                                <span className={styles.avatar}>{avatar}</span>
                            )}  
                            <img src={Lapis} className={styles.iconeLapis} onClick={() => setShowPopup(true)} />
                            <span className={styles.nome}>{nome}</span>
                            <span className={styles.email}>{email}</span>
                        </div>
                        <div className={styles.tooltip}>
                            <Tooltip txt="Ganhará a classificação: Iniciante - 0 interações
                            Júnior - 1 interação
                            Pleno – 30 interações
                            Sênior - 60 interações
                            Especialista – 100 interações"/>
                        </div>
                    </div>
                </div>
                <div className={styles.cards}>
                    <CardPerfil conteudo={classificacao} classificacao="Classificação" />
                    <CardPerfil conteudo={contribuicoes} classificacao="Contribuições" />
                    <CardPerfil conteudo={especialidade} classificacao="Especialidade" />
                </div>
                <div className={styles.atividade}>
                    <CardAtividade idUsuario={idUsuarioLogado} />
                </div>
                <div className={styles.botaoCarregaPublicacoes}>
                    <button onClick={fetchPublicacoes}>Carregar Publicações</button>
                </div>
                <div className={styles.publicacoes}>
                    {publicacoesCarregadas && (
                        publicacoes.length > 0 ? (
                            publicacoes.map((publicacao, index) => (
                                <Publicacao
                                    key={`${publicacao.id}-${index}`}
                                    id={publicacao.id}
                                    nome={publicacao.usuario.nome}
                                    materia={formatSubjectName(publicacao.canal.nome)}
                                    mensagem={publicacao.conteudo}
                                    horario={publicacao.dataHora}
                                    curtidas={publicacao.reacoes.length}
                                    quemCurtiu={publicacao.reacoes.map(reacao => reacao.usuario.nome)}
                                    comentarios={publicacao.quantidadeComentarios}
                                    listarComentarios={() => {/* implementar função para listar comentários */ }}
                                    togglePopup={togglePopup}
                                    popupAbertoId={popupAbertoId} // ajustar conforme necessidade
                                    idUsuarioQuePublicou={publicacao.usuario.id}
                                    origem="perfil"
                                    listarComentariosPerfil={() => listarComentarios(publicacao.id)}
                                />
                            ))
                        ) : (
                            <p className={styles.textoSemPublicacoes}>Nenhuma publicação encontrada</p>
                        )
                    )}
                </div>

                <Modal
                    isOpen={showComentarios}
                    onRequestClose={closeComentariosModal}
                    className={styles.comentariosModal}
                    overlayClassName={styles.comentariosOverlay}
                >
                    <div className={styles.fechar}><img src={fechar} alt="Fechar" onClick={closeComentariosModal} /></div>
                    <div className={styles.listaComentarios}>
                        {comentariosPublicacao.length > 0 ? (
                            comentariosPublicacao.map((comentario) => (
                                <Comentario
                                    key={comentario.id}
                                    id={comentario.id}
                                    nome={comentario.usuario.nome}
                                    mensagem={comentario.comentario}
                                    horario={comentario.dataHora}
                                    quemCurtiu={comentario.reacoes.map(reacao => reacao.usuario.nome)}
                                    idReacao={comentario.reacoes.find(reacao => reacao.usuario.nome === nomeUsuarioLogado)?.id}
                                    curtidas={comentario.reacoes.length}
                                    nomePublicacao={comentario.publicacao.usuario.nome}
                                    idPublicacao={comentario.publicacao.id}
                                    idUsuarioQuePublicou={comentario.usuario.id}
                                />
                            ))
                        ) : (
                            <p className={styles.textoSemComentarios}>Ainda não há comentários nessa publicação<br />Seja o primeiro!</p>
                        )}
                    </div>
                    <div className={styles.postarComentario}>
                        <textarea
                            className={styles.inputComentario}
                            placeholder='Digite aqui...'
                            value={textoComentario} // Conecta o valor ao estado
                            onChange={handleInputComentario} // Atualiza o estado quando o valor muda
                        />
                        <img
                            className={styles.botaoComentar}
                            src={Enviar}
                            alt="Enviar"
                            onClick={enviarComentario} // Função que envia o comentário
                        />
                    </div>
                </Modal>

                {/* Popup para Dropzone */}
                {showPopup && (
                    <div className={styles.blur}>
                        <div className={styles.popup}>
                            <div className={styles.popupContent}>
                                <div className={styles.iconeFechar}>
                                    <img
                                        src={fechar}
                                        onClick={() => setShowPopup(false)}
                                    />
                                </div>
                                <Dropzone origem="usuarios" />
                            </div>
                        </div>
                    </div>
                )}

            </div>
        </>
    );
};


export default Perfil;
