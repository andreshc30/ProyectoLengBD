package LengBD.controller;

import LengBD.service.AsignacionInstrumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SeccionesController {

    @Autowired
    private AsignacionInstrumentoService asignacionInstrumentoService;

    @GetMapping("/banda/secciones/listadoDirector")
    public String cargarPantallaDirector(Model model) {
        model.addAttribute("asignaciones", asignacionInstrumentoService.readAllAsignacionInstrumento());


        return "secciones/listadoDirector";
    }

}
