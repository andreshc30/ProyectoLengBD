/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Planes;
import LengBD.domain.PlanesListadoDTO;
import LengBD.repository.PlanesRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class PlanesService {
    @Autowired
    private PlanesRepository planesRepository;

    public void insertarPlanes(Planes planes) {
        planesRepository.insertarPlanes(planes);
    }

    public void actualizarPlanes(Planes planes) {
        planesRepository.actualizarPlanes(planes);
    }
    
    public List<PlanesListadoDTO> readAllPlanes() {
        return planesRepository.readAllPlanes();
    }

    public void eliminarPlanes(Integer idTipoPlan) {
        Planes planes = new Planes();
        planes.setIdTipoPlan(idTipoPlan);
        planesRepository.deletePlanes(planes);
    }
    
    public PlanesListadoDTO buscarPorId(Integer id) {
        return readAllPlanes().stream()
                .filter(plan -> plan.getIdTipoPlan().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
