package co.solvers.apilearnlink.service.publicacao.dto;

import co.solvers.apilearnlink.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicacaoListagemParquetDto {

    private Integer id;
    private String conteudo;
    private Usuario usuario;
}
