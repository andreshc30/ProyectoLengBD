/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.Estado;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall estadoReadAllCall;

    @PostConstruct
    public void init() {
        estadoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_ESTADO_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Estado.class));
    }
      // <-- ESTO es lo que falta
    public List<Estado> readAllEstado() {
        Map<String, Object> result = estadoReadAllCall.execute();
        return (List<Estado>) result.get("P_REGISTRO");
    }
}