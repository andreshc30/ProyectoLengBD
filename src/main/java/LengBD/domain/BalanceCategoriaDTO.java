/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;
 
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
 

@Data
public class BalanceCategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1l;
 
    private Integer idCategoriaMovimiento;
    private String nombreCategoria;
    private String tipo; 
    private Integer cantidadMovimientos;
    private BigDecimal total;
}
