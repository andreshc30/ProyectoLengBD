/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Evento;
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
public interface EventoRepository extends JpaRepository<Evento, Integer>{
        
    @Query(nativeQuery=true, value="SELECT FIDE_OBTENER_NOMBRE_EVENTO_FN(:idEvento) FROM DUAL")
    String obtenerNombreEvento(@Param("idEvento") Integer idEvento);
    
    @Query(nativeQuery=true, value="SELECT FIDE_TOTAL_EVENTOS_ACTIVOS_FN() FROM DUAL")
    String totalEventosActivos();
    
    @Procedure(procedureName = "FIDE_EVENTO_INSERT_SP")
    void insertarEvento(
        @Param("P_ID_EVENTO") Integer idEvento,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DETALLE") String detalle,
        @Param("P_FECHA") Date fecha,
        @Param("P_ID_DIRECCION") Integer idDireccion,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_EVENTO_UPDATE_SP")
    void updateEvento(
        @Param("P_ID_EVENTO") Integer idEvento,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DETALLE") String detalle,
        @Param("P_FECHA") Date fecha,
        @Param("P_ID_DIRECCION") Integer idDireccion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_EVENTO_DELETE_LOGICO_SP")
    void eliminarEvento(
        @Param("P_ID_EVENTO") Integer idEvento
    );
    
}
