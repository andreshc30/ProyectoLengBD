package LengBD.service;

import LengBD.domain.IntegranteComboDTO;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class IntegrantesService {

    private final DataSource dataSource;

    @Autowired
    public IntegrantesService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public List<IntegranteComboDTO> listarIntegrantes() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_INTEGRANTES_READ_ALL_SP")
                .returningResultSet("P_REGISTRO",
                        BeanPropertyRowMapper.newInstance(IntegranteComboDTO.class));
        Map<String, Object> out = jdbcCall.execute();
        return (List<IntegranteComboDTO>) out.get("P_REGISTRO");
    }
}
