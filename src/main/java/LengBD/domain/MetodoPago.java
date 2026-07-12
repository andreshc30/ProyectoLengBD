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
@Table(name = "FIDE_METODO_PAGO_TB")
public class MetodoPago implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_METODO_PAGO")
    private Integer idMetodoPago;

    @Column(name="NOMBRE", length=100)
    private String nombre;

    @Column(name="ID_ESTADO")
    private Integer idEstado;
}