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
@Table(name = "FIDE_USUARIO_TB")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="CEDULA")
    private Integer cedula;
    
    @Column(name="NOMBRE", length=100)
    private String nombre;
    
    @Column(name="PRIMER_APELLIDO", length=100)
    private String primerApellido;
    
    @Column(name="SEGUNDO_APELLIDO", length=100)
    private String segundoApellido;
    
    @Column(name="FECHA_INGRESO")
    private LocalDate fechaIngreso;
    
    @Column(name = "LOGO_URL", length = 500)
    private String logoUrl;

//    @ManyToOne
//    @JoinColumn(name = "ID_TELEFONO", referencedColumnName = "CEDULA")
//    private Telefono idTelefono;

    @ManyToOne
    @JoinColumn(name = "ID_CORREO", referencedColumnName = "ID_CORREO")
    private Correo idCorreo;

    @ManyToOne
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    private Seccion idSeccion;

    @ManyToOne
    @JoinColumn(name = "ID_DIRECCION", referencedColumnName = "ID_DIRECCION")
    private Direccion idDireccion;

    @ManyToOne
    @JoinColumn(name = "ID_BANDA", referencedColumnName = "ID_BANDA")
    private Banda idBanda;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Estado idEstado;

}
