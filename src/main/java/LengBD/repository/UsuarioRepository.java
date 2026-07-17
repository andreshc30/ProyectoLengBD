/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.UsuarioLoginDTO;
import LengBD.domain.Usuario;
import LengBD.domain.UsuarioListadoDTO;
import jakarta.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        
    // CORREGIDO: mapeo POR POSICION (indice de columna), no por nombre.
    // El mapeo por nombre fallaba (ORA-17006) porque varias tablas del JOIN
    // (Usuario, Seccion, Banda) comparten columnas fisicas llamadas "NOMBRE",
    // y el driver de Oracle no resolvia bien los alias en el cursor devuelto
    // por el CallableStatement. Por posicion es 100% confiable porque no
    // depende de como el driver interprete los labels -- solo del orden
    // exacto de columnas del SELECT dentro del SP:
    //  1 CEDULA, 2 NOMBRE, 3 PRIMER_APELLIDO, 4 SEGUNDO_APELLIDO,
    //  5 FECHA_INGRESO, 6 LOGO_URL, 7 ID_TELEFONO, 8 TELEFONO,
    //  9 ID_CORREO, 10 CORREO, 11 ESTADO, 12 ID_ESTADO, 13 ID_SECCION,
    //  14 NOMBRE_SECCION, 15 ID_DIRECCION, 16 NOMBRE_DIRECCION,
    //  17 ID_BANDA, 18 NOMBRE_BANDA
    private static final RowMapper<UsuarioListadoDTO> USUARIO_ROW_MAPPER = new RowMapper<UsuarioListadoDTO>() {
        @Override
        public UsuarioListadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsuarioListadoDTO dto = new UsuarioListadoDTO();
            dto.setCedula(rs.getObject(1) != null ? rs.getInt(1) : null);
            dto.setNombre(rs.getString(2));
            dto.setPrimerApellido(rs.getString(3));
            dto.setSegundoApellido(rs.getString(4));
            dto.setFechaIngreso(rs.getDate(5) != null ? rs.getDate(5).toLocalDate() : null);
            dto.setLogoUrl(rs.getString(6));
            dto.setIdTelefono(rs.getObject(7) != null ? rs.getInt(7) : null);
            dto.setTelefono(rs.getString(8));
            dto.setIdCorreo(rs.getObject(9) != null ? rs.getInt(9) : null);
            dto.setCorreo(rs.getString(10));
            dto.setEstado(rs.getString(11));
            dto.setIdEstado(rs.getObject(12) != null ? rs.getInt(12) : null);
            dto.setIdSeccion(rs.getObject(13) != null ? rs.getInt(13) : null);
            dto.setNombreSeccion(rs.getString(14));
            dto.setIdDireccion(rs.getObject(15) != null ? rs.getInt(15) : null);
            dto.setNombreDireccion(rs.getString(16));
            dto.setIdBanda(rs.getObject(17) != null ? rs.getInt(17) : null);
            dto.setNombreBanda(rs.getString(18));
            return dto;
        }
    };

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
                .withProcedureName("FIDE_USUARIO_DELETE_LOGICO_SP");

        usuarioReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_USUARIO_SP")
                .returningResultSet("P_CURSOR", USUARIO_ROW_MAPPER);
        
        loginCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LOGIN_USUARIO_SP")
                .returningResultSet("P_CURSOR", LOGIN_ROW_MAPPER);

        rolesCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROLES_USUARIO_SP")
                .returningResultSet("P_CURSOR", ROL_ROW_MAPPER);
    }

    public void insertarUsuario(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", usuario.getCedula());
        params.put("P_NOMBRE", usuario.getNombre());
        params.put("P_PRIMER_APELLIDO", usuario.getPrimerApellido());
        params.put("P_SEGUNDO_APELLIDO", usuario.getSegundoApellido());
        params.put("P_FECHA_INGRESO", usuario.getFechaIngreso());
        params.put("P_LOGO_URL", usuario.getLogoUrl());
        params.put("P_ID_TELEFONO", usuario.getIdTelefono());
        params.put("P_ID_CORREO", usuario.getIdCorreo());
        params.put("P_ID_ESTADO", usuario.getIdEstado());
        params.put("P_ID_SECCION", usuario.getIdSeccion());
        params.put("P_ID_DIRECCION", usuario.getIdDireccion());
        params.put("P_ID_BANDA", usuario.getIdBanda());
        usuarioInsertCall.execute(params);
    }

    public void actualizarUsuario(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", usuario.getCedula());
        params.put("P_NOMBRE", usuario.getNombre());
        params.put("P_PRIMER_APELLIDO", usuario.getPrimerApellido());
        params.put("P_SEGUNDO_APELLIDO", usuario.getSegundoApellido());
        params.put("P_FECHA_INGRESO", usuario.getFechaIngreso());
        params.put("P_LOGO_URL", usuario.getLogoUrl());
        params.put("P_ID_TELEFONO", usuario.getIdTelefono());
        params.put("P_ID_CORREO", usuario.getIdCorreo());
        params.put("P_ID_ESTADO", usuario.getIdEstado());
        params.put("P_ID_SECCION", usuario.getIdSeccion());
        params.put("P_ID_DIRECCION", usuario.getIdDireccion());
        params.put("P_ID_BANDA", usuario.getIdBanda());
        usuarioUpdateCall.execute(params);
    }

    public List<UsuarioListadoDTO> readAllUsuario() {
        Map<String, Object> result = usuarioReadAllCall.execute();
        return (List<UsuarioListadoDTO>) result.get("P_CURSOR");
    }

    public void deleteUsuario(Usuario usuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_USUARIO", usuario.getCedula());
        usuarioDeleteCall.execute(params);
    }

    
    
     @SuppressWarnings("unchecked")
    public UsuarioLoginDTO buscarPorCorreo(String correo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CORREO", correo);
        Map<String, Object> result = loginCall.execute(params);
        List<UsuarioLoginDTO> lista = (List<UsuarioLoginDTO>) result.get("P_CURSOR");
        return (lista == null || lista.isEmpty()) ? null : lista.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<String> buscarRolesPorCedula(Integer cedula) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", cedula);
        Map<String, Object> result = rolesCall.execute(params);
        List<String> roles = (List<String>) result.get("P_CURSOR");
        return roles == null ? new ArrayList<>() : roles;
    }
    
    private SimpleJdbcCall loginCall;
    private SimpleJdbcCall rolesCall;

    // Orden del SELECT en FIDE_LOGIN_USUARIO_SP:
    // 1 CEDULA, 2 PASSWORD, 3 NOMBRE, 4 PRIMER_APELLIDO,
    // 5 ID_ESTADO, 6 ID_BANDA, 7 NOMBRE_BANDA, 8 CORREO
    private static final RowMapper<UsuarioLoginDTO> LOGIN_ROW_MAPPER = (rs, rowNum) -> {
        UsuarioLoginDTO dto = new UsuarioLoginDTO();
        dto.setCedula(rs.getObject(1) != null ? rs.getInt(1) : null);
        dto.setPassword(rs.getString(2));
        dto.setNombre(rs.getString(3));
        dto.setPrimerApellido(rs.getString(4));
        dto.setIdEstado(rs.getObject(5) != null ? rs.getInt(5) : null);
        dto.setIdBanda(rs.getObject(6) != null ? rs.getInt(6) : null);
        dto.setNombreBanda(rs.getString(7));
        dto.setCorreo(rs.getString(8));
        return dto;
    };

    private static final RowMapper<String> ROL_ROW_MAPPER = (rs, rowNum) -> rs.getString(2);
}