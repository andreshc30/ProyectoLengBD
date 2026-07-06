/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.Ensayos;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class EnsayoService {

    private final DataSource dataSource;

    @Autowired
    public EnsayoService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public List<Ensayos> listarEnsayos() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_ENSAYOS_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Ensayos.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Ensayos>) out.get("P_REGISTRO");
    }
}
