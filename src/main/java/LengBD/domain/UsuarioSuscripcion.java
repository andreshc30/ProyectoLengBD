/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "FIDE_USUARIO_SUSCRIPCION_TB")
@IdClass(UsuarioSuscripcionId.class)
public class UsuarioSuscripcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CEDULA")
    private Usuario cedula;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUSCRIPCION")
    private Suscripcion idSuscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO")
    private Estado idEstado;
}