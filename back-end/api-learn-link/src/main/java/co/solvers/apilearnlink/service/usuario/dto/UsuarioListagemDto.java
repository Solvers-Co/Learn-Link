package co.solvers.apilearnlink.service.usuario.dto;

import co.solvers.apilearnlink.service.classificacao.dto.ClassificacaoListagemDto;
import co.solvers.apilearnlink.service.endereco.dto.EnderecoListagemDto;
import co.solvers.apilearnlink.service.tipousuario.dto.TipoUsuarioListagemDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioListagemDto {

    private String email;
    private String nome;
    private String cpf;
    private TipoStatusDto tipoStatus;
    private TipoUsuarioListagemDto tipoUsuario;
    private ClassificacaoListagemDto classificacao;
    private EnderecoListagemDto endereco;


    @Data
    public static class TipoStatusDto {
        public Integer id;
        public String status;
    }
/*    private int qtdContribuicoes;
    private String classificacao;*/
}
