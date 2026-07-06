/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.Presentacion;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class PresentacionService {

    private final DataSource dataSource;

    @Autowired
    public PresentacionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public List<Presentacion> listarPresentaciones() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_PRESENTACION_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Presentacion.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Presentacion>) out.get("P_REGISTRO");
    }
}