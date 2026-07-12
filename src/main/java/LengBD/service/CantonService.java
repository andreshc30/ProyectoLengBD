/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Canton;
import LengBD.domain.CantonListadoDTO;
import LengBD.repository.CantonRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class CantonService {
    @Autowired
    private CantonRepository cantonRepository;

    public void insertarCanton(Canton canton) {
        cantonRepository.insertarCanton(canton);
    }

    public void actualizarCanton(Canton canton) {
        cantonRepository.actualizarCanton(canton);
    }
    
    public List<CantonListadoDTO> readAllCanton() {
        return cantonRepository.readAllCanton();
    }

    public void eliminarCanton(Integer idCanton) {
        Canton canton = new Canton();
        canton.setIdCanton(idCanton);
        cantonRepository.deleteCanton(canton);
    }
    
    public CantonListadoDTO buscarPorId(Integer id) {
        return readAllCanton().stream()
                .filter(canton -> canton.getIdCanton().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
