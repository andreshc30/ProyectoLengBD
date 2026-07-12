package LengBD.repository;

import LengBD.domain.Instrumento;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class InstrumentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall insertCall;
    private SimpleJdbcCall updateCall;
    private SimpleJdbcCall deleteCall;
    private SimpleJdbcCall readAllCall;

    @PostConstruct
    public void init() {
        insertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_INSERT_SP");

        updateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_UPDATE_SP");

        deleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_DELETE_LOGICO_SP");

        readAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INSTRUMENTO_READ_ALL_SP")
                .returningResultSet("P_REGISTRO",
                        BeanPropertyRowMapper.newInstance(Instrumento.class));
    }

    public List<Instrumento> readAllInstrumento() {
        Map<String, Object> result = readAllCall.execute();
        return (List<Instrumento>) result.get("P_REGISTRO");
    }

    public void insertarInstrumento(Instrumento instrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_INSTRUMENTO", instrumento.getIdInstrumento());
        params.put("P_DESCRIPCION", instrumento.getNombre());
        params.put("P_ID_SECCION", instrumento.getIdSeccion()!= null ? instrumento.getIdSeccion().getIdSeccion() : null);
        params.put("P_ID_ESTADO", instrumento.getIdEstado() != null ? instrumento.getIdEstado().getIdEstado() : null);

        insertCall.execute(params);
    }

    public void updateInstrumento(Instrumento instrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_INSTRUMENTO", instrumento.getIdInstrumento());
        params.put("P_DESCRIPCION", instrumento.getNombre());
        params.put("P_ID_SECCION", instrumento.getIdSeccion() != null ? instrumento.getIdSeccion().getIdSeccion() : null);
        params.put("P_ID_ESTADO", instrumento.getIdEstado() != null ? instrumento.getIdEstado().getIdEstado() : null);

        updateCall.execute(params);
    }

    public void eliminarInstrumento(Integer idInstrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_INSTRUMENTO", idInstrumento);
        deleteCall.execute(params);
    }
}