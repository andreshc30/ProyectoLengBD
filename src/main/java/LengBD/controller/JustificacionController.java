package LengBD.controller;

import LengBD.domain.Justificacion;
import LengBD.domain.JustificacionListadoDTO;
import LengBD.service.JustificacionService;
import LengBD.service.AsistenciaEnsayoService;
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
@RequestMapping("/justificacion")
public class JustificacionController {

    @Autowired
    private JustificacionService justificacionService;

    @Autowired
    private AsistenciaEnsayoService asistenciaEnsayoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<JustificacionListadoDTO> lista = justificacionService.readAllJustificacion();
        model.addAttribute("justificaciones", lista);
        return "justificacion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("justificacion", new JustificacionListadoDTO());
        cargarCombos(model);
        return "justificacion/formulario";
    }

    @GetMapping("/editar/{idJustificacion}")
    public String editar(@PathVariable("idJustificacion") Integer id, Model model) {
        model.addAttribute("justificacion", justificacionService.buscarPorId(id));
        cargarCombos(model);
        return "justificacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute JustificacionListadoDTO dto, RedirectAttributes ra) {
        try {
            Justificacion justificacion = new Justificacion();
            justificacion.setIdJustificacion(dto.getIdJustificacion());
            justificacion.setMotivo(dto.getMotivo());
            justificacion.setIdAsistenciaEnsayos(dto.getIdAsistenciaEnsayos());
            justificacion.setIdEstado(dto.getIdEstado());

            if (dto.getIdJustificacion() != null) {
                justificacionService.actualizarJustificacion(justificacion);
            } else {
                justificacionService.insertarJustificacion(justificacion);
            }
            ra.addFlashAttribute("todoOk", "Justificación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/justificacion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idJustificacion") Integer idJustificacion, RedirectAttributes ra) {
        try {
            justificacionService.eliminarJustificacion(idJustificacion);
            ra.addFlashAttribute("todoOk", "Justificación eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/justificacion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("asistenciasEnsayo", asistenciaEnsayoService.readAllAsistenciaEnsayo());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}