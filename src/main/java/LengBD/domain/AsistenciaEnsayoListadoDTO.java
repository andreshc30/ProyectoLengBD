package LengBD.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AsistenciaEnsayoListadoDTO {
    private Integer idAsistenciaEnsayos;
    private Integer idEnsayo;
    private String nombreEnsayo;
    private String nombreUsuario;
    private Long cedula;         
    private String nombreEstado;
    private Integer idEstado;           
}