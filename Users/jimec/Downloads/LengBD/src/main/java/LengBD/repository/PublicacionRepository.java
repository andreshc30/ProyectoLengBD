/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Caracteristicas;
import LengBD.domain.Instrumento;
import LengBD.domain.Provincia;
import LengBD.domain.Publicacion;
import LengBD.domain.Suscripcion;
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
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer>{
        
    @Procedure(procedureName = "FIDE_PUBLICACION_INSERT_SP")
    void insertarPublicacion(
        @Param("P_ID_PUBLICACION") Integer idPublicacion,
        @Param("P_FECHA") Date fecha,
        @Param("P_TITULO") String titulo,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_RED_SOCIAL") Integer idRedSocial,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_PUBLICACION_UPDATE_SP")
    void updatePublicacion(
        @Param("P_ID_PUBLICACION") Integer idPublicacion,
        @Param("P_FECHA") Date fecha,
        @Param("P_TITULO") String titulo,
        @Param("P_DETALLE") String detalle,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_RED_SOCIAL") Integer idRedSocial,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_PUBLICACION_DELETE_LOGICO_SP")
    void eliminarPublicacion(
        @Param("P_ID_PUBLICACION") Integer idPublicacion
    );
    
}
