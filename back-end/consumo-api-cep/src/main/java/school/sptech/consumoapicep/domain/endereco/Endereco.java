package school.sptech.consumoapicep.domain.endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
/*@Entity*/
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Integer id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}


