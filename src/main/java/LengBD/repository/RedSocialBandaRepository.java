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
public interface RedSocialBandaRepository extends JpaRepository<RedSocialBanda, Integer>{
        
    @Procedure(procedureName = "FIDE_RED_SOCIAL_BANDA_INSERT_SP")
    void insertarRedSocialBanda(
        @Param("P_ID_RED_SOCIAL") Integer idRedSocial,
        @Param("P_PLATAFORMA") String plataforma,
        @Param("P_LINK_BANDA") String linkBanda,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_RED_SOCIAL_BANDA_UPDATE_SP")
    void updateRedSocialBanda(
        @Param("P_ID_RED_SOCIAL") Integer idRedSocial,
        @Param("P_PLATAFORMA") String plataforma,
        @Param("P_LINK_BANDA") String linkBanda,
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_RED_SOCIAL_BANDA_DELETE_LOGICO_SP")
    void eliminarRedSocialBanda(
        @Param("P_ID_RED_SOCIAL") Integer idRedSocial
    );
    
}
