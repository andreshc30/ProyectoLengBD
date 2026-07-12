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
@Table(name = "FIDE_PUBLICACION_TB")
public class Publicacion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PUBLICACION")
    private Integer idPublicacion;
    
    @Column(name="FECHA")
    private LocalDate fecha;
    
    @Column(name="TITULO", length=150)
    private String titulo;
    
    @Column(name="DETALLE", length=1000)
    private String detalle;
    
    @ManyToOne
    @JoinColumn(name="ID_RED_SOCIAL", referencedColumnName = "ID_RED_SOCIAL")
    private RedSocialBanda idRedSocial; 
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Estado idEstado; 
    

}
