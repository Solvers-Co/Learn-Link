package co.solvers.apilearnlink.service.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.publicacao.repository.PublicacaoRepository;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.tipopublicacao.repository.TipoPublicacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.InvalidoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.exception.PublicacaoJaArquivadaException;
import co.solvers.apilearnlink.service.canal.CanalService;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import org.springframework.data.domain.*;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PublicacaoServiceTest {

    @InjectMocks
    private PublicacaoService service;

    @Mock
    private CanalService canalService;

    @Mock
    private CanalRepository canalRepository;

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
    @DisplayName("Deve retornar lista paginada com 2 publicações mais recentes")
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
        Page<Publicacao> publicacoesPage = new PageImpl<>(publicacoes);

        Pageable pageable = PageRequest.of(0, 2, Sort.by("dataHora").descending());

        Mockito.when(repository.findByStatus(pageable, PublicacaoStatus.ATIVO)).thenReturn(publicacoesPage);

        Page<Publicacao> publicacaoRetorno = service.listarMaisRecentesPaginado(pageable);

        assertEquals(publicacoes.size(), publicacaoRetorno.getContent().size());
        assertEquals(2, publicacaoRetorno.getContent().size());
        assertFalse(publicacaoRetorno.isEmpty());
        assertEquals(publicacoes.get(0).getConteudo(), publicacaoRetorno.getContent().get(0).getConteudo());

        Mockito.verify(repository, Mockito.times(1)).findByStatus(pageable, PublicacaoStatus.ATIVO);
    }


//    @Test
//    @DisplayName("Deve listar todas as publicações de um tipo determinado")
//    void testListarPorTipo() {
//        List<Publicacao> publicacoes = Arrays.asList(publicacao, publicacao);
//        Mockito.when(repository.findAllByTipoPublicacaoTipoOrderByDataHoraDesc(Mockito.any(String.class))).thenReturn(publicacoes);
//
//        List<Publicacao> result = service.listarPorTipo("COMUM");
//
//        assertEquals(2, result.size());
//        assertEquals(publicacao, result.get(0));
//    }
//
//    @Test
//    @DisplayName("Deve retornar lista vazia")
//    void testListarPorTipoVazio() {
//        Mockito.when(repository.findAllByTipoPublicacaoTipoOrderByDataHoraDesc(Mockito.any(String.class))).thenReturn(Collections.emptyList());
//
//        List<Publicacao> result = service.listarPorTipo("COMUM");
//
//        assertEquals(0, result.size());
//    }


//    @Test
//    @DisplayName("Deve retornar uma publicação pelo id")
//    void retornaPublicacaoPeloId() {
//
//        Publicacao publicacao = new Publicacao();
//        publicacao.setId(1);
//        publicacao.setConteudo("Primeira publicação");
//        publicacao.setDataHora(LocalDateTime.now());
//
//        Integer idBusca = 1;
//
//        Mockito.when(repository.findById(idBusca)).thenReturn(Optional.of(publicacao));
//
//        Publicacao publicacaoEncontrada = service.listarPorId(idBusca);
//
//        assertEquals(publicacao.getId(), publicacaoEncontrada.getId());
//        assertEquals(publicacao.getConteudo(), publicacaoEncontrada.getConteudo());
//        assertEquals(publicacao.getDataHora(), publicacaoEncontrada.getDataHora());
//
//        // O método findById é chamado 2 vezes, uma vez em listarPorId e outra vez em verificaIdVazio
//        Mockito.verify(repository, Mockito.times(2)).findById(idBusca);
//    }

    @Test
    @DisplayName("Deve retornar uma publicação pelo id")
    void testListarPorId() {
        int idPublicacao = 1;
        Publicacao publicacao = new Publicacao();
        publicacao.setId(idPublicacao);
        publicacao.setStatus(PublicacaoStatus.ATIVO);

        Mockito.when(repository.findById(idPublicacao)).thenReturn(Optional.of(publicacao));

        Publicacao result = service.listarPorId(idPublicacao);

        assertEquals(publicacao, result);
        Mockito.verify(repository, Mockito.times(3)).findById(idPublicacao); // Called twice: listarPorId and verificaIdVazio
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar listar publicação com id não encontrado")
    void testListarPorIdNaoEncontrado() {
        int idPublicacao = 1;

        Mockito.when(repository.findById(idPublicacao)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.listarPorId(idPublicacao);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar listar publicação inativa")
    void testListarPorIdInativa() {
        int idPublicacao = 1;
        Publicacao publicacao = new Publicacao();
        publicacao.setId(idPublicacao);
        publicacao.setStatus(PublicacaoStatus.EXCLUIDO);

        Mockito.when(repository.findById(idPublicacao)).thenReturn(Optional.of(publicacao));

        assertThrows(InvalidoException.class, () -> {
            service.listarPorId(idPublicacao);
        });
    }


    @Test
    @DisplayName("Deve editar o conteúdo da publicação")
    void testEditarConteudo() {

//    Canal canal = new Canal();
//    canal.setId(1);
//    canal.setNome("História");

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Conteúdo original");
        publicacao.setStatus(PublicacaoStatus.ATIVO);
        publicacao.setDataHora(LocalDateTime.now());
        publicacao.setCanal(new Canal() {{
            setId(1);
            setNome("História");
        }});


        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(publicacao));
        Mockito.when(repository.save(Mockito.any(Publicacao.class))).thenReturn(publicacao);

        Canal novoCanal = new Canal();
        novoCanal.setId(2);
        novoCanal.setNome("Matemática");

        Mockito.when(canalService.buscarPorId(Mockito.eq(1))).thenReturn(novoCanal);

        Publicacao result = service.editarConteudo(1, "Novo conteúdo", "Matemática");

        assertEquals("Novo conteúdo", result.getConteudo());
        assertEquals("Matemática", result.getCanal().getNome());

        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(1)).save(publicacao);
        Mockito.verify(canalService, Mockito.times(1)).buscarPorId(1);
    }

    @Test
    @DisplayName("Deve deletar corretamente uma publicação com id correto")
    void testDeletarPublicacao() {
        // Simula encontrar uma publicação com ID válido
        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setStatus(PublicacaoStatus.ATIVO);

        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(publicacao));
        Mockito.when(repository.save(Mockito.any(Publicacao.class))).thenReturn(publicacao);

        // Chama o método deletar do serviço com um ID válido
        service.deletar(1);

        // Verifica se o status da publicação foi atualizado para EXCLUIDO
        assertEquals(PublicacaoStatus.EXCLUIDO, publicacao.getStatus());

        // Verifica se o método findById e save do repositório foram chamados com os parâmetros corretos
        Mockito.verify(repository, Mockito.times(3)).findById(1);
        Mockito.verify(repository, Mockito.times(1)).save(publicacao);
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
    @DisplayName("Deve listar publicações por palavra-chave válida")
    void testListarPorPalavraChaveValid() {
        // Configura o mock do repositório para retornar uma página de publicações
        Publicacao publicacao = new Publicacao();
        publicacao.setConteudo("Conteúdo com a palavra-chave DIFICULDADE");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("dataHora").descending());
        Page<Publicacao> publicacoesPage = new PageImpl<>(List.of(publicacao));

        Mockito.when(repository.findByConteudoLikePalavrachaveAndStatusOrderByDataHoraDesc(Mockito.anyString(), Mockito.eq(PublicacaoStatus.ATIVO), Mockito.eq(pageable)))
                .thenReturn(publicacoesPage);

        // Chama o método listarPorPalavraChavePaginado do serviço com uma palavra-chave válida
        Page<Publicacao> result = service.listarPorPalavraChavePaginado("dificuldade", pageable);

        // Verifica se a página de publicações retornada contém a publicação esperada
        assertEquals(1, result.getContent().size());
        assertEquals("Conteúdo com a palavra-chave DIFICULDADE", result.getContent().get(0).getConteudo());
    }

    @Test
    @DisplayName("Deve lançar exceção ao listar publicações com palavra-chave vazia")
    void testListarPorPalavraChaveVazio() {
        // Verifica se uma exceção InvalidoException é lançada quando a palavra-chave é em branco
        assertThrows(InvalidoException.class, () -> {
            service.listarPorPalavraChavePaginado("", PageRequest.of(0, 10));
        });
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao listar publicações com palavra-chave não encontrada")
    void testListarPorPalavraChaveNaoEncontrada() {
        // Configura o mock do repositório para retornar uma página vazia
        Pageable pageable = PageRequest.of(0, 10, Sort.by("dataHora").descending());
        Page<Publicacao> publicacoesPage = Page.empty(pageable);

        Mockito.when(repository.findByConteudoLikePalavrachaveAndStatusOrderByDataHoraDesc(Mockito.anyString(), Mockito.eq(PublicacaoStatus.ATIVO), Mockito.eq(pageable)))
                .thenReturn(publicacoesPage);

        // Chama o método listarPorPalavraChavePaginado do serviço com uma palavra-chave que não corresponde a nenhuma publicação
        Page<Publicacao> result = service.listarPorPalavraChavePaginado("inexistente", pageable);

        // Verifica se a página de publicações retornada está vazia
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve arquivar uma publicação corretamente")
    void testArquivarPublicacao() {
        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setStatus(PublicacaoStatus.ATIVO);

        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(publicacao));
        Mockito.when(repository.save(Mockito.any(Publicacao.class))).thenReturn(publicacao);

        Publicacao result = service.arquivarPublicacao(1);

        assertEquals(PublicacaoStatus.ARQUIVADO, result.getStatus());
        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(1)).save(publicacao);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar arquivar uma publicação com ID inválido")
    void testArquivarPublicacaoIdInvalido() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.arquivarPublicacao(999);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar arquivar uma publicação já arquivada")
    void testArquivarPublicacaoJaArquivada() {
        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setStatus(PublicacaoStatus.ARQUIVADO);

        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(publicacao));

        assertThrows(PublicacaoJaArquivadaException.class, () -> {
            service.arquivarPublicacao(1);
        });
    }

    @Test
    @DisplayName("Deve retornar lista paginada de publicações para um canal válido")
    void testListarPublicacoesPorCanalValid() {
        Long canalId = 1L;

        Publicacao pub1 = new Publicacao();
        pub1.setId(1);
        pub1.setConteudo("Primeira publicação");
        pub1.setDataHora(LocalDateTime.now().minusDays(1));
        pub1.setStatus(PublicacaoStatus.ATIVO);

        Publicacao pub2 = new Publicacao();
        pub2.setId(2);
        pub2.setConteudo("Segunda publicação");
        pub2.setDataHora(LocalDateTime.now());
        pub2.setStatus(PublicacaoStatus.ATIVO);

        List<Publicacao> publicacoes = List.of(pub2, pub1);
        Page<Publicacao> publicacoesPage = new PageImpl<>(publicacoes);

        Pageable pageable = PageRequest.of(0, 2, Sort.by("dataHora").descending());

        Mockito.when(repository.findByCanalIdAndStatus(canalId, PublicacaoStatus.ATIVO, pageable)).thenReturn(publicacoesPage);

        Page<Publicacao> result = service.listarPublicacoesPorCanal(canalId, 0, 2, "DESC");

        assertEquals(2, result.getContent().size());
        assertEquals("Segunda publicação", result.getContent().get(0).getConteudo());
        assertEquals("Primeira publicação", result.getContent().get(1).getConteudo());
        Mockito.verify(repository, Mockito.times(1)).findByCanalIdAndStatus(canalId, PublicacaoStatus.ATIVO, pageable);
    }

    @Test
    @DisplayName("Deve retornar lista vazia para um canal sem publicações")
    void testListarPublicacoesPorCanalEmpty() {
        Long canalId = 1L;

        Pageable pageable = PageRequest.of(0, 2, Sort.by("dataHora").descending());
        Page<Publicacao> publicacoesPage = Page.empty(pageable);

        Mockito.when(repository.findByCanalIdAndStatus(canalId, PublicacaoStatus.ATIVO, pageable)).thenReturn(publicacoesPage);

        Page<Publicacao> result = service.listarPublicacoesPorCanal(canalId, 0, 2, "DESC");

        assertTrue(result.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findByCanalIdAndStatus(canalId, PublicacaoStatus.ATIVO, pageable);
    }

    @Test
    @DisplayName("Deve lidar com direção de ordenação inválida")
    void testListarPublicacoesPorCanalInvalidSortDirection() {
        Long canalId = 1L;

        assertThrows(IllegalArgumentException.class, () -> {
            service.listarPublicacoesPorCanal(canalId, 0, 2, "INVALID");
        });
    }

    @Test
    @DisplayName("Deve listar todas as publicações de um tipo determinado")
    void testListarPorTipo() {
        List<Publicacao> publicacoes = Arrays.asList(publicacao, publicacao);
        Mockito.when(repository.findAllByTipoPublicacaoTipoAndStatusOrderByDataHoraDesc(Mockito.any(String.class), Mockito.eq(PublicacaoStatus.ATIVO)))
                .thenReturn(publicacoes);

        List<Publicacao> result = service.listarPorTipo("COMUM");

        assertEquals(2, result.size());
        assertEquals(publicacao, result.get(0));
    }

    @Test
    @DisplayName("Deve lançar exceção ao listar publicações com tipo de publicação vazio")
    void testListarPorTipoVazio() {
        assertThrows(InvalidoException.class, () -> {
            service.listarPorTipo("");
        });
    }

    @Test
    @DisplayName("Deve listar a quantidade de publicações por dia para um mês e ano específicos")
    void testListarQuantidadeDePublicacaoPorDia() {
        int mes = 5;
        int ano = 2023;
        List<QuantidadePublicacaoDiaListagemDto> quantidadePublicacoes = Arrays.asList(
                new QuantidadePublicacaoDiaListagemDto(new Date(123, 4, 1), 10L),
                new QuantidadePublicacaoDiaListagemDto(new Date(123, 4, 2), 5L)
        );

        Mockito.when(repository.buscaQuantidadeDePublicacaoPorDia(mes, ano)).thenReturn(quantidadePublicacoes);

        List<QuantidadePublicacaoDiaListagemDto> result = service.listarQuantidadeDePublicacaoPorDia(mes, ano);

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getQuantidadePublicacoes());
        assertEquals(5, result.get(1).getQuantidadePublicacoes());
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao listar a quantidade de publicações por dia para um mês e ano sem publicações")
    void testListarQuantidadeDePublicacaoPorDiaVazio() {
        int mes = 5;
        int ano = 2023;
        Mockito.when(repository.buscaQuantidadeDePublicacaoPorDia(mes, ano)).thenReturn(Collections.emptyList());

        List<QuantidadePublicacaoDiaListagemDto> result = service.listarQuantidadeDePublicacaoPorDia(mes, ano);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve listar a quantidade de publicações em cada canal para um mês e ano específicos")
    void testBuscaQuantidadePublicacoesEmCadaCanal() {
        int mes = 5;
        int ano = 2023;
        List<QuantidadePublicacaoMesCanalListagemDto> quantidadePublicacoes = Arrays.asList(
                new QuantidadePublicacaoMesCanalListagemDto("Canal 1", 10L),
                new QuantidadePublicacaoMesCanalListagemDto("Canal 2", 5L)
        );

        Mockito.when(repository.buscaQuantidadeDePublicacoesEmCadaCanal(mes, ano, PublicacaoStatus.ATIVO))
                .thenReturn(quantidadePublicacoes);

        List<QuantidadePublicacaoMesCanalListagemDto> result = service.buscaQuantidadePublicacoesEmCadaCanal(mes, ano);

        assertEquals(2, result.size());
        assertEquals("Canal 1", result.get(0).getCanal());
        assertEquals(10L, result.get(0).getTotalPublicacoes());
        assertEquals("Canal 2", result.get(1).getCanal());
        assertEquals(5L, result.get(1).getTotalPublicacoes());
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao buscar a quantidade de publicações em cada canal para um mês e ano sem publicações")
    void testBuscaQuantidadePublicacoesEmCadaCanalVazio() {
        int mes = 5;
        int ano = 2023;
        Mockito.when(repository.buscaQuantidadeDePublicacoesEmCadaCanal(mes, ano, PublicacaoStatus.ATIVO))
                .thenReturn(Collections.emptyList());

        List<QuantidadePublicacaoMesCanalListagemDto> result = service.buscaQuantidadePublicacoesEmCadaCanal(mes, ano);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar o canal com o maior número de publicações para um mês e ano específicos")
    void testBuscaCanalComMaiorNumeroDePublicacoes() {
        int mes = 5;
        int ano = 2023;
        QuantidadePublicacaoMesCanalListagemDto canalMaisPublicacoes = new QuantidadePublicacaoMesCanalListagemDto("Canal 1", 10L);

        Mockito.when(repository.buscaCanalComMaiorNumeroDePublicacoes(mes, ano, PublicacaoStatus.ATIVO))
                .thenReturn(Optional.of(canalMaisPublicacoes));

        QuantidadePublicacaoMesCanalListagemDto result = service.buscaCanalComMaiorNumeroDePublicacoes(mes, ano);

        assertEquals("Canal 1", result.getCanal());
        assertEquals(10L, result.getTotalPublicacoes());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar o canal com o maior número de publicações para um mês e ano sem publicações")
    void testBuscaCanalComMaiorNumeroDePublicacoesVazio() {
        int mes = 5;
        int ano = 2023;
        Mockito.when(repository.buscaCanalComMaiorNumeroDePublicacoes(mes, ano, PublicacaoStatus.ATIVO))
                .thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.buscaCanalComMaiorNumeroDePublicacoes(mes, ano);
        });
    }

    @Test
    @DisplayName("Deve listar todas as publicações de um usuário específico")
    void testListarPorUsuario() {
        Long idUsuario = 1L;
        List<Publicacao> publicacoes = Arrays.asList(
                new Publicacao() {{
                    setId(1);
                    setConteudo("Publicação 1");
                    setUsuario(new Usuario() {{
                        setId(idUsuario);
                    }});
                    setStatus(PublicacaoStatus.ATIVO);
                }},
                new Publicacao() {{
                    setId(2);
                    setConteudo("Publicação 2");
                    setUsuario(new Usuario() {{
                        setId(idUsuario);
                    }});
                    setStatus(PublicacaoStatus.ATIVO);
                }}
        );

        Mockito.when(repository.findByUsuarioId(idUsuario, PublicacaoStatus.ATIVO)).thenReturn(publicacoes);

        List<Publicacao> result = service.listarPorUsuario(idUsuario);

        assertEquals(2, result.size());
        assertEquals("Publicação 1", result.get(0).getConteudo());
        assertEquals("Publicação 2", result.get(1).getConteudo());
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao listar publicações de um usuário sem publicações")
    void testListarPorUsuarioVazio() {
        Long idUsuario = 1L;
        Mockito.when(repository.findByUsuarioId(idUsuario, PublicacaoStatus.ATIVO)).thenReturn(Collections.emptyList());

        List<Publicacao> result = service.listarPorUsuario(idUsuario);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve lançar exceção ao verificar tipo de publicação vazio")
    void testVerificaTipoVazioNaoEncontrado() {
        PublicacaoCriacaoRequestDto novaPublicacao = new PublicacaoCriacaoRequestDto();
        novaPublicacao.setIdTipoPublicacao(1);

        Mockito.when(tipoPublicacaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            service.verificaTipoVazio(novaPublicacao);
        });
    }

    @Test
    @DisplayName("Não deve lançar exceção ao verificar tipo de publicação existente")
    void testVerificaTipoVazioEncontrado() {
        PublicacaoCriacaoRequestDto novaPublicacao = new PublicacaoCriacaoRequestDto();
        novaPublicacao.setIdTipoPublicacao(1);

        TipoPublicacao tipoPublicacao = new TipoPublicacao();
        tipoPublicacao.setId(1);

        Mockito.when(tipoPublicacaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(tipoPublicacao));

        assertDoesNotThrow(() -> {
            service.verificaTipoVazio(novaPublicacao);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao verificar conteúdo vazio")
    void testVerificaConteudoVazio() {
        String conteudoVazio = "";

        assertThrows(InvalidoException.class, () -> {
            service.verificaConteudoVazio(conteudoVazio);
        });
    }

    @Test
    @DisplayName("Não deve lançar exceção ao verificar conteúdo não vazio")
    void testVerificaConteudoNaoVazio() {
        String conteudoNaoVazio = "Conteúdo válido";

        assertDoesNotThrow(() -> {
            service.verificaConteudoVazio(conteudoNaoVazio);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao verificar publicação não ativa")
    void testVerificaPublicacaoNaoAtiva() {
        int idPublicacao = 1;
        Publicacao publicacao = new Publicacao();
        publicacao.setId(idPublicacao);
        publicacao.setStatus(PublicacaoStatus.EXCLUIDO);

        Mockito.when(repository.findById(idPublicacao)).thenReturn(Optional.of(publicacao));

        assertThrows(InvalidoException.class, () -> {
            service.verificaPublicacaoAtiva(idPublicacao);
        });
    }

    @Test
    @DisplayName("Não deve lançar exceção ao verificar publicação ativa")
    void testVerificaPublicacaoAtiva() {
        int idPublicacao = 1;
        Publicacao publicacao = new Publicacao();
        publicacao.setId(idPublicacao);
        publicacao.setStatus(PublicacaoStatus.ATIVO);

        Mockito.when(repository.findById(idPublicacao)).thenReturn(Optional.of(publicacao));

        assertDoesNotThrow(() -> {
            service.verificaPublicacaoAtiva(idPublicacao);
        });
    }

}
