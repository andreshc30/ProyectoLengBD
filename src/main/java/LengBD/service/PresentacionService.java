/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Presentacion;
import LengBD.domain.PresentacionListadoDTO;
import LengBD.repository.PresentacionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class PresentacionService {
    @Autowired
    private PresentacionRepository presentacionRepository;

    public void insertarPresentacion(Presentacion presentacion) {
        presentacionRepository.insertarPresentacion(presentacion);
    }

    public void actualizarPresentacion(Presentacion presentacion) {
        presentacionRepository.actualizarPresentacion(presentacion);
    }
    
    public List<PresentacionListadoDTO> readAllPresentacion() {
        return presentacionRepository.readAllPresentacion();
    }

    public void eliminarPresentacion(Integer idPresentacion) {
        Presentacion presentacion = new Presentacion();
        presentacion.setIdPresentacion(idPresentacion);
        presentacionRepository.deletePresentacion(presentacion);
    }
    
    public PresentacionListadoDTO buscarPorId(Integer id) {
        return readAllPresentacion().stream()
                .filter(asis -> asis.getIdPresentacion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
