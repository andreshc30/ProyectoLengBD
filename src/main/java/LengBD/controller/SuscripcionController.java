package LengBD.controller;

import LengBD.domain.Suscripcion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/suscripcion")
public class SuscripcionController {

    @GetMapping("/listado")
    public String listado() {
        return "suscripcion/listado";
    }

    @GetMapping("/formulario")
    public String formulario(@RequestParam(name = "plan", required = false) String plan, Model model) {
        String planSeleccionado = (plan != null) ? plan : "Ninguno seleccionado";

        model.addAttribute("planSeleccionado", planSeleccionado);
        model.addAttribute("suscripcion", new Suscripcion());
        return "suscripcion/formulario";
    }
}
