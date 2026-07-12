/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.AsistenciaPresentacionListadoDTO;
import LengBD.repository.AsistenciaPresentacionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class AsistenciaPresentacionService {
    @Autowired
    private AsistenciaPresentacionRepository asistenciaPresentacionRepository;

    public void insertarAsistenciaPresentacion(AsistenciaPresentacion asistenciaPresentacion) {
        asistenciaPresentacionRepository.insertarAsistenciaPresentacion(asistenciaPresentacion);
    }

    public void actualizarAsistenciaPresentacion(AsistenciaPresentacion asistenciaPresentacion) {
        asistenciaPresentacionRepository.actualizarAsistenciaPresentacion(asistenciaPresentacion);
    }
    
    public List<AsistenciaPresentacionListadoDTO> readAllAsistenciaPresentacion() {
        return asistenciaPresentacionRepository.readAllAsistenciaPresentacion();
    }

    public void eliminarAsistenciaPresentacion(Integer idAsistenciaPresentacion) {
        AsistenciaPresentacion asistenciaPresentacion = new AsistenciaPresentacion();
        asistenciaPresentacion.setIdAsistenciaPresentacion(idAsistenciaPresentacion);
        asistenciaPresentacionRepository.deleteAsistenciaPresentacion(asistenciaPresentacion);
    }
    
    public AsistenciaPresentacionListadoDTO buscarPorId(Integer id) {
        return readAllAsistenciaPresentacion().stream()
                .filter(asisPre -> asisPre.getIdAsistenciaPresentacion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
