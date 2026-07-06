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
import LengBD.domain.RedSocialBanda;
import LengBD.domain.Rol;
import LengBD.domain.RolUsuario;
import LengBD.domain.Seccion;
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
public interface SeccionRepository extends JpaRepository<Seccion, Integer>{
        
    @Procedure(procedureName = "FIDE_SECCION_INSERT_SP")
    void insertarSeccion(
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_SECCION_UPDATE_SP")
    void updateSeccion(
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_SECCION_DELETE_LOGICO_SP")
    void eliminarSeccion(
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_NOMBRE") String nombre,
        @Param("P_DESCRIPCION") String descripcion
    );
    
}
