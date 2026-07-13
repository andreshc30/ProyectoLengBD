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
import LengBD.domain.Suscripcion;
import LengBD.domain.SuscripcionListadoDTO;
import LengBD.domain.SuscripcionListadoDTO;
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
public class SuscripcionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall suscripcionInsertCall;
    private SimpleJdbcCall suscripcionUpdateCall;
    private SimpleJdbcCall suscripcionDeleteCall;
    private SimpleJdbcCall suscripcionReadAllCall;

    @PostConstruct
    public void init() {
        suscripcionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_INSERT_SP");

        suscripcionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_UPDATE_SP");

        suscripcionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_DELETE_LOGICO_SP");

        suscripcionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_SUSCRIPCION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(SuscripcionListadoDTO.class));;
    }

    public void insertarSuscripcion(Suscripcion suscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", suscripcion.getNombre());
        params.put("P_FECHA_INICIO", suscripcion.getFechaInicio());
        params.put("P_FECHA_FINAL", suscripcion.getFechaFinal());
        params.put("P_AUTO_RENOVAR", suscripcion.getAutoRenovar());
        params.put("P_ID_TIPO_PLAN", suscripcion.getIdTipoPlan());
        params.put("P_ID_BANDA", suscripcion.getIdBanda());
        params.put("P_ID_ESTADO", suscripcion.getIdEstado());
        suscripcionInsertCall.execute(params);
    }

    public void actualizarSuscripcion(Suscripcion suscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_SUSCRIPCION", suscripcion.getIdSuscripcion());
        params.put("P_NOMBRE", suscripcion.getNombre());
        params.put("P_FECHA_INICIO", suscripcion.getFechaInicio());
        params.put("P_FECHA_FINAL", suscripcion.getFechaFinal());
        params.put("P_AUTO_RENOVAR", suscripcion.getAutoRenovar());
        params.put("P_ID_TIPO_PLAN", suscripcion.getIdTipoPlan());
        params.put("P_ID_BANDA", suscripcion.getIdBanda());
        params.put("P_ID_ESTADO", suscripcion.getIdEstado());
        suscripcionUpdateCall.execute(params);
    }

    public 
        List<SuscripcionListadoDTO> readAllSuscripcion() {
        Map<String, Object> result = suscripcionReadAllCall.execute();
        return (List<SuscripcionListadoDTO>) result.get("p_cursor");
    }

    public void deleteSuscripcion(Suscripcion suscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_SUSCRIPCION", suscripcion.getIdSuscripcion());
        suscripcionDeleteCall.execute(params);
    }

}
