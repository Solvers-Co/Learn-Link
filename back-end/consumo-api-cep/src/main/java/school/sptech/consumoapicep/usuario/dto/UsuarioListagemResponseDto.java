package school.sptech.consumoapicep.usuario.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import school.sptech.consumoapicep.domain.endereco.Endereco;

@Getter
@Setter
public class UsuarioListagemResponseDto {

    private Integer id;
    private String nome;
    private String email;
    private String especialidade;
    private Integer pontos;
    private Endereco endereco;
}
