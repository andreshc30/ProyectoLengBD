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
import LengBD.domain.Usuario;
import LengBD.domain.UsuarioListadoDTO;
import LengBD.domain.UsuarioListadoDTO;
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
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall usuarioInsertCall;
    private SimpleJdbcCall usuarioUpdateCall;
    private SimpleJdbcCall usuarioDeleteCall;
    private SimpleJdbcCall usuarioReadAllCall;

    @PostConstruct
    public void init() {
        usuarioInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_USUARIO_INSERT_SP");

        usuarioUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_USUARIO_UPDATE_SP");

        usuarioDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_USUARIO_DELETE_SP");

        usuarioReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_USUARIO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(UsuarioListadoDTO.class));;
    }

    public void insertarUsuario(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", usuario.getCedula());
        params.put("P_NOMBRE", usuario.getNombre());
        params.put("P_PRIMER_APELLIDO", usuario.getPrimerApellido());
        params.put("P_SEGUDNO_APELLIDO", usuario.getSegundoApellido());
        params.put("P_FECHO_INGRESO", usuario.getFechaIngreso());
        params.put("P_LOGO_URL", usuario.getLogoUrl());
        params.put("P_ID_TELEFONO", usuario.getIdTelefono());
        params.put("P_ID_CORREO", usuario.getIdCorreo());
        params.put("P_ID_ESTADO", usuario.getIdEstado());
        params.put("P_ID_SECCION", usuario.getIdSeccion());
        params.put("P_ID_DIRECCION", usuario.getIdDireccion());
        params.put("P_ID_BANDA", usuario.getIdBanda());
        usuarioUpdateCall.execute(params);
        usuarioInsertCall.execute(params);
    }

    public void actualizarUsuario(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", usuario.getCedula());
        params.put("P_NOMBRE", usuario.getNombre());
        params.put("P_PRIMER_APELLIDO", usuario.getPrimerApellido());
        params.put("P_SEGUDNO_APELLIDO", usuario.getSegundoApellido());
        params.put("P_FECHO_INGRESO", usuario.getFechaIngreso());
        params.put("P_LOGO_URL", usuario.getLogoUrl());
        params.put("P_ID_TELEFONO", usuario.getIdTelefono());
        params.put("P_ID_CORREO", usuario.getIdCorreo());
        params.put("P_ID_ESTADO", usuario.getIdEstado());
        params.put("P_ID_SECCION", usuario.getIdSeccion());
        params.put("P_ID_DIRECCION", usuario.getIdDireccion());
        params.put("P_ID_BANDA", usuario.getIdBanda());
        usuarioUpdateCall.execute(params);
    }

    public 
        List<UsuarioListadoDTO> readAllUsuario() {
        Map<String, Object> result = usuarioReadAllCall.execute();
        return (List<UsuarioListadoDTO>) result.get("p_cursor");
    }

    public void deleteUsuario(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_USUARIO", usuario.getCedula());
        usuarioDeleteCall.execute(params);
    }

}
