/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Distrito;
import LengBD.domain.DistritoListadoDTO;
import LengBD.repository.DistritoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class DistritoService {
    @Autowired
    private DistritoRepository distritoRepository;

    public void insertarDistrito(Distrito distrito) {
        distritoRepository.insertarDistrito(distrito);
    }

    public void actualizarDistrito(Distrito distrito) {
        distritoRepository.actualizarDistrito(distrito);
    }
    
    public List<DistritoListadoDTO> readAllDistrito() {
        return distritoRepository.readAllDistrito();
    }

    public void eliminarDistrito(Integer idDistrito) {
        Distrito distrito = new Distrito();
        distrito.setIdDistrito(idDistrito);
        distritoRepository.deleteDistrito(distrito);
    }
    
    public DistritoListadoDTO buscarPorId(Integer id) {
        return readAllDistrito().stream()
                .filter(dis -> dis.getIdDistrito().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
