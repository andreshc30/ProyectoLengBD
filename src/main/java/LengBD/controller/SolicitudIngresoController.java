package LengBD.controller;

import LengBD.domain.Banda;
import LengBD.domain.Correo;
import LengBD.domain.Estado;
import LengBD.domain.Seccion;
import LengBD.domain.SolicitudIngreso;
import LengBD.domain.Telefono;
import LengBD.service.SolicitudIngresoService;
import LengBD.service.SeccionService;
import LengBD.service.BandaService;
import LengBD.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudIngresoController {

    @Autowired
    private SolicitudIngresoService solicitudService;
    @Autowired
    private SeccionService seccionService;
    @Autowired
    private BandaService bandaService;
    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("solicitudes", solicitudService.readAllSolicitudIngreso());
        // listarSecciones NO está en solicitudService -> usar seccionService
        model.addAttribute("secciones", seccionService.readAllSeccion());
        return "solicitudes/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SolicitudIngreso solicitud, RedirectAttributes ra) {
        try {
            if (solicitud.getIdSolicitud() != null) {
                solicitudService.actualizarSolicitudIngreso(solicitud);
            } else {
                solicitudService.insertarSolicitudIngreso(solicitud);
            }
            ra.addFlashAttribute("todoOk", "¡Tu solicitud se envió correctamente!");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al enviar: " + ex.getMessage());
        }
        return "redirect:/audiciones/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idSolicitud") Integer idSolicitud, RedirectAttributes ra) {
        try {
            solicitudService.eliminarSolicitudIngreso(idSolicitud);
            ra.addFlashAttribute("todoOk", "Solicitud eliminada");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/solicitudes/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("secciones", seccionService.readAllSeccion());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("solicitud", new SolicitudIngreso());
        cargarCombos(model);
        return "solicitudes/formulario";
    }

    @GetMapping("/editar/{idSolicitud}")
    public String editar(@PathVariable("idSolicitud") Integer id, Model model) {
        model.addAttribute("solicitud", solicitudService.buscarPorId(id));
        cargarCombos(model);
        return "solicitudes/formulario";
    }
}
