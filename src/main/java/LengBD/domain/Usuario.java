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
    private Long cedula;
    
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

    @Column(name = "ID_TELEFONO")
    private Integer idTelefono;

    @Column(name = "ID_CORREO")
    private Integer idCorreo;

    @Column(name = "ID_SECCION")
    private Integer idSeccion;

    @Column(name = "ID_DIRECCION")
    private Integer idDireccion;

    @Column(name = "ID_BANDA")
    private Integer idBanda;

    @Column(name = "ID_ESTADO")
    private Integer idEstado;

}
