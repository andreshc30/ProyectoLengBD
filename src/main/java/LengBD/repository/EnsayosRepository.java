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
import LengBD.domain.Ensayos;
import LengBD.domain.EnsayosListadoDTO;
import LengBD.domain.EnsayosListadoDTO;
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
public class EnsayosRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall ensayosInsertCall;
    private SimpleJdbcCall ensayosUpdateCall;
    private SimpleJdbcCall ensayosDeleteCall;
    private SimpleJdbcCall ensayosReadAllCall;

    @PostConstruct
    public void init() {
        ensayosInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ENSAYOS_INSERT_SP");

        ensayosUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ENSAYOS_UPDATE_SP");

        ensayosDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ENSAYOS_DELETE_SP");

        ensayosReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ENSAYOS_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(EnsayosListadoDTO.class));;
    }

    public void insertarEnsayos(Ensayos ensayos) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", ensayos.getNombre());
        params.put("P_FECHA_INICIO", ensayos.getFechaInicio());
        params.put("P_FECHA_FIN", ensayos.getFechaFin());
        params.put("P_DESCRIPCION", ensayos.getIdDireccion());
        params.put("P_BANDA", ensayos.getIdBanda());
        params.put("P_ID_ESTADO", ensayos.getIdEstado());
        ensayosUpdateCall.execute(params);
        ensayosInsertCall.execute(params);
    }

    public void actualizarEnsayos(Ensayos ensayos) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ENSAYO", ensayos.getIdEnsayo());
        params.put("P_NOMBRE", ensayos.getNombre());
        params.put("P_FECHA_INICIO", ensayos.getFechaInicio());
        params.put("P_FECHA_FIN", ensayos.getFechaFin());
        params.put("P_DESCRIPCION", ensayos.getIdDireccion());
        params.put("P_BANDA", ensayos.getIdBanda());
        params.put("P_ID_ESTADO", ensayos.getIdEstado());
        ensayosUpdateCall.execute(params);
    }

    public 
        List<EnsayosListadoDTO> readAllEnsayos() {
        Map<String, Object> result = ensayosReadAllCall.execute();
        return (List<EnsayosListadoDTO>) result.get("p_cursor");
    }

    public void deleteEnsayos(Ensayos ensayos) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ENSAYO", ensayos.getIdEnsayo());
        ensayosDeleteCall.execute(params);
    }

}
