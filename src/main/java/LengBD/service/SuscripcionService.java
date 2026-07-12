/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Suscripcion;
import LengBD.domain.SuscripcionListadoDTO;
import LengBD.repository.SuscripcionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class SuscripcionService {
    @Autowired
    private SuscripcionRepository suscripcionRepository;

    public void insertarSuscripcion(Suscripcion suscripcion) {
        suscripcionRepository.insertarSuscripcion(suscripcion);
    }

    public void actualizarSuscripcion(Suscripcion suscripcion) {
        suscripcionRepository.actualizarSuscripcion(suscripcion);
    }
    
    public List<SuscripcionListadoDTO> readAllSuscripcion() {
        return suscripcionRepository.readAllSuscripcion();
    }

    public void eliminarSuscripcion(Integer idSuscripcion) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setIdSuscripcion(idSuscripcion);
        suscripcionRepository.deleteSuscripcion(suscripcion);
    }
    
    public SuscripcionListadoDTO buscarPorId(Integer id) {
        return readAllSuscripcion().stream()
                .filter(asis -> asis.getIdSuscripcion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
