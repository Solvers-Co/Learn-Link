package co.solvers.apilearnlink.service.denuncia;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.domain.denuncia.repository.DenunciaRespository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoCriarDto;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.PublicacoesDenunciadasDto;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.mapper.PublicacoesDenunciadasMapper;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

import java.io.FileWriter;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class DenunciaService {

    private final DenunciaRespository denunciaRespository;
    private final PublicacaoService publicacaoService;
    private final ComentarioService comentarioService;
    private final UsuarioService usuarioService;

    public Denuncia denunciarPublicacao(int idPublicacao, DenunciaPublicacaoCriarDto denunciaPublicacaoCriarDto) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);
        Usuario usuario = usuarioService.buscarPorId(denunciaPublicacaoCriarDto.getIdUsuario());

        if (usuario.getId() == publicacao.getUsuario().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário não pode denúnciar sua própria publicação");
        }

        if (verificaSeUsuarioJaDenunciouPublicacao(publicacao, usuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário já denunciou essa publicação");
        }

        Denuncia denuncia = new Denuncia();

        denuncia.setPublicacao(publicacao);
        denuncia.setUsuario(usuario);

        return denunciaRespository.save(denuncia);
    }

    public Denuncia denunciarComentario(int idComentario, DenunciaComentarioCriarDto denunciaComentarioCriarDto) {
        Comentario comentario = comentarioService.buscarPorId(idComentario);
        Usuario usuario = usuarioService.buscarPorId(denunciaComentarioCriarDto.getIdUsuario());

        if (usuario.getId() == comentario.getUsuario().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário não pode denúnciar seu próprio comentário");
        }

        if (verificaSeUsuarioJaDenunciouComentario(comentario, usuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário já denunciou esse comentário");
        }

        Denuncia denuncia = new Denuncia();

        denuncia.setComentario(comentario);
        denuncia.setUsuario(usuario);

        return denunciaRespository.save(denuncia);
    }

    private Boolean verificaSeUsuarioJaDenunciouPublicacao(Publicacao publicacao, Usuario usuario) {
        Optional<Denuncia> denuncia = denunciaRespository.findByPublicacaoAndUsuario(publicacao, usuario);

        if (denuncia.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verificaSeUsuarioJaDenunciouComentario(Comentario comentario, Usuario usuario) {
        Optional<Denuncia> denuncia = denunciaRespository.findByComentarioAndUsuario(comentario, usuario);

        if (denuncia.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public List<PublicacoesDenunciadas> buscaPublicacoesDenunciadas() {
        List<PublicacoesDenunciadas> publicacoesDenunciadas = denunciaRespository.buscaPublicacoesDenunciadas();
        return publicacoesDenunciadas;
    }

    public Resource gravaDenuncias(String tipo) throws IOException {
        List<?> denuncias;

        // Verifica o tipo de denúncia solicitado e busca as respectivas denúncias
        if ("publicacao".equalsIgnoreCase(tipo)) {
            denuncias = denunciaRespository.buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = denunciaRespository.buscaComentariosDenunciados();
        } else {
            throw new IllegalArgumentException("Tipo de denúncia inválido");
        }

        if (denuncias.isEmpty()) {
            return null;
        }

        String nomeArquivo = "denuncias_" + tipo + ".csv";
        Path tempfile = Files.createTempFile(nomeArquivo, ".csv");

        try (FileWriter arq = new FileWriter(tempfile.toFile());
             Formatter saida = new Formatter(arq)) {

            // Gera o CSV de acordo com o tipo de denúncia
            if ("publicacao".equalsIgnoreCase(tipo)) {
                for (PublicacoesDenunciadas denuncia : (List<PublicacoesDenunciadas>) denuncias) {
                    saida.format("%s;%s;%d\n",
                            denuncia.getPublicacao().getUsuario().getNome(),
                            denuncia.getPublicacao().getConteudo(),
                            denuncia.getQuantidadeDenuncias());
                }
            } else {
                for (ComentariosDenunciados denuncia : (List<ComentariosDenunciados>) denuncias) {
                    saida.format("%s;%s;%d\n",
                            denuncia.getComentario().getUsuario().getNome(),
                            denuncia.getComentario().getComentario(),
                            denuncia.getQuantidadeDenuncias());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
            throw e;
        }

        // Retorna o recurso gerado a partir do arquivo CSV
        return new UrlResource(tempfile.toUri());
    }

    public Resource gravaTxtDenuncias(String tipo) throws IOException {
        List<?> denuncias;

        // Verifica o tipo de denúncia solicitado
        if ("publicacao".equalsIgnoreCase(tipo)) {
            denuncias = denunciaRespository.buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = denunciaRespository.buscaComentariosDenunciados();
        } else {
            throw new IllegalArgumentException("Tipo de denúncia inválido");
        }

        if (denuncias.isEmpty()) {
            return null;
        }

        String nomeArquivo = "denuncias_" + tipo + ".txt";
        Path tempfile = Files.createTempFile(nomeArquivo, ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile.toFile()))) {
            // HEADER
            String header = "00DENUNCIA";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            header += "01";

            writer.write(header);
            writer.newLine();

            // Dados das denúncias
            String corpo = "01";
            if ("publicacao".equalsIgnoreCase(tipo)) {
                for (PublicacoesDenunciadas denuncia : (List<PublicacoesDenunciadas>) denuncias) {
                    String nome = formatarCampo(denuncia.getPublicacao().getUsuario().getNome(), 45);
                    String conteudo = formatarCampo(denuncia.getPublicacao().getConteudo(), 256);
                    String qtdDenuncias = formatarCampo(String.valueOf(denuncia.getQuantidadeDenuncias()), 3);
                    writer.write(String.format("%s%s%s%s\n", corpo, nome, conteudo, qtdDenuncias));
                }
            } else {
                for (ComentariosDenunciados denuncia : (List<ComentariosDenunciados>) denuncias) {
                    String nome = formatarCampo(denuncia.getComentario().getUsuario().getNome(), 45);
                    String conteudo = formatarCampo(denuncia.getComentario().getComentario(), 256);
                    String qtdDenuncias = formatarCampo(String.valueOf(denuncia.getQuantidadeDenuncias()), 3);
                    writer.write(String.format("%s%s%s%s\n", corpo, nome, conteudo, qtdDenuncias));
                }
            }
            // TRAILER
            String trailer = "02";
            trailer += String.format("%010d", denuncias.size());
            writer.write(trailer);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
            throw e;
        }

        return new UrlResource(tempfile.toUri());
    }
    
    private String formatarCampo(String valor, int tamanho) {
        if (valor == null) {
            valor = "";
        }
        if (valor.length() > tamanho) {
            return valor.substring(0, tamanho); // Trunca se for maior
        } else {
            return String.format("%-" + tamanho + "s", valor); // Preenche com espaços se for menor
        }
    }

    public Resource gravaJsonDenuncias(String tipo) throws IOException {
        List<?> denuncias;

        // Verifica o tipo de denúncia solicitado
        if ("publicacao".equalsIgnoreCase(tipo)) {
            denuncias = denunciaRespository.buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = denunciaRespository.buscaComentariosDenunciados();
        } else {
            throw new IllegalArgumentException("Tipo de denúncia inválido");
        }

        if (denuncias.isEmpty()) {
            return null;
        }

        String nomeArquivo = "denuncias_" + tipo + ".json";
        Path tempfile = Files.createTempFile(nomeArquivo, ".json");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile.toFile()))) {
            // Cria um JSON array
            writer.write("[\n");

            if ("publicacao".equalsIgnoreCase(tipo)) {
                for (int i = 0; i < denuncias.size(); i++) {
                    PublicacoesDenunciadas denuncia = (PublicacoesDenunciadas) denuncias.get(i);
                    String json = String.format("  {\n" +
                                    "    \"nome\": \"%s\",\n" +
                                    "    \"conteudo\": \"%s\",\n" +
                                    "    \"quantidadeDenuncias\": %d\n" +
                                    "  }",
                            escapeJson(denuncia.getPublicacao().getUsuario().getNome()),
                            escapeJson(denuncia.getPublicacao().getConteudo()),
                            denuncia.getQuantidadeDenuncias());

                    writer.write(json);
                    if (i < denuncias.size() - 1) {
                        writer.write(",\n"); // Adiciona vírgula entre os objetos
                    } else {
                        writer.write("\n"); // Último objeto não precisa de vírgula
                    }
                }
            } else {
                for (int i = 0; i < denuncias.size(); i++) {
                    ComentariosDenunciados denuncia = (ComentariosDenunciados) denuncias.get(i);
                    String json = String.format("  {\n" +
                                    "    \"nome\": \"%s\",\n" +
                                    "    \"conteudo\": \"%s\",\n" +
                                    "    \"quantidadeDenuncias\": %d\n" +
                                    "  }",
                            escapeJson(denuncia.getComentario().getUsuario().getNome()),
                            escapeJson(denuncia.getComentario().getComentario()),
                            denuncia.getQuantidadeDenuncias());

                    writer.write(json);
                    if (i < denuncias.size() - 1) {
                        writer.write(",\n"); // Adiciona vírgula entre os objetos
                    } else {
                        writer.write("\n"); // Último objeto não precisa de vírgula
                    }
                }
            }

            writer.write("]"); // Fecha o JSON array
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
            throw e;
        }

        return new UrlResource(tempfile.toUri());
    }

    private String escapeJson(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.replace("\\", "\\\\") // Escapa barras invertidas
                .replace("\"", "\\\""); // Escapa aspas duplas
    }

    public List<ComentariosDenunciados> buscaComentariosDenunciados() {
        List<ComentariosDenunciados> comentariosDenunciados = denunciaRespository.buscaComentariosDenunciados();
        return comentariosDenunciados;
    }

    @Transactional
    public void removerDenunciasPublicacao(Integer idPublicacao) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);

        denunciaRespository.deleteAllByPublicacao(publicacao);
    }

    @Transactional
    public void removerDenunciasComentario(Integer idComentario) {
        Comentario comentario = comentarioService.buscarPorId(idComentario);

        denunciaRespository.deleteAllByComentario(comentario);
    }

}
