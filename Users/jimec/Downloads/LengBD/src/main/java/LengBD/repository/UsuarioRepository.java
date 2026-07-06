/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.Usuario;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    

    @Procedure(procedureName = "FIDE_USUARIO_INSERT_SP")
    void insertarUsuario(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_NOMBRE") String nombre,
        @Param("P_PRIMER_APELLIDO") String primerApellido,
        @Param("P_SEGUNDO_APELLIDO") String segundoApellido,
        @Param("P_FECHA_INGRESO") LocalDate fechaIngreso,
        @Param("P_LOGO_URL") String logoUrl,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_CORREO") Integer idCorreo,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    
    @Procedure(procedureName = "FIDE_USUARIO_UPDATE_SP")
    void actualizarUsuario(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_NOMBRE") String nombre,
        @Param("P_PRIMER_APELLIDO") String primerApellido,
        @Param("P_SEGUNDO_APELLIDO") String segundoApellido,
        @Param("P_FECHA_INGRESO") LocalDate fechaIngreso,
        @Param("P_LOGO_URL") String logoUrl,
        @Param("P_ID_TIPO") Integer idTipo,
        @Param("P_ID_CORREO") Integer idCorreo,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_USUARIO_DELETE_LOGICO_SP")
    void eliminarUsuario(
        @Param("P_CEDULA") Integer cedula
    );
}
