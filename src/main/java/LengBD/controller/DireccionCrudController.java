package LengBD.controller;

import LengBD.domain.Direccion;
import LengBD.domain.DireccionListadoDTO;
import LengBD.service.DireccionService;
import LengBD.service.EstadoService;
import LengBD.service.DireccionService;
import LengBD.service.DireccionService;
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
@RequestMapping("/direccion")
public class DireccionCrudController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private DireccionService direccionService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<DireccionListadoDTO> lista = direccionService.readAllDireccion();
        model.addAttribute("direcciones", lista);
        model.addAttribute("nuevoDireccion", new DireccionListadoDTO());
        cargarCombos(model);
        return "direccion/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("direccion", new DireccionListadoDTO());
        cargarCombos(model);
        return "direccion/formulario";
    }

    @GetMapping("/editar/{idDireccion}")
    public String editar(@PathVariable("idDireccion") Integer id, Model model) {
        model.addAttribute("direccion", direccionService.buscarPorId(id));
        cargarCombos(model);
        return "direccion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DireccionListadoDTO dto, RedirectAttributes ra) {
        try {
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(dto.getIdDireccion());
            direccion.setIdProvincia(dto.getIdProvincia());
            direccion.setIdCanton(dto.getIdCanton());
            direccion.setIdDistrito(dto.getIdDistrito());
            direccion.setOtrosDetalles(dto.getOtrosDetalles());
            direccion.setIdEstado(dto.getIdEstado());

            if (dto.getIdDireccion()!= null) {
                direccionService.actualizarDireccion(direccion);
            } else {
                direccionService.insertarDireccion(direccion);
            }
            ra.addFlashAttribute("todoOk", "Direccion guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/direccion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idDireccion") Integer idDireccion, RedirectAttributes ra) {
        try {
            direccionService.eliminarDireccion(idDireccion);
            ra.addFlashAttribute("todoOk", "Direccion eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/direccion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}