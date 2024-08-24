package co.solvers.apilearnlink.service.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.repository.PublicacaoRepository;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.tipopublicacao.repository.TipoPublicacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.InvalidoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.canal.CanalService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PublicacaoServiceTest {

    @InjectMocks
    private PublicacaoService service;

    @InjectMocks
    private CanalService canalService;

    @Mock
    private CanalService canalRepository;

    @Mock
    private PublicacaoRepository repository;

    @Mock
    private TipoPublicacaoRepository tipoPublicacaoRepository;

    @Mock
    private UsuarioService usuarioService;

    private PublicacaoCriacaoRequestDto publicacaoCriacaoRequestDto;
    private TipoPublicacao tipoPublicacao;
    private Usuario usuario;
    private Publicacao publicacao;

    @BeforeEach
    void setUp() {
        publicacaoCriacaoRequestDto = new PublicacaoCriacaoRequestDto();
        publicacaoCriacaoRequestDto.setIdTipoPublicacao(1);
        publicacaoCriacaoRequestDto.setIdUsuario(1L);

        tipoPublicacao = new TipoPublicacao();
        tipoPublicacao.setId(1);

        usuario = new Usuario();
        usuario.setId(1L);

        publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setTipoPublicacao(tipoPublicacao);
        publicacao.setUsuario(usuario);
    }

    @Test
    @DisplayName("Deve criar uma publicação")
    void testCriarPublicacao() {
        Mockito.when(tipoPublicacaoRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(tipoPublicacao));
        Mockito.when(usuarioService.buscarPorId(Mockito.any(Long.class))).thenReturn(usuario);
        Mockito.when(repository.save(Mockito.any(Publicacao.class))).thenReturn(publicacao);
        Publicacao result = service.criarPublicacao(publicacaoCriacaoRequestDto);

        assertEquals(publicacao, result);
    }

    @Test
    @DisplayName("Deve retornar lista com 2 publicações")
    public void retornarListaDePublicacoes() {

        Publicacao pub1 = new Publicacao();
        pub1.setId(1);
        pub1.setConteudo("Primeira publicação");
        pub1.setDataHora(LocalDateTime.now().minusDays(1));

        Publicacao pub2 = new Publicacao();
        pub2.setId(2);
        pub2.setConteudo("Segunda publicação");
        pub2.setDataHora(LocalDateTime.now());

        List<Publicacao> publicacoes = List.of(pub2, pub1);

        Mockito.when(repository.findAllByOrderByDataHoraDesc()).thenReturn(publicacoes);

        List<Publicacao> publicacaoRetorno = service.listarMaisRecentes();

        assertEquals(publicacoes.size(), publicacaoRetorno.size());
        assertEquals(2, publicacaoRetorno.size());
        assertFalse(publicacaoRetorno.isEmpty());
        assertEquals(publicacoes.get(0).getConteudo(), publicacaoRetorno.get(0).getConteudo());

        Mockito.verify(repository, Mockito.times(1)).findAllByOrderByDataHoraDesc();
    }


    @Test
    @DisplayName("Deve listar todas as publicações de um tipo determinado")
    void testListarPorTipo() {
        List<Publicacao> publicacoes = Arrays.asList(publicacao, publicacao);
        Mockito.when(repository.findAllByTipoPublicacaoTipoOrderByDataHoraDesc(Mockito.any(String.class))).thenReturn(publicacoes);

        List<Publicacao> result = service.listarPorTipo("COMUM");

        assertEquals(2, result.size());
        assertEquals(publicacao, result.get(0));
    }

    @Test
    @DisplayName("Deve retornar lista vazia")
    void testListarPorTipoVazio() {
        Mockito.when(repository.findAllByTipoPublicacaoTipoOrderByDataHoraDesc(Mockito.any(String.class))).thenReturn(Collections.emptyList());

        List<Publicacao> result = service.listarPorTipo("COMUM");

        assertEquals(0, result.size());
    }


    @Test
    @DisplayName("Deve retornar uma publicação pelo id")
    void retornaPublicacaoPeloId() {

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Primeira publicação");
        publicacao.setDataHora(LocalDateTime.now());

        Integer idBusca = 1;

        Mockito.when(repository.findById(idBusca)).thenReturn(Optional.of(publicacao));

        Publicacao publicacaoEncontrada = service.listarPorId(idBusca);

        assertEquals(publicacaoEncontrada.getId(), idBusca);

        //O método findById é chamado 2 vezes, uma vez em listarPorId e outra vez em verificaIdVazio
        Mockito.verify(repository, Mockito.times(2)).findById(idBusca);
    }


    @Test
    @DisplayName("Deve editar o conteúdo da publicação")
    void testEditarConteudo() {

        publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Conteúdo original");

        Canal canal = new Canal();
        canal.setId(1);
        canal.setNome("História");

        publicacao.setCanal(canal);

        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(publicacao));
        Mockito.when(repository.save(Mockito.any(Publicacao.class))).thenReturn(publicacao);

        Canal novaMateria = new Canal();
        novaMateria.setId(2);
        novaMateria.setNome("Matemática");

        Publicacao result = service.editarConteudo(1, "Novo conteúdo",2);

        assertEquals("Novo conteúdo", result.getConteudo());
        assertEquals("Matemática", result.getCanal().getNome());
    }

    @Test
    @DisplayName("Deve deletar corretmente uma publicação com id correto")
    void testDeletarPublicacao() {
        // Configura o mock do repositório para não fazer nada quando o método deleteById é chamado
        Mockito.doNothing().when(repository).deleteById(Mockito.anyInt());

        // Simula encontrar uma publicação com ID válido
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Publicacao()));

        // Chama o método deletar do serviço com um ID válido
        service.deletar(1);

        // Verifica se o método deleteById do repositório foi chamado com o ID correto
        Mockito.verify(repository).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar uma publicação com ID inválido")
    void testDeletarPublicacaoIdVazio() {
        // Verifica se uma exceção NaoEncontradoException é lançada quando o ID é nulo
        assertThrows(NaoEncontradoException.class, () -> {
            service.deletar(null);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar uma publicação não encontrada")
    void testDeletarPublicacaoNaoEncontrada() {
        // Configura o mock do repositório para lançar uma exceção quando o método findById não encontra uma publicação
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // Verifica se a exceção NaoEncontradoException é lançada quando o método deletar é chamado com um ID inválido
        assertThrows(NaoEncontradoException.class, () -> {
            service.deletar(999);
        });
    }

    @Test
    void testListarPorPalavraChaveValid() {
        // Configura o mock do repositório para retornar uma lista de publicações
        Publicacao publicacao = new Publicacao();
        publicacao.setConteudo("Conteúdo com a palavra-chave DIFICULDADE");
        Mockito.when(repository.findByConteudoLikePalavrachaveOrderByDataHoraDesc(Mockito.anyString()))
                .thenReturn(List.of(publicacao));

        // Chama o método listarPorPalavraChave do serviço com uma palavra-chave válida
        List<Publicacao> result = service.listarPorPalavraChave("dificuldade");

        // Verifica se a lista de publicações retornada contém a publicação esperada
        assertEquals(1, result.size());
        assertEquals("Conteúdo com a palavra-chave DIFICULDADE", result.get(0).getConteudo());
    }

    @Test
    void testListarPorPalavraChaveVazio() {
        // Verifica se uma exceção InvalidoException é lançada quando a palavra-chave é em branco
        assertThrows(InvalidoException.class, () -> {
            service.listarPorPalavraChave("");
        });
    }

    @Test
    void testListarPorPalavraChaveNaoEncontrada() {
        // Configura o mock do repositório para retornar uma lista vazia
        Mockito.when(repository.findByConteudoLikePalavrachaveOrderByDataHoraDesc(Mockito.anyString()))
                .thenReturn(Collections.emptyList());

        // Chama o método listarPorPalavraChave do serviço com uma palavra-chave que não corresponde a nenhuma publicação
        List<Publicacao> result = service.listarPorPalavraChave("inexistente");

        // Verifica se a lista de publicações retornada está vazia
        assertTrue(result.isEmpty());
    }

}
