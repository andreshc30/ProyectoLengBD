/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Rol;
import LengBD.domain.RolListadoDTO;
import LengBD.repository.RolRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public void insertarRol(Rol rol) {
        rolRepository.insertarRol(rol);
    }

    public void actualizarRol(Rol rol) {
        rolRepository.actualizarRol(rol);
    }
    
    public List<RolListadoDTO> readAllRol() {
        return rolRepository.readAllRol();
    }

    public void eliminarRol(Integer idRol) {
        Rol rol = new Rol();
        rol.setIdRol(idRol);
        rolRepository.deleteRol(rol);
    }
    
    public RolListadoDTO buscarPorId(Integer id) {
        return readAllRol().stream()
                .filter(rol -> rol.getIdRol().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
