/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Estado;
import LengBD.domain.EstadoListadoDTO;
import LengBD.repository.EstadoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public void insertarEstado(Estado estado) {
        estadoRepository.insertarEstado(estado);
    }

    public void actualizarEstado(Estado estado) {
        estadoRepository.actualizarEstado(estado);
    }
    
    public List<EstadoListadoDTO> readAllEstado() {
        return estadoRepository.readAllEstado();
    }

    public void eliminarEstado(Integer idEstado) {
        Estado estado = new Estado();
        estado.setIdEstado(idEstado);
        estadoRepository.deleteEstado(estado);
    }
    
    public EstadoListadoDTO buscarPorId(Integer id) {
        return readAllEstado().stream()
                .filter(asis -> asis.getIdEstado().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
