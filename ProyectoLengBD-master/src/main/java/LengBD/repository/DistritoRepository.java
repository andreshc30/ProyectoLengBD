/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Cuota;
import LengBD.domain.Direccion;
import LengBD.domain.Distrito;
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
public interface DistritoRepository extends JpaRepository<Distrito, Integer>{
        
    @Procedure(procedureName = "FIDE_DISTRITO_INSERT_SP")
    void insertarDistrito(
        @Param("P_ID_DISTRITO") Integer idDistrito,
        @Param("P_NOMBRE") String nombre,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_DISTRITO_UPDATE_SP")
    void updateDistrito(
        @Param("P_ID_DISTRITO") Integer idDistrito,
        @Param("P_NOMBRE") String nombre,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_DISTRITO_DELETE_LOGICO_SP")
    void eliminarDistrito(
        @Param("P_ID_DISTRITO") Integer idDistrito
    );
}
