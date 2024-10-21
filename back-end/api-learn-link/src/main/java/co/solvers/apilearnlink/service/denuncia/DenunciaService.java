package co.solvers.apilearnlink.service.denuncia;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.ComentarioStatus;
import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.domain.denuncia.repository.DenunciaRespository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoCriarDto;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoDenunciadasListagemComIa;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.mapper.PublicacoesDenunciadasMapper;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        if (Objects.equals(usuario.getId(), publicacao.getUsuario().getId())) {
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

        if (Objects.equals(usuario.getId(), comentario.getUsuario().getId())) {
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

    public Boolean verificaSeUsuarioJaDenunciouPublicacao(Publicacao publicacao, Usuario usuario) {
        Optional<Denuncia> denuncia = denunciaRespository.findByPublicacaoAndUsuario(publicacao, usuario);

        return denuncia.isPresent();
    }

    public Boolean verificaSeUsuarioJaDenunciouComentario(Comentario comentario, Usuario usuario) {
        Optional<Denuncia> denuncia = denunciaRespository.findByComentarioAndUsuario(comentario, usuario);

        if (denuncia.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public List<PublicacoesDenunciadas> buscaPublicacoesDenunciadas() {
        return denunciaRespository.buscaPublicacoesDenunciadas(PublicacaoStatus.ATIVO);
    }

    public Resource gravaDenuncias(String tipo) throws IOException {
        List<?> denuncias;

        // Verifica o tipo de denúncia solicitado e busca as respectivas denúncias
        if ("publicacao".equalsIgnoreCase(tipo)) {
            denuncias = buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = buscaComentariosDenunciados();
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
            denuncias = buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = buscaComentariosDenunciados();
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
            denuncias = buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = buscaComentariosDenunciados();
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

    public Resource gravaXmlDenuncias(String tipo) throws IOException {
        List<?> denuncias;

        // Verifica o tipo de denúncia solicitado
        if ("publicacao".equalsIgnoreCase(tipo)) {
            denuncias = buscaPublicacoesDenunciadas();
        } else if ("comentario".equalsIgnoreCase(tipo)) {
            denuncias = buscaComentariosDenunciados();
        } else {
            throw new IllegalArgumentException("Tipo de denúncia inválido");
        }

        if (denuncias.isEmpty()) {
            return null;
        }

        String nomeArquivo = "denuncias_" + tipo + ".xml";
        Path tempfile = Files.createTempFile(nomeArquivo, ".xml");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempfile.toFile()))) {
            // Escreve o cabeçalho do XML
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<denuncias>\n");

            if ("publicacao".equalsIgnoreCase(tipo)) {
                for (PublicacoesDenunciadas denuncia : (List<PublicacoesDenunciadas>) denuncias) {
                    writer.write("  <publicacao>\n");
                    writer.write(String.format("    <nome>%s</nome>\n", escapeXml(denuncia.getPublicacao().getUsuario().getNome())));
                    writer.write(String.format("    <conteudo>%s</conteudo>\n", escapeXml(denuncia.getPublicacao().getConteudo())));
                    writer.write(String.format("    <quantidadeDenuncias>%d</quantidadeDenuncias>\n", denuncia.getQuantidadeDenuncias()));
                    writer.write("  </publicacao>\n");
                }
            } else {
                for (ComentariosDenunciados denuncia : (List<ComentariosDenunciados>) denuncias) {
                    writer.write("  <comentario>\n");
                    writer.write(String.format("    <nome>%s</nome>\n", escapeXml(denuncia.getComentario().getUsuario().getNome())));
                    writer.write(String.format("    <conteudo>%s</conteudo>\n", escapeXml(denuncia.getComentario().getComentario())));
                    writer.write(String.format("    <quantidadeDenuncias>%d</quantidadeDenuncias>\n", denuncia.getQuantidadeDenuncias()));
                    writer.write("  </comentario>\n");
                }
            }

            // Escreve o rodapé do XML
            writer.write("</denuncias>\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
            throw e;
        }

        return new UrlResource(tempfile.toUri());
    }

    private String escapeXml(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.replace("&", "&amp;") // Escapa o caractere &
                .replace("<", "&lt;") // Escapa o caractere <
                .replace(">", "&gt;") // Escapa o caractere >
                .replace("\"", "&quot;") // Escapa aspas duplas
                .replace("'", "&apos;"); // Escapa aspas simples
    }

    public List<ComentariosDenunciados> buscaComentariosDenunciados() {
        return denunciaRespository.buscaComentariosDenunciados(ComentarioStatus.ATIVO);
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

    public List<PublicacaoDenunciadasListagemComIa> buscaPublicacoesDenunciadasOrdenadasPorIa() {
        // Busca publicações denunciadas com status ativo
        List<PublicacoesDenunciadas> publicacoesDenunciadas = denunciaRespository.buscaPublicacoesDenunciadas(PublicacaoStatus.ATIVO);

        // Mapeia as publicações para a lista de saída
        List<PublicacaoDenunciadasListagemComIa> publicacaoDenunciadasListagemComIa = PublicacoesDenunciadasMapper.toIaList(publicacoesDenunciadas);

        // Prepara a lista de conteúdos para enviar à IA
        List<String> requestIa = new ArrayList<>();
        for (PublicacaoDenunciadasListagemComIa obj : publicacaoDenunciadasListagemComIa) {
            requestIa.add(obj.getPublicacao().getConteudo());
        }

        // Chamar o serviço de IA para classificar os conteúdos das publicações
        ResponseEntity<List<Map<String, Object>>> respostaIa = classificarPublicacoes(requestIa);

        // Verificar se a resposta da IA foi bem-sucedida
        if (respostaIa.getStatusCode().is2xxSuccessful() && respostaIa.getBody() != null) {
            List<Map<String, Object>> publicacoesClassificadas = respostaIa.getBody();

            // Associar as classificações retornadas às publicações
            for (int i = 0; i < publicacaoDenunciadasListagemComIa.size(); i++) {
                String classificacao = (String) publicacoesClassificadas.get(i).get("classificacao");
                // Ajuste aqui para garantir que a classificação seja "Nocivo" ou "Não nocivo"
                if ("nocivo".equalsIgnoreCase(classificacao)) {
                    publicacaoDenunciadasListagemComIa.get(i).setClassificacao("Nocivo");
                } else {
                    publicacaoDenunciadasListagemComIa.get(i).setClassificacao("Não nocivo");
                }
            }
        } else {
            // Em caso de erro, adicionar uma classificação padrão
            for (PublicacaoDenunciadasListagemComIa obj : publicacaoDenunciadasListagemComIa) {
                obj.setClassificacao("Não classificado");
            }
        }

        // Ordenar a lista com base na classificação: Nocivo primeiro
        publicacaoDenunciadasListagemComIa.sort((a, b) -> {
            if ("Nocivo".equals(a.getClassificacao()) && "Não nocivo".equals(b.getClassificacao())) {
                return -1; // a deve vir antes
            } else if ("Não nocivo".equals(a.getClassificacao()) && "Nocivo".equals(b.getClassificacao())) {
                return 1; // b deve vir antes
            }
            return 0; // sem alteração na ordem
        });

        // Retornar a lista de publicações com classificações
        return publicacaoDenunciadasListagemComIa;
    }


    private ResponseEntity<List<Map<String, Object>>> classificarPublicacoes(List<String> publicacoes) {
        String promptSistema = "Você receberá uma lista de publicações. Para cada publicação, classifique-a como 'Nocivo' ou 'Não nocivo' com base no conteúdo. " +
                "'Nocivo' indica conteúdo que é ofensivo, inapropriado ou desinformativo, enquanto 'Não nocivo' indica que o conteúdo é aceitável para discussões. " +
                "Por favor, responda apenas com 'Nocivo' ou 'Não nocivo' sem nenhuma explicação adicional ou informação extra. " +
                "Certifique-se de que suas respostas sejam concisas e diretas.";

        var modelo = "gpt-3.5-turbo";
        int tamanhoRespostaEsperada = 2048;
        List<Map<String, Object>> resultados = new ArrayList<>();
        String token = "OPEN_AI_KEY";
        var service = new OpenAiService(token, Duration.ofSeconds(60));

        // Formatar o prompt com a lista de publicações
        StringBuilder promptBuilder = new StringBuilder(promptSistema);
        promptBuilder.append("\n\nPublicações:\n");

        for (int i = 0; i < publicacoes.size(); i++) {
            String conteudo = publicacoes.get(i);
            promptBuilder.append(i + 1).append(". Conteúdo: ").append(conteudo).append("\n");
        }

        promptBuilder.append("\nClassifique cada publicação como 'nocivo' ou 'não nocivo'.");

        // Criar a mensagem para o modelo
        var mensagens = List.of(
                new ChatMessage(ChatMessageRole.SYSTEM.value(), promptSistema),
                new ChatMessage(ChatMessageRole.USER.value(), promptBuilder.toString())
        );

        var request = ChatCompletionRequest.builder()
                .model(modelo)
                .maxTokens(tamanhoRespostaEsperada)
                .messages(mensagens)
                .n(1)
                .build();

        try {
            var response = service.createChatCompletion(request);
            var respostaBot = response.getChoices().get(0).getMessage().getContent();

            // Separar a resposta em linhas e associar a classificação de volta às publicações
            String[] classificacoes = respostaBot.split("\n");
            for (int i = 0; i < publicacoes.size(); i++) {
                Map<String, Object> publicacaoMap = new HashMap<>();
                if (i < classificacoes.length) {
                    publicacaoMap.put("classificacao", classificacoes[i].trim());
                } else {
                    publicacaoMap.put("classificacao", "Não classificado");
                }
                resultados.add(publicacaoMap);
            }

            return ResponseEntity.ok(resultados);

        } catch (Exception e) {
            System.err.println("Erro ao processar a requisição para a API do OpenAI: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
