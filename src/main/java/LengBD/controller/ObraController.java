package LengBD.controller;

import LengBD.domain.Obra;
import LengBD.domain.ObraListadoDTO;
import LengBD.service.ObraService;
import LengBD.service.TipoService;
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
@RequestMapping("/obra")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @Autowired
    private TipoService tipoService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<ObraListadoDTO> lista = obraService.readAllObra();
        model.addAttribute("obras", lista);
        model.addAttribute("nuevaObra", new ObraListadoDTO());
        cargarCombos(model);
        return "obra/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("obra", new ObraListadoDTO());
        cargarCombos(model);
        return "obra/formulario";
    }

    @GetMapping("/editar/{idObra}")
    public String editar(@PathVariable("idObra") Integer id, Model model) {
        model.addAttribute("obra", obraService.buscarPorId(id));
        cargarCombos(model);
        return "obra/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ObraListadoDTO dto, RedirectAttributes ra) {
        try {
            Obra obra = new Obra();
            obra.setIdObra(dto.getIdObra());
            obra.setNombre(dto.getNombre());
            obra.setFecha(dto.getFecha());
            obra.setDetalle(dto.getDetalle());
            obra.setIdTipo(dto.getIdTipo());
            obra.setIdBanda(dto.getIdBanda());
            obra.setIdEstado(dto.getIdEstado());

            if (dto.getIdObra() != null) {
                obraService.actualizarObra(obra);
            } else {
                obraService.insertarObra(obra);
            }
            ra.addFlashAttribute("todoOk", "Obra guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/obra/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idObra") Integer idObra, RedirectAttributes ra) {
        try {
            obraService.eliminarObra(idObra);
            ra.addFlashAttribute("todoOk", "Obra eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/obra/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("tipos", tipoService.readAllTipo());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}