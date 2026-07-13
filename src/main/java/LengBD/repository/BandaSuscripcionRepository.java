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
import LengBD.domain.BandaSuscripcion;
import LengBD.domain.BandaSuscripcionListadoDTO;
import LengBD.domain.BandaSuscripcionListadoDTO;
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
public class BandaSuscripcionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall bandaSuscripcionInsertCall;
    private SimpleJdbcCall bandaSuscripcionUpdateCall;
    private SimpleJdbcCall bandaSuscripcionDeleteCall;
    private SimpleJdbcCall bandaSuscripcionReadAllCall;

    @PostConstruct
    public void init() {
        bandaSuscripcionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_SUSCRIPCION_INSERT_SP");

        bandaSuscripcionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_SUSCRIPCION_UPDATE_SP");

        bandaSuscripcionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_SUSCRIPCION_DELETE_LOGICO_SP");

        bandaSuscripcionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_BANDA_SUSCRIPCION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(BandaSuscripcionListadoDTO.class));;
    }

    public void insertarBandaSuscripcion(BandaSuscripcion bandaSuscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", bandaSuscripcion.getIdBanda());
        params.put("P_ID_SUSCRIPCION", bandaSuscripcion.getIdSuscripcion());
        params.put("P_ID_ESTADO", bandaSuscripcion.getIdEstado());
        bandaSuscripcionInsertCall.execute(params);
    }

    public void actualizarBandaSuscripcion(BandaSuscripcion bandaSuscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", bandaSuscripcion.getIdBanda());
        params.put("P_ID_SUSCRIPCION", bandaSuscripcion.getIdSuscripcion());
        params.put("P_ID_ESTADO", bandaSuscripcion.getIdEstado());
        bandaSuscripcionUpdateCall.execute(params);
    }

    public 
        List<BandaSuscripcionListadoDTO> readAllBandaSuscripcion() {
        Map<String, Object> result = bandaSuscripcionReadAllCall.execute();
        return (List<BandaSuscripcionListadoDTO>) result.get("p_cursor");
    }

    public void deleteBandaSuscripcion(BandaSuscripcion bandaSuscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", bandaSuscripcion.getIdBanda());
        params.put("P_ID_SUSCRIPCION", bandaSuscripcion.getIdSuscripcion());
        bandaSuscripcionDeleteCall.execute(params);
    }

}
