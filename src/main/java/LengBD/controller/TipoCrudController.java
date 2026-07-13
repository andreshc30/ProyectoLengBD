package LengBD.controller;

import LengBD.domain.Tipo;
import LengBD.domain.TipoListadoDTO;
import LengBD.service.TipoService;
import LengBD.service.EstadoService;
import LengBD.service.TipoService;
import LengBD.service.TipoService;
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
@RequestMapping("/tipo")
public class TipoCrudController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private TipoService tipoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<TipoListadoDTO> lista = tipoService.readAllTipo();
        model.addAttribute("tipos", lista);
        model.addAttribute("nuevoTipo", new TipoListadoDTO());
        cargarCombos(model);
        return "tipo/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipo", new TipoListadoDTO());
        cargarCombos(model);
        return "tipo/formulario";
    }

    @GetMapping("/editar/{idTipo}")
    public String editar(@PathVariable("idTipo") Integer id, Model model) {
        model.addAttribute("tipo", tipoService.buscarPorId(id));
        cargarCombos(model);
        return "tipo/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute TipoListadoDTO dto, RedirectAttributes ra) {
        try {
            Tipo tipo = new Tipo();
            tipo.setIdTipo(dto.getIdTipo());
            tipo.setNombre(dto.getNombre());
            tipo.setDescripcion(dto.getDescripcion());
            tipo.setIdEstado(dto.getIdEstado());

            if (dto.getIdTipo()!= null) {
                tipoService.actualizarTipo(tipo);
            } else {
                tipoService.insertarTipo(tipo);
            }
            ra.addFlashAttribute("todoOk", "Tipo guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/tipo/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idTipo") Integer idTipo, RedirectAttributes ra) {
        try {
            tipoService.eliminarTipo(idTipo);
            ra.addFlashAttribute("todoOk", "Tipo eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/tipo/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}