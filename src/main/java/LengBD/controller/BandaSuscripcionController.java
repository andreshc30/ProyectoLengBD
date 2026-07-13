package LengBD.controller;

import LengBD.domain.BandaSuscripcion;
import LengBD.domain.BandaSuscripcionListadoDTO;
import LengBD.service.BandaSuscripcionService;
import LengBD.service.BandaService;
import LengBD.service.SuscripcionService;
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

/*
 * NOTA 1: FIDE_BANDA_SUCRIPCION_TB (así, con el typo "SUCRIPCION" en el nombre real
 * de la tabla en Oracle) usa clave compuesta (idBanda + idSuscripcion) -- mismo
 * patrón que RolUsuarioController con el campo oculto "editando".
 *
 * NOTA 2: en BandaSuscripcionListadoDTO.java el naming está invertido respecto al
 * resto del proyecto: "estado" es el Integer (ID) y "estadoNombre" es el String
 * (nombre a mostrar) -- al revés del patrón idEstado/estado usado en las demás 30+
 * tablas. Este controller usa dto.getEstado() como el ID tal como está definido hoy.
 */
@Controller
@RequestMapping("/bandaSuscripcion")
public class BandaSuscripcionController {

    @Autowired
    private BandaSuscripcionService bandaSuscripcionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<BandaSuscripcionListadoDTO> lista = bandaSuscripcionService.readAllBandaSuscripcion();
        model.addAttribute("bandaSuscripciones", lista);
        return "bandaSuscripcion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("bandaSuscripcion", new BandaSuscripcionListadoDTO());
        cargarCombos(model);
        return "bandaSuscripcion/formulario";
    }

    @GetMapping("/editar/{idBanda}/{idSuscripcion}")
    public String editar(@PathVariable("idBanda") Integer idBanda,
                          @PathVariable("idSuscripcion") Integer idSuscripcion, Model model) {
        model.addAttribute("bandaSuscripcion", bandaSuscripcionService.buscarPorId(idBanda, idSuscripcion));
        cargarCombos(model);
        return "bandaSuscripcion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute BandaSuscripcionListadoDTO dto,
                           @RequestParam(value = "editando", required = false, defaultValue = "false") boolean editando,
                           RedirectAttributes ra) {
        try {
            BandaSuscripcion bandaSuscripcion = new BandaSuscripcion();
            bandaSuscripcion.setIdBanda(dto.getIdBanda());
            bandaSuscripcion.setIdSuscripcion(dto.getIdSuscripcion());
            bandaSuscripcion.setIdEstado(dto.getEstado());

            if (editando) {
                bandaSuscripcionService.actualizarBandaSuscripcion(bandaSuscripcion);
            } else {
                bandaSuscripcionService.insertarBandaSuscripcion(bandaSuscripcion);
            }
            ra.addFlashAttribute("todoOk", "Suscripción de banda guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/bandaSuscripcion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idBanda") Integer idBanda,
                            @RequestParam("idSuscripcion") Integer idSuscripcion, RedirectAttributes ra) {
        try {
            bandaSuscripcionService.eliminarBandaSuscripcion(idBanda, idSuscripcion);
            ra.addFlashAttribute("todoOk", "Suscripción de banda eliminada correctamente");
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