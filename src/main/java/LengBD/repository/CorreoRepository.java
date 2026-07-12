/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Correo;
import LengBD.domain.Instrumento;
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
public interface CorreoRepository extends JpaRepository<Correo, Integer>{
        
    @Procedure(procedureName = "FIDE_CORREO_INSERT_SP")
    void insertarCorreo(
        @Param("P_ID_CORREO") Integer idCorreo,
        @Param("P_CORREO") String correo,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_CORREO_UPDATE_SP")
    void updateCorreo(
        @Param("P_ID_CORREO") Integer idCorreo,
        @Param("P_CORREO") String correo,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_CORREO_DELETE_LOGICO_SP")
    void eliminarCorreo(
        @Param("P_ID_CORREO") Integer idCorreo
    );
    
}
