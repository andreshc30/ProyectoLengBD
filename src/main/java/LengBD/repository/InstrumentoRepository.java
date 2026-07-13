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
import LengBD.domain.Instrumento;
import LengBD.domain.InstrumentoListadoDTO;
import LengBD.domain.InstrumentoListadoDTO;
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
public class InstrumentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall instrumentoInsertCall;
    private SimpleJdbcCall instrumentoUpdateCall;
    private SimpleJdbcCall instrumentoDeleteCall;
    private SimpleJdbcCall instrumentoReadAllCall;

    @PostConstruct
    public void init() {
        instrumentoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_INSERT_SP");

        instrumentoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_UPDATE_SP");

        instrumentoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_DELETE_SP");

        instrumentoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_INSTRUMENTO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(InstrumentoListadoDTO.class));;
    }

    public void insertarInstrumento(Instrumento instrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_INSTRUMENTO_ID_SECCION", instrumento.getIdSeccion());
        params.put("P_INSTRUMENTO_ID_ESTADO", instrumento.getIdEstado());
        params.put("P_INSTRUMENTO_NOMBRE", instrumento.getNombre());
        instrumentoInsertCall.execute(params);
    }

    public void actualizarInstrumento(Instrumento instrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_INSTRUMENTO_ID_INSTRUMENTO", instrumento.getIdInstrumento());
        params.put("P_INSTRUMENTO_ID_SECCION", instrumento.getIdSeccion());
        params.put("P_INSTRUMENTO_ID_ESTADO", instrumento.getIdEstado());
        params.put("P_INSTRUMENTO_NOMBRE", instrumento.getNombre());
        instrumentoUpdateCall.execute(params);
}

    public List<InstrumentoListadoDTO> readAllInstrumento() {
        Map<String, Object> result = instrumentoReadAllCall.execute();
        return (List<InstrumentoListadoDTO>) result.get("p_cursor");
    }

    public void deleteInstrumento(Instrumento instrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_INSTRUMENTO_ID_INSTRUMENTO", instrumento.getIdInstrumento());
        instrumentoDeleteCall.execute(params);
    }

}
