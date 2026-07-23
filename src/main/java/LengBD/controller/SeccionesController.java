package LengBD.controller;

import LengBD.service.AsignacionInstrumentoService;
import LengBD.service.SolicitudIngresoService;
import LengBD.service.UsuarioService;
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
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudIngresoService solicitudIngresoService;

    @GetMapping("/banda/secciones/listadoDirector")
    public String cargarPantallaDirector(Model model) {
        model.addAttribute("asignaciones", asignacionInstrumentoService.readAllAsignacionInstrumento());
        model.addAttribute("solicitudes", solicitudIngresoService.readAllSolicitudIngreso());
        model.addAttribute("usuarios", usuarioService.readAllUsuario());
        return "seccion/listadoDirector";
    }

}
