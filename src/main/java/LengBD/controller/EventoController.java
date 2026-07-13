package LengBD.controller;

import LengBD.domain.Evento;
import LengBD.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute("evento", new Evento());
        model.addAttribute("listaEventos", eventoService.readAllEvento());

        return "eventos/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Evento evento) {

        eventoService.insertarEvento(evento);

        return "redirect:/eventos/listado";
    }

    @PostMapping("/actualizar")
    public String actualizar(Evento evento) {

        eventoService.actualizarEvento(evento);

        return "redirect:/eventos/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(Integer idEvento) {

        eventoService.eliminarEvento(idEvento);

        return "redirect:/eventos/listado";
    }

}
