package LengBD.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AsistenciaEnsayoListadoDTO {
    private Integer idAsistenciaEnsayos;
    private Integer idEnsayo;
    private Long cedula;                   
    private Integer idEstado;           
}