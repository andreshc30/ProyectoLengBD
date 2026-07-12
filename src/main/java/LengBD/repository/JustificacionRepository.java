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
import LengBD.domain.Justificacion;
import LengBD.domain.JustificacionListadoDTO;
import LengBD.domain.JustificacionListadoDTO;
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
public class JustificacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall justificacionInsertCall;
    private SimpleJdbcCall justificacionUpdateCall;
    private SimpleJdbcCall justificacionDeleteCall;
    private SimpleJdbcCall justificacionReadAllCall;

    @PostConstruct
    public void init() {
        justificacionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_JUSTIFICACION_INSERT_SP");

        justificacionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_JUSTIFICACION_UPDATE_SP");

        justificacionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_JUSTIFICACION_DELETE_SP");

        justificacionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_JUSTIFICACION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(JustificacionListadoDTO.class));;
    }

    public void insertarJustificacion(Justificacion justificacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_MOTIVO", justificacion.getMotivo());
        params.put("P_ID_ASISTENCIA_ENSAYOS", justificacion.getIdAsistenciaEnsayos());
        params.put("P_ID_ESTADO", justificacion.getIdEstado());
        justificacionUpdateCall.execute(params);
        justificacionInsertCall.execute(params);
    }

    public void actualizarJustificacion(Justificacion justificacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_JUSTIFICACION", justificacion.getIdJustificacion());
        params.put("P_MOTIVO", justificacion.getMotivo());
        params.put("P_ID_ASISTENCIA_ENSAYOS", justificacion.getIdAsistenciaEnsayos());
        params.put("P_ID_ESTADO", justificacion.getIdEstado());
        justificacionUpdateCall.execute(params);
    }

    public 
        List<JustificacionListadoDTO> readAllJustificacion() {
        Map<String, Object> result = justificacionReadAllCall.execute();
        return (List<JustificacionListadoDTO>) result.get("p_cursor");
    }

    public void deleteJustificacion(Justificacion justificacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_JUSTIFICACION", justificacion.getIdJustificacion());
        justificacionDeleteCall.execute(params);
    }

}
