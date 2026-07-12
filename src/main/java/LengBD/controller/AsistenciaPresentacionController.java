/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.AsistenciaPresentacionListadoDTO;
import LengBD.domain.Estado;
import LengBD.domain.Instrumento;
import LengBD.domain.Usuario;
import LengBD.service.AsistenciaPresentacionService;
import LengBD.service.AudicionesService;
import LengBD.service.EstadoService;
import LengBD.service.InstrumentoService;
import LengBD.service.IntegrantesService;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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

@Controller
@RequestMapping("/asistenciaPresentacion")
public class AsistenciaPresentacionController {

    @Autowired
    private IntegrantesService integrantesService;

    @Autowired
    private InstrumentoService instrumentoService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private AsistenciaPresentacionService asistenciaPresentacionService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<AsistenciaPresentacionListadoDTO> lista = asistenciaPresentacionService.readAllAsistenciaPresentacion();
        model.addAttribute("asignaciones", lista);
        return "asistenciaPresentacion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asistenciaPresentacion", new AsistenciaPresentacionListadoDTO());
        cargarCombos(model);
        return "formularioIntegrantes";
    }

    @GetMapping("/editar/{idAsistenciaPresentacion}")
    public String editar(@PathVariable("idAsistenciaPresentacion") Integer id, Model model) {
        model.addAttribute("asistenciaPresentacion", asistenciaPresentacionService.buscarPorId(id));
        cargarCombos(model);
        return "formularioIntegrantes";
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
            ra.addFlashAttribute("todoOk", "Asignación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/banda/secciones/listadoDirector";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idAsistenciaPresentacion") Integer idAsistenciaPresentacion, RedirectAttributes ra) {
        try {
            asistenciaPresentacionService.eliminarAsistenciaPresentacion(idAsistenciaPresentacion);
            ra.addFlashAttribute("todoOk", "Asignación eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/banda/secciones/listadoDirector";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("usuarios", integrantesService.listarIntegrantes());
        model.addAttribute("instrumentos", instrumentoService.readAllInstrumento());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}
