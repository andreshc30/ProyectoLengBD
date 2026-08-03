package LengBD.controller;

import LengBD.domain.Evento;
import LengBD.domain.EventoListadoDTO;
import LengBD.service.EventoService;
import LengBD.service.DireccionService;
import LengBD.service.BandaService;
import LengBD.service.CantonService;
import LengBD.service.DistritoService;
import LengBD.service.EstadoService;
import LengBD.service.ProvinciaService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private CantonService cantonService;

    @Autowired
    private DistritoService distritoService;

    @GetMapping("/listado")
    public String listado(Model model,
            @RequestParam(value = "idEventoConsulta", required = false) Integer idEventoConsulta) {
        List<EventoListadoDTO> lista = eventoService.readAllEvento();
        model.addAttribute("listaEventos", lista);
        model.addAttribute("eventosBusqueda", eventoService.obtenerEventosBusqueda());
        model.addAttribute("evento", new EventoListadoDTO());
        model.addAttribute("totalEventosActivos", eventoService.totalEventosActivos());

        if (idEventoConsulta != null) {
            model.addAttribute("idEventoConsulta", idEventoConsulta);
            model.addAttribute("nombreConsultado", eventoService.obtenerNombreEvento(idEventoConsulta));
        }

        return "eventos/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("evento", new EventoListadoDTO());
        cargarCombos(model);
        return "eventos/listado";
    }

    @GetMapping("/editar/{idEvento}")
    public String editar(@PathVariable("idEvento") Integer id, Model model) {
        model.addAttribute("evento", eventoService.buscarPorId(id));
        cargarCombos(model);
        return "eventos/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EventoListadoDTO dto, RedirectAttributes ra) {
        try {
            Evento evento = new Evento();
            evento.setIdEvento(dto.getIdEvento());
            evento.setNombre(dto.getNombre());
            evento.setDetalle(dto.getDetalle());
            evento.setFecha(dto.getFecha());
            evento.setDireccion(dto.getIdDireccion());
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
        return "redirect:/eventos/admin";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute EventoListadoDTO dto, RedirectAttributes ra) {
        try {
            Evento evento = new Evento();
            evento.setIdEvento(dto.getIdEvento());
            evento.setNombre(dto.getNombre());
            evento.setDetalle(dto.getDetalle());
            evento.setFecha(dto.getFecha());
            evento.setDireccion(dto.getIdDireccion());
            evento.setIdBanda(dto.getIdBanda());
            evento.setIdEstado(dto.getIdEstado());

            eventoService.actualizarEvento(evento);
            ra.addFlashAttribute("todoOk", "Evento actualizado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al actualizar: " + ex.getMessage());
        }
        return "redirect:/eventos/admin";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idEvento") Integer idEvento, RedirectAttributes ra) {
        try {
            eventoService.eliminarEvento(idEvento);
            ra.addFlashAttribute("todoOk", "Evento eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/eventos/admin";
    }

    private void cargarCombos(Model model) {

        model.addAttribute("provincias", provinciaService.readAllProvincia());
        model.addAttribute("cantones", cantonService.readAllCanton());
        model.addAttribute("distritos", distritoService.readAllDistrito());

        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }

    @GetMapping("/detalle")
    public String detalleEvento(
            @RequestParam Integer idEvento,
            Model model) {

        model.addAttribute("detalleEvento", eventoService.obtenerDetalleEvento(idEvento));

        return "eventos/listado";
    }

    @GetMapping("/detalle/{idEvento}")
    @ResponseBody
    public EventoListadoDTO obtenerDetalleEvento(@PathVariable Integer idEvento) {

        return eventoService.obtenerDetalleEvento(idEvento);

    }

    @GetMapping("/admin")
    public String listadoAdmin(Model model) {

        model.addAttribute("listaEventos", eventoService.readAllEvento());
        model.addAttribute("evento", new EventoListadoDTO());

        cargarCombos(model);

        return "eventosAdmin/listado";
    }
}
