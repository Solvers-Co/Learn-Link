package co.solvers.apilearnlink.domain.usuario;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String especialidade;
    @OneToOne
    private Escolaridade escolaridade;
    @OneToOne
    private Classificacao classificacao;
    @OneToOne
    private Endereco endereco;
    @ManyToOne
    private TipoStatus tipoStatus;
    @OneToOne
    private TipoUsuario tipoUsuario;




}
