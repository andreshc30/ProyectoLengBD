/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.Obra;
import LengBD.domain.Presentacion;
import LengBD.domain.Suscripcion;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class SolicitudIngresoService {

    private final DataSource dataSource;

    @Autowired
    public SolicitudIngresoService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertarSuscripcion(Suscripcion suscripcion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_SOLICITUD_INGRESO_INSERT_SP");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_SOLICITUD", suscripcion.getIdSuscripcion())
                .addValue("P_NOMBRE", suscripcion.getNombre())
                .addValue("P_PRIMER_APELLIDO", suscripcion.getFechaInicio())
                .addValue("P_SEGUNDO_APELLIDO", suscripcion.getFechaFinal())
                .addValue("P_MENSAJE", suscripcion.getAutoRenovar())
                .addValue("P_FECHA_SOLICITUD", suscripcion.getIdTipoPlan())
                //.addValue("P_ID_CORREO", suscripcion.getIdPagoSuscripcion())
                //.addValue("P_ID_SECCION", suscripcion.getIdPagoSuscripcion())
                //.addValue("P_ID_BANDA", suscripcion.getIdPagoSuscripcion())
                //.addValue("P_ID_TELEFONO", suscripcion.getIdPagoSuscripcion())
                .addValue("P_ID_ESTADO", suscripcion.getIdEstado());

        jdbcCall.execute(params);
    }
}

