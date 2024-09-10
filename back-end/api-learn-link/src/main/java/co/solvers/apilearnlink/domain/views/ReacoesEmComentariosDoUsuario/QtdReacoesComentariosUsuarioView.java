package co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario;

import co.solvers.apilearnlink.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "view_quantidade_reacoes_comentarios_do_usuario")
public class QtdReacoesComentariosUsuarioView {

    private Integer reacoes;
    @Id
    private Integer usuarioId;
    private Integer pontuacao;
    private String nome;
}
