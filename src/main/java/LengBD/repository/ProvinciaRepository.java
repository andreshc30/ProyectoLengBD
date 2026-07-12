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
import LengBD.domain.Provincia;
import LengBD.domain.ProvinciaListadoDTO;
import LengBD.domain.ProvinciaListadoDTO;
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
public class ProvinciaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall provinciaInsertCall;
    private SimpleJdbcCall provinciaUpdateCall;
    private SimpleJdbcCall provinciaDeleteCall;
    private SimpleJdbcCall provinciaReadAllCall;

    @PostConstruct
    public void init() {
        provinciaInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PROVINCIA_INSERT_SP");

        provinciaUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PROVINCIA_UPDATE_SP");

        provinciaDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PROVINCIA_DELETE_SP");

        provinciaReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PROVINCIA_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(ProvinciaListadoDTO.class));;
    }

    public void insertarProvincia(Provincia provincia) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", provincia.getNombre());
        params.put("P_ID_ESTADO", provincia.getIdEstado());
        provinciaUpdateCall.execute(params);
        provinciaInsertCall.execute(params);
    }

    public void actualizarProvincia(Provincia provincia) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PROVINCIA", provincia.getIdProvincia());
        params.put("P_NOMBRE", provincia.getNombre());
        params.put("P_ID_ESTADO", provincia.getIdEstado());
        provinciaUpdateCall.execute(params);
    }

    public 
        List<ProvinciaListadoDTO> readAllProvincia() {
        Map<String, Object> result = provinciaReadAllCall.execute();
        return (List<ProvinciaListadoDTO>) result.get("p_cursor");
    }

    public void deleteProvincia(Provincia provincia) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PROVINCIA", provincia.getIdProvincia());
        provinciaDeleteCall.execute(params);
    }

}
