/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.MetodoPago;
import LengBD.domain.MetodoPagoListadoDTO;
import LengBD.repository.MetodoPagoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class MetodoPagoService {
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public void insertarMetodoPago(MetodoPago metodoPago) {
        metodoPagoRepository.insertarMetodoPago(metodoPago);
    }

    public void actualizarMetodoPago(MetodoPago metodoPago) {
        metodoPagoRepository.actualizarMetodoPago(metodoPago);
    }
    
    public List<MetodoPagoListadoDTO> readAllMetodoPago() {
        return metodoPagoRepository.readAllMetodoPago();
    }

    public void eliminarMetodoPago(Integer idMetodoPago) {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(idMetodoPago);
        metodoPagoRepository.deleteMetodoPago(metodoPago);
    }
    
    public MetodoPagoListadoDTO buscarPorId(Integer id) {
        return readAllMetodoPago().stream()
                .filter(metoPag -> metoPag.getIdMetodoPago().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
