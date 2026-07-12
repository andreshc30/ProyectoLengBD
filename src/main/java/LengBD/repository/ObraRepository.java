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
import LengBD.domain.Obra;
import LengBD.domain.ObraListadoDTO;
import LengBD.domain.ObraListadoDTO;
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
public class ObraRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall obraInsertCall;
    private SimpleJdbcCall obraUpdateCall;
    private SimpleJdbcCall obraDeleteCall;
    private SimpleJdbcCall obraReadAllCall;

    @PostConstruct
    public void init() {
        obraInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_OBRA_INSERT_SP");

        obraUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_OBRA_UPDATE_SP");

        obraDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_OBRA_DELETE_SP");

        obraReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_OBRA_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(ObraListadoDTO.class));;
    }

    public void insertarObra(Obra obra) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_FECHA", obra.getFecha());
        params.put("P_NOMBRE", obra.getNombre());
        params.put("P_DETALLE", obra.getDetalle());
        params.put("P_ID_TIPO", obra.getIdTipo());
        params.put("P_ID_ESTADO", obra.getIdEstado());
        params.put("P_ID_BANDA", obra.getIdBanda());
        obraUpdateCall.execute(params);
        obraInsertCall.execute(params);
    }

    public void actualizarObra(Obra obra) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_OBRA", obra.getIdObra());
        params.put("P_FECHA", obra.getFecha());
        params.put("P_NOMBRE", obra.getNombre());
        params.put("P_DETALLE", obra.getDetalle());
        params.put("P_ID_TIPO", obra.getIdTipo());
        params.put("P_ID_ESTADO", obra.getIdEstado());
        params.put("P_ID_BANDA", obra.getIdBanda());
        obraUpdateCall.execute(params);
    }

    public 
        List<ObraListadoDTO> readAllObra() {
        Map<String, Object> result = obraReadAllCall.execute();
        return (List<ObraListadoDTO>) result.get("p_cursor");
    }

    public void deleteObra(Obra obra) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_OBRA", obra.getIdObra());
        obraDeleteCall.execute(params);
    }

}
