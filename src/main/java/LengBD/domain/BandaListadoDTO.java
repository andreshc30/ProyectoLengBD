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
import java.util.Date;
import lombok.Data;

@Data
public class BandaListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Integer idBanda;
    
    private String nombre;
    
    private String logoUrl;
    
    private BigDecimal montoCuota;
    
    private LocalDate fechaFundacion;
    
    private Integer idDireccion;
    private String direccion;
    
    
    private Integer idCorreo;
    private String correo;
    
    
    private Integer idTelefono; 
    private Integer telefono; 
    
    
    private Integer idEstado; 
    private String estado; 
    

}
