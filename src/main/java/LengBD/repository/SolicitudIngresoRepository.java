/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Caracteristicas;
import LengBD.domain.Instrumento;
import LengBD.domain.SolicitudIngreso;
import LengBD.domain.Suscripcion;
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
public interface SolicitudIngresoRepository extends JpaRepository<SolicitudIngreso, Integer>{
        
    @Procedure(procedureName = "FIDE_SOLICITUD_INGRESO_INSERT_SP")
    void insertarSolicitudIngreso(
        @Param("P_ID_SOLICITUD") Integer idSolicitud,
        @Param("P_NOMBRE") String nombre,
        @Param("P_PRIMER_APELLIDO") String primerApellido,
        @Param("P_SEGUNDO_APELLIDO") String segundoApellido,
        @Param("P_FECHA_SOLICITUD") Date fechaSolicitud,
        @Param("P_ID_CORREO") Integer idCorreo,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_SOLICITUD_INGRESO_UPDATE_SP")
    void updateSolicitudIngreso(
        @Param("P_ID_SOLICITUD") Integer idSolicitud,
        @Param("P_NOMBRE") String nombre,
        @Param("P_PRIMER_APELLIDO") String primerApellido,
        @Param("P_SEGUNDO_APELLIDO") String segundoApellido,
        @Param("P_FECHA_SOLICITUD") Date fechaSolicitud,
        @Param("P_ID_CORREO") Integer idCorreo,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_SOLICITUD_INGRESO_DELETE_LOGICO_SP")
    void eliminarSolicitudIngreso(
        @Param("P_ID_SOLICITUD") Integer idSolicitud
    );
    
}
