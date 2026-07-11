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
@Table(name = "FIDE_RED_SOCIAL_BANDA_TB")
public class RedSocialBanda implements Serializable {
    private static final long serialVersionUID = 1l;
    
    @Id
    @Column(name="ID_RED_SOCIAL")
    private Integer idRedSocial;
    
    @Column(name="PLATAFORMA", length=100)
    private String plataforma;
    
    @Column(name="LINK_BANDA", length=500)
    private String linkbanda;
    
    @Column(name="ID_BANDA")
    private Banda idBanda; 
    
    @Column(name="ID_ESTADO")
    private Estado idEstado; 
    

}
