/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Publicacion;
import LengBD.domain.PublicacionListadoDTO;
import LengBD.repository.PublicacionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    public void insertarPublicacion(Publicacion publicacion) {
        publicacionRepository.insertarPublicacion(publicacion);
    }

    public void actualizarPublicacion(Publicacion publicacion) {
        publicacionRepository.actualizarPublicacion(publicacion);
    }
    
    public List<PublicacionListadoDTO> readAllPublicacion() {
        return publicacionRepository.readAllPublicacion();
    }

    public void eliminarPublicacion(Integer idPublicacion) {
        Publicacion publicacion = new Publicacion();
        publicacion.setIdPublicacion(idPublicacion);
        publicacionRepository.deletePublicacion(publicacion);
    }
    
    public PublicacionListadoDTO buscarPorId(Integer id) {
        return readAllPublicacion().stream()
                .filter(publi -> publi.getIdPublicacion().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
