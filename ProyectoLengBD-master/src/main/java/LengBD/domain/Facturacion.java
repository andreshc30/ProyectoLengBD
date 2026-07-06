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
@Entity
@Table(name = "FIDE_FACTURACION_TB")
public class Facturacion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_FACTURA")
    private Integer idFactura;
    
    @ManyToOne
    @JoinColumn(name="CEDULA")
    private Usuario cedula;
    
    @Column(name="FECHA_EMISION")
    private LocalDate fechaEmision;
    
    @Column(name="DETALLE", length=100)
    private String detalle;
    
    @Column(name="SUBTOTAL", precision=12, scale=2)
    private BigDecimal subtotal;
    
    @Column(name="IMPUESTOS", precision=12, scale=2)
    private BigDecimal impuestos;
    
    @Column(name="TOTAL", precision=12, scale=2)
    private BigDecimal total;
    
    @ManyToOne
    @JoinColumn(name="ID_METODO_PAGO")
    private MetodoPago idMetodoPago;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private Estado idEstado; 

}
