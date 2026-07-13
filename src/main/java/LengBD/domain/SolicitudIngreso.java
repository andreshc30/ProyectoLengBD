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
    @Column(name="ID_SOLICITUD")
    private Integer idSolicitud;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="PRIMER_APELLIDO", length=100)
    private String primerApellido;
    
    @Column(name="SEGUNDO_APELLIDO", length=100)
    private String segundoApellido;
    
    @Column(name="MENSAJE", length=1000)
    private String mensaje;
    
    @Column(name="CORREO", length=150)
    private String correo;
    
    @Column(name="TELEFONO", length=9)
    private String telefono;
    
    @Column(name="FECHA_SOLICITUD")
    private LocalDate fechaSolicitud;
    
    @Column(name="ID_SECCION")
    private Integer idSeccion; 
    
    @Column(name="ID_BANDA")
    private Integer idBanda; 
    
    @Column(name="ID_ESTADO")
    private Integer idEstado; 
    

}