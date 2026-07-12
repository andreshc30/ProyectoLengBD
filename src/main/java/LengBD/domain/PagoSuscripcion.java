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
@Table(name = "FIDE_PAGO_SUSCRIPCION_TB")
public class PagoSuscripcion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_PAGO_SUSCRIPCION")
    private Integer idPagoSuscripcion;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="DESCRIPCION", length=300)
    private String descripcion;
    
    @Column(name="MONTO", precision=12, scale=2)
    private BigDecimal monto;
    
    @Column(name="FECHA_PAGO")
    private LocalDate fechaPago;
    
    @Column(name="ID_METODO_PAGO")
    private Integer idMetodoPago; 
    
    @Column(name="ID_TIPO_PLAN")
    private Integer idTipoPlan; 
    
    @Column(name="ID_SUSCRIPCION")
    private Integer idSuscripcion; 
    
    @Column(name="ID_ESTADO")
    private Integer idEstado; 

}
