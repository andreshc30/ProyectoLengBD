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
import LengBD.domain.Pago;
import LengBD.domain.PagoListadoDTO;
import LengBD.domain.PagoListadoDTO;
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
public class PagoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall pagoInsertCall;
    private SimpleJdbcCall pagoUpdateCall;
    private SimpleJdbcCall pagoDeleteCall;
    private SimpleJdbcCall pagoReadAllCall;

    @PostConstruct
    public void init() {
        pagoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PAGO_INSERT_SP");

        pagoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PAGO_UPDATE_SP");

        pagoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PAGO_DELETE_LOGICO_SP");

        pagoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PAGO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(PagoListadoDTO.class));
    }

    public void insertarPago(Pago pago) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_FACTURACION", pago.getIdFacturacion());
        params.put("P_ID_METODO_PAGO", pago.getIdMetodoPago());
        params.put("P_ID_SUSCRIPCION", pago.getIdSuscripcion());
        params.put("P_ID_CUOTA", pago.getIdCuota());
        params.put("P_FECHA_PAGO", pago.getFechaPago());
        params.put("P_MONTO", pago.getMonto());
        params.put("P_ID_ESTADO", pago.getIdEstado());
        pagoInsertCall.execute(params);
    }

    public void actualizarPago(Pago pago) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PAGO", pago.getIdPago());
        params.put("P_ID_FACTURACION", pago.getIdFacturacion());
        params.put("P_ID_METODO_PAGO", pago.getIdMetodoPago());
        params.put("P_ID_SUSCRIPCION", pago.getIdSuscripcion());
        params.put("P_ID_CUOTA", pago.getIdCuota());
        params.put("P_FECHA_PAGO", pago.getFechaPago());
        params.put("P_MONTO", pago.getMonto());
        params.put("P_ID_ESTADO", pago.getIdEstado());
        pagoUpdateCall.execute(params);
    }

    public 
        List<PagoListadoDTO> readAllPago() {
        Map<String, Object> result = pagoReadAllCall.execute();
        return (List<PagoListadoDTO>) result.get("p_cursor");
    }

    public void deletePago(Pago pago) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PAGO", pago.getIdPago());
        pagoDeleteCall.execute(params);
    }

}
