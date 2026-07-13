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
import LengBD.domain.Movimiento;
import LengBD.domain.MovimientoListadoDTO;
import LengBD.domain.MovimientoListadoDTO;
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
public class MovimientoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall movimientoInsertCall;
    private SimpleJdbcCall movimientoUpdateCall;
    private SimpleJdbcCall movimientoDeleteCall;
    private SimpleJdbcCall movimientoReadAllCall;

    @PostConstruct
    public void init() {
        movimientoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_MOVIMIENTO_INSERT_SP");

        movimientoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_MOVIMIENTO_UPDATE_SP");

        movimientoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_MOVIMIENTO_DELETE_LOGICO_SP");

        movimientoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_MOVIMIENTO_SP")
                .returningResultSet("p_cursor",
                        BeanPropertyRowMapper.newInstance(MovimientoListadoDTO.class));;
    }

    public void insertarMovimiento(Movimiento movimiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CANTIDAD", movimiento.getCantidad());
        params.put("P_FECHA", movimiento.getFecha());
        params.put("P_DETALLE", movimiento.getDetalle());
        params.put("P_ID_CATEGORIA_MOVIMIENTO", movimiento.getIdCategoriaMovimiento());
        params.put("P_ID_METODO_PAGO", movimiento.getIdMetodoPago());
        params.put("P_ID_BANDA", movimiento.getIdBanda());
        params.put("P_ID_ESTADO", movimiento.getIdEstado());
        movimientoInsertCall.execute(params);
    }

    public void actualizarMovimiento(Movimiento movimiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MOVIMIENTO", movimiento.getIdMovimiento());
        params.put("P_CANTIDAD", movimiento.getCantidad());
        params.put("P_FECHA", movimiento.getFecha());
        params.put("P_DETALLE", movimiento.getDetalle());
        params.put("P_ID_CATEGORIA_MOVIMIENTO", movimiento.getIdCategoriaMovimiento());
        params.put("P_ID_METODO_PAGO", movimiento.getIdMetodoPago());
        params.put("P_ID_BANDA", movimiento.getIdBanda());
        params.put("P_ID_ESTADO", movimiento.getIdEstado());
        movimientoUpdateCall.execute(params);
    }

    public List<MovimientoListadoDTO> readAllMovimiento() {
        Map<String, Object> result = movimientoReadAllCall.execute();
        return (List<MovimientoListadoDTO>) result.get("p_cursor");
    }

    public void deleteMovimiento(Movimiento movimiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MOVIMIENTO", movimiento.getIdMovimiento());
        movimientoDeleteCall.execute(params);
    }

}
