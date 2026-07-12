/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Evento;
import LengBD.domain.EventoListadoDTO;
import LengBD.repository.EventoRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public void insertarEvento(Evento evento) {
        eventoRepository.insertarEvento(evento);
    }

    public void actualizarEvento(Evento evento) {
        eventoRepository.actualizarEvento(evento);
    }
    
    public List<EventoListadoDTO> readAllEvento() {
        return eventoRepository.readAllEvento();
    }

    public void eliminarEvento(Integer idEvento) {
        Evento evento = new Evento();
        evento.setIdEvento(idEvento);
        eventoRepository.deleteEvento(evento);
    }
    
    public EventoListadoDTO buscarPorId(Integer id) {
        return readAllEvento().stream()
                .filter(even -> even.getIdEvento().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
