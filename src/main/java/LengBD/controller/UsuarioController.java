package LengBD.controller;

import LengBD.domain.Usuario;
import LengBD.domain.UsuarioListadoDTO;
import LengBD.service.AsignacionInstrumentoService;
import LengBD.service.UsuarioService;
import LengBD.service.TelefonoService;
import LengBD.service.CorreoService;
import LengBD.service.SeccionService;
import LengBD.service.DireccionService;
import LengBD.service.BandaService;
import LengBD.service.EstadoService;
import LengBD.service.SolicitudIngresoService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    
    @Autowired
    private AsignacionInstrumentoService asignacionInstrumentoService;
    
    @Autowired
    private SolicitudIngresoService solicitudIngresoService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private CorreoService correoService;

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<UsuarioListadoDTO> lista = usuarioService.readAllUsuario();
        model.addAttribute("usuarios", lista);
        model.addAttribute("nuevoUsuario", new UsuarioListadoDTO());
        cargarCombos(model);
        return "usuario/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new UsuarioListadoDTO());
        cargarCombos(model);
        return "usuario/formulario";
    }

    @GetMapping("/editar/{cedula}")
    public String editar(@PathVariable("cedula") Integer cedula, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(cedula));
        cargarCombos(model);
        return "usuario/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute UsuarioListadoDTO dto, RedirectAttributes ra) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCedula(dto.getCedula());
            usuario.setNombre(dto.getNombre());
            usuario.setPrimerApellido(dto.getPrimerApellido());
            usuario.setSegundoApellido(dto.getSegundoApellido());
            usuario.setFechaIngreso(dto.getFechaIngreso());
            usuario.setLogoUrl(dto.getLogoUrl());
            usuario.setTelefono(dto.getTelefono());
            usuario.setCorreo(dto.getCorreo());
            usuario.setIdSeccion(dto.getIdSeccion());
            usuario.setIdDireccion(dto.getIdDireccion());
            usuario.setIdBanda(dto.getIdBanda());
            usuario.setIdEstado(dto.getIdEstado());
            if (usuarioService.buscarPorId(dto.getCedula()) != null) {
                usuarioService.actualizarUsuario(usuario);
            } else {
                usuario.setPassword(passwordEncoder.encode(String.valueOf(dto.getCedula())));
                usuarioService.insertarUsuario(usuario);
            }
            ra.addFlashAttribute("todoOk", "Usuario guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/usuario/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("cedula") Integer cedula, RedirectAttributes ra) {
        try {
            usuarioService.eliminarUsuario(cedula);
            ra.addFlashAttribute("todoOk", "Usuario eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/usuario/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("telefonos", telefonoService.readAllTelefono());
        model.addAttribute("correos", correoService.readAllCorreo());
        model.addAttribute("secciones", seccionService.readAllSeccion());
        model.addAttribute("direcciones", direccionService.readAllDireccion());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
    

    
    
    @PostMapping("/guardarD")
    public String guardarD(@ModelAttribute UsuarioListadoDTO dto, RedirectAttributes ra) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCedula(dto.getCedula());
            usuario.setNombre(dto.getNombre());
            usuario.setPrimerApellido(dto.getPrimerApellido());
            usuario.setSegundoApellido(dto.getSegundoApellido());
            usuario.setFechaIngreso(dto.getFechaIngreso());
            usuario.setLogoUrl(dto.getLogoUrl());
            usuario.setTelefono(dto.getTelefono());
            usuario.setCorreo(dto.getCorreo());
            usuario.setIdSeccion(dto.getIdSeccion());
            usuario.setIdDireccion(dto.getIdDireccion());
            usuario.setIdBanda(dto.getIdBanda());
            usuario.setIdEstado(dto.getIdEstado());
            if (usuarioService.buscarPorId(dto.getCedula()) != null) {
                usuarioService.actualizarUsuario(usuario);
            } else {
                usuario.setPassword(passwordEncoder.encode(String.valueOf(dto.getCedula())));
                usuarioService.insertarUsuario(usuario);
            }
            ra.addFlashAttribute("todoOk", "Usuario guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
            return "redirect:/banda/secciones/listadoDirector";
    }
    
    @PostMapping("/eliminarD")
    public String eliminarD(@RequestParam("cedula") Integer cedula, RedirectAttributes ra) {
        try {
            usuarioService.eliminarUsuario(cedula);
            ra.addFlashAttribute("todoOk", "Usuario eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
         return "redirect:/banda/secciones/listadoDirector";
    }
    
    @GetMapping("/cambiarPassword")
    public String cambiarPasswordForm() {
        return "usuario/cambiarPassword";
    }

    @PostMapping("/cambiarPassword")
    public String cambiarPassword(@RequestParam("actual") String actual,
                                  @RequestParam("nueva") String nueva,
                                  @RequestParam("confirmar") String confirmar,
                                  Authentication auth,
                                  RedirectAttributes ra) {
        try {
            // 1. quien esta logueado (el username es el correo)
            UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(auth.getName());
            if (usuario == null) {
                ra.addFlashAttribute("error", "No se encontro el usuario");
                return "redirect:/usuario/cambiarPassword";
            }

            // 2. la contrasena actual debe coincidir
            if (!passwordEncoder.matches(actual, usuario.getPassword())) {
                ra.addFlashAttribute("error", "La contrasena actual no es correcta");
                return "redirect:/usuario/cambiarPassword";
            }

            // 3. la nueva y la confirmacion deben ser iguales
            if (!nueva.equals(confirmar)) {
                ra.addFlashAttribute("error", "La nueva contrasena y su confirmacion no coinciden");
                return "redirect:/usuario/cambiarPassword";
            }

            // 4. largo minimo
            if (nueva.length() < 8) {
                ra.addFlashAttribute("error", "La contrasena debe tener al menos 8 caracteres");
                return "redirect:/usuario/cambiarPassword";
            }

            // 5. que no sea la cedula (la temporal)
            if (nueva.equals(String.valueOf(usuario.getCedula()))) {
                ra.addFlashAttribute("error", "No podes usar tu cedula como contrasena");
                return "redirect:/usuario/cambiarPassword";
            }

            usuarioService.cambiarPassword(usuario.getCedula(), passwordEncoder.encode(nueva));
            ra.addFlashAttribute("todoOk", "Contrasena actualizada correctamente");
            return "redirect:/banda/listado";

        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al cambiar la contrasena");
            return "redirect:/usuario/cambiarPassword";
        }
    }
    
    
}