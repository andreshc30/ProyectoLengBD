package LengBD.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AsignacionListadoDTO {
    private Integer idAsignacion;
    private String nombreIntegrante;    // para la tabla
    private String nombreInstrumento;   // para la tabla
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String motivo;
    private Integer cedula;             // para el editar
    private Integer idInstrumento;      // para el editar
    private Integer idEstado;           // para el editar
}