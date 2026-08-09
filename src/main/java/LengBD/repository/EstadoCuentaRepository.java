/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author andre
 */
import LengBD.domain.EstadoCuentaMusicoDTO;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoCuentaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall estadoCuentaCall;
    private SimpleJdbcCall morososCall;

    @PostConstruct
    public void init() {
        estadoCuentaCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_ESTADO_CUENTA_MUSICO_SP")
                .returningResultSet("P_CURSOR",
                BeanPropertyRowMapper.newInstance(EstadoCuentaMusicoDTO.class));

        morososCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_MOROSOS_SP")
                .returningResultSet("P_CURSOR",
                BeanPropertyRowMapper.newInstance(EstadoCuentaMusicoDTO.class));
    }

    public List<EstadoCuentaMusicoDTO> readEstadoCuenta() {
        Map<String, Object> result = estadoCuentaCall.execute();
        return (List<EstadoCuentaMusicoDTO>) result.get("P_CURSOR");
    }

    public List<EstadoCuentaMusicoDTO> readMorosos() {
        Map<String, Object> result = morososCall.execute();
        return (List<EstadoCuentaMusicoDTO>) result.get("P_CURSOR");
    }
}
