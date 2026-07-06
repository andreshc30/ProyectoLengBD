/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Cuota;
import LengBD.domain.Direccion;
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
public interface DireccionRepository extends JpaRepository<Direccion, Integer>{
        
    @Procedure(procedureName = "FIDE_DIRECCION_INSERT_SP")
    void insertarDireccion(
        @Param("P_ID_DIRECCION") Integer idDireccion,
        @Param("P_ID_PROVINCIA") Integer idProvincia,
        @Param("P_ID_CANTON") Integer idCanton,
        @Param("P_ID_DISTRITO") Integer idDistrito,
        @Param("P_OTROS_DETALLES") Integer otrosDetalles,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_DIRECCION_UPDATE_SP")
    void updateDireccion(
        @Param("P_ID_DIRECCION") Integer idDireccion,
        @Param("P_ID_PROVINCIA") Integer idProvincia,
        @Param("P_ID_CANTON") Integer idCanton,
        @Param("P_ID_DISTRITO") Integer idDistrito,
        @Param("P_OTROS_DETALLES") Integer otrosDetalles,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_DIRECCION_DELETE_LOGICO_SP")
    void eliminarDireccion(
        @Param("P_ID_DIRECCION") Integer idDireccion,
        @Param("P_ID_PROVINCIA") Integer idProvincia,
        @Param("P_ID_CANTON") Integer idCanton,
        @Param("P_ID_DISTRITO") Integer idDistrito
    );
}
