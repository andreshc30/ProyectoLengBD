/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

/**
 *
 * @author peper
 */
import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioSuscripcionId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long cedula;
    private Long idSuscripcion;
}
