/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.Telefono;
import LengBD.domain.Tipo;
import LengBD.domain.Usuario;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    
    

    @Procedure(procedureName = "FIDE_TIPO_INSERT_SP")
    void insertarTipo(
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    
    @Procedure(procedureName = "FIDE_TIPO_UPDATE_SP")
    void updateTipo(
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_TIPO_DELETE_LOGICO_SP")
    void eliminarTipo(
        @Param("P_ID_TIPO") Integer idTipo
    );
}
