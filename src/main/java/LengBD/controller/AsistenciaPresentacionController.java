package LengBD.controller;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.AsistenciaPresentacionListadoDTO;
import LengBD.service.AsistenciaPresentacionService;
import LengBD.service.PresentacionService;
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
@RequestMapping("/asistenciaPresentacion")
public class AsistenciaPresentacionController {

    @Autowired
    private AsistenciaPresentacionService asistenciaPresentacionService;

    @Autowired
    private PresentacionService presentacionService;

    @Autowired
    private IntegrantesService integrantesService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<AsistenciaPresentacionListadoDTO> lista = asistenciaPresentacionService.readAllAsistenciaPresentacion();
        model.addAttribute("asistenciasPresentacion", lista);
        return "asistenciaPresentacion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asistenciaPresentacion", new AsistenciaPresentacionListadoDTO());
        cargarCombos(model);
        return "asistenciaPresentacion/formulario";
    }

    @GetMapping("/editar/{idAsistenciaPresentacion}")
    public String editar(@PathVariable("idAsistenciaPresentacion") Integer id, Model model) {
        model.addAttribute("asistenciaPresentacion", asistenciaPresentacionService.buscarPorId(id));
        cargarCombos(model);
        return "asistenciaPresentacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AsistenciaPresentacionListadoDTO dto, RedirectAttributes ra) {
        try {
            AsistenciaPresentacion asistenciaPresentacion = new AsistenciaPresentacion();
            asistenciaPresentacion.setIdAsistenciaPresentacion(dto.getIdAsistenciaPresentacion());
            asistenciaPresentacion.setIdPresentacion(dto.getIdPresentacion());
            asistenciaPresentacion.setCedula(dto.getCedula());
            asistenciaPresentacion.setIdEstado(dto.getIdEstado());

            if (dto.getIdAsistenciaPresentacion() != null) {
                asistenciaPresentacionService.actualizarAsistenciaPresentacion(asistenciaPresentacion);
            } else {
                asistenciaPresentacionService.insertarAsistenciaPresentacion(asistenciaPresentacion);
            }
            ra.addFlashAttribute("todoOk", "Asistencia a presentación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/asistenciaPresentacion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idAsistenciaPresentacion") Integer idAsistenciaPresentacion, RedirectAttributes ra) {
        try {
            asistenciaPresentacionService.eliminarAsistenciaPresentacion(idAsistenciaPresentacion);
            ra.addFlashAttribute("todoOk", "Asistencia a presentación eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/asistenciaPresentacion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("presentaciones", presentacionService.readAllPresentacion());
        model.addAttribute("usuarios", integrantesService.listarIntegrantes());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}