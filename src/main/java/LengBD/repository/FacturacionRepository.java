/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Facturacion;
import LengBD.domain.Instrumento;
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
public interface FacturacionRepository extends JpaRepository<Facturacion, Integer>{
        
    @Procedure(procedureName = "FIDE_FACTURACION_INSERT_SP")
    void insertarFacturacion(
        @Param("P_ID_FACTURA") Integer idFactura,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_FECHA_EMISION") Date fechaEmision,
        @Param("P_DETALLE") String detalle,
        @Param("P_SUBTOTAL") BigDecimal subtotal,
        @Param("P_IMPUESTOS") BigDecimal impuestos,
        @Param("P_TOTAL") BigDecimal total,
        @Param("P_ID_METODO_PAGO") Integer idMetodoPago,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_FACTURACION_UPDATE_SP")
    void updateFacturacion(
        @Param("P_ID_FACTURA") Integer idFactura,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_FECHA_EMISION") Date fechaEmision,
        @Param("P_DETALLE") String detalle,
        @Param("P_SUBTOTAL") BigDecimal subtotal,
        @Param("P_IMPUESTOS") BigDecimal impuestos,
        @Param("P_TOTAL") BigDecimal total,
        @Param("P_ID_METODO_PAGO") Integer idMetodoPago,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_FACTURACION_DELETE_LOGICO_SP")
    void eliminarFacturacion(
        @Param("P_ID_FACTURA") Integer idFactura
    );
    
}
