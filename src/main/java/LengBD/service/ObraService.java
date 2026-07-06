/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;


import LengBD.domain.Obra;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class ObraService {

    private final DataSource dataSource;

    @Autowired
    public ObraService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public List<Obra> listarObras() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_OBRA_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Obra.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Obra>) out.get("P_REGISTRO");
    }
}
