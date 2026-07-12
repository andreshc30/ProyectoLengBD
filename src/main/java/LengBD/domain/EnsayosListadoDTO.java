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
import java.time.LocalDateTime;
import java.util.Date;
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

    private Integer idEstado;
    

}
