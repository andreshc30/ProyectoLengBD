package LengBD.service;

import LengBD.domain.Suscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class SuscripcionService {

    private final DataSource dataSource;

    @Autowired
    public SuscripcionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public Suscripcion buscarPorId(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_READ_BY_ID_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Suscripcion.class));

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_SUSCRIPCION", id);

        Map<String, Object> out = jdbcCall.execute(params);
        List<Suscripcion> resultados = (List<Suscripcion>) out.get("P_REGISTRO");

        return (resultados != null && !resultados.isEmpty()) ? resultados.get(0) : null;
    }

    public void insertarSuscripcion(Suscripcion suscripcion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_INSERT_SP");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_SUSCRIPCION", suscripcion.getIdSuscripcion())
                .addValue("P_NOMBRE", suscripcion.getNombre())
                .addValue("P_FECHA_INICIO", suscripcion.getFechaInicio())
                .addValue("P_FECHA_FINAL", suscripcion.getFechaFinal())
                .addValue("P_AUTO_RENOVAR", suscripcion.getAutoRenovar())
                .addValue("P_ID_TIPO_PLAN", suscripcion.getIdTipoPlan())
                .addValue("P_ID_PAGO_SUSCRIPCION", suscripcion.getIdPagoSuscripcion())
                .addValue("P_ID_ESTADO", suscripcion.getIdEstado());

        jdbcCall.execute(params);
    }

    public void actualizarSuscripcion(Suscripcion suscripcion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_UPDATE_SP");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_SUSCRIPCION", suscripcion.getIdSuscripcion())
                .addValue("P_NOMBRE", suscripcion.getNombre())
                .addValue("P_FECHA_INICIO", suscripcion.getFechaInicio())
                .addValue("P_FECHA_FINAL", suscripcion.getFechaFinal())
                .addValue("P_AUTO_RENOVAR", suscripcion.getAutoRenovar())
                .addValue("P_ID_TIPO_PLAN", suscripcion.getIdTipoPlan())
                .addValue("P_ID_PAGO_SUSCRIPCION", suscripcion.getIdPagoSuscripcion())
                .addValue("P_ID_ESTADO", suscripcion.getIdEstado());

        jdbcCall.execute(params);
    }

    public void eliminarLogico(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_DELETE_SP");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_SUSCRIPCION", id);

        jdbcCall.execute(params);
    }
}