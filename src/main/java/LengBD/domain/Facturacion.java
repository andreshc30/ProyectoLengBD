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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_FACTURACION_TB")
public class Facturacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_FACTURA")
    private Integer idFactura;

    @Column(name="FECHA_EMISION")
    private LocalDate fechaEmision;

    @Column(name="DETALLE", length=500)
    private String detalle;

    @Column(name="SUBTOTAL")
    private Double subtotal;

    @Column(name="IMPUESTOS")
    private Double impuestos;

    @Column(name="TOTAL")
    private Double total;

    @Column(name="ID_METODO_PAGO")
    private Integer idMetodoPago;
    
    @Column(name="ID_CUOTA")
    private Integer idCuota;
    
    @Column(name="ID_SUSCRIPCION")
    private Integer idSuscripcion;

    @Column(name="ID_ESTADO")
    private Integer idEstado;
}
