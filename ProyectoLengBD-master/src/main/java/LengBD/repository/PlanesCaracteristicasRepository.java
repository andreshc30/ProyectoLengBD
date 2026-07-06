/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Cuota;
import LengBD.domain.Direccion;
import LengBD.domain.Planes;
import LengBD.domain.PlanesCaracteristicas;
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
public interface PlanesCaracteristicasRepository extends JpaRepository<PlanesCaracteristicas, Integer>{
        
    @Procedure(procedureName = "FIDE_PLANES_CARACTERISTICAS_INSERT_SP")
    void insertarPlanesCaracteristicas(
        @Param("P_ID_TIPO_PLAN") Integer idTipoPlan,
        @Param("P_ID_CARACTERISTICA") Integer idCaracteristica,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_PLANES_CARACTERISTICAS_UPDATE_SP")
    void updatePlanesCaracteristicas(
        @Param("P_ID_TIPO_PLAN") Integer idTipoPlan,
        @Param("P_ID_CARACTERISTICA") Integer idCaracteristica,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_PLANES_CARACTERISTICAS_DELETE_LOGICO_SP")
    void eliminarPlanesCaracteristicas(
        @Param("P_ID_TIPO_PLAN") Integer idTipoPlan,
        @Param("P_ID_CARACTERISTICA") Integer idCaracteristica
    );
}
