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
import LengBD.domain.Cuota;
import LengBD.domain.CuotaListadoDTO;
import LengBD.domain.CuotaListadoDTO;
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
public class CuotaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall cuotaInsertCall;
    private SimpleJdbcCall cuotaUpdateCall;
    private SimpleJdbcCall cuotaDeleteCall;
    private SimpleJdbcCall cuotaReadAllCall;

    @PostConstruct
    public void init() {
        cuotaInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CUOTA_INSERT_SP");

        cuotaUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CUOTA_UPDATE_SP");

        cuotaDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CUOTA_DELETE_SP");

        cuotaReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_CUOTA_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(CuotaListadoDTO.class));;
    }

    public void insertarCuota(Cuota cuota) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", cuota.getCedula());
        params.put("P_FECHA", cuota.getFecha());
        params.put("P_MONTO_PAGADO", cuota.getMontoPagado());
        params.put("P_ID_ESTADO", cuota.getIdEstado());
        cuotaUpdateCall.execute(params);
        cuotaInsertCall.execute(params);
    }

    public void actualizarCuota(Cuota cuota) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_CUOTA", cuota.getIdCuota());
        params.put("P_CEDULA", cuota.getCedula());
        params.put("P_FECHA", cuota.getFecha());
        params.put("P_MONTO_PAGADO", cuota.getMontoPagado());
        params.put("P_ID_ESTADO", cuota.getIdEstado());
        cuotaUpdateCall.execute(params);
    }

    public 
        List<CuotaListadoDTO> readAllCuota() {
        Map<String, Object> result = cuotaReadAllCall.execute();
        return (List<CuotaListadoDTO>) result.get("p_cursor");
    }

    public void deleteCuota(Cuota cuota) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_CUOTA", cuota.getIdCuota());
        cuotaDeleteCall.execute(params);
    }

}
