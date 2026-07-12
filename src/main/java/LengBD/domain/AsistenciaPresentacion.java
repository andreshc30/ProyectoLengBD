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
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_ASISTENCIA_PRESENTACION_TB")
public class AsistenciaPresentacion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ASISTENCIA_PRESENTACION")
    private Integer idAsistenciaPresentacion;
    
    @ManyToOne
    @JoinColumn(name="ID_PRESENTACION", referencedColumnName = "ID_PRESENTACION")
    private Integer idPresentacion;
    
    @ManyToOne
    @JoinColumn(name="CEDULA", referencedColumnName = "CEDULA")
    private Long cedula;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Integer idEstado; 

}
