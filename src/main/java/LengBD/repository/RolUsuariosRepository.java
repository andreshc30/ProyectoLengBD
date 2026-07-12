/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.RolUsuario;
import LengBD.domain.RolUsuarioListadoDTO;
import LengBD.domain.RolUsuarioListadoDTO;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class RolUsuariosRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall rolUsuarioInsertCall;
    private SimpleJdbcCall rolUsuarioUpdateCall;
    private SimpleJdbcCall rolUsuarioDeleteCall;
    private SimpleJdbcCall rolUsuarioReadAllCall;

    @PostConstruct
    public void init() {
        rolUsuarioInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_USUARIOS_INSERT_SP");

        rolUsuarioUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_USUARIOS_UPDATE_SP");

        rolUsuarioDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_USUARIOS_DELETE_SP");

        rolUsuarioReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ROL_USUARIOS_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(RolUsuarioListadoDTO.class));;
    }

    public void insertarRolUsuario(RolUsuario rolUsuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", rolUsuario.getCedula());
        params.put("P_ID_ROL", rolUsuario.getIdRol());
        params.put("P_ID_ESTADO", rolUsuario.getIdEstado());
        rolUsuarioUpdateCall.execute(params);
        rolUsuarioInsertCall.execute(params);
    }

    public void actualizarRolUsuario(RolUsuario rolUsuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", rolUsuario.getCedula());
        params.put("P_ID_ROL", rolUsuario.getIdRol());
        params.put("P_ID_ESTADO", rolUsuario.getIdEstado());
        rolUsuarioUpdateCall.execute(params);
    }

    public 
        List<RolUsuarioListadoDTO> readAllRolUsuario() {
        Map<String, Object> result = rolUsuarioReadAllCall.execute();
        return (List<RolUsuarioListadoDTO>) result.get("p_cursor");
    }

    public void deleteRolUsuario(RolUsuario rolUsuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", rolUsuario.getCedula());
        params.put("P_ID_ROL", rolUsuario.getIdRol());
        rolUsuarioDeleteCall.execute(params);
    }

}
