/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Facturacion;
import LengBD.domain.Instrumento;
import LengBD.domain.MaterialEstudio;
import LengBD.domain.MetodoPago;
import LengBD.domain.Movimiento;
import LengBD.domain.PagoSuscripcion;
import LengBD.domain.Suscripcion;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
        
    @Procedure(procedureName = "FIDE_MOVIMIENTO_INSERT_SP")
    void insertarMovimiento(
        @Param("P_ID_MOVIMIENTO") Integer idMovimiento,
        @Param("P_CANTIDAD") BigDecimal cantidad,
        @Param("P_FECHA") Date fecha,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_CATEGORIA_MOVIMIENTO") Integer idCategoriaMovimiento,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_MOVIMIENTO_UPDATE_SP")
    void updateMovimiento(
        @Param("P_ID_MOVIMIENTO") Integer idMovimiento,
        @Param("P_CANTIDAD") BigDecimal cantidad,
        @Param("P_FECHA") Date fecha,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_CATEGORIA_MOVIMIENTO") Integer idCategoriaMovimiento,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_MOVIMIENTO_DELETE_LOGICO_SP")
    void eliminarMovimiento(
        @Param("P_ID_MOVIMIENTO") Integer idMovimiento
    );
    
}
