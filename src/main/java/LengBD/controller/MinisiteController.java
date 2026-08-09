package LengBD.controller;

import LengBD.domain.EventoListadoDTO;
import LengBD.domain.PresentacionListadoDTO;
import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import LengBD.service.BandaService;
import LengBD.service.EventoService;
import LengBD.service.ObraService;
import LengBD.service.PresentacionService;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/minisite")
public class MinisiteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ObraService obraService;

    @Autowired
    private PresentacionService presentacionService;

    private static final List<String> FOTOS_GALERIA_DEMO = List.of(
            "https://commons.wikimedia.org/wiki/Special:FilePath/Marching_Band_SMA_TN.jpg?width=600",
            "https://commons.wikimedia.org/wiki/Special:FilePath/Extra_Action_Marching_Band.jpg?width=600",
            "https://commons.wikimedia.org/wiki/Special:FilePath/Viewmont_High_School_marching_band_(36017232333).jpg?width=600",
            "https://commons.wikimedia.org/wiki/Special:FilePath/RT%C3%89_Concert_Orchestra_NCH.jpg?width=600",
            "https://commons.wikimedia.org/wiki/Special:FilePath/Banda_Municipal_de_Porto_Alegre_P%C3%A7a_XV_Novembro_80.jpg?width=600"
    );

    private Integer idBandaActual(Authentication auth) {
        UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(auth.getName());
        return usuario.getIdBanda();
    }


    @GetMapping("/configuracion")
    public String configuracion(Authentication auth, Model model) {
        Integer idBanda = idBandaActual(auth);
        model.addAttribute("banda", bandaService.buscarPorId(idBanda));
        model.addAttribute("biografia",
                "Somos una agrupación musical comprometida con la excelencia artística "
                + "y el desarrollo de nuestros integrantes. Participamos activamente en "
                + "actividades cívicas, culturales y comunitarias.");
        return "minisite/configuracion";
    }

    @GetMapping("/galeria")
    public String galeria(Authentication auth, Model model) {
        Integer idBanda = idBandaActual(auth);
        model.addAttribute("banda", bandaService.buscarPorId(idBanda));
        model.addAttribute("fotos", FOTOS_GALERIA_DEMO);
        return "minisite/galeria";
    }

    @GetMapping("/eventos")
    public String eventos(Authentication auth, Model model) {
        Integer idBanda = idBandaActual(auth);
        model.addAttribute("banda", bandaService.buscarPorId(idBanda));

        LocalDate hoy = LocalDate.now();
        List<EventoListadoDTO> proximos = eventoService.readEventosPorBanda(idBanda).stream()
                .filter(e -> e.getFecha() != null && !e.getFecha().isBefore(hoy))
                .sorted(Comparator.comparing(EventoListadoDTO::getFecha))
                .toList();

        model.addAttribute("eventos", proximos);
        return "minisite/eventos";
    }

    @GetMapping("/repertorio")
    public String repertorio(Authentication auth, Model model) {
        Integer idBanda = idBandaActual(auth);
        model.addAttribute("banda", bandaService.buscarPorId(idBanda));
        model.addAttribute("obras", obraService.readObrasPorBanda(idBanda));
        return "minisite/repertorio";
    }


    @GetMapping("/logros")
    public String logros(Authentication auth, Model model) {
        Integer idBanda = idBandaActual(auth);
        model.addAttribute("banda", bandaService.buscarPorId(idBanda));

        List<PresentacionListadoDTO> logros = presentacionService.readPresentacionPorBanda(idBanda).stream()
                .filter(p -> p.getEstado() != null && p.getEstado().equalsIgnoreCase("Realizado"))
                .toList();

        model.addAttribute("logros", logros);
        return "minisite/logros";
    }
}