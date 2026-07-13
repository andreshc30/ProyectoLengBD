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
import LengBD.domain.PagoSuscripcion;
import LengBD.domain.PagoSuscripcionListadoDTO;
import LengBD.domain.PagoSuscripcionListadoDTO;
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
public class PagoSuscripcionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall pagoSuscripcionInsertCall;
    private SimpleJdbcCall pagoSuscripcionUpdateCall;
    private SimpleJdbcCall pagoSuscripcionDeleteCall;
    private SimpleJdbcCall pagoSuscripcionReadAllCall;

    @PostConstruct
    public void init() {
        pagoSuscripcionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PAGO_SUSCRIPCION_INSERT_SP");

        pagoSuscripcionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PAGO_SUSCRIPCION_UPDATE_SP");

        pagoSuscripcionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PAGO_SUSCRIPCION_DELETE_SP");

        pagoSuscripcionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PAGO_SUSCRIPCION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(PagoSuscripcionListadoDTO.class));;
    }

    public void insertarPagoSuscripcion(PagoSuscripcion pagoSuscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", pagoSuscripcion.getNombre());
        params.put("P_DESCRIPCION", pagoSuscripcion.getDescripcion());
        params.put("P_FECHA_PAGO", pagoSuscripcion.getFechaPago());
        params.put("P_MONTO", pagoSuscripcion.getMonto());
        params.put("P_ID_METODO_PAGO", pagoSuscripcion.getIdMetodoPago());
        params.put("P_ID_TIPO_PLAN", pagoSuscripcion.getIdPagoSuscripcion());
        params.put("P_ID_SUSCRIPCION", pagoSuscripcion.getIdMetodoPago());
        params.put("P_ID_ESTADO", pagoSuscripcion.getIdEstado());
        pagoSuscripcionInsertCall.execute(params);
    }

    public void actualizarPagoSuscripcion(PagoSuscripcion pagoSuscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PAGO_SUSCRIPCION", pagoSuscripcion.getIdPagoSuscripcion());
        params.put("P_NOMBRE", pagoSuscripcion.getNombre());
        params.put("P_DESCRIPCION", pagoSuscripcion.getDescripcion());
        params.put("P_FECHA_PAGO", pagoSuscripcion.getFechaPago());
        params.put("P_MONTO", pagoSuscripcion.getMonto());
        params.put("P_ID_METODO_PAGO", pagoSuscripcion.getIdMetodoPago());
        params.put("P_ID_TIPO_PLAN", pagoSuscripcion.getIdPagoSuscripcion());
        params.put("P_ID_SUSCRIPCION", pagoSuscripcion.getIdMetodoPago());
        params.put("P_ID_ESTADO", pagoSuscripcion.getIdEstado());
        pagoSuscripcionUpdateCall.execute(params);
    }

    public 
        List<PagoSuscripcionListadoDTO> readAllPagoSuscripcion() {
        Map<String, Object> result = pagoSuscripcionReadAllCall.execute();
        return (List<PagoSuscripcionListadoDTO>) result.get("p_cursor");
    }

    public void deletePagoSuscripcion(PagoSuscripcion pagoSuscripcion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PAGO_SUSCRIPCION", pagoSuscripcion.getIdPagoSuscripcion());
        pagoSuscripcionDeleteCall.execute(params);
    }

}
