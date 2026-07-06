/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
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
public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer>{
            
    @Procedure(procedureName = "FIDE_INSTRUMENTO_INSERT_SP")
    void insertarInstrumento(
        @Param("P_ID_INSTRUMENTO") Integer idInstrumento,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_ID_ESTADO") Integer idEstado,
        @Param("P_NOMBRE") String nombre
        
    );
    
    @Procedure(procedureName = "FIDE_INSTRUMENTO_UPDATE_SP")
    void updateInstrumento(
        @Param("P_ID_INSTRUMENTO") Integer idInstrumento,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_ID_ESTADO") Integer idEstado,
        @Param("P_NOMBRE") String nombre
    );
    
    @Procedure(procedureName = "FIDE_INSTRUMENTO_DELETE_LOGICO_SP")
    void eliminarInstrumento(
        @Param("P_ID_INSTRUMENTO") Integer idInstrumento
    );
}
