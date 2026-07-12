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
public class PagoListadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPago;
    
    private Double monto;
    
    private LocalDate fechaPago;
    
    private String nombreMetodoPago;
    private String nombreSuscripcion;
    private String nombreCuota;
    private String nombreFactura;
    private String estado;
    
    private Integer idMetodoPago;
    private Integer idFacturacion;
    
    private Integer idSuscripcion;
    
    private Integer idCuota;
    
    private Integer idEstado;

    
}