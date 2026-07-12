/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.AsistenciaEnsayo;
import LengBD.domain.AsistenciaEnsayoListadoDTO;
import LengBD.repository.AsistenciaEnsayoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class AsistenciaEnsayoService {
    @Autowired
    private AsistenciaEnsayoRepository asistenciaEnsayoRepository;

    public void insertarAsistenciaEnsayo(AsistenciaEnsayo asistenciaEnsayo) {
        asistenciaEnsayoRepository.insertarAsistenciaEnsayo(asistenciaEnsayo);
    }

    public void actualizarAsistenciaEnsayo(AsistenciaEnsayo asistenciaEnsayo) {
        asistenciaEnsayoRepository.actualizarAsistenciaEnsayo(asistenciaEnsayo);
    }
    
    public List<AsistenciaEnsayoListadoDTO> readAllAsistenciaEnsayo() {
        return asistenciaEnsayoRepository.readAllAsistenciaEnsayo();
    }

    public void eliminarAsistenciaEnsayo(Integer idAsistenciaEnsayo) {
        AsistenciaEnsayo asistenciaEnsayo = new AsistenciaEnsayo();
        asistenciaEnsayo.setIdAsistenciaEnsayos(idAsistenciaEnsayo);
        asistenciaEnsayoRepository.deleteAsistenciaEnsayo(asistenciaEnsayo);
    }
    
    public AsistenciaEnsayoListadoDTO buscarPorId(Integer id) {
        return readAllAsistenciaEnsayo().stream()
                .filter(asisEns -> asisEns.getIdAsistenciaEnsayos().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
