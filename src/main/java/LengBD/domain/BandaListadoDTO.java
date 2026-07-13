/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private String telefono;  
    
    
    private Integer idEstado; 
    private String estado; 
    

}