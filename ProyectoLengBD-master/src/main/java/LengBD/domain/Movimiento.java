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
@Table(name = "FIDE_MOVIMIENTO_TB")
public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_MOVIMIENTO")
    private Integer idMovimiento;
    
    @Column(name="CANTIDAD", precision=14, scale=2)
    private BigDecimal cantidad;
    
    @Column(name="FECHA")
    private LocalDate fecha;
    
    @Column(name="DETALLE", length=100)
    private String detalle;
    
    @ManyToOne
    @JoinColumn(name="ID_TIPO")
    private Tipo idTipo;
    
    @ManyToOne
    @JoinColumn(name="ID_CATEGORIA_MOVIMIENTO")
    private CategoriaMovimiento idCategoriaMovimiento;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private Estado idEstado; 

}
