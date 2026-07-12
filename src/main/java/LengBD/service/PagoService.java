/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Pago;
import LengBD.domain.PagoListadoDTO;
import LengBD.repository.PagoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public void insertarPago(Pago pago) {
        pagoRepository.insertarPago(pago);
    }

    public void actualizarPago(Pago pago) {
        pagoRepository.actualizarPago(pago);
    }
    
    public List<PagoListadoDTO> readAllPago() {
        return pagoRepository.readAllPago();
    }

    public void eliminarPago(Integer idPago) {
        Pago pago = new Pago();
        pago.setIdPago(idPago);
        pagoRepository.deletePago(pago);
    }
    
    public PagoListadoDTO buscarPorId(Integer id) {
        return readAllPago().stream()
                .filter(pago -> pago.getIdPago().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
