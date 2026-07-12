    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.CategoriaMovimiento;
import LengBD.domain.CategoriaMovimientoListadoDTO;
import LengBD.repository.CategoriaMovimientoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class CategoriaMovimientoService {
    @Autowired
    private CategoriaMovimientoRepository categoriaMovimientoRepository;

    public void insertarCategoriaMovimiento(CategoriaMovimiento categoriaMovimiento) {
        categoriaMovimientoRepository.insertarCategoriaMovimiento(categoriaMovimiento);
    }

    public void actualizarCategoriaMovimiento(CategoriaMovimiento categoriaMovimiento) {
        categoriaMovimientoRepository.actualizarCategoriaMovimiento(categoriaMovimiento);
    }
    
    public List<CategoriaMovimientoListadoDTO> readAllCategoriaMovimiento() {
        return categoriaMovimientoRepository.readAllCategoriaMovimiento();
    }

    public void eliminarCategoriaMovimiento(Integer idCategoriaMovimiento) {
        CategoriaMovimiento categoriaMovimiento = new CategoriaMovimiento();
        categoriaMovimiento.setIdCategoriaMovimiento(idCategoriaMovimiento);
        categoriaMovimientoRepository.deleteCategoriaMovimiento(categoriaMovimiento);
    }
    
    public CategoriaMovimientoListadoDTO buscarPorId(Integer id) {
        return readAllCategoriaMovimiento().stream()
                .filter(catMov -> catMov.getIdCategoriaMovimiento().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
