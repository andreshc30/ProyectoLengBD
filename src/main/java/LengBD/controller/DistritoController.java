package LengBD.controller;

import LengBD.domain.Distrito;
import LengBD.domain.DistritoListadoDTO;
import LengBD.service.DistritoService;
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
@RequestMapping("/distrito")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<DistritoListadoDTO> lista = distritoService.readAllDistrito();
        model.addAttribute("distritos", lista);
        return "distrito/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("distrito", new DistritoListadoDTO());
        cargarCombos(model);
        return "distrito/formulario";
    }

    @GetMapping("/editar/{idDistrito}")
    public String editar(@PathVariable("idDistrito") Integer id, Model model) {
        model.addAttribute("distrito", distritoService.buscarPorId(id));
        cargarCombos(model);
        return "distrito/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DistritoListadoDTO dto, RedirectAttributes ra) {
        try {
            Distrito distrito = new Distrito();
            distrito.setIdDistrito(dto.getIdDistrito());
            distrito.setNombre(dto.getNombre());
            distrito.setIdEstado(dto.getIdEstado());

            if (dto.getIdDistrito() != null) {
                distritoService.actualizarDistrito(distrito);
            } else {
                distritoService.insertarDistrito(distrito);
            }
            ra.addFlashAttribute("todoOk", "Distrito guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/distrito/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idDistrito") Integer idDistrito, RedirectAttributes ra) {
        try {
            distritoService.eliminarDistrito(idDistrito);
            ra.addFlashAttribute("todoOk", "Distrito eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/distrito/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}