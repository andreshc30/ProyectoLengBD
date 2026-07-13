package LengBD.controller;

import LengBD.domain.Ensayos;
import LengBD.domain.EnsayosListadoDTO;
import LengBD.service.EnsayosService;
import LengBD.service.DireccionService;
import LengBD.service.BandaService;
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
@RequestMapping("/ensayos")
public class EnsayosController {

    @Autowired
    private EnsayosService ensayosService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<EnsayosListadoDTO> lista = ensayosService.readAllEnsayos();
        model.addAttribute("ensayos", lista);
        model.addAttribute("nuevoEnsayo", new EnsayosListadoDTO());
        cargarCombos(model);
        return "ensayos/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("ensayo", new EnsayosListadoDTO());
        cargarCombos(model);
        return "ensayos/formulario";
    }

    @GetMapping("/editar/{idEnsayo}")
    public String editar(@PathVariable("idEnsayo") Integer id, Model model) {
        model.addAttribute("ensayo", ensayosService.buscarPorId(id));
        cargarCombos(model);
        return "ensayos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EnsayosListadoDTO dto, RedirectAttributes ra) {
        try {
            Ensayos ensayo = new Ensayos();
            ensayo.setIdEnsayo(dto.getIdEnsayo());
            ensayo.setNombre(dto.getNombre());
            ensayo.setFechaInicio(dto.getFechaInicio());
            ensayo.setFechaFin(dto.getFechaFin());
            ensayo.setDescripcion(dto.getDescripcion());
            ensayo.setIdDireccion(dto.getIdDireccion());
            ensayo.setIdBanda(dto.getIdBanda());
            ensayo.setIdEstado(dto.getIdEstado());

            if (dto.getIdEnsayo() != null) {
                ensayosService.actualizarEnsayos(ensayo);
            } else {
                ensayosService.insertarEnsayos(ensayo);
            }
            ra.addFlashAttribute("todoOk", "Ensayo guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/ensayos/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idEnsayo") Integer idEnsayo, RedirectAttributes ra) {
        try {
            ensayosService.eliminarEnsayos(idEnsayo);
            ra.addFlashAttribute("todoOk", "Ensayo eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/ensayos/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("direcciones", direccionService.readAllDireccion());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}