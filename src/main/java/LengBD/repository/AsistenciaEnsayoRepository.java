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
import LengBD.domain.AsistenciaEnsayo;
import LengBD.domain.AsistenciaEnsayoListadoDTO;
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
public class AsistenciaEnsayoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall asistenciaEnsayoInsertCall;
    private SimpleJdbcCall asistenciaEnsayoUpdateCall;
    private SimpleJdbcCall asistenciaEnsayoDeleteCall;
    private SimpleJdbcCall asistenciaEnsayoReadAllCall;

    @PostConstruct
    public void init() {
        asistenciaEnsayoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASISTENCIA_ENSAYOS_INSERT_SP");

        asistenciaEnsayoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASISTENCIA_ENSAYOS_UPDATE_SP");

        asistenciaEnsayoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASISTENCIA_ENSAYOS_DELETE_SP");

        asistenciaEnsayoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ASISTENCIA_ENSAYOS_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(AsistenciaEnsayoListadoDTO.class));;
    }

    public void insertarAsistenciaEnsayo(AsistenciaEnsayo asistenciaEnsayo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ENSAYO", asistenciaEnsayo.getIdEnsayo());
        params.put("P_CEDULA", asistenciaEnsayo.getCedula());
        params.put("P_ID_ESTADO", asistenciaEnsayo.getIdEstado());
        asistenciaEnsayoInsertCall.execute(params);
    }

    public void actualizarAsistenciaEnsayo(AsistenciaEnsayo asistenciaEnsayo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ASISTENCIA_ENSAYOS", asistenciaEnsayo.getIdAsistenciaEnsayos());
        params.put("P_ID_ENSAYO", asistenciaEnsayo.getIdEnsayo());
        params.put("P_CEDULA", asistenciaEnsayo.getCedula());
        params.put("P_ID_ESTADO", asistenciaEnsayo.getIdEstado());
        asistenciaEnsayoUpdateCall.execute(params);
    }

    public List<AsistenciaEnsayoListadoDTO> readAllAsistenciaEnsayo() {
        Map<String, Object> result = asistenciaEnsayoReadAllCall.execute();
        return (List<AsistenciaEnsayoListadoDTO>) result.get("p_cursor");
    }

    public void deleteAsistenciaEnsayo(AsistenciaEnsayo asistenciaEnsayo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ASISTENCIA_ENSAYOS", asistenciaEnsayo.getIdAsistenciaEnsayos());
        asistenciaEnsayoDeleteCall.execute(params);
    }

}
