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
import LengBD.domain.Evento;
import LengBD.domain.EventoListadoDTO;
import LengBD.domain.EventoListadoDTO;
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
public class EventoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall eventoInsertCall;
    private SimpleJdbcCall eventoUpdateCall;
    private SimpleJdbcCall eventoDeleteCall;
    private SimpleJdbcCall eventoReadAllCall;

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
                .returningResultSet("p_cursor",
                        BeanPropertyRowMapper.newInstance(EventoListadoDTO.class));;
    }

    public void insertarEvento(Evento evento) {
        Map<String, Object> params = new HashMap<>();

        params.put("P_ID_EVENTO", evento.getIdEvento());
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
        return (List<EventoListadoDTO>) result.get("p_cursor");
    }

    public void deleteEvento(Evento evento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_EVENTO", evento.getIdEvento());
        eventoDeleteCall.execute(params);
    }

}
