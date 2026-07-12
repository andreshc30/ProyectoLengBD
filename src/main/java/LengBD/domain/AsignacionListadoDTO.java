package LengBD.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AsignacionListadoDTO {
    private Integer idAsignacion;
    private String nombreIntegrante;    
    private String nombreInstrumento;   
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String motivo;
    private Long cedula;             
    private Integer idInstrumento;    
    private String nombreEstado;
    private Integer idEstado;           
}