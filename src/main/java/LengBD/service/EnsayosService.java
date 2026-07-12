/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Ensayos;
import LengBD.domain.EnsayosListadoDTO;
import LengBD.repository.EnsayosRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class EnsayosService {
    @Autowired
    private EnsayosRepository ensayosRepository;

    public void insertarEnsayos(Ensayos ensayos) {
        ensayosRepository.insertarEnsayos(ensayos);
    }

    public void actualizarEnsayos(Ensayos ensayos) {
        ensayosRepository.actualizarEnsayos(ensayos);
    }
    
    public List<EnsayosListadoDTO> readAllEnsayos() {
        return ensayosRepository.readAllEnsayos();
    }

    public void eliminarEnsayos(Integer idEnsayo) {
        Ensayos ensayos = new Ensayos();
        ensayos.setIdEnsayo(idEnsayo);
        ensayosRepository.deleteEnsayos(ensayos);
    }
    
    public EnsayosListadoDTO buscarPorId(Integer id) {
        return readAllEnsayos().stream()
                .filter(asis -> asis.getIdEnsayo().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
