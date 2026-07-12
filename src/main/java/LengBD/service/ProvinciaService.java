/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Provincia;
import LengBD.domain.ProvinciaListadoDTO;
import LengBD.repository.ProvinciaRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void insertarProvincia(Provincia provincia) {
        provinciaRepository.insertarProvincia(provincia);
    }

    public void actualizarProvincia(Provincia provincia) {
        provinciaRepository.actualizarProvincia(provincia);
    }
    
    public List<ProvinciaListadoDTO> readAllProvincia() {
        return provinciaRepository.readAllProvincia();
    }

    public void eliminarProvincia(Integer idProvincia) {
        Provincia provincia = new Provincia();
        provincia.setIdProvincia(idProvincia);
        provinciaRepository.deleteProvincia(provincia);
    }
    
    public ProvinciaListadoDTO buscarPorId(Integer id) {
        return readAllProvincia().stream()
                .filter(prov -> prov.getIdProvincia().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
