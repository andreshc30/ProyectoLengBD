package LengBD.controller;

import LengBD.domain.Planes;
import LengBD.domain.PlanesListadoDTO;
import LengBD.service.PlanesService;
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
@RequestMapping("/planes")
public class PlanesController {

    @Autowired
    private PlanesService planesService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PlanesListadoDTO> lista = planesService.readAllPlanes();
        model.addAttribute("planes", lista);
        return "planes/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("planes", new PlanesListadoDTO());
        cargarCombos(model);
        return "planes/formulario";
    }

    @GetMapping("/editar/{idTipoPlan}")
    public String editar(@PathVariable("idTipoPlan") Integer id, Model model) {
        model.addAttribute("planes", planesService.buscarPorId(id));
        cargarCombos(model);
        return "planes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PlanesListadoDTO dto, RedirectAttributes ra) {
        try {
            Planes planes = new Planes();
            planes.setIdTipoPlan(dto.getIdTipoPlan());
            planes.setNombre(dto.getNombre());
            planes.setPrecio(dto.getPrecio());
            planes.setDescripcion(dto.getDescripcion());
            planes.setPeriodicidad(dto.getPeriodicidad());
            planes.setIdEstado(dto.getIdEstado());

            if (dto.getIdTipoPlan() != null) {
                planesService.actualizarPlanes(planes);
            } else {
                planesService.insertarPlanes(planes);
            }
            ra.addFlashAttribute("todoOk", "Plan guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/planes/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idTipoPlan") Integer idTipoPlan, RedirectAttributes ra) {
        try {
            planesService.eliminarPlanes(idTipoPlan);
            ra.addFlashAttribute("todoOk", "Plan eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/planes/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}