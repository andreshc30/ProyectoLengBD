package LengBD.controller;

import LengBD.domain.BandaSuscripcion;
import LengBD.domain.BandaSuscripcionListadoDTO;
import LengBD.service.BandaService;
import LengBD.service.BandaSuscripcionService;
import LengBD.service.BandaSuscripcionService;
import LengBD.service.EstadoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.BandaSuscripcionService;
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
@RequestMapping("/bandaSuscripcion")
public class BandaSuscripcionCrudController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private BandaSuscripcionService bandaSuscripcionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private SuscripcionService suscripcionService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<BandaSuscripcionListadoDTO> lista = bandaSuscripcionService.readAllBandaSuscripcion();
        model.addAttribute("bandasSuscripciones", lista);
        model.addAttribute("nuevaBandaSuscripcion", new BandaSuscripcionListadoDTO());
        cargarCombos(model);
        return "bandaSuscripcion/listado";
    }

    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute BandaSuscripcionListadoDTO dto, RedirectAttributes ra) {
        try {
            BandaSuscripcion bandaSuscripcion = new BandaSuscripcion();
            bandaSuscripcion.setIdBanda(dto.getIdBanda());
            bandaSuscripcion.setIdSuscripcion(dto.getIdSuscripcion());
            bandaSuscripcion.setIdEstado(dto.getIdEstado());

            bandaSuscripcionService.insertarBandaSuscripcion(bandaSuscripcion);

            ra.addFlashAttribute("todoOk", "Banda Suscripcion guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/bandaSuscripcion/listado";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute BandaSuscripcionListadoDTO dto, RedirectAttributes ra) {
        try {
            BandaSuscripcion bandaSuscripcion = new BandaSuscripcion();
            bandaSuscripcion.setIdBanda(dto.getIdBanda());
            bandaSuscripcion.setIdSuscripcion(dto.getIdSuscripcion());
            bandaSuscripcion.setIdEstado(dto.getIdEstado());

            bandaSuscripcionService.actualizarBandaSuscripcion(bandaSuscripcion);
            ra.addFlashAttribute("todoOk", "Banda Suscripcion guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/bandaSuscripcion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idBanda") Integer idBanda, @RequestParam("idSuscripcion") Integer idSuscripcion, RedirectAttributes ra) {
        try {
            bandaSuscripcionService.eliminarBandaSuscripcion(idBanda, idSuscripcion);
            ra.addFlashAttribute("todoOk", "Banda Suscripcion eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/bandaSuscripcion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("suscripciones", suscripcionService.readAllSuscripcion());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}
