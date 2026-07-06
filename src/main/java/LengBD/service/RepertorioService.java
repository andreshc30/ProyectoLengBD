/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

/**
 *
 * @author peper
 */
import LengBD.domain.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class RepertorioService {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Autowired
    public RepertorioService(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public List<Obra> listarObras() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_OBRA_LIST_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Obra.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Obra>) out.get("P_REGISTRO");
    }
}