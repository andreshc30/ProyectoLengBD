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
import LengBD.domain.Estado;
import LengBD.domain.EstadoListadoDTO;
import LengBD.domain.EstadoListadoDTO;
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
public class EstadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall estadoInsertCall;
    private SimpleJdbcCall estadoUpdateCall;
    private SimpleJdbcCall estadoDeleteCall;
    private SimpleJdbcCall estadoReadAllCall;

    @PostConstruct
    public void init() {
        estadoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ESTADO_INSERT_SP");

        estadoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ESTADO_UPDATE_SP");

        estadoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ESTADO_DELETE_SP");

        estadoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ESTADO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(EstadoListadoDTO.class));;
    }

    public void insertarEstado(Estado estado) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ESTADO_ESTADO", estado.getEstado());
        estadoInsertCall.execute(params);
    }

    public void actualizarEstado(Estado estado) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ESTADO_ID_ESTADO", estado.getIdEstado());
        params.put("P_ESTADO_ESTADO", estado.getEstado());
        estadoUpdateCall.execute(params);
    }

    public List<EstadoListadoDTO> readAllEstado() {
        Map<String, Object> result = estadoReadAllCall.execute();
        return (List<EstadoListadoDTO>) result.get("p_cursor");
    }

    public void deleteEstado(Estado estado) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ESTADO_ID_ESTADO", estado.getIdEstado());
        estadoDeleteCall.execute(params);
    }

}
