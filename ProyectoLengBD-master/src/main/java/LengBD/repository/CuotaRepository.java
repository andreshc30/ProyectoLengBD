/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Cuota;
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
public interface CuotaRepository extends JpaRepository<Cuota, Integer>{
        
    @Query(nativeQuery=true, value="SELECT FIDE_TOTAL_CUOTAS_USUARIO_FN(:cedula) FROM DUAL")
    String totalCuotasUsuario(@Param("cedula") Integer cedula);
    
    @Procedure(procedureName = "FIDE_CUOTA_INSERT_SP")
    void insertarCuota(
        @Param("P_ID_CUOTA") Integer idCuota,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_FECHA") Date fecha,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_MONTO_PAGADO") BigDecimal montoPagado,
        @Param("P_PERIODICIDAD") String periodicidad,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_CUOTA_UPDATE_SP")
    void updateCuota(
        @Param("P_ID_CUOTA") Integer idCuota,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_FECHA") Date fecha,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_MONTO_PAGADO") BigDecimal montoPagado,
        @Param("P_PERIODICIDAD") String periodicidad,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_CUOTA_DELETE_LOGICO_SP")
    void eliminarCuota(
        @Param("P_ID_CUOTA") Integer idCuota,
        @Param("P_CEDULA") Integer cedula
    );
}
