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
import LengBD.domain.Rol;
import LengBD.domain.RolListadoDTO;
import LengBD.domain.RolListadoDTO;
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
public class RolRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall rolInsertCall;
    private SimpleJdbcCall rolUpdateCall;
    private SimpleJdbcCall rolDeleteCall;
    private SimpleJdbcCall rolReadAllCall;

    @PostConstruct
    public void init() {
        rolInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_INSERT_SP");

        rolUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_UPDATE_SP");

        rolDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_DELETE_SP");

        rolReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ROL_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(RolListadoDTO.class));;
    }

    public void insertarRol(Rol rol) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", rol.getNombre());
        params.put("P_ID_ESTADO", rol.getIdEstado());
        rolUpdateCall.execute(params);
        rolInsertCall.execute(params);
    }

    public void actualizarRol(Rol rol) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ROL", rol.getIdRol());
        params.put("P_NOMBRE", rol.getNombre());
        params.put("P_ID_ESTADO", rol.getIdEstado());
        rolUpdateCall.execute(params);
    }

    public 
        List<RolListadoDTO> readAllRol() {
        Map<String, Object> result = rolReadAllCall.execute();
        return (List<RolListadoDTO>) result.get("p_cursor");
    }

    public void deleteRol(Rol rol) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ROL", rol.getIdRol());
        rolDeleteCall.execute(params);
    }

}
