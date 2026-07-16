/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.SeccionListadoDTO;
import LengBD.domain.SolicitudIngreso;
import LengBD.domain.SolicitudIngresoListadoDTO;
import LengBD.repository.SeccionRepository;
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

    @Autowired
    private SeccionService seccionService;

    public void insertarSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        solicitudIngresoRepository.insertarSolicitudIngreso(solicitudIngreso);
    }

    public void actualizarSolicitudIngreso(SolicitudIngreso solicitudIngreso) {
        solicitudIngresoRepository.actualizarSolicitudIngreso(solicitudIngreso);
    }
    
    public List<SolicitudIngresoListadoDTO> readAllSolicitudIngreso() {
        List<SolicitudIngresoListadoDTO> solicitudes = solicitudIngresoRepository.readAllSolicitudIngreso();
        // Obtenemos todas las secciones de una vez usando el SP existente
        List<SeccionListadoDTO> secciones = seccionService.readAllSeccion();

        // Cruzamos la información en memoria
        for (SolicitudIngresoListadoDTO sol : solicitudes) {
            if (sol.getIdSeccion() != null) {
                // Buscamos el nombre correspondiente al ID
                String nombre = secciones.stream()
                    .filter(s -> s.getIdSeccion().equals(sol.getIdSeccion()))
                    .findFirst()
                    .map(SeccionListadoDTO::getNombre)
                    .orElse("N/A");
                
                sol.setNombreSeccion(nombre);
            }
        }
        return solicitudes;
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
