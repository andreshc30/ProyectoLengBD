/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Instrumento;
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
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer>{
        
    @Procedure(procedureName = "FIDE_SUSCRIPCION_INSERT_SP")
    void insertarSuscripcion(
        @Param("P_ID_SUSCRIPCION") Integer idSuscripcion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_FECHA_INICIO") Date fechaInicio,
        @Param("P_FECHA_FINAL") Date fechaFinal,
        @Param("P_AUTO_RENOVAR") String autoRenovar,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_SUSCRIPCION_UPDATE_SP")
    void updateSuscripcion(
        @Param("P_ID_SUSCRIPCION") Integer idSuscripcion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_FECHA_INICIO") Date fechaInicio,
        @Param("P_FECHA_FINAL") Date fechaFinal,
        @Param("P_AUTO_RENOVAR") String autoRenovar,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_SUSCRIPCION_DELETE_LOGICO_SP")
    void eliminarSuscripcion(
        @Param("P_ID_CARACTERISTICAS") Integer idSuscripcion
    );
    
}
