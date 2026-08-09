/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;
 
import LengBD.domain.BalanceCategoriaDTO;
import LengBD.domain.ProveedorListadoDTO;
import LengBD.repository.FinanzasReporteRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class FinanzasReporteService {
    @Autowired
    private FinanzasReporteRepository finanzasReporteRepository;
 
    public List<ProveedorListadoDTO> readAllProveedores() {
        return finanzasReporteRepository.readAllProveedores();
    }
 
    public List<BalanceCategoriaDTO> readBalanceCategoria(Integer idBanda, LocalDate fechaInicio, LocalDate fechaFin) {
        return finanzasReporteRepository.readBalanceCategoria(idBanda, fechaInicio, fechaFin);
    }
 
    public BigDecimal calcularBalanceBanda(Integer idBanda, LocalDate fechaInicio, LocalDate fechaFin) {
        return finanzasReporteRepository.calcularBalanceBanda(idBanda, fechaInicio, fechaFin);
    }
}
