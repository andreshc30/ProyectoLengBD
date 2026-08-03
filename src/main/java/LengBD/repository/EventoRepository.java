/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.Evento;
import LengBD.domain.EventoListadoDTO;
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
public class EventoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall eventoInsertCall;
    private SimpleJdbcCall eventoUpdateCall;
    private SimpleJdbcCall eventoDeleteCall;
    private SimpleJdbcCall eventoReadAllCall;
    private SimpleJdbcCall totalEventosActivosCall;
    private SimpleJdbcCall obtenerNombreEventoCall;

    @PostConstruct
    public void init() {
        eventoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_EVENTO_INSERT_SP");

        eventoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_EVENTO_UPDATE_SP");

        eventoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_EVENTO_DELETE_LOGICO_SP");

        eventoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_EVENTO_SP")
                .returningResultSet("P_CURSOR",
                        BeanPropertyRowMapper.newInstance(EventoListadoDTO.class));

        totalEventosActivosCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withFunctionName("FIDE_TOTAL_EVENTOS_ACTIVOS_FN");

        obtenerNombreEventoCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withFunctionName("FIDE_OBTENER_NOMBRE_EVENTO_FN");
    }

    public void insertarEvento(Evento evento) {
        Map<String, Object> params = new HashMap<>();
        /*params.put("P_ID_EVENTO", evento.getIdEvento());*/
        params.put("P_NOMBRE", evento.getNombre());
        params.put("P_DETALLE", evento.getDetalle());
        params.put("P_FECHA", evento.getFecha());
        params.put("P_ID_DIRECCION", evento.getDireccion());
        params.put("P_ID_ESTADO", evento.getIdEstado());
        params.put("P_ID_BANDA", evento.getIdBanda());
        eventoInsertCall.execute(params);
    }

    public void actualizarEvento(Evento evento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_EVENTO", evento.getIdEvento());
        params.put("P_NOMBRE", evento.getNombre());
        params.put("P_DETALLE", evento.getDetalle());
        params.put("P_FECHA", evento.getFecha());
        params.put("P_ID_DIRECCION", evento.getDireccion());
        params.put("P_ID_ESTADO", evento.getIdEstado());
        params.put("P_ID_BANDA", evento.getIdBanda());
        eventoUpdateCall.execute(params);
    }

    public List<EventoListadoDTO> readAllEvento() {
        Map<String, Object> result = eventoReadAllCall.execute();
        return (List<EventoListadoDTO>) result.get("P_CURSOR");
    }

    public void deleteEvento(Evento evento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_EVENTO", evento.getIdEvento());
        eventoDeleteCall.execute(params);
    }

    public Integer totalEventosActivos() {
        java.math.BigDecimal resultado = totalEventosActivosCall.executeFunction(java.math.BigDecimal.class);
        return resultado != null ? resultado.intValue() : 0;
    }

    public String obtenerNombreEvento(Integer idEvento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_EVENTO", idEvento);
        return obtenerNombreEventoCall.executeFunction(String.class, params);
    }

    public List<EventoListadoDTO> obtenerEventosBusqueda() {

        String sql = """
        SELECT
            ID_EVENTO,
            NOMBRE_EVENTO AS NOMBRE
        FROM FIDE_EVENTOS_BUSQUEDA_V
        """;

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(EventoListadoDTO.class)
        );
    }

    public EventoListadoDTO obtenerDetalleEvento(Integer idEvento) {

        String sql = """
        SELECT
            ID_EVENTO,
            NOMBRE_EVENTO AS NOMBRE,
            DETALLE,
            FECHA,
            NOMBRE_BANDA AS NOMBREBANDA,
            LUGAR AS NOMBREDIRECCION,
            ESTADO
        FROM FIDE_EVENTO_DETALLE_V
        WHERE ID_EVENTO = ?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                BeanPropertyRowMapper.newInstance(EventoListadoDTO.class),
                idEvento);
    }
}
