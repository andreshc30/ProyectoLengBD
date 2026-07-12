/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Obra;
import LengBD.domain.ObraListadoDTO;
import LengBD.repository.ObraRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class ObraService {
    @Autowired
    private ObraRepository obraRepository;

    public void insertarObra(Obra obra) {
        obraRepository.insertarObra(obra);
    }

    public void actualizarObra(Obra obra) {
        obraRepository.actualizarObra(obra);
    }
    
    public List<ObraListadoDTO> readAllObra() {
        return obraRepository.readAllObra();
    }

    public void eliminarObra(Integer idObra) {
        Obra obra = new Obra();
        obra.setIdObra(idObra);
        obraRepository.deleteObra(obra);
    }
    
    public ObraListadoDTO buscarPorId(Integer id) {
        return readAllObra().stream()
                .filter(asis -> asis.getIdObra().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
