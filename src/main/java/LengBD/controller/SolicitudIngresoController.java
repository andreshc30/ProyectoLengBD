package LengBD.controller;

import LengBD.domain.SolicitudIngreso;
import LengBD.domain.SolicitudIngresoListadoDTO;
import LengBD.service.SolicitudIngresoService;
import LengBD.service.SeccionService;
import LengBD.service.BandaService;
import LengBD.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/solicitudIngreso")
public class SolicitudIngresoController {

    @Autowired
    private SolicitudIngresoService solicitudIngresoService;

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<SolicitudIngresoListadoDTO> lista = solicitudIngresoService.readAllSolicitudIngreso();
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listado";      // ← su propia vista (del director), NO la cartelera
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("solicitudIngreso", new SolicitudIngresoListadoDTO());
        cargarCombos(model);
        return "solicitudes/formulario";
    }

    @GetMapping("/editar/{idSolicitud}")
    public String editar(@PathVariable("idSolicitud") Integer id, Model model) {
        model.addAttribute("solicitudIngreso", solicitudIngresoService.buscarPorId(id));
        cargarCombos(model);
        return "solicitudes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SolicitudIngresoListadoDTO dto, RedirectAttributes ra) {
        try {
            SolicitudIngreso solicitudIngreso = new SolicitudIngreso();
            solicitudIngreso.setIdSolicitud(dto.getIdSolicitud());
            solicitudIngreso.setNombre(dto.getNombre());
            solicitudIngreso.setPrimerApellido(dto.getPrimerApellido());
            solicitudIngreso.setSegundoApellido(dto.getSegundoApellido());
            solicitudIngreso.setMensaje(dto.getMensaje());
            solicitudIngreso.setCorreo(dto.getCorreo());
            solicitudIngreso.setTelefono(dto.getTelefono());
            solicitudIngreso.setFechaSolicitud(dto.getFechaSolicitud());
            solicitudIngreso.setIdSeccion(dto.getIdSeccion());
            solicitudIngreso.setIdBanda(dto.getIdBanda());
            solicitudIngreso.setIdEstado(dto.getIdEstado());

            if (dto.getIdSolicitud() != null) {
                solicitudIngresoService.actualizarSolicitudIngreso(solicitudIngreso);
            } else {
                solicitudIngresoService.insertarSolicitudIngreso(solicitudIngreso);
            }
            ra.addFlashAttribute("todoOk", "Solicitud de ingreso guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/audiciones/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idSolicitud") Integer idSolicitud, RedirectAttributes ra) {
        try {
            solicitudIngresoService.eliminarSolicitudIngreso(idSolicitud);
            ra.addFlashAttribute("todoOk", "Solicitud de ingreso eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/solicitudIngreso/listado";
    }

    private void cargarCombos(Model model) {
        try {
            model.addAttribute("secciones", seccionService.readAllSeccion());
            model.addAttribute("bandas", bandaService.readAllBanda());
            model.addAttribute("estados", estadoService.readAllEstado());
        } catch (Exception e) {
            System.err.println("Error cargando combos: " + e.getMessage());
            model.addAttribute("errorCarga", "No se pudieron cargar los datos de las listas.");
        }
    }
    

}


