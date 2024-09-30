package co.solvers.apilearnlink.service.publicacao.dto;

import co.solvers.apilearnlink.service.canal.dto.CanalListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeListagemDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PublicacaoListagemResponseDto {

    private Integer id;
    private String conteudo;
    private LocalDateTime dataHora;
    private TipoPublicacaoDto tipoPublicacao;
    private CanalListagemDto canal;
    private UsuarioPublicacaoListagemDto usuario;
    private List<ReacaoDto> reacoes;
    private List<ComentarioDto> comentarios;

    @Getter
    @Setter
    public static class TipoPublicacaoDto {
        private Integer id;
        private String tipo;
    }

    @Getter
    @Setter
    public static class UsuarioPublicacaoListagemDto {
        private Long id;
        private String nome;
        private String email;
        private EspecialidadeListagemDto especialidade;
    }

    @Getter
    @Setter
    public static class ComentarioDto {

        private Integer id;
        private String comentario;
        private LocalDateTime dataHora;
        private UsuarioListagemDto usuario;
        private List<ReacaoDto> reacoes;
    }

    @Getter
    @Setter
    public static class ReacaoDto {
        private Integer id;
        private TipoReacaoListagemDto tipoReacao;
        private UsuarioPublicacaoListagemDto usuario;
    }

}
