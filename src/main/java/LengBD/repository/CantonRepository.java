/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
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
public interface CantonRepository extends JpaRepository<Canton, Integer>{
        
    @Procedure(procedureName = "FIDE_CANTON_INSERT_SP")
    void insertarCanton(
        @Param("P_ID_CANTON") Integer idCanton,
        @Param("P_NOMBRE") String nombre,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_CANTON_UPDATE_SP")
    void updateCanton(
        @Param("P_ID_CANTON") Integer idCanton,
        @Param("P_NOMBRE") String nombre,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_CANTON_DELETE_LOGICO_SP")
    void eliminarCanton(
        @Param("P_ID_CANTON") Integer idCanton
    );
    
}
