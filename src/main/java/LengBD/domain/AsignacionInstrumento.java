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
@Table(name="FIDE_ASIGNACION_INSTRUMENTO_TB")
public class AsignacionInstrumento implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_ASIGNACION")
    private Integer idAsignacion;
    
    @Column(name="FECHA_INICIO")
    private LocalDate fechaInicio;
    
    @Column(name="FECHA_FINAL")
    private LocalDate fechaFinal;
    
    @Column(name="MOTIVO")
    private String motivo;
    
    @Column(name="CEDULA")
    private Long cedula; 
    
    @Column(name="ID_INSTRUMENTO")
    private Integer idInstrumento;
    
    @Column(name="ID_ESTADO")
    private Integer idEstado;
}