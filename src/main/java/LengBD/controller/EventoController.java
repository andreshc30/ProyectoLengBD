package LengBD.controller;

import LengBD.domain.Evento;
import LengBD.domain.EventoListadoDTO;
import LengBD.service.EventoService;
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

/*
 * NOTA: en Evento.java y EventoListadoDTO.java el campo se llama "direccion" (Integer),
 * no "idDireccion" como en el resto del proyecto -- ambos coinciden entre sí así que
 * no rompe nada, pero es una inconsistencia de naming a tener en cuenta.
 */
@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<EventoListadoDTO> lista = eventoService.readAllEvento();
        model.addAttribute("eventos", lista);
        return "evento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("evento", new EventoListadoDTO());
        cargarCombos(model);
        return "evento/formulario";
    }

    @GetMapping("/editar/{idEvento}")
    public String editar(@PathVariable("idEvento") Integer id, Model model) {
        model.addAttribute("evento", eventoService.buscarPorId(id));
        cargarCombos(model);
        return "evento/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EventoListadoDTO dto, RedirectAttributes ra) {
        try {
            Evento evento = new Evento();
            evento.setIdEvento(dto.getIdEvento());
            evento.setNombre(dto.getNombre());
            evento.setDetalle(dto.getDetalle());
            evento.setFecha(dto.getFecha());
            evento.setDireccion(dto.getDireccion());
            evento.setIdBanda(dto.getIdBanda());
            evento.setIdEstado(dto.getIdEstado());

            if (dto.getIdEvento() != null) {
                eventoService.actualizarEvento(evento);
            } else {
                eventoService.insertarEvento(evento);
            }
            ra.addFlashAttribute("todoOk", "Evento guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/evento/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idEvento") Integer idEvento, RedirectAttributes ra) {
        try {
            eventoService.eliminarEvento(idEvento);
            ra.addFlashAttribute("todoOk", "Evento eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/evento/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("direcciones", direccionService.readAllDireccion());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}