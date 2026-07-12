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
import LengBD.domain.CategoriaMovimiento;
import LengBD.domain.CategoriaMovimientoListadoDTO;
import LengBD.domain.CategoriaMovimientoListadoDTO;
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
public class CategoriaMovimientoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall categoriaMovimientoInsertCall;
    private SimpleJdbcCall categoriaMovimientoUpdateCall;
    private SimpleJdbcCall categoriaMovimientoDeleteCall;
    private SimpleJdbcCall categoriaMovimientoReadAllCall;

    @PostConstruct
    public void init() {
        categoriaMovimientoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CATEGORIA_MOVIMIENTO_INSERT_SP");

        categoriaMovimientoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CATEGORIA_MOVIMIENTO_UPDATE_SP");

        categoriaMovimientoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CATEGORIA_MOVIMIENTO_DELETE_SP");

        categoriaMovimientoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_CATEGORIA_MOVIMIENTO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(CategoriaMovimientoListadoDTO.class));;
    }

    public void insertarCategoriaMovimiento(CategoriaMovimiento categoriaMovimiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", categoriaMovimiento.getNombre());
        params.put("P_DETALLE", categoriaMovimiento.getDetalle());
        params.put("P_ID_ESTADO", categoriaMovimiento.getIdEstado());
        categoriaMovimientoInsertCall.execute(params);
    }

    public void actualizarCategoriaMovimiento(CategoriaMovimiento categoriaMovimiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_CATEGORIA_MOVIMIENTO", categoriaMovimiento.getIdCategoriaMovimiento());
        params.put("P_NOMBRE", categoriaMovimiento.getNombre());
        params.put("P_DETALLE", categoriaMovimiento.getDetalle());
        params.put("P_ID_ESTADO", categoriaMovimiento.getIdEstado());
        categoriaMovimientoUpdateCall.execute(params);
    }

    public List<CategoriaMovimientoListadoDTO> readAllCategoriaMovimiento() {
        Map<String, Object> result = categoriaMovimientoReadAllCall.execute();
        return (List<CategoriaMovimientoListadoDTO>) result.get("p_cursor");
    }

    public void deleteCategoriaMovimiento(CategoriaMovimiento categoriaMovimiento) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_CATEGORIA_MOVIMIENTO", categoriaMovimiento.getIdCategoriaMovimiento());
        categoriaMovimientoDeleteCall.execute(params);
    }

}
