package LengBD.controller;

import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import LengBD.service.AsignacionInstrumentoService;
import LengBD.service.EnsayosService;
import LengBD.service.PresentacionService;
import LengBD.service.RolUsuariosService;
import LengBD.service.SeccionService;
import LengBD.service.SolicitudIngresoService;
import LengBD.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private RolUsuariosService rolUsuariosService;
    
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private SeccionService seccionService;
    
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudIngresoService solicitudIngresoService;


    @Autowired
    private EnsayosService ensayosService;


    @Autowired
    private PresentacionService presentacionService;

    @GetMapping("/banda/secciones/listadoDirector")
    public String cargarPantallaDirector(Model model, Authentication auth) {
        UsuarioLoginDTO director = usuarioRepository.buscarPorCorreo(auth.getName());
        Integer idBanda = director.getIdBanda();

        model.addAttribute("asignaciones", asignacionInstrumentoService.readAllAsignacionInstrumento());
        model.addAttribute("solicitudes", solicitudIngresoService.readAllSolicitudIngreso());
        model.addAttribute("usuarios", usuarioService.readUsuariosPorBanda(idBanda));
        model.addAttribute("lideres", rolUsuariosService.readLideresPorBanda(idBanda));
        model.addAttribute("secciones", seccionService.readSeccionPorBanda(idBanda)); 
        model.addAttribute("ensayos", ensayosService.readEnsayosPorBanda(idBanda));
        model.addAttribute("presentaciones", presentacionService.readPresentacionPorBanda(idBanda));
        return "seccion/listadoDirector";
    }
}


