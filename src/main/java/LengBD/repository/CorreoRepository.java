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
import LengBD.domain.Correo;
import LengBD.domain.CorreoListadoDTO;
import LengBD.domain.CorreoListadoDTO;
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
public class CorreoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall correoInsertCall;
    private SimpleJdbcCall correoUpdateCall;
    private SimpleJdbcCall correoDeleteCall;
    private SimpleJdbcCall correoReadAllCall;

    @PostConstruct
    public void init() {
        correoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CORREO_INSERT_SP");

        correoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CORREO_UPDATE_SP");

        correoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_CORREO_DELETE_SP");

        correoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_CORREO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(CorreoListadoDTO.class));;
    }

    public void insertarCorreo(Correo correo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CORREO", correo.getCorreo());
        params.put("P_ID_ESTADO", correo.getIdEstado());
        correoInsertCall.execute(params);
    }

    public void actualizarCorreo(Correo correo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_CORREO", correo.getIdCorreo());
        params.put("P_CORREO", correo.getCorreo());
        params.put("P_ID_ESTADO", correo.getIdEstado());
        correoUpdateCall.execute(params);
    }

    public List<CorreoListadoDTO> readAllCorreo() {
        Map<String, Object> result = correoReadAllCall.execute();
        return (List<CorreoListadoDTO>) result.get("p_cursor");
    }

    public void deleteCorreo(Correo correo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_CORREO", correo.getIdCorreo());
        correoDeleteCall.execute(params);
    }

}
