package LengBD.controller;

import LengBD.domain.Usuario;
import LengBD.domain.UsuarioListadoDTO;
import LengBD.service.UsuarioService;
import LengBD.service.TelefonoService;
import LengBD.service.CorreoService;
import LengBD.service.SeccionService;
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

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

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

    @GetMapping("/listado")
    public String listado(Model model) {
        List<UsuarioListadoDTO> lista = usuarioService.readAllUsuario();
        model.addAttribute("usuarios", lista);
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
            usuario.setIdTelefono(dto.getIdTelefono());
            usuario.setIdCorreo(dto.getIdCorreo());
            usuario.setIdSeccion(dto.getIdSeccion());
            usuario.setIdDireccion(dto.getIdDireccion());
            usuario.setIdBanda(dto.getIdBanda());
            usuario.setIdEstado(dto.getIdEstado());

            // NOTA: al usar "cedula" como PK sin autoincremento (similar a RolUsuario),
            // dto.getCedula() casi siempre va a venir con valor. Si tu flujo de "nuevo"
            // usuario ingresa la cédula a mano, este check funciona porque buscarPorId
            // determina si ya existe; si no, puede que necesites el mismo patrón de
            // campo oculto "editando" que usamos en RolUsuarioController.
            if (usuarioService.buscarPorId(dto.getCedula()) != null) {
                usuarioService.actualizarUsuario(usuario);
            } else {
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
}