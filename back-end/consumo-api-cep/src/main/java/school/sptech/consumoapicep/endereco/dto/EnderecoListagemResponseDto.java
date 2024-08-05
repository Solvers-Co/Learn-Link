package school.sptech.consumoapicep.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoListagemResponseDto {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
