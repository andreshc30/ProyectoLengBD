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
@Table(name = "FIDE_MATERIAL_ESTUDIO_TB")
public class MaterialEstudio implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MATERIAL")
    private Integer idMaterial;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="FECHA")
    private LocalDate fecha;
    
    @Column(name="RUTA_MATERIAL_ESTUDIO", length=500)
    private String rutaMaterialEstudio;
    
    @ManyToOne
    @JoinColumn(name="ID_SECCION", referencedColumnName = "ID_SECCION")
    private Seccion idSeccion;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Estado idEstado; 

}
