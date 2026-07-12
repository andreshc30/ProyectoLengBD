/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.BandaInstrumento;
import LengBD.domain.BandaInstrumentoListadoDTO;
import LengBD.repository.BandaInstrumentoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class BandaInstrumentoService {
    @Autowired
    private BandaInstrumentoRepository bandaInstrumentoRepository;

    public void insertarBandaInstrumento(BandaInstrumento bandaInstrumento) {
        bandaInstrumentoRepository.insertarBandaInstrumento(bandaInstrumento);
    }

    public void actualizarBandaInstrumento(BandaInstrumento bandaInstrumento) {
        bandaInstrumentoRepository.actualizarBandaInstrumento(bandaInstrumento);
    }
    
    public List<BandaInstrumentoListadoDTO> readAllBandaInstrumento() {
        return bandaInstrumentoRepository.readAllBandaInstrumento();
    }

    public void eliminarBandaInstrumento(Integer idBanda, Integer idInstrumento) {
        BandaInstrumento bandaInstrumento = new BandaInstrumento();
        bandaInstrumento.setIdBanda(idBanda);
        bandaInstrumento.setIdInstrumento(idInstrumento);
        bandaInstrumentoRepository.deleteBandaInstrumento(bandaInstrumento);
    }
    
    public BandaInstrumentoListadoDTO buscarPorId(Integer idBanda, Integer idInstrumento) {
        return readAllBandaInstrumento().stream()
                .filter(bandaIns -> bandaIns.getIdBanda().equals(idBanda)
                && bandaIns.getIdInstrumento().equals(idInstrumento))
                .findFirst()
                .orElse(null);
    }
    
}
