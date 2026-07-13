package LengBD.controller;

import LengBD.domain.AsistenciaEnsayo;
import LengBD.domain.AsistenciaEnsayoListadoDTO;
import LengBD.service.AsistenciaEnsayoService;
import LengBD.service.EnsayosService;
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
@RequestMapping("/asistenciaEnsayo")
public class AsistenciaEnsayoController {

    @Autowired
    private AsistenciaEnsayoService asistenciaEnsayoService;

    @Autowired
    private EnsayosService ensayosService;

    @Autowired
    private IntegrantesService integrantesService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<AsistenciaEnsayoListadoDTO> lista = asistenciaEnsayoService.readAllAsistenciaEnsayo();
        model.addAttribute("asistenciasEnsayo", lista);
        model.addAttribute("nuevaAsistenciaEnsayo", new AsistenciaEnsayoListadoDTO());
        cargarCombos(model);
        return "asistenciaEnsayo/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asistenciaEnsayo", new AsistenciaEnsayoListadoDTO());
        cargarCombos(model);
        return "asistenciaEnsayo/formulario";
    }

    @GetMapping("/editar/{idAsistenciaEnsayos}")
    public String editar(@PathVariable("idAsistenciaEnsayos") Integer id, Model model) {
        model.addAttribute("asistenciaEnsayo", asistenciaEnsayoService.buscarPorId(id));
        cargarCombos(model);
        return "asistenciaEnsayo/formulario";
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
            ra.addFlashAttribute("todoOk", "Asistencia a ensayo guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/asistenciaEnsayo/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idAsistenciaEnsayos") Integer idAsistenciaEnsayos, RedirectAttributes ra) {
        try {
            asistenciaEnsayoService.eliminarAsistenciaEnsayo(idAsistenciaEnsayos);
            ra.addFlashAttribute("todoOk", "Asistencia a ensayo eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/asistenciaEnsayo/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("ensayos", ensayosService.readAllEnsayos());
        model.addAttribute("usuarios", integrantesService.listarIntegrantes());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}