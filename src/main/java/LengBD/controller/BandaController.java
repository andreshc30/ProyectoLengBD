/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.service.BandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banda")
public class BandaController {
    
    @Autowired
    private BandaService bandaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("bandas", bandaService.listarBandas());
        return "gestion_bandas/listado";
    }
}
