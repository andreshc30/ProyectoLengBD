/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Seccion;
import LengBD.domain.SeccionListadoDTO;
import LengBD.repository.SeccionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class SeccionService {
    @Autowired
    private SeccionRepository seccionRepository;

    public void insertarSeccion(Seccion seccion) {
        seccionRepository.insertarSeccion(seccion);
    }

    public void actualizarSeccion(Seccion seccion) {
        seccionRepository.actualizarSeccion(seccion);
    }
    
    public List<SeccionListadoDTO> readAllSeccion() {
        return seccionRepository.readAllSeccion();
    }

    public void eliminarSeccion(Integer idSeccion) {
        Seccion seccion = new Seccion();
        seccion.setIdSeccion(idSeccion);
        seccionRepository.deleteSeccion(seccion);
    }
    
    public SeccionListadoDTO buscarPorId(Integer id) {
        return readAllSeccion().stream()
                .filter(sec -> sec.getIdSeccion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
