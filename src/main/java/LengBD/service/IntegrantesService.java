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
        // CORREGIDO: el procedure real es FIDE_LISTAR_USUARIO_SP (no FIDE_INTEGRANTES_READ_ALL_SP,
        // que no existe en el package), y el cursor se llama P_CURSOR (no P_REGISTRO).
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_USUARIO_SP")
                .returningResultSet("P_CURSOR",
                        BeanPropertyRowMapper.newInstance(IntegranteComboDTO.class));
        Map<String, Object> out = jdbcCall.execute();
        return (List<IntegranteComboDTO>) out.get("P_CURSOR");
    }
}