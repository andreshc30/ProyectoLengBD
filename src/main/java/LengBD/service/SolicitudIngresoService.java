/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.SolicitudIngreso;
import LengBD.domain.SolicitudIngresoListadoDTO;
import LengBD.repository.SolicitudIngresoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class SolicitudIngresoService {
    @Autowired
    private SolicitudIngresoRepository solicitudIngresoRepository;

    public void insertarSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        solicitudIngresoRepository.insertarSolicitudIngreso(solicitudIngreso);
    }

    public void actualizarSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        solicitudIngresoRepository.actualizarSolicitudIngreso(solicitudIngreso);
    }
    
    public List<SolicitudIngresoListadoDTO> readAllSolicitudIngreso() {
        return solicitudIngresoRepository.readAllSolicitudIngreso();
    }

    public void eliminarSolicitudIngreso(Integer idSolicitud) {
        SolicitudIngreso solicitudIngreso = new SolicitudIngreso();
        solicitudIngreso.setIdSolicitud(idSolicitud);
        solicitudIngresoRepository.deleteSolicitudIngreso(solicitudIngreso);
    }
    
    public SolicitudIngresoListadoDTO buscarPorId(Integer id) {
        return readAllSolicitudIngreso().stream()
                .filter(asis -> asis.getIdSolicitud().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
