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
public class EstadoCuentaMusicoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
 
    private Long cedula;
    private String nombreMusico;
    private Integer idBanda;
    private String nombreBanda;
    private BigDecimal montoCuota;
    private BigDecimal totalPagado;
    private LocalDate fechaUltimoPago;
    private String moroso; 
}
