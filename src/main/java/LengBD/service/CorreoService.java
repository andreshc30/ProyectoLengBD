/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Correo;
import LengBD.domain.CorreoListadoDTO;
import LengBD.repository.CorreoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class CorreoService {
    @Autowired
    private CorreoRepository correoRepository;

    public void insertarCorreo(Correo correo) {
        correoRepository.insertarCorreo(correo);
    }

    public void actualizarCorreo(Correo correo) {
        correoRepository.actualizarCorreo(correo);
    }
    
    public List<CorreoListadoDTO> readAllCorreo() {
        return correoRepository.readAllCorreo();
    }

    public void eliminarCorreo(Integer idCorreo) {
        Correo correo = new Correo();
        correo.setIdCorreo(idCorreo);
        correoRepository.deleteCorreo(correo);
    }
    
    public CorreoListadoDTO buscarPorId(Integer id) {
        return readAllCorreo().stream()
                .filter(correo -> correo.getIdCorreo().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
