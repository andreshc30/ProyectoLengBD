/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Instrumento;
import LengBD.domain.InstrumentoListadoDTO;
import LengBD.repository.InstrumentoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class InstrumentoService {
    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public void insertarInstrumento(Instrumento instrumento) {
        instrumentoRepository.insertarInstrumento(instrumento);
    }

    public void actualizarInstrumento(Instrumento instrumento) {
        instrumentoRepository.actualizarInstrumento(instrumento);
    }
    
    public List<InstrumentoListadoDTO> readAllInstrumento() {
        return instrumentoRepository.readAllInstrumento();
    }

    public void eliminarInstrumento(Integer idInstrumento) {
        Instrumento instrumento = new Instrumento();
        instrumento.setIdInstrumento(idInstrumento);
        instrumentoRepository.deleteInstrumento(instrumento);
    }
    
    public InstrumentoListadoDTO buscarPorId(Integer id) {
        return readAllInstrumento().stream()
                .filter(asis -> asis.getIdInstrumento().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
