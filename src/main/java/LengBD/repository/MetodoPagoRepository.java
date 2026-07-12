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
import LengBD.domain.MetodoPago;
import LengBD.domain.MetodoPagoListadoDTO;
import LengBD.domain.MetodoPagoListadoDTO;
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
public class MetodoPagoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall metodoPagoInsertCall;
    private SimpleJdbcCall metodoPagoUpdateCall;
    private SimpleJdbcCall metodoPagoDeleteCall;
    private SimpleJdbcCall metodoPagoReadAllCall;

    @PostConstruct
    public void init() {
        metodoPagoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_METODO_PAGO_INSERT_SP");

        metodoPagoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_METODO_PAGO_UPDATE_SP");

        metodoPagoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_METODO_PAGO_DELETE_SP");

        metodoPagoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_METODO_PAGO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(MetodoPagoListadoDTO.class));;
    }

    public void insertarMetodoPago(MetodoPago metodoPago) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", metodoPago.getNombre());
        params.put("P_ID_ESTADO", metodoPago.getIdEstado());
        metodoPagoInsertCall.execute(params);
    }

    public void actualizarMetodoPago(MetodoPago metodoPago) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_METODO_PAGO", metodoPago.getIdMetodoPago());
        params.put("P_NOMBRE", metodoPago.getNombre());
        params.put("P_ID_ESTADO", metodoPago.getIdEstado());
        metodoPagoUpdateCall.execute(params);
    }

    public List<MetodoPagoListadoDTO> readAllMetodoPago() {
        Map<String, Object> result = metodoPagoReadAllCall.execute();
        return (List<MetodoPagoListadoDTO>) result.get("p_cursor");
    }

    public void deleteMetodoPago(MetodoPago metodoPago) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_METODO_PAGO", metodoPago.getIdMetodoPago());
        metodoPagoDeleteCall.execute(params);
    }

}
