package LengBD.controller;

import LengBD.domain.Telefono;
import LengBD.domain.TelefonoListadoDTO;
import LengBD.service.TelefonoService;
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
@RequestMapping("/telefono")
public class TelefonoController {

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<TelefonoListadoDTO> lista = telefonoService.readAllTelefono();
        model.addAttribute("telefonos", lista);
        return "telefono/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("telefono", new TelefonoListadoDTO());
        cargarCombos(model);
        return "telefono/formulario";
    }

    @GetMapping("/editar/{idTelefono}")
    public String editar(@PathVariable("idTelefono") Integer id, Model model) {
        model.addAttribute("telefono", telefonoService.buscarPorId(id));
        cargarCombos(model);
        return "telefono/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute TelefonoListadoDTO dto, RedirectAttributes ra) {
        try {
            Telefono telefono = new Telefono();
            telefono.setIdTelefono(dto.getIdTelefono());
            telefono.setTelefono(dto.getTelefono());
            telefono.setIdEstado(dto.getIdEstado());

            if (dto.getIdTelefono() != null) {
                telefonoService.actualizarTelefono(telefono);
            } else {
                telefonoService.insertarTelefono(telefono);
            }
            ra.addFlashAttribute("todoOk", "Teléfono guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/telefono/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idTelefono") Integer idTelefono, RedirectAttributes ra) {
        try {
            telefonoService.eliminarTelefono(idTelefono);
            ra.addFlashAttribute("todoOk", "Teléfono eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/telefono/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}