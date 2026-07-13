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
import LengBD.domain.Direccion;
import LengBD.domain.DireccionListadoDTO;
import LengBD.domain.DireccionListadoDTO;
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
public class DireccionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall direccionInsertCall;
    private SimpleJdbcCall direccionUpdateCall;
    private SimpleJdbcCall direccionDeleteCall;
    private SimpleJdbcCall direccionReadAllCall;

    @PostConstruct
    public void init() {
        direccionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_DIRECCION_INSERT_SP");

        direccionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_DIRECCION_UPDATE_SP");

        direccionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_DIRECCION_DELETE_SP");

        direccionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_DIRECCION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(DireccionListadoDTO.class));;
    }

    public void insertarDireccion(Direccion direccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_DIRECCION_ID_PROVINCIA", direccion.getIdProvincia());
        params.put("P_DIRECCION_ID_CANTON", direccion.getIdCanton());
        params.put("P_DIRECCION_ID_DISTRITO", direccion.getIdDistrito());
        params.put("P_DIRECCION_OTROS_DETALLES", direccion.getOtrosDetalles());
        params.put("P_DIRECCION_ID_ESTADO", direccion.getIdEstado());
        direccionInsertCall.execute(params);
    }

    public void actualizarDireccion(Direccion direccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_DIRECCION_ID_DIRECCION", direccion.getIdDireccion());
        params.put("P_DIRECCION_ID_PROVINCIA", direccion.getIdProvincia());
        params.put("P_DIRECCION_ID_CANTON", direccion.getIdCanton());
        params.put("P_DIRECCION_ID_DISTRITO", direccion.getIdDistrito());
        params.put("P_DIRECCION_OTROS_DETALLES", direccion.getOtrosDetalles());
        params.put("P_DIRECCION_ID_ESTADO", direccion.getIdEstado());
        direccionUpdateCall.execute(params);
    }

    public 
        List<DireccionListadoDTO> readAllDireccion() {
        Map<String, Object> result = direccionReadAllCall.execute();
        return (List<DireccionListadoDTO>) result.get("p_cursor");
    }

    public void deleteDireccion(Direccion direccion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_DIRECCION_ID_DIRECCION", direccion.getIdDireccion());
        direccionDeleteCall.execute(params);
    }

}
