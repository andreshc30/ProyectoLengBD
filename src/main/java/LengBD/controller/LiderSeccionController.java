package LengBD.controller;

import LengBD.domain.RolUsuario;
import LengBD.domain.RolUsuarioListadoDTO;
import LengBD.service.RolUsuariosService;
import LengBD.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lider")
public class LiderSeccionController {

    @Autowired
    private RolUsuariosService rolUsuariosService;
    @Autowired
    private UsuarioService usuarioService;

    private static final Integer ROL_LIDER = 4;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuarios", usuarioService.readAllUsuario());
        return "lider/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("cedula") Integer cedula, RedirectAttributes ra) {
        try {
            RolUsuarioListadoDTO existente = rolUsuariosService.buscarPorId(ROL_LIDER, cedula);

            if (existente == null) {
                // no existe → insertar
                RolUsuario ru = new RolUsuario();
                ru.setCedula(cedula);
                ru.setIdRol(ROL_LIDER);
                ru.setIdEstado(1);
                rolUsuariosService.insertarRolUsuario(ru);
                ra.addFlashAttribute("todoOk", "Líder asignado correctamente");
            } else {
                // ya existe (revocado) → reactivar con update
                RolUsuario ru = new RolUsuario();
                ru.setCedula(cedula);
                ru.setIdRol(ROL_LIDER);
                ru.setIdEstado(1);
                rolUsuariosService.actualizarRolUsuario(ru);
                ra.addFlashAttribute("todoOk", "Líder reactivado correctamente");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al asignar líder: " + ex.getMessage());
        }
        return "redirect:/banda/secciones/listadoDirector";
    }
    
    @PostMapping("/revocar")
    public String revocar(@RequestParam("cedula") Integer cedula, RedirectAttributes ra) {
        try {
            rolUsuariosService.eliminarRolUsuario(ROL_LIDER, cedula);  // OJO: (idRol, cedula)
            ra.addFlashAttribute("todoOk", "Rol de líder revocado");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al revocar");
        }
        return "redirect:/banda/secciones/listadoDirector";
    }
}