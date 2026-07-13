package LengBD.controller;

import LengBD.domain.Rol;
import LengBD.domain.RolListadoDTO;
import LengBD.service.RolService;
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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<RolListadoDTO> lista = rolService.readAllRol();
        model.addAttribute("roles", lista);
        model.addAttribute("nuevoRol", new RolListadoDTO());
        cargarCombos(model);
        return "rol/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new RolListadoDTO());
        cargarCombos(model);
        return "rol/formulario";
    }

    @GetMapping("/editar/{idRol}")
    public String editar(@PathVariable("idRol") Integer id, Model model) {
        model.addAttribute("rol", rolService.buscarPorId(id));
        cargarCombos(model);
        return "rol/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute RolListadoDTO dto, RedirectAttributes ra) {
        try {
            Rol rol = new Rol();
            rol.setIdRol(dto.getIdRol());
            rol.setNombre(dto.getNombre());
            rol.setIdEstado(dto.getIdEstado());

            if (dto.getIdRol() != null) {
                rolService.actualizarRol(rol);
            } else {
                rolService.insertarRol(rol);
            }
            ra.addFlashAttribute("todoOk", "Rol guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/rol/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idRol") Integer idRol, RedirectAttributes ra) {
        try {
            rolService.eliminarRol(idRol);
            ra.addFlashAttribute("todoOk", "Rol eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/rol/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}