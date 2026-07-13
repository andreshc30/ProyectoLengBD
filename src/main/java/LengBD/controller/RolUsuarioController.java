package LengBD.controller;

import LengBD.domain.RolUsuario;
import LengBD.domain.RolUsuarioListadoDTO;
import LengBD.service.RolUsuariosService;
import LengBD.service.RolService;
import LengBD.service.IntegrantesService;
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
@RequestMapping("/rolUsuario")
public class RolUsuarioController {

    @Autowired
    private RolUsuariosService rolUsuariosService;

    @Autowired
    private RolService rolService;

    // TODO: usando IntegrantesService (ya confirmado en AsignacionInstrumentoController)
    // para el combo de cedula/usuarios. Confirmar si es el service correcto para este contexto.
    @Autowired
    private IntegrantesService integrantesService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<RolUsuarioListadoDTO> lista = rolUsuariosService.readAllRolUsuario();
        model.addAttribute("rolesUsuarios", lista);
        model.addAttribute("nuevoRolUsuario", new RolUsuarioListadoDTO());
        cargarCombos(model);
        return "rolUsuario/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rolUsuario", new RolUsuarioListadoDTO());
        cargarCombos(model);
        return "rolUsuario/formulario";
    }

    @GetMapping("/editar/{idRol}/{cedula}")
    public String editar(@PathVariable("idRol") Integer idRol,
                          @PathVariable("cedula") Integer cedula, Model model) {
        model.addAttribute("rolUsuario", rolUsuariosService.buscarPorId(idRol, cedula));
        cargarCombos(model);
        return "rolUsuario/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute RolUsuarioListadoDTO dto,
                           @RequestParam(value = "editando", required = false, defaultValue = "false") boolean editando,
                           RedirectAttributes ra) {
        try {
            RolUsuario rolUsuario = new RolUsuario();
            rolUsuario.setCedula(dto.getCedula());
            rolUsuario.setIdRol(dto.getIdRol());
            rolUsuario.setIdEstado(dto.getIdEstado());

            if (editando) {
                rolUsuariosService.actualizarRolUsuario(rolUsuario);
            } else {
                rolUsuariosService.insertarRolUsuario(rolUsuario);
            }
            ra.addFlashAttribute("todoOk", "Rol de usuario guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/rolUsuario/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idRol") Integer idRol,
                            @RequestParam("cedula") Integer cedula, RedirectAttributes ra) {
        try {
            rolUsuariosService.eliminarRolUsuario(idRol, cedula);
            ra.addFlashAttribute("todoOk", "Rol de usuario eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/rolUsuario/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("roles", rolService.readAllRol());
        model.addAttribute("usuarios", integrantesService.listarIntegrantes());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}