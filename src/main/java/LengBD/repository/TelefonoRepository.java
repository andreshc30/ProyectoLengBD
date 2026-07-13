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
import LengBD.domain.Telefono;
import LengBD.domain.TelefonoListadoDTO;
import LengBD.domain.TelefonoListadoDTO;
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
public class TelefonoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall telefonoInsertCall;
    private SimpleJdbcCall telefonoUpdateCall;
    private SimpleJdbcCall telefonoDeleteCall;
    private SimpleJdbcCall telefonoReadAllCall;

    @PostConstruct
    public void init() {
        telefonoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_TELEFONO_INSERT_SP");

        telefonoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_TELEFONO_UPDATE_SP");

        telefonoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_TELEFONO_DELETE_LOGICO_SP");

        telefonoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_TELEFONO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(TelefonoListadoDTO.class));;
    }

    public void insertarTelefono(Telefono telefono) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_TELEFONO", telefono.getTelefono());
        params.put("P_ID_ESTADO", telefono.getIdEstado());
        telefonoInsertCall.execute(params);
    }

    public void actualizarTelefono(Telefono telefono) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_TELEFONO", telefono.getIdTelefono());
        params.put("P_TELEFONO", telefono.getTelefono());
        params.put("P_ID_ESTADO", telefono.getIdEstado());
        telefonoUpdateCall.execute(params);
    }

    public 
        List<TelefonoListadoDTO> readAllTelefono() {
        Map<String, Object> result = telefonoReadAllCall.execute();
        return (List<TelefonoListadoDTO>) result.get("p_cursor");
    }

    public void deleteTelefono(Telefono telefono) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_TELEFONO", telefono.getIdTelefono());
        telefonoDeleteCall.execute(params);
    }

}
