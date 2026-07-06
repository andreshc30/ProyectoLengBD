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
                
                .withProcedureName("FIDE_SUSCRIPCION_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Suscripcion.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Suscripcion>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<Planes> listarPlanes() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_PLANES_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Planes.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Planes>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<Pago> listarPagos() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_PAGO_SUSCRIP_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Pago.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Pago>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<Facturacion> listarFacturas() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_FACTURACION_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(Facturacion.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<Facturacion>) out.get("P_REGISTRO");
    }

    @SuppressWarnings("unchecked")
    public List<MetodoPago> listarMetodosPago() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("FIDE_METODO_PAGO_READ_ALL_SP")
                .returningResultSet("P_REGISTRO", BeanPropertyRowMapper.newInstance(MetodoPago.class));

        Map<String, Object> out = jdbcCall.execute();
        return (List<MetodoPago>) out.get("P_REGISTRO");
    }
}