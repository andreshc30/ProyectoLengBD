/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.domain.AsignacionInstrumento;
import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.Estado;
import LengBD.domain.Instrumento;
import LengBD.domain.Usuario;
import LengBD.service.AsignacionInstrumentoService;
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
@RequestMapping("/asignacionInstrumento")
public class AsignacionInstrumentoController {

   
    @Autowired
    private IntegrantesService integrantesService;

    @Autowired
    private InstrumentoService instrumentoService;

    @Autowired
    private EstadoService estadoService;
    
    
    @Autowired
    private AsignacionInstrumentoService asignacionInstrumentoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<AsignacionListadoDTO> lista = asignacionInstrumentoService.readAllAsignacionInstrumento();
        model.addAttribute("asignaciones", lista);
        return "asignacionInstrumento/listado";
    }

    @GetMapping("/nuevo")
public String nuevo(Model model) {
    model.addAttribute("asignacionInstrumento", new AsignacionListadoDTO());
    cargarCombos(model);
    return "formularioIntegrantes";
}

    @GetMapping("/editar/{idAsignacion}")
public String editar(@PathVariable("idAsignacion") Integer id, Model model) {
    model.addAttribute("asignacionInstrumento", asignacionInstrumentoService.buscarPorId(id));
    cargarCombos(model);
    return "formularioIntegrantes";
}

    @PostMapping("/guardar")
public String guardar(@ModelAttribute AsignacionListadoDTO dto, RedirectAttributes ra) {
    try {
        AsignacionInstrumento asignacion = new AsignacionInstrumento();
        asignacion.setIdAsignacion(dto.getIdAsignacion());
        asignacion.setFechaInicio(dto.getFechaInicio());
        asignacion.setFechaFinal(dto.getFechaFinal());
        asignacion.setMotivo(dto.getMotivo());

        Usuario u = new Usuario();     u.setCedula(dto.getCedula());
        Instrumento i = new Instrumento(); i.setIdInstrumento(dto.getIdInstrumento());
        Estado e = new Estado();       e.setIdEstado(dto.getIdEstado());
        asignacion.setUsuario(u);
        asignacion.setInstrumento(i);
        asignacion.setEstado(e);

        if (dto.getIdAsignacion() != null) {
            asignacionInstrumentoService.actualizarAsignacionInstrumento(asignacion);
        } else {
            asignacionInstrumentoService.insertarAsignacionInstrumento(asignacion);
        }
        ra.addFlashAttribute("todoOk", "Asignación guardada correctamente");
    } catch (Exception ex) {
        ex.printStackTrace();
        ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
    }
    return "redirect:/banda/secciones/listadoDirector";
}
    
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idAsignacion") Integer idAsignacion, RedirectAttributes ra) {
        try {
            asignacionInstrumentoService.eliminarAsignacionInstrumento(idAsignacion);
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
