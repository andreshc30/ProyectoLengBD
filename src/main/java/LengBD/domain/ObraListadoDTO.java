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
public class ObraListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Integer idObra;
    
    private String nombre;
    
    private LocalDate fecha;
    
    private String detalle;
    private String nombreTipo;
    private String nombrebanda;
    private String estado;
    
    private Integer idTipo;
    
    private Integer idBanda;

    private Integer idEstado; 

}
