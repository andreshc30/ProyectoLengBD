package LengBD.controller;

import LengBD.domain.Correo;
import LengBD.domain.CorreoListadoDTO;
import LengBD.service.CorreoService;
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
@RequestMapping("/correo")
public class CorreoController {

    @Autowired
    private CorreoService correoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<CorreoListadoDTO> lista = correoService.readAllCorreo();
        model.addAttribute("correos", lista);
        return "correo/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("correo", new CorreoListadoDTO());
        cargarCombos(model);
        return "correo/formulario";
    }

    @GetMapping("/editar/{idCorreo}")
    public String editar(@PathVariable("idCorreo") Integer id, Model model) {
        model.addAttribute("correo", correoService.buscarPorId(id));
        cargarCombos(model);
        return "correo/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CorreoListadoDTO dto, RedirectAttributes ra) {
        try {
            Correo correo = new Correo();
            correo.setIdCorreo(dto.getIdCorreo());
            correo.setCorreo(dto.getCorreo());
            correo.setIdEstado(dto.getIdEstado());

            if (dto.getIdCorreo() != null) {
                correoService.actualizarCorreo(correo);
            } else {
                correoService.insertarCorreo(correo);
            }
            ra.addFlashAttribute("todoOk", "Correo guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/correo/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idCorreo") Integer idCorreo, RedirectAttributes ra) {
        try {
            correoService.eliminarCorreo(idCorreo);
            ra.addFlashAttribute("todoOk", "Correo eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/correo/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}