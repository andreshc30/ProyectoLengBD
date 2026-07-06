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
@Table(name = "FIDE_INSTRUMENTO_TB")
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_INSTRUMENTO")
    private Integer idInstrumento;
    
    @Column(name="DESCRIPCION", length=100)
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="ID_SECCION")
    private Seccion idSeccion;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private Estado idEstado; 
    
    @Column(name="NOMBRE", length=100)
    private String nombre;

}
