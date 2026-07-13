package LengBD.controller;

import LengBD.domain.Planes;
import LengBD.domain.PlanesListadoDTO;
import LengBD.service.PlanesService;
import LengBD.service.EstadoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.PlanesService;
import LengBD.service.SuscripcionService;
import LengBD.service.UsuarioService;
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
@RequestMapping("/plan")
public class PlanesCrudController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private PlanesService planesService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PlanesListadoDTO> lista = planesService.readAllPlanes();
        model.addAttribute("planes", lista);
        model.addAttribute("nuevoPlan", new PlanesListadoDTO());
        cargarCombos(model);
        return "plan/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("plan", new PlanesListadoDTO());
        cargarCombos(model);
        return "plan/formulario";
    }

    @GetMapping("/editar/{idTipoPlan}")
    public String editar(@PathVariable("idTipoPlan") Integer id, Model model) {
        model.addAttribute("plan", planesService.buscarPorId(id));
        cargarCombos(model);
        return "plan/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PlanesListadoDTO dto, RedirectAttributes ra) {
        try {
            Planes plan = new Planes();
            plan.setIdTipoPlan(dto.getIdTipoPlan());
            plan.setNombre(dto.getNombre());
            plan.setPrecio(dto.getPrecio());
            plan.setDescripcion(dto.getDescripcion());
            plan.setPeriodicidad(dto.getPeriodicidad());
            plan.setIdEstado(dto.getIdEstado());

            if (dto.getIdTipoPlan()!= null) {
                planesService.actualizarPlanes(plan);
            } else {
                planesService.insertarPlanes(plan);
            }
            ra.addFlashAttribute("todoOk", "Plan guardado correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/plan/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idTipoPlan") Integer idTipoPlan, RedirectAttributes ra) {
        try {
            planesService.eliminarPlanes(idTipoPlan);
            ra.addFlashAttribute("todoOk", "Plan eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/plan/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}