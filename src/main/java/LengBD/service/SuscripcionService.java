/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author peper
 */
import LengBD.domain.Suscripcion;
import LengBD.domain.Planes;
import LengBD.domain.Pago;
import LengBD.domain.Facturacion;
import LengBD.domain.MetodoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
=======
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc

@Service
public class SuscripcionService {

    private final DataSource dataSource;

    @Autowired
    public SuscripcionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    public List<Suscripcion> listarSuscripciones() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
<<<<<<< HEAD
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
=======
                
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc
                .withProcedureName("FIDE_SUSCRIPCION_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Suscripcion.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Suscripcion>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<Planes> listarPlanes() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
<<<<<<< HEAD
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
=======
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc
                .withProcedureName("FIDE_PLANES_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Planes.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Planes>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<Pago> listarPagos() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
<<<<<<< HEAD
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
=======
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc
                .withProcedureName("FIDE_PAGO_SUSCRIP_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Pago.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Pago>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<Facturacion> listarFacturas() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
<<<<<<< HEAD
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
=======
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc
                .withProcedureName("FIDE_FACTURACION_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Facturacion.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Facturacion>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<MetodoPago> listarMetodosPago() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
<<<<<<< HEAD
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
=======
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc
                .withProcedureName("FIDE_METODO_PAGO_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(MetodoPago.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<MetodoPago>) out.get("P_REGISTRO");
    }
<<<<<<< HEAD
    
    @SuppressWarnings("unchecked")
    public Suscripcion buscarPorId(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SUSCRIPCION_READ_BY_ID_SP")
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
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
                .addValue("P_ID_SUSCRIPCION", id)
                .addValue("P_ESTADO_INACT", 2); 

        jdbcCall.execute(params);
    }
    
    
}
=======
}
>>>>>>> 05d6135faa99019f7e05bcf8372845e64711bffc
