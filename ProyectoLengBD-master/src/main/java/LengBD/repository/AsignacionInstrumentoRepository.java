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
public interface AsignacionInstrumentoRepository extends JpaRepository<AsignacionInstrumento, Integer>{
    
    @Procedure(procedureName = "FIDE_USUARIO_INSTRUMENTO_SECCION_SP")
    void usuarioInstrumentoSeccionSP(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_NOMBRE_INSTRUMENTO") String nombreInstrumento,
        @Param("P_NOMBRE_SECCION") String nombreSeccion,
        @Param("P_FECHA_INICIO") Date fechaInicio,
        @Param("P_FECHA_FINAL") Date fechaFinal,
        @Param("P_NOMBRE_USUARIO") String nombreUsuario,
        @Param("P_PRIMER_APELLIDO") String primerApellido,
        @Param("P_SEGUNDO_APELLIDO") String segundoApellido,
        @Param("P_MENSAJE") String mensaje
        
    );
    
    @Procedure(procedureName = "FIDE_ASIGNACION_INSTRUMENTO_INSERT_SP")
    void insertarAsignacionInstrumento(
        @Param("P_ID_ASIGNACION_INSTRUMENTO") Integer idAsignacionInstrumento,
        @Param("P_FECHA_INICIO") Date fechaInicio,
        @Param("P_FECHA_FINAL") Date fechaFinal,
        @Param("P_MOTIVO") String motivo,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_INSTRUMENTO") Integer idInstrumento,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_ASIGNACION_INSTRUMENTO_UPDATE_SP")
    void updateAsignacionInstrumento(
        @Param("P_ID_ASIGNACION_INSTRUMENTO") Integer idAsignacionInstrumento,
        @Param("P_FECHA_INICIO") Date fechaInicio,
        @Param("P_FECHA_FINAL") Date fechaFinal,
        @Param("P_MOTIVO") String motivo,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_INSTRUMENTO") Integer idInstrumento,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_ASIGNACION_INSTRUMENTO_DELETE_LOGICO_SP")
    void eliminarAsignacionInstrumento(
        @Param("P_ID_ASIGNACION_INSTRUMENTO") Integer idAsignacionInstrumento,
        @Param("P_ID_PRESENTACION") Integer idPresentacion
    );
    
}
