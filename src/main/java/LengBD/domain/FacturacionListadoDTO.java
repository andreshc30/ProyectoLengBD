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
public class FacturacionListadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idFactura;

    private LocalDate fechaEmision;

    private String detalle;
    private String nombreMetodoPago;
    private String nombreCuota;
    private String nombreSuscripcion;
    private String estado;

    private Double subtotal;

    private Double impuestos;

    private Double total;

    private Integer idMetodoPago;
    
    private Integer idCuota;
    
    private Integer idSuscripcion;

    private Integer idEstado;
}
