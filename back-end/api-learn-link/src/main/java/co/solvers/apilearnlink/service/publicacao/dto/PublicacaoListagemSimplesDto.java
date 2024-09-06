package co.solvers.apilearnlink.service.publicacao.dto;

import co.solvers.apilearnlink.service.canal.dto.CanalListagemDto;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeListagemDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PublicacaoListagemSimplesDto {

    private Integer id;
    private String conteudo;
    private LocalDateTime dataHora;
    private PublicacaoListagemResponseDto.TipoPublicacaoDto tipoPublicacao;
    private CanalListagemDto canal;
    private PublicacaoListagemResponseDto.UsuarioPublicacaoListagemDto usuario;

    @Getter
    @Setter
    public static class TipoPublicacaoDto {
        private Integer id;
        private String tipo;
    }

    @Getter
    @Setter
    public static class UsuarioPublicacaoListagemDto {
        private String nome;
        private String email;
        private EspecialidadeListagemDto especialidade;
    }





}
