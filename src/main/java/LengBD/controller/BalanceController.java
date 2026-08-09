/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.service.BandaService;
import LengBD.service.FinanzasReporteService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private FinanzasReporteService finanzasReporteService;

    @Autowired
    private BandaService bandaService;

    @GetMapping("/reporte")
    public String reporte(
            @RequestParam(value = "idBanda", required = false) Integer idBanda,
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model) {

        model.addAttribute("bandas", bandaService.readAllBanda());

        if (idBanda != null && fechaInicio != null && fechaFin != null) {
            model.addAttribute("total", finanzasReporteService.calcularBalanceBanda(idBanda, fechaInicio, fechaFin));
            model.addAttribute("detalle", finanzasReporteService.readBalanceCategoria(idBanda, fechaInicio, fechaFin));
            model.addAttribute("idBandaSel", idBanda);
            model.addAttribute("fechaInicioSel", fechaInicio);
            model.addAttribute("fechaFinSel", fechaFin);
        }
        return "balance/reporte";
    }
}