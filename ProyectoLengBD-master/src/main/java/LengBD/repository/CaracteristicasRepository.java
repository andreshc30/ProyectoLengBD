/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Caracteristicas;
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
public interface CaracteristicasRepository extends JpaRepository<Caracteristicas, Integer>{
        
    @Procedure(procedureName = "FIDE_CARACTERISTICAS_INSERT_SP")
    void insertarCaracteristicas(
        @Param("P_ID_CARACTERISTICAS") Integer idCaracteristicas,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_CARACTERISTICAS_UPDATE_SP")
    void updateCaracteristicas(
        @Param("P_ID_CARACTERISTICAS") Integer idCaracteristicas,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_CARACTERISTICAS_DELETE_LOGICO_SP")
    void eliminarCaracteristicas(
        @Param("P_ID_CARACTERISTICAS") Integer idCaracteristicas
    );
    
}
