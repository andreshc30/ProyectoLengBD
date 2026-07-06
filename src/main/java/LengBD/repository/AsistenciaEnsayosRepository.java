/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaEnsayos;
import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Evento;
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
public interface AsistenciaEnsayosRepository extends JpaRepository<AsistenciaEnsayos, Integer>{
        
    @Query(nativeQuery=true, value="SELECT FIDE_PORCENTAJE_ASISTENCIA_ENSAYO_FN(:cedula) FROM DUAL")
    String porcentajeAsistenciaEnsayo(@Param("cedula") Integer cedula);
    
    @Procedure(procedureName = "FIDE_ASISTENCIA_ENSAYO_INSERT_SP")
    void insertarAsistenciaEnsayo(
        @Param("P_ID_ASISTENCIA_ENSAYOS") Integer idAsistenciaEnsayos,
        @Param("P_ID_ENSAYO") Integer idEnsayo,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_ASISTENCIA_ENSAYO_UPDATE_SP")
    void updateAsistenciaEnsayo(
        @Param("P_ID_ASISTENCIA_ENSAYOS") Integer idAsistenciaEnsayos,
        @Param("P_ID_ENSAYO") Integer idEnsayo,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_ASISTENCIA_ENSAYO_DELETE_LOGICO_SP")
    void eliminarAsistenciaEnsayo(
        @Param("P_ID_ASISTENCIA_ENSAYOS") Integer idAsistenciaEnsayos,
        @Param("P_ID_ENSAYO") Integer idEnsayo
    );
}
