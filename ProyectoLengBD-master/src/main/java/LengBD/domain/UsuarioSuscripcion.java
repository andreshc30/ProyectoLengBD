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
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_USUARIO_SUSCRIPCION_TB")
public class UsuarioSuscripcion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="CEDULA")
    private Usuario cedula;
    
    @Id
    @Column(name="ID_SUSCRIPCION")
    private Suscripcion idSuscripcion;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO")
    private Estado idEstado; 
    
}
