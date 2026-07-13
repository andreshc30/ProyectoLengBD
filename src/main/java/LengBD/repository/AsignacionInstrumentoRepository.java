/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.AsignacionInstrumento;
import LengBD.domain.AsignacionListadoDTO;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


@Repository
public class AsignacionInstrumentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall asignacionInstrumentoInsertCall;
    private SimpleJdbcCall asignacionInstrumentoUpdateCall;
    private SimpleJdbcCall asignacionInstrumentoDeleteCall;
    private SimpleJdbcCall asignacionInstrumentoReadAllCall;

    @PostConstruct
    public void init() {
        asignacionInstrumentoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASIGNACION_INSTRUMENTO_INSERT_SP");

        asignacionInstrumentoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASIGNACION_INSTRUMENTO_UPDATE_SP");

        asignacionInstrumentoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ASIGNACION_INSTRUMENTO_DELETE_SP");

         
        asignacionInstrumentoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
        .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
        .withProcedureName("FIDE_LISTAR_ASIGNACION_INSTRUMENTO_SP")
        .returningResultSet("P_REGISTRO",
                BeanPropertyRowMapper.newInstance(AsignacionListadoDTO.class));
}
    

    public void insertarAsignacionInstrumento(AsignacionInstrumento asignacion) {
    Map<String, Object> params = new HashMap<>();
    params.put("P_FECHA_INICIO", asignacion.getFechaInicio());
    params.put("P_FECHA_FINAL", asignacion.getFechaFinal());
    params.put("P_MOTIVO", asignacion.getMotivo());
    params.put("P_CEDULA", asignacion.getCedula());
    params.put("P_ID_INSTRUMENTO", asignacion.getIdInstrumento());
    params.put("P_ID_ESTADO", asignacion.getIdEstado());
    
    asignacionInstrumentoInsertCall.execute(params);
}

public void actualizarAsignacionInstrumento(AsignacionInstrumento asignacion) {
    Map<String, Object> params = new HashMap<>();
    
    params.put("P_ID_ASIGNACION", asignacion.getIdAsignacion());
    params.put("P_FECHA_INICIO", asignacion.getFechaInicio());
    params.put("P_FECHA_FINAL", asignacion.getFechaFinal());
    params.put("P_MOTIVO", asignacion.getMotivo());
    params.put("P_CEDULA", asignacion.getCedula());
    params.put("P_ID_INSTRUMENTO", asignacion.getIdInstrumento());
    params.put("P_ID_ESTADO", asignacion.getIdEstado());
    
    asignacionInstrumentoUpdateCall.execute(params);
}

    public List<AsignacionListadoDTO> readAllAsignacionInstrumento() {
        Map<String, Object> result = asignacionInstrumentoReadAllCall.execute();
        return (List<AsignacionListadoDTO>) result.get("P_REGISTRO");
    }

    public void deleteAsignacionInstrumento(AsignacionInstrumento asignacionInstrumento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_ASIGNACION", asignacionInstrumento.getIdAsignacion());
        asignacionInstrumentoDeleteCall.execute(params);
    }

}