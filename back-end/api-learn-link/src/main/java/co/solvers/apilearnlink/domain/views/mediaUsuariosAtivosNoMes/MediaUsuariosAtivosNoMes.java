package co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "view_usuarios_ativos_no_mes")
public class MediaUsuariosAtivosNoMes {

    private int ano;
    @Id
    private Integer mes;
    private int usuariosAtivos;
}
