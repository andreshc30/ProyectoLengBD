/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.RolUsuario;
import LengBD.domain.RolUsuarioListadoDTO;
import LengBD.repository.RolUsuariosRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class RolUsuariosService {
    @Autowired
    private RolUsuariosRepository rolUsuariosRepository;

    public void insertarRolUsuario(RolUsuario rolUsuarios) {
        rolUsuariosRepository.insertarRolUsuario(rolUsuarios);
    }

    public void actualizarRolUsuario(RolUsuario rolUsuarios) {
        rolUsuariosRepository.actualizarRolUsuario(rolUsuarios);
    }
    
    public List<RolUsuarioListadoDTO> readAllRolUsuario() {
        return rolUsuariosRepository.readAllRolUsuario();
    }

    public void eliminarRolUsuario(Integer idRol, Integer cedula) {
        RolUsuario rolUsuarios = new RolUsuario();
        rolUsuarios.setIdRol(idRol);
        rolUsuarios.setCedula(cedula);
        rolUsuariosRepository.deleteRolUsuario(rolUsuarios);
    }
    
    public RolUsuarioListadoDTO buscarPorId(Integer idRol, Integer cedula) {
        return readAllRolUsuario().stream()
                .filter(rolUsu -> rolUsu.getIdRol().equals(idRol)
                && rolUsu.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }
    
}
