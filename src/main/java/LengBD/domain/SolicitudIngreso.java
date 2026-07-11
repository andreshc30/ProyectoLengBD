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
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_SOLICITUD_INGRESO_TB")
public class SolicitudIngreso implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_SOLICITUD")
    private Integer idSolicitud;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="PRIMER_APELLIDO", length=100)
    private String primerApellido;
    
    @Column(name="SEGUNDO_APELLIDO", length=100)
    private String segundoApellido;
    
    @Column(name="MENSAJE", length=100)
    private String mensaje;
    
    @Column(name="FECHA_SOLICITUD")
    private LocalDate fechaSolicitud;
    
    @Column(name="ID_CORREO")
    private Correo idCorreo; 
    
    @Column(name="ID_SECCION")
    private Seccion idSeccion; 
    
    @Column(name="ID_BANDA")
    private Banda idBanda; 
    
    @Column(name="ID_TELEFONO")
    private Telefono idTelefono; 
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private Estado idEstado; 
    

}
