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
import LengBD.domain.Canton;
import LengBD.domain.CantonListadoDTO;
import LengBD.domain.CantonListadoDTO;
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
public class CantonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall cantonInsertCall;
    private SimpleJdbcCall cantonUpdateCall;
    private SimpleJdbcCall cantonDeleteCall;
    private SimpleJdbcCall cantonReadAllCall;

    @PostConstruct
    public void init() {
        cantonInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CANTON_INSERT_SP");

        cantonUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CANTON_UPDATE_SP");

        cantonDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CANTON_DELETE_SP");

        cantonReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_CANTON_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(CantonListadoDTO.class));;
    }

    public void insertarCanton(Canton canton) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CANTON_NOMBRE", canton.getNombre());
        params.put("P_CANTON_ID_ESTADO", canton.getIdEstado());
        cantonInsertCall.execute(params);
    }

    public void actualizarCanton(Canton canton) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CANTON_ID_CANTON", canton.getIdCanton());
        params.put("P_CANTON_NOMBRE", canton.getNombre());
        params.put("P_CANTON_ID_ESTADO", canton.getIdEstado());
        cantonUpdateCall.execute(params);
    }

    public 
        List<CantonListadoDTO> readAllCanton() {
        Map<String, Object> result = cantonReadAllCall.execute();
        return (List<CantonListadoDTO>) result.get("p_cursor");
    }

    public void deleteCanton(Canton canton) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CANTON_ID_CANTON", canton.getIdCanton());
        cantonDeleteCall.execute(params);
    }

}
