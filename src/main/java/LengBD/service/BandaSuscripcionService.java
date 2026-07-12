/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.BandaSuscripcion;
import LengBD.domain.BandaSuscripcionListadoDTO;
import LengBD.repository.BandaSuscripcionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class BandaSuscripcionService {
    @Autowired
    private BandaSuscripcionRepository bandaSuscripcionRepository;

    public void insertarBandaSuscripcion(BandaSuscripcion bandaSuscripcion) {
        bandaSuscripcionRepository.insertarBandaSuscripcion(bandaSuscripcion);
    }

    public void actualizarBandaSuscripcion(BandaSuscripcion bandaSuscripcion) {
        bandaSuscripcionRepository.actualizarBandaSuscripcion(bandaSuscripcion);
    }
    
    public List<BandaSuscripcionListadoDTO> readAllBandaSuscripcion() {
        return bandaSuscripcionRepository.readAllBandaSuscripcion();
    }

    public void eliminarBandaSuscripcion(Integer idBanda, Integer idSuscripcion) {
        BandaSuscripcion bandaSuscripcion = new BandaSuscripcion();
        bandaSuscripcion.setIdBanda(idBanda);
        bandaSuscripcion.setIdSuscripcion(idSuscripcion);
        bandaSuscripcionRepository.deleteBandaSuscripcion(bandaSuscripcion);
    }
    
    public BandaSuscripcionListadoDTO buscarPorId(Integer idBanda, Integer idSuscripcion) {
        return readAllBandaSuscripcion().stream()
                .filter(bandaSus -> bandaSus.getIdBanda().equals(idBanda)
                && bandaSus.getIdSuscripcion().equals(idSuscripcion))
                .findFirst()
                .orElse(null);
    }
    
}
