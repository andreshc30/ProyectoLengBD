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
import LengBD.domain.Usuario;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {
    
    

    @Procedure(procedureName = "FIDE_TELEFONO_INSERT_SP")
    void insertarTelefono(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_TELEFONO") String telefono,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    
    @Procedure(procedureName = "FIDE_TELEFONO_UPDATE_SP")
    void updateTelefono(
        @Param("P_CEDULA") Integer cedula,
        @Param("P_TELEFONO") String telefono,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_TELEFONO_DELETE_LOGICO_SP")
    void eliminarTelefono(
        @Param("P_CEDULA") Integer cedula
    );
}
