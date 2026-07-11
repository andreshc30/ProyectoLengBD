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
@Table(name = "FIDE_CUOTA_TB")
public class Cuota implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_CUOTA")
    private Integer idCuota;
    
    @Column(name="CEDULA")
    private Usuario cedula;
    
    @Column(name="FECHA")
    private LocalDate fecha;
    
    @Column(name="MONTO_PAGADO", precision=12, scale=2)
    private BigDecimal montoPagado;
    
    @Column(name="ID_ESTADO")
    private Estado idEstado; 

}
