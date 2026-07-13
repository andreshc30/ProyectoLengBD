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
import LengBD.domain.Seccion;
import LengBD.domain.SeccionListadoDTO;
import LengBD.domain.SeccionListadoDTO;
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
public class SeccionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall seccionInsertCall;
    private SimpleJdbcCall seccionUpdateCall;
    private SimpleJdbcCall seccionDeleteCall;
    private SimpleJdbcCall seccionReadAllCall;

    @PostConstruct
    public void init() {
        seccionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SECCION_INSERT_SP");

        seccionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SECCION_UPDATE_SP");

        seccionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SECCION_DELETE_LOGICO_SP");

        seccionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_SECCION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(SeccionListadoDTO.class));;
    }

    public void insertarSeccion(Seccion seccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", seccion.getNombre());
        params.put("P_DESCRIPCION", seccion.getDescripcion());
        params.put("P_ID_ESTADO", seccion.getIdEstado());
        params.put("P_ID_BANDA", seccion.getIdBanda());
        seccionInsertCall.execute(params);
    }

    public void actualizarSeccion(Seccion seccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_SECCION", seccion.getIdSeccion());
        params.put("P_NOMBRE", seccion.getNombre());
        params.put("P_DESCRIPCION", seccion.getDescripcion());
        params.put("P_ID_ESTADO", seccion.getIdEstado());
        params.put("P_ID_BANDA", seccion.getIdBanda());
        seccionUpdateCall.execute(params);
    }

    public 
        List<SeccionListadoDTO> readAllSeccion() {
        Map<String, Object> result = seccionReadAllCall.execute();
        return (List<SeccionListadoDTO>) result.get("p_cursor");
    }

    public void deleteSeccion(Seccion seccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_SECCION", seccion.getIdSeccion());
        seccionDeleteCall.execute(params);
    }

}
