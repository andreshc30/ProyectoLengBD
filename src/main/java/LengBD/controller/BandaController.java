package LengBD.controller;

import LengBD.domain.PresentacionListadoDTO;
import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import LengBD.service.BandaService;
import LengBD.service.EnsayosService;
import LengBD.service.PresentacionService;
import LengBD.service.RolUsuariosService;
import LengBD.service.SeccionService;
import LengBD.service.UsuarioService;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banda")
public class BandaController {

    @Autowired
    private BandaService bandaService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SeccionService seccionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EnsayosService ensayosService;
    @Autowired
    private RolUsuariosService rolUsuariosService;

@Autowired
    private PresentacionService presentacionService;

    @GetMapping("/listado")
    public String listado(Model model, Authentication auth) {
        UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(auth.getName());
        Integer idBanda = usuario.getIdBanda();

        model.addAttribute("nombreBanda", usuario.getNombreBanda());
        model.addAttribute("totalSecciones", seccionService.readSeccionPorBanda(idBanda).size());
        model.addAttribute("totalIntegrantes", usuarioService.readUsuariosPorBanda(idBanda).size());
        model.addAttribute("ensayos", ensayosService.readEnsayosPorBanda(idBanda));
        model.addAttribute("totalLideres", rolUsuariosService.readLideresPorBanda(idBanda).size());

        // Presentaciones de la banda
        List<PresentacionListadoDTO> presentaciones = presentacionService.readPresentacionPorBanda(idBanda);
        model.addAttribute("presentaciones", presentaciones);

        // La próxima presentación (para la cuenta regresiva)
        LocalDateTime ahora = LocalDateTime.now();
        presentaciones.stream()
            .filter(p -> p.getFecha() != null && p.getFecha().isAfter(ahora))
            .min(Comparator.comparing(PresentacionListadoDTO::getFecha))
            .ifPresent(prox -> model.addAttribute("fechaEvento", prox.getFecha().toString()));

        return "gestion_bandas/listado";
    }
}