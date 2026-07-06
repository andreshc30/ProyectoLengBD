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
@Table(name = "FIDE_ENSAYOS_TB")
public class Ensayos implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_ENSAYO")
    private Integer idEnsayo;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="FECHA_INICIO")
    private LocalDate fechaInicio;
    
    @Column(name="FECHA_FIN")
    private LocalDate fechaFin;
    
    @Column(name="DESCRIPCION", length=100)
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="CEDULA")
    private Usuario cedula;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private Estado idEstado; 

}
