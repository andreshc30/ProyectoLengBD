package LengBD.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AudicionListadoDTO {
    // Para la tabla HTML
    private Integer idSolicitud;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String mensaje;
    private LocalDate fechaSolicitud;
    
    // Nombres cruzados que vendrán del JOIN en Oracle (para mostrar bonito)
    private String correoFisico; // asumiendo que es un email
    private String numeroTelefono;
    private String nombreSeccion;
    private String nombreBanda;
    private String nombreEstado;
    
    // IDs puros (para cuando queramos Editar el registro)
    private Integer idCorreo;
    private Integer idSeccion;
    private Integer idBanda;
    private Integer idTelefono;
    private Integer idEstado;
}