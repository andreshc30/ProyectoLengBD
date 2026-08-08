package LengBD.controller;

import LengBD.domain.Seccion;
import LengBD.domain.SeccionListadoDTO;
import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import LengBD.service.AsistenciaEnsayoService;
import LengBD.service.SeccionService;
import LengBD.service.BandaService;
import LengBD.service.EnsayosService;
import LengBD.service.EstadoService;
import LengBD.service.MaterialEstudioService;
import LengBD.service.ObraService;
import LengBD.service.RolUsuariosService;
import LengBD.service.UsuarioService;
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
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/seccion")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolUsuariosService rolusuariosService;

    @Autowired
    private ObraService obraService;
    @Autowired
    private MaterialEstudioService materialEstudioService;
    
    @Autowired
    private EnsayosService ensayosService;
    
    @Autowired
    private AsistenciaEnsayoService asistenciaEnsayoService;
    

    
    @GetMapping("/listado")
    public String listado(Model model) {
        List<SeccionListadoDTO> lista = seccionService.readAllSeccion();
        model.addAttribute("secciones", lista);
        model.addAttribute("nuevaSeccion", new SeccionListadoDTO());
        cargarCombos(model);
        return "seccion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("seccion", new SeccionListadoDTO());
        cargarCombos(model);
        return "seccion/formulario";
    }

    @GetMapping("/editar/{idSeccion}")
    public String editar(@PathVariable("idSeccion") Integer id, Model model) {
        model.addAttribute("seccion", seccionService.buscarPorId(id));
        cargarCombos(model);
        return "seccion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SeccionListadoDTO dto, RedirectAttributes ra) {
        try {
            Seccion seccion = new Seccion();
            seccion.setIdSeccion(dto.getIdSeccion());
            seccion.setNombre(dto.getNombre());
            seccion.setDescripcion(dto.getDescripcion());
            seccion.setIdBanda(dto.getIdBanda());
            seccion.setIdEstado(dto.getIdEstado());

            if (dto.getIdSeccion() != null) {
                seccionService.actualizarSeccion(seccion);
            } else {
                seccionService.insertarSeccion(seccion);
            }
            ra.addFlashAttribute("todoOk", "Sección guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/seccion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idSeccion") Integer idSeccion, RedirectAttributes ra) {
        try {
            seccionService.eliminarSeccion(idSeccion);
            ra.addFlashAttribute("todoOk", "Sección eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/seccion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
    
    
    
       @Autowired
    private UsuarioRepository usuarioRepository;   // para buscar por correo

    @PostMapping("/guardarD")
    public String guardarD(@ModelAttribute SeccionListadoDTO dto,
                           Authentication auth,
                           RedirectAttributes ra) {
        try {
            UsuarioLoginDTO director = usuarioRepository.buscarPorCorreo(auth.getName());

            Seccion seccion = new Seccion();
            seccion.setIdSeccion(dto.getIdSeccion());
            seccion.setNombre(dto.getNombre());
            seccion.setDescripcion(dto.getDescripcion());
            seccion.setIdBanda(director.getIdBanda());
            seccion.setIdEstado(dto.getIdEstado() != null ? dto.getIdEstado() : 1);

            if (dto.getIdSeccion() != null) {
                seccionService.actualizarSeccion(seccion);
            } else {
                seccionService.insertarSeccion(seccion);
            }
            ra.addFlashAttribute("todoOk", "Sección guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/banda/secciones/listadoDirector";
    }
    @PostMapping("/eliminarD")
    public String eliminarD(@RequestParam("idSeccion") Integer idSeccion, RedirectAttributes ra) {
        try {
            seccionService.eliminarSeccion(idSeccion);
            ra.addFlashAttribute("todoOk", "Sección eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/banda/secciones/listadoDirector";
    }
    
    
    @GetMapping("/editarD/{idSeccion}")
    public String editarD(@PathVariable("idSeccion") Integer id, Model model) {
        model.addAttribute("seccion", seccionService.buscarPorId(id));
        cargarCombos(model);
        return "seccion/formulario";
    }

    @GetMapping("/nuevoD")
    public String nuevoD(Model model) {
        model.addAttribute("seccion", new SeccionListadoDTO());
        cargarCombos(model);
        return "seccion/formulario";
    }

    @GetMapping("/ver/{id}")
    public String verSeccion(@PathVariable("id") Integer idSeccion,
            @RequestParam(value = "idEnsayo", required = false) Integer idEnsayo,
            Model model, Authentication auth) {
        UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(auth.getName());
        SeccionListadoDTO seccion = seccionService.buscarPorId(idSeccion);
        System.out.println(">>> verSeccion - usuario.idBanda: " + (usuario != null ? usuario.getIdBanda() : "null"));
        System.out.println(">>> verSeccion - seccion: " + (seccion != null ? seccion.getIdSeccion() + " banda=" + seccion.getIdBanda() : "null"));

        if (seccion == null || !seccion.getIdBanda().equals(usuario.getIdBanda())) {
            return "redirect:/banda/listado";
        }
        model.addAttribute("seccion", seccion);
        model.addAttribute("integrantes", usuarioService.readUsuariosPorSeccion(idSeccion));
        model.addAttribute("lideres", rolusuariosService.readLideresPorSeccion(idSeccion));
        model.addAttribute("obras", obraService.readObrasPorBanda(seccion.getIdBanda()));
        model.addAttribute("materiales", materialEstudioService.readMaterialPorSeccion(idSeccion));
        model.addAttribute("ensayos", ensayosService.readEnsayosPorBanda(seccion.getIdBanda()));
        if (idEnsayo != null) {
            model.addAttribute("asistencias", asistenciaEnsayoService.listar(idEnsayo, idSeccion));
            model.addAttribute("idEnsayoSel", idEnsayo);
        }
        return "seccion/detalle";
    }
    
    @PostMapping("/ver/{id}/asistencia")
    public String guardarAsistencia(@PathVariable("id") Integer idSeccion,
                                    @RequestParam("idEnsayo") Integer idEnsayo,
                                    @RequestParam java.util.Map<String, String> params,
                                    RedirectAttributes ra) {
        try {
            for (java.util.Map.Entry<String, String> e : params.entrySet()) {
                if (e.getKey().startsWith("estado_")) {
                    Integer cedula = Integer.valueOf(e.getKey().substring("estado_".length()));
                    String val = e.getValue();
                    if (val != null && !val.isBlank()) {
                        Integer idEstado = Integer.valueOf(val);
                        asistenciaEnsayoService.guardar(idEnsayo, cedula, idEstado);
                    }
                }
            }
            ra.addFlashAttribute("todoOk", "Asistencia guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar asistencia: " + ex.getMessage());
        }
        return "redirect:/seccion/ver/" + idSeccion + "?idEnsayo=" + idEnsayo;
    }
}