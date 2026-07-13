/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EventoListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Integer idEvento;
    
    private String nombre;
    
    private String detalle;
    private String nombreDireccion;
    private String nombreBanda;
    private String estado;
    
    private LocalDate fecha;
    
    private String direccion;     
    private Integer idDireccion;  
    
    private Integer idBanda;
    
    private Integer idEstado; 
}