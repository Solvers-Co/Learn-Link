package co.solvers.apilearnlink.service.comentario.dto;

import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class ComentarioListagemDto {
    private Integer id;
    private String comentario;
    private LocalDateTime dataHora;
    private List<ReacaoDto> reacoes;
    private PublicacaoDto publicacao;
    private UsuarioDto usuario;

    @Getter
    @Setter
    public static class PublicacaoDto{
        private Integer id;
        private String conteudo;
        private LocalDateTime dataHora;
        private TipoPublicacaoDto tipoPublicacao;
        private UsuarioListagemDto usuario;
/*        private List<ReacaoDto> reacoes;*/
/*        private List<PublicacaoListagemResponseDto.ComentarioDto> comentarios;*/
    }

    @Getter
    @Setter
    public static class UsuarioDto {
        private String nome;
        private String email;
        private EspecialidadeListagemDto especialidade;
    }
    @Getter
    @Setter
    public static class TipoPublicacaoDto{
        private Integer id;
        private String tipo;
    }

    @Getter
    @Setter
    public static class ReacaoDto{
        private Integer id;
        private TipoReacaoListagemDto tipoReacao;
        private UsuarioDto usuario;
    }



}
