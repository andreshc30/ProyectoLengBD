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
import LengBD.domain.Planes;
import LengBD.domain.PlanesListadoDTO;
import LengBD.domain.PlanesListadoDTO;
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
public class PlanesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall planesInsertCall;
    private SimpleJdbcCall planesUpdateCall;
    private SimpleJdbcCall planesDeleteCall;
    private SimpleJdbcCall planesReadAllCall;

    @PostConstruct
    public void init() {
        planesInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PLANES_INSERT_SP");

        planesUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PLANES_UPDATE_SP");

        planesDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PLANES_DELETE_SP");

        planesReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PLANES_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(PlanesListadoDTO.class));;
    }

    public void insertarPlanes(Planes planes) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", planes.getNombre());
        params.put("P_PRECIO", planes.getPrecio());
        params.put("P_DESCRIPCION", planes.getDescripcion());
        params.put("P_PERIODICIDAD", planes.getPeriodicidad());
        params.put("P_ID_ESTADO", planes.getIdEstado());
        planesUpdateCall.execute(params);
        planesInsertCall.execute(params);
    }

    public void actualizarPlanes(Planes planes) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_TIPO_PLAN", planes.getIdTipoPlan());
        params.put("P_NOMBRE", planes.getNombre());
        params.put("P_PRECIO", planes.getPrecio());
        params.put("P_DESCRIPCION", planes.getDescripcion());
        params.put("P_PERIODICIDAD", planes.getPeriodicidad());
        params.put("P_ID_ESTADO", planes.getIdEstado());
        planesUpdateCall.execute(params);
    }

    public 
        List<PlanesListadoDTO> readAllPlanes() {
        Map<String, Object> result = planesReadAllCall.execute();
        return (List<PlanesListadoDTO>) result.get("p_cursor");
    }

    public void deletePlanes(Planes planes) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_TIPO_PLAN", planes.getIdTipoPlan());
        planesDeleteCall.execute(params);
    }

}
