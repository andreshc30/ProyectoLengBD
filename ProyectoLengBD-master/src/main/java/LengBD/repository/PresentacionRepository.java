/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Cuota;
import LengBD.domain.Direccion;
import LengBD.domain.Planes;
import LengBD.domain.Presentacion;
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
public interface PresentacionRepository extends JpaRepository<Presentacion, Integer>{
        
    @Procedure(procedureName = "FIDE_PRESENTACION_INSERT_SP")
    void insertarPresentacion(
        @Param("P_ID_PRESENTACION") Integer idPresentacion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_HORA_INICIO") Date horaInicio,
        @Param("P_HORA_FIN") Date horaFin,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_LUGAR") Integer idLugar,
        @Param("P_ID_ESTADO") Integer idEstado,
        @Param("P_FECHA") Date fecha
        
    );
    
    @Procedure(procedureName = "FIDE_PRESENTACION_UPDATE_SP")
    void updatePresentacion(
        @Param("P_ID_PRESENTACION") Integer idPresentacion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_HORA_INICIO") Date horaInicio,
        @Param("P_HORA_FIN") Date horaFin,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_LUGAR") Integer idLugar,
        @Param("P_ID_ESTADO") Integer idEstado,
        @Param("P_FECHA") Date fecha
    );
    
    @Procedure(procedureName = "FIDE_PRESENTACION_DELETE_LOGICO_SP")
    void eliminarPresentacion(
        @Param("P_ID_PRESENTACION") Integer idPresentacion
    );
}
