/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SolicitudIngresoListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Integer idSolicitud;
    
    private String nombre;
    
    private String primerApellido;
    
    private String segundoApellido;
    
    private String mensaje;
    private String correo;
    private String telefono;
    private String nombreSeccion;
    private String nombreBanda;
    private String estado;
    
    private LocalDate fechaSolicitud;
    
    private Integer idSeccion; 
    
    private Integer idBanda; 
    
    private Integer idEstado; 
    

}