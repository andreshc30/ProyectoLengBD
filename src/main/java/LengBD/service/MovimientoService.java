/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Movimiento;
import LengBD.domain.MovimientoListadoDTO;
import LengBD.repository.MovimientoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    public void insertarMovimiento(Movimiento movimiento) {
        movimientoRepository.insertarMovimiento(movimiento);
    }

    public void actualizarMovimiento(Movimiento movimiento) {
        movimientoRepository.actualizarMovimiento(movimiento);
    }
    
    public List<MovimientoListadoDTO> readAllMovimiento() {
        return movimientoRepository.readAllMovimiento();
    }

    public void eliminarMovimiento(Integer idMovimiento) {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(idMovimiento);
        movimientoRepository.deleteMovimiento(movimiento);
    }
    
    public MovimientoListadoDTO buscarPorId(Integer id) {
        return readAllMovimiento().stream()
                .filter(mov -> mov.getIdMovimiento().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
