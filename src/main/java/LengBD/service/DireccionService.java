/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Direccion;
import LengBD.domain.DireccionListadoDTO;
import LengBD.repository.DireccionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    public void insertarDireccion(Direccion direccion) {
        direccionRepository.insertarDireccion(direccion);
    }

    public void actualizarDireccion(Direccion direccion) {
        direccionRepository.actualizarDireccion(direccion);
    }
    
    public List<DireccionListadoDTO> readAllDireccion() {
        return direccionRepository.readAllDireccion();
    }

    public void eliminarDireccion(Integer idDireccion) {
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(idDireccion);
        direccionRepository.deleteDireccion(direccion);
    }
    
    public DireccionListadoDTO buscarPorId(Integer idDireccion) {
        return readAllDireccion().stream()
                .filter(dire -> dire.getIdDireccion().equals(idDireccion))
                .findFirst()
                .orElse(null);
    }
    
}
