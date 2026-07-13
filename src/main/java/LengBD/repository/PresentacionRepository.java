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
import LengBD.domain.Presentacion;
import LengBD.domain.PresentacionListadoDTO;
import LengBD.domain.PresentacionListadoDTO;
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
public class PresentacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall presentacionInsertCall;
    private SimpleJdbcCall presentacionUpdateCall;
    private SimpleJdbcCall presentacionDeleteCall;
    private SimpleJdbcCall presentacionReadAllCall;

    @PostConstruct
    public void init() {
        presentacionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PRESENTACION_INSERT_SP");

        presentacionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PRESENTACION_UPDATE_SP");

        presentacionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PRESENTACION_DELETE_LOGICO_SP");

        presentacionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PRESENTACION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(PresentacionListadoDTO.class));;
    }

    public void insertarPresentacion(Presentacion presentacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", presentacion.getNombre());
        params.put("P_DESCRIPCION", presentacion.getDescripcion());
        params.put("P_ID_BANDA", presentacion.getIdBanda());
        params.put("P_FECHA", presentacion.getFecha());
        params.put("P_ID_LUGAR", presentacion.getIdLugar());
        params.put("P_ID_ESTADO", presentacion.getIdEstado());
        presentacionInsertCall.execute(params);
    }

    public void actualizarPresentacion(Presentacion presentacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PRESENTACION", presentacion.getIdPresentacion());
        params.put("P_NOMBRE", presentacion.getNombre());
        params.put("P_DESCRIPCION", presentacion.getDescripcion());
        params.put("P_ID_BANDA", presentacion.getIdBanda());
        params.put("P_FECHA", presentacion.getFecha());
        params.put("P_ID_LUGAR", presentacion.getIdLugar());
        params.put("P_ID_ESTADO", presentacion.getIdEstado());
        presentacionUpdateCall.execute(params);
    }

    public 
        List<PresentacionListadoDTO> readAllPresentacion() {
        Map<String, Object> result = presentacionReadAllCall.execute();
        return (List<PresentacionListadoDTO>) result.get("p_cursor");
    }

    public void deletePresentacion(Presentacion presentacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PRESENTACION", presentacion.getIdPresentacion());
        presentacionDeleteCall.execute(params);
    }

}
