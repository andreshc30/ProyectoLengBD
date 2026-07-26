package LengBD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/planes")
public class PlanesController {

    @GetMapping("/listado")
    public String listado(Model model,
                           @RequestParam(value = "plan", required = false, defaultValue = "Plan Básico") String planSeleccionado) {
        model.addAttribute("planSeleccionado", planSeleccionado);
        return "planes/listado";
    }
}