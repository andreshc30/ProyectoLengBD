/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsignacionInstrumento;
import LengBD.domain.AsistenciaPresentacion;
import jakarta.persistence.EntityManager;
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
public interface AsistenciaPresentacionRepository extends JpaRepository<AsistenciaPresentacion, Integer>{
    
    @Procedure(procedureName = "FIDE_ASISTENCIA_PRESENTACION_INSERT_SP")
    void insertarAsistenciaPresentacion(
        @Param("P_ID_ASISTENCIA_PRESENTACION") Integer idAsignacionInstrumento,
        @Param("P_ID_PRESENTACION") Integer idPresentacion,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_ASISTENCIA_PRESENTACION_UPDATE_SP")
    void updateAsistenciaPresentacion(
        @Param("P_ID_ASISTENCIA_PRESENTACION") Integer idAsignacionInstrumento,
        @Param("P_ID_PRESENTACION") Integer idPresentacion,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_ASISTENCIA_PRESENTACION_DELETE_LOGICO_SP")
    void eliminarAsistenciaPresentacion(
        @Param("P_ID_ASISTENCIA_PRESENTACION") Integer idAsignacionInstrumento,
        @Param("P_ID_PRESENTACION") Integer idPresentacion
    );
    
}
