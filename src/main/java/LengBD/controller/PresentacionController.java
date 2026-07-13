package LengBD.controller;

import LengBD.domain.Presentacion;
import LengBD.domain.PresentacionListadoDTO;
import LengBD.service.PresentacionService;
import LengBD.service.BandaService;
import LengBD.service.LugarService;
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
@RequestMapping("/presentacion")
public class PresentacionController {

    @Autowired
    private PresentacionService presentacionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private LugarService lugarService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PresentacionListadoDTO> lista = presentacionService.readAllPresentacion();
        model.addAttribute("presentaciones", lista);
        model.addAttribute("nuevaPresentacion", new PresentacionListadoDTO());
        cargarCombos(model);
        return "presentacion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("presentacion", new PresentacionListadoDTO());
        cargarCombos(model);
        return "presentacion/formulario";
    }

    @GetMapping("/editar/{idPresentacion}")
    public String editar(@PathVariable("idPresentacion") Integer id, Model model) {
        model.addAttribute("presentacion", presentacionService.buscarPorId(id));
        cargarCombos(model);
        return "presentacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PresentacionListadoDTO dto, RedirectAttributes ra) {
        try {
            Presentacion presentacion = new Presentacion();
            presentacion.setIdPresentacion(dto.getIdPresentacion());
            presentacion.setNombre(dto.getNombre());
            presentacion.setDescripcion(dto.getDescripcion());
            presentacion.setFecha(dto.getFecha());
            presentacion.setIdBanda(dto.getIdBanda());
            presentacion.setIdLugar(dto.getIdLugar());
            presentacion.setIdEstado(dto.getIdEstado());

            if (dto.getIdPresentacion() != null) {
                presentacionService.actualizarPresentacion(presentacion);
            } else {
                presentacionService.insertarPresentacion(presentacion);
            }
            ra.addFlashAttribute("todoOk", "Presentación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/presentacion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idPresentacion") Integer idPresentacion, RedirectAttributes ra) {
        try {
            presentacionService.eliminarPresentacion(idPresentacion);
            ra.addFlashAttribute("todoOk", "Presentación eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/presentacion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("lugares", lugarService.readAllLugar());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}