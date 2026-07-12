/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.CategoriaMovimiento;
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
public interface CategoriaMovimientoRepository extends JpaRepository<CategoriaMovimiento, Integer>{
        
    @Procedure(procedureName = "FIDE_CATEGORIA_MOVIMIENTO_INSERT_SP")
    void insertarCategoriaMovimiento(
        @Param("P_ID_CATEGORIA_MOVIMIENTO") Integer IdCategoriaMovimiento,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_CATEGORIA_MOVIMIENTO_UPDATE_SP")
    void updateCategoriaMovimiento(
        @Param("P_ID_CATEGORIA_MOVIMIENTO") Integer IdCategoriaMovimiento,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_CATEGORIA_MOVIMIENTO_DELETE_LOGICO_SP")
    void eliminarCategoriaMovimiento(
        @Param("P_ID_CATEGORIA_MOVIMIENTO") Integer IdCategoriaMovimiento
    );
    
}
