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
@Table(name = "FIDE_JUSTIFICACION_TB")
public class Justificacion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_JUSTIFICACION")
    private Justificacion idJustificacion;
    
    @Column(name="MOTIVO", length=500)
    private String motivo;
    
    @ManyToOne
    @JoinColumn(name="ID_ASISTENCIA_ENSAYOS", referencedColumnName = "ID_ASISTENCIA_ENSAYOS")
    private AsistenciaEnsayo idAsistenciaEnsayos;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Estado idEstado; 

}
