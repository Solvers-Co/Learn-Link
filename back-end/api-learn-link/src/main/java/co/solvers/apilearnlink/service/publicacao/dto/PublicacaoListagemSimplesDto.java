package co.solvers.apilearnlink.service.publicacao.dto;

import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.service.canal.dto.CanalListagemDto;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeListagemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublicacaoListagemSimplesDto {

    private Integer id;
    private String conteudo;
    private LocalDateTime dataHora;
    private PublicacaoListagemResponseDto.TipoPublicacaoDto tipoPublicacao;
    private PublicacaoStatus status;
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
