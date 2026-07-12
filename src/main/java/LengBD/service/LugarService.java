/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Lugar;
import LengBD.domain.LugarListadoDTO;
import LengBD.repository.LugarRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class LugarService {
    @Autowired
    private LugarRepository lugarRepository;

    public void insertarLugar(Lugar lugar) {
        lugarRepository.insertarLugar(lugar);
    }

    public void actualizarLugar(Lugar lugar) {
        lugarRepository.actualizarLugar(lugar);
    }
    
    public List<LugarListadoDTO> readAllLugar() {
        return lugarRepository.readAllLugar();
    }

    public void eliminarLugar(Integer idLugar) {
        Lugar lugar = new Lugar();
        lugar.setIdLugar(idLugar);
        lugarRepository.deleteLugar(lugar);
    }
    
    public LugarListadoDTO buscarPorId(Integer id) {
        return readAllLugar().stream()
                .filter(lugar -> lugar.getIdLugar().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
