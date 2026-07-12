/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.RedSocialBanda;
import LengBD.domain.RedSocialBandaListadoDTO;
import LengBD.repository.RedSocialBandaRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class RedSocialBandaService {
    @Autowired
    private RedSocialBandaRepository redSocialBandaRepository;

    public void insertarRedSocialBanda(RedSocialBanda redSocialBanda) {
        redSocialBandaRepository.insertarRedSocialBanda(redSocialBanda);
    }

    public void actualizarRedSocialBanda(RedSocialBanda redSocialBanda) {
        redSocialBandaRepository.actualizarRedSocialBanda(redSocialBanda);
    }
    
    public List<RedSocialBandaListadoDTO> readAllRedSocialBanda() {
        return redSocialBandaRepository.readAllRedSocialBanda();
    }

    public void eliminarRedSocialBanda(Integer idRedSocial) {
        RedSocialBanda redSocialBanda = new RedSocialBanda();
        redSocialBanda.setIdRedSocial(idRedSocial);
        redSocialBandaRepository.deleteRedSocialBanda(redSocialBanda);
    }
    
    public RedSocialBandaListadoDTO buscarPorId(Integer id) {
        return readAllRedSocialBanda().stream()
                .filter(redSoc -> redSoc.getIdRedSocial().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
