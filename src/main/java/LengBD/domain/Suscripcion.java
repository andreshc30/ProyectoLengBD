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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_SUSCRIPCION_TB")
public class Suscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ID_SUSCRIPCION")
    private Integer idSuscripcion;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="FECHA_INICIO")
    private LocalDate fechaInicio;
    
    @Column(name="FECHA_FINAL")
    private LocalDate fechaFinal;
    
    @Column(name="AUTO_RENOVAR", precision=1, scale=0)
    private BigDecimal autoRenovar;
    
    @Column(name="ID_TIPO_PLAN")
    private Planes idTipoPlan; 
    
    @Column(name="ID_BANDA")
    private Banda idBanda; 
    
    @Column(name="ID_ESTADO")
    private Estado idEstado; 
    
}