/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.EstadoCuentaMusicoDTO;
import LengBD.repository.EstadoCuentaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoCuentaService {
    @Autowired
    private EstadoCuentaRepository estadoCuentaRepository;

    public List<EstadoCuentaMusicoDTO> readEstadoCuenta() {
        return estadoCuentaRepository.readEstadoCuenta();
    }

    public List<EstadoCuentaMusicoDTO> readMorosos() {
        return estadoCuentaRepository.readMorosos();
    }
}
