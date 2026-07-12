/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionInstrumento;
import LengBD.domain.AsignacionListadoDTO;
import LengBD.repository.AsignacionInstrumentoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class AsignacionInstrumentoService {
    @Autowired
    private AsignacionInstrumentoRepository asignacionInstrumentoRepository;

    public void insertarAsignacionInstrumento(AsignacionInstrumento asignacionInstrumento) {
        asignacionInstrumentoRepository.insertarAsignacionInstrumento(asignacionInstrumento);
    }

    // En AsignacionInstrumentoService.java
    public void actualizarAsignacionInstrumento(AsignacionInstrumento asignacionInstrumento) {
        asignacionInstrumentoRepository.actualizarAsignacionInstrumento(asignacionInstrumento);
    }

    public List<AsignacionListadoDTO> readAllAsignacionInstrumento() {
        return asignacionInstrumentoRepository.readAllAsignacionInstrumento();
    }

    public void eliminarAsignacionInstrumento(Integer idAsignacion) {
        AsignacionInstrumento asignacionInstrumento = new AsignacionInstrumento();
        asignacionInstrumento.setIdAsignacion(idAsignacion);
        asignacionInstrumentoRepository.deleteAsignacionInstrumento(asignacionInstrumento);
    }

    public AsignacionListadoDTO buscarPorId(Integer id) {
        return readAllAsignacionInstrumento().stream()
                .filter(asig -> asig.getIdAsignacion().equals(id))
                .findFirst()
                .orElse(null);
    }
}
