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
import LengBD.domain.Banda;
import LengBD.domain.BandaListadoDTO;
import LengBD.domain.BandaListadoDTO;
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
public class BandaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall bandaInsertCall;
    private SimpleJdbcCall bandaUpdateCall;
    private SimpleJdbcCall bandaDeleteCall;
    private SimpleJdbcCall bandaReadAllCall;

    @PostConstruct
    public void init() {
        bandaInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_INSERT_SP");

        bandaUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_UPDATE_SP");

        bandaDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_BANDA_DELETE_SP");

        bandaReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_BANDA_SP")
                .returningResultSet("P_REGISTRO",
                BeanPropertyRowMapper.newInstance(BandaListadoDTO.class));;
    }

    public void insertarBanda(Banda banda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", banda.getNombre());
        params.put("P_LOGO_URL", banda.getLogoUrl());
        params.put("P_MONTO_CUOTA", banda.getMontoCuota());
        params.put("P_FECHA_FUNDACION", banda.getFechaFundacion());
        params.put("P_ID_DIRECCION", banda.getIdDireccion());
        params.put("P_ID_CORREO", banda.getIdCorreo());
        params.put("P_ID_ESTADO", banda.getIdEstado());
        params.put("P_ID_TELEFONO", banda.getIdTelefono());
        bandaInsertCall.execute(params);
    }

    public void actualizarBanda(Banda banda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", banda.getIdBanda());
        params.put("P_NOMBRE", banda.getNombre());
        params.put("P_LOGO_URL", banda.getLogoUrl());
        params.put("P_MONTO_CUOTA", banda.getMontoCuota());
        params.put("P_FECHA_FUNDACION", banda.getFechaFundacion());
        params.put("P_ID_DIRECCION", banda.getIdDireccion());
        params.put("P_ID_CORREO", banda.getIdCorreo());
        params.put("P_ID_ESTADO", banda.getIdEstado());
        params.put("P_ID_TELEFONO", banda.getIdTelefono());
        bandaUpdateCall.execute(params);
    }

    public List<BandaListadoDTO> readAllBanda() {
        Map<String, Object> result = bandaReadAllCall.execute();
        return (List<BandaListadoDTO>) result.get("P_REGISTRO");
    }

    public void deleteBanda(Banda banda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", banda.getIdBanda());
        bandaDeleteCall.execute(params);
    }

}
