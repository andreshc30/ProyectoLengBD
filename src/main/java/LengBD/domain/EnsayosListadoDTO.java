/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EnsayosListadoDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    private Integer idEnsayo;

    private String nombre;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String descripcion;

    private String direccion;
    
    private Integer idDireccion; 
    
    private String nombreBanda;
    
    private Integer idBanda; 

    private String estado;

    private Integer idEstado;
    

}