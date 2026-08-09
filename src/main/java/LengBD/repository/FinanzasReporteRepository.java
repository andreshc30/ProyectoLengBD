/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.BalanceCategoriaDTO;
import LengBD.domain.ProveedorListadoDTO;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class FinanzasReporteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall proveedoresCall;
    private SimpleJdbcCall balanceCategoriaCall;
    private SimpleJdbcCall balanceBandaFn;

    @PostConstruct
    public void init() {
        proveedoresCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_PROVEEDORES_SP")
                .returningResultSet("P_CURSOR",
                BeanPropertyRowMapper.newInstance(ProveedorListadoDTO.class));

        balanceCategoriaCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_BALANCE_CATEGORIA_SP")
                .returningResultSet("P_CURSOR",
                BeanPropertyRowMapper.newInstance(BalanceCategoriaDTO.class));


        balanceBandaFn = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withFunctionName("FIDE_BALANCE_BANDA_FN");
    }

    public List<ProveedorListadoDTO> readAllProveedores() {
        Map<String, Object> result = proveedoresCall.execute();
        return (List<ProveedorListadoDTO>) result.get("P_CURSOR");
    }

    public List<BalanceCategoriaDTO> readBalanceCategoria(Integer idBanda, LocalDate fechaInicio, LocalDate fechaFin) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_BANDA", idBanda);
        params.put("P_FECHA_INICIO", fechaInicio);
        params.put("P_FECHA_FIN", fechaFin);
        Map<String, Object> result = balanceCategoriaCall.execute(params);
        return (List<BalanceCategoriaDTO>) result.get("P_CURSOR");
    }

    public BigDecimal calcularBalanceBanda(Integer idBanda, LocalDate fechaInicio, LocalDate fechaFin) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_ID_BANDA", idBanda)
                .addValue("P_FECHA_INICIO", fechaInicio)
                .addValue("P_FECHA_FIN", fechaFin);
        return balanceBandaFn.executeFunction(BigDecimal.class, in);
    }
}