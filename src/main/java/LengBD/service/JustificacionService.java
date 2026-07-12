/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Justificacion;
import LengBD.domain.JustificacionListadoDTO;
import LengBD.repository.JustificacionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class JustificacionService {
    @Autowired
    private JustificacionRepository justificacionRepository;

    public void insertarJustificacion(Justificacion justificacion) {
        justificacionRepository.insertarJustificacion(justificacion);
    }

    public void actualizarJustificacion(Justificacion justificacion) {
        justificacionRepository.actualizarJustificacion(justificacion);
    }
    
    public List<JustificacionListadoDTO> readAllJustificacion() {
        return justificacionRepository.readAllJustificacion();
    }

    public void eliminarJustificacion(Integer idJustificacion) {
        Justificacion justificacion = new Justificacion();
        justificacion.setIdJustificacion(idJustificacion);
        justificacionRepository.deleteJustificacion(justificacion);
    }
    
    public JustificacionListadoDTO buscarPorId(Integer id) {
        return readAllJustificacion().stream()
                .filter(just -> just.getIdJustificacion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
