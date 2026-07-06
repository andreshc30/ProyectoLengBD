package LengBD.controller;

import LengBD.domain.Suscripcion;
import LengBD.repository.FacturacionRepository;
import LengBD.repository.MetodoPagoRepository;
import LengBD.repository.PagoSuscripcionRepository;
import LengBD.repository.PlanesRepository;
import LengBD.repository.SuscripcionRepository;
import LengBD.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suscripcion")
public class SuscripcionController {

    @Autowired private SuscripcionService suscripcionService;
    @Autowired private PlanesRepository planesRepository;
    @Autowired private SuscripcionRepository suscripcionRepository;
    @Autowired private PagoSuscripcionRepository pagoSuscripcionRepository;
    @Autowired private FacturacionRepository facturacionRepository;
    @Autowired private MetodoPagoRepository metodoPagoRepository;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("planes", planesRepository.findAll());
        model.addAttribute("suscripciones", suscripcionRepository.findAll());
        model.addAttribute("pagos", pagoSuscripcionRepository.findAll());
        model.addAttribute("facturas", facturacionRepository.findAll());
        model.addAttribute("metodos", metodoPagoRepository.findAll());
        return "suscripcion_pagos/listado";
    }
    
    
    @GetMapping("/nuevo")
    public String nuevaSuscripcion(Model model) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setIdEstado(1); // Activo por defecto
        model.addAttribute("suscripcion", suscripcion);
        return "suscripcion_pagos/formularioSuscripcion";
    }

    @PostMapping("/guardar")
    public String guardar(Suscripcion suscripcion) {
        suscripcionService.insertarSuscripcion(suscripcion);
        return "redirect:/suscripcion/listado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        System.out.println("=========================================");
        System.out.println("1. Entrando a editar. Buscando el ID: " + id);
        
        Suscripcion suscripcion = suscripcionService.buscarPorId(id);
        
        if (suscripcion == null) {
            System.out.println("2. ERROR FATAL: El SP en Oracle no devolvió NINGÚN dato para el ID " + id);
            System.out.println("=========================================");
            // Lo devolvemos al listado para que no salga la pantalla blanca de error
            return "redirect:/suscripcion/listado"; 
        }
        
        System.out.println("2. ÉXITO: Oracle devolvió la suscripción: " + suscripcion.getNombre());
        System.out.println("=========================================");
        
        model.addAttribute("suscripcion", suscripcion);
        return "suscripcion_pagos/formularioSuscripcion";
    }

    @PostMapping("/actualizar")
    public String actualizar(Suscripcion suscripcion) {
        suscripcionService.actualizarSuscripcion(suscripcion);
        return "redirect:/suscripcion/listado";
    }
}
