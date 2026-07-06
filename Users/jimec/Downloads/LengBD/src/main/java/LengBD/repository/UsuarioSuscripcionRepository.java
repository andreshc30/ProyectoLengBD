/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.Usuario;
import LengBD.domain.UsuarioSuscripcion;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peper
 */
@Repository
public interface UsuarioSuscripcionRepository  extends JpaRepository<UsuarioSuscripcion, Integer> {
    
    @Procedure(procedureName = "FIDE_USUARIO_SUSCRIPCION_INSERT_SP")
    void insertarUsuarioSuscripcion(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_SUSCRIPCION") Integer idSuscripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    
    @Procedure(procedureName = "FIDE_USUARIO_SUSCRIPCION_UPDATE_SP")
    void actualizarUsuarioSuscripcion(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_SUSCRIPCION") Integer idSuscripcion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    
    @Procedure(procedureName = "FIDE_USUARIO_SUSCRIPCION_DELETE_LOGICO_SP")
    void eliminarUsuarioSuscripcion(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_ID_SUSCRIPCION") Integer idSuscripcion   );
    
    
    
    
    
}
