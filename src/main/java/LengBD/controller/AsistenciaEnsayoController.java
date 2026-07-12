/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.domain.AsistenciaEnsayo;
import LengBD.domain.AsistenciaEnsayoListadoDTO;
import LengBD.domain.Estado;
import LengBD.domain.Instrumento;
import LengBD.domain.Usuario;
import LengBD.service.AsistenciaEnsayoService;
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
@RequestMapping("/asistenciaEnsayo")
public class AsistenciaEnsayoController {

    @Autowired
    private IntegrantesService integrantesService;

    @Autowired
    private InstrumentoService instrumentoService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private AsistenciaEnsayoService asistenciaEnsayoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<AsistenciaEnsayoListadoDTO> lista = asistenciaEnsayoService.readAllAsistenciaEnsayo();
        model.addAttribute("asignaciones", lista);
        return "asistenciaEnsayo/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asistenciaEnsayo", new AsistenciaEnsayoListadoDTO());
        cargarCombos(model);
        return "formularioIntegrantes";
    }

    @GetMapping("/editar/{idAsistenciaEnsayo}")
    public String editar(@PathVariable("idAsistenciaEnsayo") Integer id, Model model) {
        model.addAttribute("asistenciaEnsayo", asistenciaEnsayoService.buscarPorId(id));
        cargarCombos(model);
        return "formularioIntegrantes";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AsistenciaEnsayoListadoDTO dto, RedirectAttributes ra) {
        try {
            AsistenciaEnsayo asistenciaEnsayo = new AsistenciaEnsayo();
            asistenciaEnsayo.setIdAsistenciaEnsayos(dto.getIdAsistenciaEnsayos());
            asistenciaEnsayo.setIdEnsayo(dto.getIdEnsayo());
            asistenciaEnsayo.setCedula(dto.getCedula());
            asistenciaEnsayo.setIdEstado(dto.getIdEstado());

            if (dto.getIdAsistenciaEnsayos() != null) {
                asistenciaEnsayoService.actualizarAsistenciaEnsayo(asistenciaEnsayo);
            } else {
                asistenciaEnsayoService.insertarAsistenciaEnsayo(asistenciaEnsayo);
            }
            ra.addFlashAttribute("todoOk", "Asignación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/banda/secciones/listadoDirector";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idAsistenciaEnsayo") Integer idAsistenciaEnsayo, RedirectAttributes ra) {
        try {
            asistenciaEnsayoService.eliminarAsistenciaEnsayo(idAsistenciaEnsayo);
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
