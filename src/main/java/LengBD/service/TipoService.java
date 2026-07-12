/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Tipo;
import LengBD.domain.TipoListadoDTO;
import LengBD.repository.TipoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    public void insertarTipo(Tipo tipo) {
        tipoRepository.insertarTipo(tipo);
    }

    public void actualizarTipo(Tipo tipo) {
        tipoRepository.actualizarTipo(tipo);
    }
    
    public List<TipoListadoDTO> readAllTipo() {
        return tipoRepository.readAllTipo();
    }

    public void eliminarTipo(Integer idTipo) {
        Tipo tipo = new Tipo();
        tipo.setIdTipo(idTipo);
        tipoRepository.deleteTipo(tipo);
    }
    
    public TipoListadoDTO buscarPorId(Integer id) {
        return readAllTipo().stream()
                .filter(tipo -> tipo.getIdTipo().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
