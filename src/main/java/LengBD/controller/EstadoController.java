package LengBD.controller;

import LengBD.domain.Estado;
import LengBD.domain.EstadoListadoDTO;
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
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<EstadoListadoDTO> lista = estadoService.readAllEstado();
        model.addAttribute("estados", lista);
        return "estado/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("estado", new EstadoListadoDTO());
        return "estado/formulario";
    }

    @GetMapping("/editar/{idEstado}")
    public String editar(@PathVariable("idEstado") Integer id, Model model) {
        model.addAttribute("estado", estadoService.buscarPorId(id));
        return "estado/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstadoListadoDTO dto, RedirectAttributes ra) {
        try {
            Estado estado = new Estado();
            estado.setIdEstado(dto.getIdEstado());
            estado.setEstado(dto.getEstado());

            if (dto.getIdEstado() != null) {
                estadoService.actualizarEstado(estado);
            } else {
                estadoService.insertarEstado(estado);
            }
            ra.addFlashAttribute("todoOk", "Estado guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/estado/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idEstado") Integer idEstado, RedirectAttributes ra) {
        try {
            estadoService.eliminarEstado(idEstado);
            ra.addFlashAttribute("todoOk", "Estado eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/estado/listado";
    }
}