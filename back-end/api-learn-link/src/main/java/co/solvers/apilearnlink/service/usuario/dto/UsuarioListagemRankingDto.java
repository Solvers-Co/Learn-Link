package co.solvers.apilearnlink.service.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListagemRankingDto {

    private Long usuarioId;
    private String nome;
    private String email;
    private Long totalReacoes;
}
