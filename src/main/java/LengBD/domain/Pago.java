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
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_PAGO_SUSCRIPCION_TB")
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PAGO")
    private Integer idPago;
    
    @Column(name="MONTO")
    private Double monto;
    
    @Column(name="FECHA_PAGO")
    private LocalDate fechaPago;
    
    @ManyToOne
    @JoinColumn(name="ID_METODO_PAGO", referencedColumnName = "ID_METODO_PAGO")
    private Integer idMetodoPago;
    
    @ManyToOne
    @JoinColumn(name="ID_SUSCRIPCION", referencedColumnName = "ID_SUSCRIPCION")
    private Suscripcion idSuscripcion;
    
    @ManyToOne
    @JoinColumn(name="ID_CUOTA", referencedColumnName = "ID_CUOTA")
    private Cuota idCuota;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Integer idEstado;

    
}