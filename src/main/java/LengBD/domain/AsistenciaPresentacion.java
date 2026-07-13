/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_ASISTENCIA_PRESENTACION_TB")
public class AsistenciaPresentacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_ASISTENCIA_PRESENTACION")
    private Integer idAsistenciaPresentacion;
    
    @Column(name="ID_PRESENTACION")
    private Integer idPresentacion;
    
    @Column(name="CEDULA")
    private Integer cedula;
    
    @Column(name="ID_ESTADO")
    private Integer idEstado;
}

