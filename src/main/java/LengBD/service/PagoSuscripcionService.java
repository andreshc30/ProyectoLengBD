/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.PagoSuscripcion;
import LengBD.domain.PagoSuscripcionListadoDTO;
import LengBD.repository.PagoSuscripcionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class PagoSuscripcionService {
    @Autowired
    private PagoSuscripcionRepository pagoSuscripcionRepository;

    public void insertarPagoSuscripcion(PagoSuscripcion pagoSuscripcion) {
        pagoSuscripcionRepository.insertarPagoSuscripcion(pagoSuscripcion);
    }

    public void actualizarPagoSuscripcion(PagoSuscripcion pagoSuscripcion) {
        pagoSuscripcionRepository.actualizarPagoSuscripcion(pagoSuscripcion);
    }
    
    public List<PagoSuscripcionListadoDTO> readAllPagoSuscripcion() {
        return pagoSuscripcionRepository.readAllPagoSuscripcion();
    }

    public void eliminarPagoSuscripcion(Integer idPagoSuscripcion) {
        PagoSuscripcion pagoSuscripcion = new PagoSuscripcion();
        pagoSuscripcion.setIdPagoSuscripcion(idPagoSuscripcion);
        pagoSuscripcionRepository.deletePagoSuscripcion(pagoSuscripcion);
    }
    
    public PagoSuscripcionListadoDTO buscarPorId(Integer id) {
        return readAllPagoSuscripcion().stream()
                .filter(pagoSus -> pagoSus.getIdPagoSuscripcion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
