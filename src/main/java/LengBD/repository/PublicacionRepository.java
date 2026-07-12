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
import LengBD.domain.Publicacion;
import LengBD.domain.PublicacionListadoDTO;
import LengBD.domain.PublicacionListadoDTO;
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
public class PublicacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall publicacionInsertCall;
    private SimpleJdbcCall publicacionUpdateCall;
    private SimpleJdbcCall publicacionDeleteCall;
    private SimpleJdbcCall publicacionReadAllCall;

    @PostConstruct
    public void init() {
        publicacionInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PUBLICACION_INSERT_SP");

        publicacionUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PUBLICACION_UPDATE_SP");

        publicacionDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_PUBLICACION_DELETE_SP");

        publicacionReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PUBLICACION_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(PublicacionListadoDTO.class));;
    }

    public void insertarPublicacion(Publicacion publicacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_FECHA", publicacion.getFecha());
        params.put("P_TITULO", publicacion.getTitulo());
        params.put("P_DETALLE", publicacion.getDetalle());
        params.put("P_ID_RED_SOCIAL", publicacion.getIdRedSocial());
        params.put("P_ID_ESTADO", publicacion.getIdEstado());
        publicacionUpdateCall.execute(params);
        publicacionInsertCall.execute(params);
    }

    public void actualizarPublicacion(Publicacion publicacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PUBLICACION", publicacion.getIdPublicacion());
        params.put("P_FECHA", publicacion.getFecha());
        params.put("P_TITULO", publicacion.getTitulo());
        params.put("P_DETALLE", publicacion.getDetalle());
        params.put("P_ID_RED_SOCIAL", publicacion.getIdRedSocial());
        params.put("P_ID_ESTADO", publicacion.getIdEstado());
        publicacionUpdateCall.execute(params);
    }

    public 
        List<PublicacionListadoDTO> readAllPublicacion() {
        Map<String, Object> result = publicacionReadAllCall.execute();
        return (List<PublicacionListadoDTO>) result.get("p_cursor");
    }

    public void deletePublicacion(Publicacion publicacion) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PUBLICACION", publicacion.getIdPublicacion());
        publicacionDeleteCall.execute(params);
    }

}
