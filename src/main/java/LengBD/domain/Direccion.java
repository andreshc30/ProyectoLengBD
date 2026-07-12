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
@Table(name = "FIDE_DIRECCION_TB")
public class Direccion implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_DIRECCION")
    private Integer idDireccion;
    
    @ManyToOne
    @JoinColumn(name="ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
    private Provincia idProvincia;
    
    @ManyToOne
    @JoinColumn(name="ID_CANTON", referencedColumnName = "ID_CANTON")
    private Canton idCanton;
    
    @ManyToOne
    @JoinColumn(name="ID_DISTRITO", referencedColumnName = "ID_DISTRITO")
    private Distrito idDistrito;
    
    @Column(name="OTROS_DETALLES", length=300)
    private String otrosDetalles;
    
    @ManyToOne
    @JoinColumn(name="ID_ESTADO", referencedColumnName = "ID_ESTADO")
    private Estado idEstado; 

}
