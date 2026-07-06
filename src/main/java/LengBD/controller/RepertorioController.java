/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.domain.Obra;
import LengBD.repository.ObraRepository;
import LengBD.service.RepertorioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repertorio")
public class RepertorioController {

    @Autowired
    private RepertorioService repertorioService;

    @Autowired
    private ObraRepository obraRepository;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("obras", obraRepository.findAll());
        return "repertorio/listado";
    }
}
