/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Cuota;
import LengBD.domain.CuotaListadoDTO;
import LengBD.repository.CuotaRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class CuotaService {
    @Autowired
    private CuotaRepository cuotaRepository;

    public void insertarCuota(Cuota cuota) {
        cuotaRepository.insertarCuota(cuota);
    }

    public void actualizarCuota(Cuota cuota) {
        cuotaRepository.actualizarCuota(cuota);
    }
    
    public List<CuotaListadoDTO> readAllCuota() {
        return cuotaRepository.readAllCuota();
    }

    public void eliminarCuota(Integer idCuota) {
        Cuota cuota = new Cuota();
        cuota.setIdCuota(idCuota);
        cuotaRepository.deleteCuota(cuota);
    }
    
    public CuotaListadoDTO buscarPorId(Integer id) {
        return readAllCuota().stream()
                .filter(cuota -> cuota.getIdCuota().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
