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
@Table(name = "FIDE_EVENTO_TB")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_EVENTO")
    private Integer idEvento;
    
    @Column(name="NOMBRE", length=150)
    private String nombre;
    
    @Column(name="DETALLE", length=1000)
    private String detalle;
    
    @Column(name="FECHA")
    private LocalDate fecha;
    
    @ManyToOne
    @JoinColumn(name="ID_DIRECCION", referencedColumnName = "ID_DIRECCION")
    private Direccion direccion;
    
    @ManyToOne
    @JoinColumn(name="ID_BANDA", referencedColumnName = "ID_BANDA")
    private Banda idBanda;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Estado idEstado; 

}
