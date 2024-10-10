package co.solvers.apilearnlink.domain.endereco;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
