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
import LengBD.domain.LiderListadoDTO;
import LengBD.domain.RolUsuario;
import LengBD.domain.RolUsuarioListadoDTO;
import LengBD.domain.RolUsuarioListadoDTO;
import jakarta.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class RolUsuariosRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcCall listarLideresCall;

    private SimpleJdbcCall rolUsuarioInsertCall;
    private SimpleJdbcCall rolUsuarioUpdateCall;
    private SimpleJdbcCall rolUsuarioDeleteCall;
    private SimpleJdbcCall lideresPorBandaCall;
    private SimpleJdbcCall rolUsuarioReadAllCall;

    @PostConstruct
    public void init() {
        rolUsuarioInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_USUARIOS_INSERT_SP");
        
        
        listarLideresCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_LIDERES_SP")
                .returningResultSet("P_CURSOR", new RowMapper<LiderListadoDTO>() {
                    @Override
                    public LiderListadoDTO mapRow(ResultSet rs, int n) throws SQLException {
                        LiderListadoDTO d = new LiderListadoDTO();
                        d.setCedula(rs.getObject(1) != null ? rs.getInt(1) : null);
                        d.setNombreUsuario(rs.getString(2));
                        d.setIdRol(rs.getObject(3) != null ? rs.getInt(3) : null);
                        d.setIdSeccion(rs.getObject(4) != null ? rs.getInt(4) : null);
                        d.setNombreSeccion(rs.getString(5));
                        d.setCorreo(rs.getString(6));
                        d.setEstado(rs.getString(7));
                        System.out.println(">>> LIDER sec=[" + rs.getString(5) + "] cor=[" + rs.getString(6) + "] est=[" + rs.getString(7) + "]");
                        d.setIdEstado(rs.getObject(8) != null ? rs.getInt(8) : null);
                        return d;
                    }
                });


        rolUsuarioUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_USUARIOS_UPDATE_SP");

        rolUsuarioDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ROL_USUARIOS_DELETE_LOGICO_SP");

        rolUsuarioReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ROL_USUARIOS_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(RolUsuarioListadoDTO.class));;
                
                
        lideresPorBandaCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_LIDERES_POR_BANDA_SP")
                .returningResultSet("p_cursor", new RowMapper<LiderListadoDTO>() {
                    @Override
                    public LiderListadoDTO mapRow(ResultSet rs, int n) throws SQLException {
                        LiderListadoDTO d = new LiderListadoDTO();
                        d.setCedula(rs.getObject(1) != null ? rs.getInt(1) : null);
                        d.setNombreUsuario(rs.getString(2));
                        d.setIdRol(rs.getObject(3) != null ? rs.getInt(3) : null);
                        d.setIdSeccion(rs.getObject(4) != null ? rs.getInt(4) : null);
                        d.setNombreSeccion(rs.getString(5));
                        d.setCorreo(rs.getString(6));
                        d.setEstado(rs.getString(7));
                        d.setIdEstado(rs.getObject(8) != null ? rs.getInt(8) : null);
                        return d;
                    }
                });
        
    }

    public void insertarRolUsuario(RolUsuario rolUsuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", rolUsuario.getCedula());
        params.put("P_ID_ROL", rolUsuario.getIdRol());
        params.put("P_ID_ESTADO", rolUsuario.getIdEstado());
        rolUsuarioInsertCall.execute(params);
    }

    public void actualizarRolUsuario(RolUsuario rolUsuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", rolUsuario.getCedula());
        params.put("P_ID_ROL", rolUsuario.getIdRol());
        params.put("P_ID_ESTADO", rolUsuario.getIdEstado());
        rolUsuarioUpdateCall.execute(params);
    }

    public 
        List<RolUsuarioListadoDTO> readAllRolUsuario() {
        Map<String, Object> result = rolUsuarioReadAllCall.execute();
        return (List<RolUsuarioListadoDTO>) result.get("p_cursor");
    }

    public void deleteRolUsuario(RolUsuario rolUsuario) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CEDULA", rolUsuario.getCedula());
        params.put("P_ID_ROL", rolUsuario.getIdRol());
        rolUsuarioDeleteCall.execute(params);
    }
    
    @SuppressWarnings("unchecked")
    public List<LiderListadoDTO> readAllLideres() {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> result = listarLideresCall.execute(params);
        List<LiderListadoDTO> lista = (List<LiderListadoDTO>) result.get("P_CURSOR");
        return lista == null ? new java.util.ArrayList<>() : lista;
    }
    
    @SuppressWarnings("unchecked")
    public List<LiderListadoDTO> readLideresPorBanda(Integer idBanda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", idBanda);
        Map<String, Object> result = lideresPorBandaCall.execute(params);
        List<LiderListadoDTO> lista = (List<LiderListadoDTO>) result.get("p_cursor");
        return lista == null ? new java.util.ArrayList<>() : lista;
    }

}
