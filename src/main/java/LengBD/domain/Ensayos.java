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
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_ENSAYOS_TB")
public class Ensayos implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "ID_ENSAYO")
    private Integer idEnsayo;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "FECHA_INICIO")
    private LocalDateTime fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDateTime fechaFin;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @Column(name = "ID_DIRECCION")
    private Direccion idDireccion; 
    
    @Column(name = "ID_BANDA")
    private Banda idBanda; 

    @Column(name = "ID_ESTADO")
    private Estado idEstado;

}
