package school.sptech.consumoapicep.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.consumoapicep.domain.endereco.Endereco;

/*@Entity*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String especialidade;
    private Integer pontos;

    /*@ManyToOne*/
    private Endereco endereco;
}
