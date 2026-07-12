/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Facturacion;
import LengBD.domain.Instrumento;
import LengBD.domain.MetodoPago;
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
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer>{
        
    @Procedure(procedureName = "FIDE_METODO_PAGO_INSERT_SP")
    void insertarMetodoPago(
        @Param("P_ID_METODO_PAGO") Integer idMetodoPago,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_METODO_PAGO_UPDATE_SP")
    void updateMetodoPago(
        @Param("P_ID_METODO_PAGO") Integer idMetodoPago,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_METODO_PAGO_DELETE_LOGICO_SP")
    void eliminarMetodoPago(
        @Param("P_ID_METODO_PAGO") Integer idMetodoPago
    );
    
}
