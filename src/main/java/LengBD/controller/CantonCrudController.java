package LengBD.controller;

import LengBD.domain.Canton;
import LengBD.domain.CantonListadoDTO;
import LengBD.service.CantonService;
import LengBD.service.EstadoService;
import LengBD.service.CantonService;
import LengBD.service.CantonService;
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
@RequestMapping("/canton")
public class CantonCrudController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private CantonService cantonService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<CantonListadoDTO> lista = cantonService.readAllCanton();
        model.addAttribute("cantones", lista);
        model.addAttribute("nuevoCanton", new CantonListadoDTO());
        cargarCombos(model);
        return "canton/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("canton", new CantonListadoDTO());
        cargarCombos(model);
        return "canton/formulario";
    }

    @GetMapping("/editar/{idCanton}")
    public String editar(@PathVariable("idCanton") Integer id, Model model) {
        model.addAttribute("canton", cantonService.buscarPorId(id));
        cargarCombos(model);
        return "canton/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CantonListadoDTO dto, RedirectAttributes ra) {
        try {
            Canton canton = new Canton();
            canton.setIdCanton(dto.getIdCanton());
            canton.setNombre(dto.getNombre());
            canton.setIdEstado(dto.getIdEstado());

            if (dto.getIdCanton()!= null) {
                cantonService.actualizarCanton(canton);
            } else {
                cantonService.insertarCanton(canton);
            }
            ra.addFlashAttribute("todoOk", "Canton guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/canton/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idCanton") Integer idCanton, RedirectAttributes ra) {
        try {
            cantonService.eliminarCanton(idCanton);
            ra.addFlashAttribute("todoOk", "Canton eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/canton/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}