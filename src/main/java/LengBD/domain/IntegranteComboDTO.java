package LengBD.domain;

import lombok.Data;

@Data
public class IntegranteComboDTO {
    private Integer cedula;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String nombreSeccion;   // del JOIN en el SP
    private String nombreEstado;    // del JOIN en el SP
}