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
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_PLANES_TB")
public class Planes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_TIPO_PLAN")
    private Integer idTipoPlan;

    @Column(name="NOMBRE", length=100)
    private String nombre;

    @Column(name="PRECIO")
    private Double precio;

    @Column(name="DESCRIPCION", length=300)
    private String descripcion;

    @Column(name="PERIODICIDAD", length=50)
    private String periodicidad;

    @Column(name="ID_ESTADO")
    private Integer idEstado;
}
