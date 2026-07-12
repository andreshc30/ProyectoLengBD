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
import LengBD.domain.BandaInstrumento;
import LengBD.domain.BandaInstrumentoListadoDTO;
import LengBD.domain.BandaInstrumentoListadoDTO;
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
public class BandaInstrumentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall bandaInstrumentoInsertCall;
    private SimpleJdbcCall bandaInstrumentoUpdateCall;
    private SimpleJdbcCall bandaInstrumentoDeleteCall;
    private SimpleJdbcCall bandaInstrumentoReadAllCall;

    @PostConstruct
    public void init() {
        bandaInstrumentoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_INSTRUMENTO_INSERT_SP");

        bandaInstrumentoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_INSTRUMENTO_UPDATE_SP");

        bandaInstrumentoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_INSTRUMENTO_DELETE_SP");

        bandaInstrumentoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_BANDA_INSTRUMENTO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(BandaInstrumentoListadoDTO.class));;
    }

    public void insertarBandaInstrumento(BandaInstrumento bandaInstrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", bandaInstrumento.getIdBanda());
        params.put("P_ID_INSTRUMENTO", bandaInstrumento.getIdInstrumento());
        params.put("P_ID_ESTADO", bandaInstrumento.getIdEstado());
        bandaInstrumentoUpdateCall.execute(params);
        bandaInstrumentoInsertCall.execute(params);
    }

    public void actualizarBandaInstrumento(BandaInstrumento bandaInstrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", bandaInstrumento.getIdBanda());
        params.put("P_ID_INSTRUMENTO", bandaInstrumento.getIdInstrumento());
        params.put("P_ID_ESTADO", bandaInstrumento.getIdEstado());
        bandaInstrumentoUpdateCall.execute(params);
    }

    public 
        List<BandaInstrumentoListadoDTO> readAllBandaInstrumento() {
        Map<String, Object> result = bandaInstrumentoReadAllCall.execute();
        return (List<BandaInstrumentoListadoDTO>) result.get("p_cursor");
    }

    public void deleteBandaInstrumento(BandaInstrumento bandaInstrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", bandaInstrumento.getIdBanda());
        params.put("P_ID_INSTRUMENTO", bandaInstrumento.getIdInstrumento());
        bandaInstrumentoDeleteCall.execute(params);
    }

}
