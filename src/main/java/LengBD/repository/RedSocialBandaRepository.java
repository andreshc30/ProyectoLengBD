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
import LengBD.domain.RedSocialBanda;
import LengBD.domain.RedSocialBandaListadoDTO;
import LengBD.domain.RedSocialBandaListadoDTO;
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
public class RedSocialBandaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall redSocialBandaInsertCall;
    private SimpleJdbcCall redSocialBandaUpdateCall;
    private SimpleJdbcCall redSocialBandaDeleteCall;
    private SimpleJdbcCall redSocialBandaReadAllCall;

    @PostConstruct
    public void init() {
        redSocialBandaInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_RED_SOCIAL_BANDA_INSERT_SP");

        redSocialBandaUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_RED_SOCIAL_BANDA_UPDATE_SP");

        redSocialBandaDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_RED_SOCIAL_BANDA_DELETE_SP");

        redSocialBandaReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_RED_SOCIAL_BANDA_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(RedSocialBandaListadoDTO.class));;
    }

    public void insertarRedSocialBanda(RedSocialBanda redSocialBanda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PLATAFORMA", redSocialBanda.getPlataforma());
        params.put("P_LINK_BANDA", redSocialBanda.getLinkBanda());
        params.put("P_ID_BANDA", redSocialBanda.getIdBanda());
        params.put("P_ID_ESTADO", redSocialBanda.getIdEstado());
        redSocialBandaUpdateCall.execute(params);
        redSocialBandaInsertCall.execute(params);
    }

    public void actualizarRedSocialBanda(RedSocialBanda redSocialBanda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_RED_SOCIAL", redSocialBanda.getIdRedSocial());
        params.put("P_PLATAFORMA", redSocialBanda.getPlataforma());
        params.put("P_LINK_BANDA", redSocialBanda.getLinkBanda());
        params.put("P_ID_BANDA", redSocialBanda.getIdBanda());
        params.put("P_ID_ESTADO", redSocialBanda.getIdEstado());
        redSocialBandaUpdateCall.execute(params);
    }

    public 
        List<RedSocialBandaListadoDTO> readAllRedSocialBanda() {
        Map<String, Object> result = redSocialBandaReadAllCall.execute();
        return (List<RedSocialBandaListadoDTO>) result.get("p_cursor");
    }

    public void deleteRedSocialBanda(RedSocialBanda redSocialBanda) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_RED_SOCIAL", redSocialBanda.getIdRedSocial());
        redSocialBandaDeleteCall.execute(params);
    }

}
