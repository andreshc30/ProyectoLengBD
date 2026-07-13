package LengBD.controller;

import LengBD.domain.Seccion;
import LengBD.domain.SeccionListadoDTO;
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
@RequestMapping("/seccion")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<SeccionListadoDTO> lista = seccionService.readAllSeccion();
        model.addAttribute("secciones", lista);
        return "seccion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("seccion", new SeccionListadoDTO());
        cargarCombos(model);
        return "seccion/formulario";
    }

    @GetMapping("/editar/{idSeccion}")
    public String editar(@PathVariable("idSeccion") Integer id, Model model) {
        model.addAttribute("seccion", seccionService.buscarPorId(id));
        cargarCombos(model);
        return "seccion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SeccionListadoDTO dto, RedirectAttributes ra) {
        try {
            Seccion seccion = new Seccion();
            seccion.setIdSeccion(dto.getIdSeccion());
            seccion.setNombre(dto.getNombre());
            seccion.setDescripcion(dto.getDescripcion());
            seccion.setIdBanda(dto.getIdBanda());
            seccion.setIdEstado(dto.getIdEstado());

            if (dto.getIdSeccion() != null) {
                seccionService.actualizarSeccion(seccion);
            } else {
                seccionService.insertarSeccion(seccion);
            }
            ra.addFlashAttribute("todoOk", "Sección guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/seccion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idSeccion") Integer idSeccion, RedirectAttributes ra) {
        try {
            seccionService.eliminarSeccion(idSeccion);
            ra.addFlashAttribute("todoOk", "Sección eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/seccion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}