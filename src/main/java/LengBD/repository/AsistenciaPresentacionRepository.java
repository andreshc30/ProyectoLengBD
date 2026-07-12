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
import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.AsistenciaPresentacionListadoDTO;
import LengBD.domain.AsistenciaPresentacionListadoDTO;
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
public class AsistenciaPresentacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall asistenciaPresentacionInsertCall;
    private SimpleJdbcCall asistenciaPresentacionUpdateCall;
    private SimpleJdbcCall asistenciaPresentacionDeleteCall;
    private SimpleJdbcCall asistenciaPresentacionReadAllCall;

    @PostConstruct
    public void init() {
        asistenciaPresentacionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASISTENCIA_PRESENTACION_INSERT_SP");

        asistenciaPresentacionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASISTENCIA_PRESENTACION_UPDATE_SP");

        asistenciaPresentacionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASISTENCIA_PRESENTACION_DELETE_SP");

        asistenciaPresentacionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ASISTENCIA_PRESENTACION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(AsistenciaPresentacionListadoDTO.class));;
    }

    public void insertarAsistenciaPresentacion(AsistenciaPresentacion asistenciaPresentacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PRESENTACION", asistenciaPresentacion.getIdPresentacion());
        params.put("P_CEDULA", asistenciaPresentacion.getCedula());
        params.put("P_ID_ESTADO", asistenciaPresentacion.getIdEstado());
        asistenciaPresentacionInsertCall.execute(params);
    }

    public void actualizarAsistenciaPresentacion(AsistenciaPresentacion asistenciaPresentacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ASISTENCIA_PRESENTACION", asistenciaPresentacion.getIdAsistenciaPresentacion());
        params.put("P_ID_PRESENTACION", asistenciaPresentacion.getIdPresentacion());
        params.put("P_CEDULA", asistenciaPresentacion.getCedula());
        params.put("P_ID_ESTADO", asistenciaPresentacion.getIdEstado());
        asistenciaPresentacionUpdateCall.execute(params);
    }

    public List<AsistenciaPresentacionListadoDTO> readAllAsistenciaPresentacion() {
        Map<String, Object> result = asistenciaPresentacionReadAllCall.execute();
        return (List<AsistenciaPresentacionListadoDTO>) result.get("p_cursor");
    }

    public void deleteAsistenciaPresentacion(AsistenciaPresentacion asistenciaPresentacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ASISTENCIA_PRESENTACION", asistenciaPresentacion.getIdAsistenciaPresentacion());
        asistenciaPresentacionDeleteCall.execute(params);
    }

}
