/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;
import LengBD.domain.Obra;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer>{        
    @Procedure(procedureName = "FIDE_OBRA_INSERT_SP")
    void insertarObra(
        @Param("P_ID_OBRA") Integer idObra,
        @Param("P_NOMBRE") String nombre,
        @Param("P_FECHA") Date fecha,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_OBRA_UPDATE_SP")
    void updateObra(
        @Param("P_ID_OBRA") Integer idObra,
        @Param("P_NOMBRE") String nombre,
        @Param("P_FECHA") Date fecha,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_OBRA_DELETE_LOGICO_SP")
    void eliminarObra(
        @Param("P_ID_OBRA") Integer idObra
    );
    
       
}
