/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Banda;
import LengBD.domain.BandaListadoDTO;
import LengBD.repository.BandaRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class BandaService {
    @Autowired
    private BandaRepository bandaRepository;

    public void insertarBanda(Banda banda) {
        bandaRepository.insertarBanda(banda);
    }

    public void actualizarBanda(Banda banda) {
        bandaRepository.actualizarBanda(banda);
    }
    
    public List<BandaListadoDTO> readAllBanda() {
        return bandaRepository.readAllBanda();
    }

    public void eliminarBanda(Integer idBanda) {
        Banda banda = new Banda();
        banda.setIdBanda(idBanda);
        bandaRepository.deleteBanda(banda);
    }
    
    public BandaListadoDTO buscarPorId(Integer id) {
        return readAllBanda().stream()
                .filter(band -> band.getIdBanda().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
