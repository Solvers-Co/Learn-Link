package co.solvers.apilearnlink.service.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoListagemDto {
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
