/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class UsuarioListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Integer cedula;
    
    private String nombre;
    
    private String primerApellido;
    
    private String segundoApellido;
    
    private LocalDate fechaIngreso;
    
    private String logoUrl;
    private String telefono; 
    private String correo;
    private String nombreSeccion;
    private String nombreDireccion;
    private String nombreBanda;
    private String estado;
    private Integer idTelefono;
    private Integer idCorreo;
    private Integer idSeccion;
    private Integer idDireccion;
    private Integer idBanda;
    private Integer idEstado;
}