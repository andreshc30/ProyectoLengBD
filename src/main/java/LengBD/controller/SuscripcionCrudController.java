package LengBD.controller;

import LengBD.domain.Suscripcion;
import LengBD.domain.SuscripcionListadoDTO;
import LengBD.service.BandaService;
import LengBD.service.SuscripcionService;
import LengBD.service.EstadoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.SuscripcionService;
import LengBD.service.PlanesService;
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
@RequestMapping("/suscripcion")
public class SuscripcionCrudController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private SuscripcionService suscripcionService;
    
    @Autowired
    private BandaService bandaService;
    
    @Autowired
    private PlanesService planesService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<SuscripcionListadoDTO> lista = suscripcionService.readAllSuscripcion();
        model.addAttribute("suscripciones", lista);
        model.addAttribute("nuevaSuscripcion", new SuscripcionListadoDTO());
        cargarCombos(model);
        return "suscripcion/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("suscripcion", new SuscripcionListadoDTO());
        cargarCombos(model);
        return "suscripcion/formulario";
    }

    @GetMapping("/editar/{idSuscripcion}")
    public String editar(@PathVariable("idSuscripcion") Integer id, Model model) {
        model.addAttribute("suscripcion", suscripcionService.buscarPorId(id));
        cargarCombos(model);
        return "suscripcion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SuscripcionListadoDTO dto, RedirectAttributes ra) {
        try {
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setIdSuscripcion(dto.getIdSuscripcion());
            suscripcion.setNombre(dto.getNombre());
            suscripcion.setFechaInicio(dto.getFechaInicio());
            suscripcion.setFechaFinal(dto.getFechaFinal());
            suscripcion.setAutoRenovar(dto.getAutoRenovar());
            suscripcion.setIdTipoPlan(dto.getIdTipoPlan());
            suscripcion.setIdBanda(dto.getIdBanda());
            suscripcion.setIdEstado(dto.getIdEstado());

            if (dto.getIdSuscripcion()!= null) {
                suscripcionService.actualizarSuscripcion(suscripcion);
            } else {
                suscripcionService.insertarSuscripcion(suscripcion);
            }
            ra.addFlashAttribute("todoOk", "Suscripcion guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/suscripcion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idSuscripcion") Integer idSuscripcion, RedirectAttributes ra) {
        try {
            suscripcionService.eliminarSuscripcion(idSuscripcion);
            ra.addFlashAttribute("todoOk", "Suscripcion eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/suscripcion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("planes", planesService.readAllPlanes());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}