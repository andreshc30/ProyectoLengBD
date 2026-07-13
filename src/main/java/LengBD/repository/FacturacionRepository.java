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
import LengBD.domain.Facturacion;
import LengBD.domain.FacturacionListadoDTO;
import LengBD.domain.FacturacionListadoDTO;
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
public class FacturacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall facturacionInsertCall;
    private SimpleJdbcCall facturacionUpdateCall;
    private SimpleJdbcCall facturacionDeleteCall;
    private SimpleJdbcCall facturacionReadAllCall;

    @PostConstruct
    public void init() {
        facturacionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_FACTURACION_INSERT_SP");

        facturacionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_FACTURACION_UPDATE_SP");

        facturacionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_FACTURACION_DELETE_SP");

        facturacionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_FACTURACION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(FacturacionListadoDTO.class));;
    }

    public void insertarFacturacion(Facturacion facturacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_FECHA_EMISION", facturacion.getFechaEmision());
        params.put("P_DETALLE", facturacion.getDetalle());
        params.put("P_SUBTOTAL", facturacion.getSubtotal());
        params.put("P_IMPUESTOS", facturacion.getImpuestos());
        params.put("P_TOTAL", facturacion.getTotal());
        params.put("P_ID_METODO_PAGO", facturacion.getIdMetodoPago());
        params.put("P_ID_CUOTA", facturacion.getIdCuota());
        params.put("P_ID_SUSCRIPCION", facturacion.getIdSuscripcion());
        params.put("P_ID_ESTADO", facturacion.getIdEstado());
        facturacionInsertCall.execute(params);
    }

    public void actualizarFacturacion(Facturacion facturacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_FACTURA", facturacion.getIdFactura());
        params.put("P_FECHA_EMISION", facturacion.getFechaEmision());
        params.put("P_DETALLE", facturacion.getDetalle());
        params.put("P_SUBTOTAL", facturacion.getSubtotal());
        params.put("P_IMPUESTOS", facturacion.getImpuestos());
        params.put("P_TOTAL", facturacion.getTotal());
        params.put("P_ID_METODO_PAGO", facturacion.getIdMetodoPago());
        params.put("P_ID_CUOTA", facturacion.getIdCuota());
        params.put("P_ID_SUSCRIPCION", facturacion.getIdSuscripcion());
        params.put("P_ID_ESTADO", facturacion.getIdEstado());
        facturacionUpdateCall.execute(params);
    }

    public List<FacturacionListadoDTO> readAllFacturacion() {
        Map<String, Object> result = facturacionReadAllCall.execute();
        return (List<FacturacionListadoDTO>) result.get("p_cursor");
    }

    public void deleteFacturacion(Facturacion facturacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_FACTURA", facturacion.getIdFactura());
        facturacionDeleteCall.execute(params);
    }

}
