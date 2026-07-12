/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Facturacion;
import LengBD.domain.FacturacionListadoDTO;
import LengBD.repository.FacturacionRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class FacturacionService {
    @Autowired
    private FacturacionRepository facturacionRepository;

    public void insertarFacturacion(Facturacion facturacion) {
        facturacionRepository.insertarFacturacion(facturacion);
    }

    public void actualizarFacturacion(Facturacion facturacion) {
        facturacionRepository.actualizarFacturacion(facturacion);
    }
    
    public List<FacturacionListadoDTO> readAllFacturacion() {
        return facturacionRepository.readAllFacturacion();
    }

    public void eliminarFacturacion(Integer idFactura) {
        Facturacion facturacion = new Facturacion();
        facturacion.setIdFactura(idFactura);
        facturacionRepository.deleteFacturacion(facturacion);
    }
    
    public FacturacionListadoDTO buscarPorId(Integer id) {
        return readAllFacturacion().stream()
                .filter(fac -> fac.getIdFactura().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
