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
import LengBD.domain.Distrito;
import LengBD.domain.DistritoListadoDTO;
import LengBD.domain.DistritoListadoDTO;
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
public class DistritoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall distritoInsertCall;
    private SimpleJdbcCall distritoUpdateCall;
    private SimpleJdbcCall distritoDeleteCall;
    private SimpleJdbcCall distritoReadAllCall;

    @PostConstruct
    public void init() {
        distritoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_DISTRITO_INSERT_SP");

        distritoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_DISTRITO_UPDATE_SP");

        distritoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_DISTRITO_DELETE_SP");

        distritoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_DISTRITO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(DistritoListadoDTO.class));;
    }

    public void insertarDistrito(Distrito distrito) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", distrito.getNombre());
        params.put("P_ID_ESTADO", distrito.getIdEstado());
        distritoUpdateCall.execute(params);
        distritoInsertCall.execute(params);
    }

    public void actualizarDistrito(Distrito distrito) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_DISTRITO", distrito.getIdDistrito());
        params.put("P_NOMBRE", distrito.getNombre());
        params.put("P_ID_ESTADO", distrito.getIdEstado());
        distritoUpdateCall.execute(params);
    }

    public 
        List<DistritoListadoDTO> readAllDistrito() {
        Map<String, Object> result = distritoReadAllCall.execute();
        return (List<DistritoListadoDTO>) result.get("p_cursor");
    }

    public void deleteDistrito(Distrito distrito) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_DISTRITO", distrito.getIdDistrito());
        distritoDeleteCall.execute(params);
    }

}
