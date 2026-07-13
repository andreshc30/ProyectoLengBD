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
import LengBD.domain.SolicitudIngreso;
import LengBD.domain.SolicitudIngresoListadoDTO;
import LengBD.domain.SolicitudIngresoListadoDTO;
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
public class SolicitudIngresoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall solicitudIngresoInsertCall;
    private SimpleJdbcCall solicitudIngresoUpdateCall;
    private SimpleJdbcCall solicitudIngresoDeleteCall;
    private SimpleJdbcCall solicitudIngresoReadAllCall;

    @PostConstruct
    public void init() {
        solicitudIngresoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SOLICITUD_INGRESO_INSERT_SP");

        solicitudIngresoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SOLICITUD_INGRESO_UPDATE_SP");

        solicitudIngresoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SOLICITUD_INGRESO_DELETE_LOGICO_SP");

        solicitudIngresoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_SOLICITUD_INGRESO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(SolicitudIngresoListadoDTO.class));;
    }

    public void insertarSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", solicitudIngreso.getNombre());
        params.put("P_PRIMER_APELLIDO", solicitudIngreso.getPrimerApellido());
        params.put("P_SEGUNDO_APELLIDO", solicitudIngreso.getSegundoApellido());
        params.put("P_MENSAJE", solicitudIngreso.getMensaje());
        params.put("P_FECHA_SOLICITUD", solicitudIngreso.getFechaSolicitud());
        params.put("P_CORREO", solicitudIngreso.getCorreo());
        params.put("P_ID_SECCION", solicitudIngreso.getIdSeccion());
        params.put("P_ID_BANDA", solicitudIngreso.getIdBanda());
        params.put("P_TELEFONO", solicitudIngreso.getTelefono());
        params.put("P_ID_ESTADO", solicitudIngreso.getIdEstado());
        solicitudIngresoInsertCall.execute(params);
    }

    public void actualizarSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_SOLICITUD", solicitudIngreso.getIdSolicitud());
        params.put("P_NOMBRE", solicitudIngreso.getNombre());
        params.put("P_PRIMER_APELLIDO", solicitudIngreso.getPrimerApellido());
        params.put("P_SEGUNDO_APELLIDO", solicitudIngreso.getSegundoApellido());
        params.put("P_MENSAJE", solicitudIngreso.getMensaje());
        params.put("P_FECHA_SOLICITUD", solicitudIngreso.getFechaSolicitud());
        params.put("P_CORREO", solicitudIngreso.getCorreo());
        params.put("P_ID_SECCION", solicitudIngreso.getIdSeccion());
        params.put("P_ID_BANDA", solicitudIngreso.getIdBanda());
        params.put("P_TELEFONO", solicitudIngreso.getTelefono());
        params.put("P_ID_ESTADO", solicitudIngreso.getIdEstado());
        solicitudIngresoUpdateCall.execute(params);
    }

    public 
        List<SolicitudIngresoListadoDTO> readAllSolicitudIngreso() {
        Map<String, Object> result = solicitudIngresoReadAllCall.execute();
        return (List<SolicitudIngresoListadoDTO>) result.get("p_cursor");
    }

    public void deleteSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_SOLICITUD", solicitudIngreso.getIdSolicitud());
        solicitudIngresoDeleteCall.execute(params);
    }

}