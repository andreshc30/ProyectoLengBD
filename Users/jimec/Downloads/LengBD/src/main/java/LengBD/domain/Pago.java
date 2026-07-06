/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

/**
 *
 * @author peper
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_PAGO_SUSCRIPCION_TB")
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_PAGO_SUSCRIPCION")
    private Integer idPagoSuscripcion;

    @Column(name="NOMBRE", length=100)
    private String nombre;

    @Column(name="DESCRIPCION", length=300)
    private String descripcion;

    @Column(name="MONTO")
    private Double monto;

    @Column(name="FECHA_PAGO")
    private LocalDate fechaPago;

    @Column(name="ID_ESTADO")
    private Integer idEstado;

    @Column(name="ID_METODO_PAGO")
    private Integer idMetodoPago;
}