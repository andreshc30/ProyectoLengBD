package LengBD.controller;

import LengBD.domain.SeccionListadoDTO;
import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import LengBD.service.SeccionService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SidebarAdvice {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SeccionService seccionService;

    @ModelAttribute("seccionesSidebar")
    public List<SeccionListadoDTO> cargarSecciones(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return Collections.emptyList();
        }
        UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(auth.getName());
        if (usuario == null || usuario.getIdBanda() == null) {
            return Collections.emptyList();
        }
        return seccionService.readSeccionPorBanda(usuario.getIdBanda());
    }
}