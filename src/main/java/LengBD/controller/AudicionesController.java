package LengBD.controller;

import LengBD.service.BandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audiciones")
public class AudicionesController {

    @Autowired
    private BandaService bandaService;

    @GetMapping("/listado")
    public String carteleraAudiciones(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        return "audiciones/listado";
    }
    
}