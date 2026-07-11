/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.service.AudicionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author peper
 */
@Controller
@RequestMapping("/audiciones")
public class AudicionesController {



    @Autowired
    private AudicionesService audicionesService;

    @GetMapping("/listado")
    public String listado(Model model) {
        return "audiciones/listado";
    }
}
