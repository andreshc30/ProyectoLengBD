/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * Columnas EXACTAS de FIDE_LISTAR_PROVEEDORES_SP. 8.4 es solo lectura: no
 * hay tabla de proveedores, es un filtro sobre FIDE_MOVIMIENTO_TB
 * (categorias Patrocinios/Donaciones).
 */
@Data
public class ProveedorListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Integer idMovimiento;
    private String nombreBanda;
    private String nombreCategoria;
    private String proveedorPatrocinador;
    private BigDecimal cantidad;
    private LocalDate fecha;
    private String estado;
    private Integer idEstado;
}