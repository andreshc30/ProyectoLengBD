package LengBD.controller;

import LengBD.domain.Instrumento;
import LengBD.domain.InstrumentoListadoDTO;
import LengBD.service.InstrumentoService;
import LengBD.service.SeccionService;
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
@RequestMapping("/instrumento")
public class InstrumentoController {

    @Autowired
    private InstrumentoService instrumentoService;

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<InstrumentoListadoDTO> lista = instrumentoService.readAllInstrumento();
        model.addAttribute("instrumentos", lista);
        return "instrumento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("instrumento", new InstrumentoListadoDTO());
        cargarCombos(model);
        return "instrumento/formulario";
    }

    @GetMapping("/editar/{idInstrumento}")
    public String editar(@PathVariable("idInstrumento") Integer id, Model model) {
        model.addAttribute("instrumento", instrumentoService.buscarPorId(id));
        cargarCombos(model);
        return "instrumento/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute InstrumentoListadoDTO dto, RedirectAttributes ra) {
        try {
            Instrumento instrumento = new Instrumento();
            instrumento.setIdInstrumento(dto.getIdInstrumento());
            instrumento.setNombre(dto.getNombre());
            instrumento.setIdSeccion(dto.getIdSeccion());
            instrumento.setIdEstado(dto.getIdEstado());

            if (dto.getIdInstrumento() != null) {
                instrumentoService.actualizarInstrumento(instrumento);
            } else {
                instrumentoService.insertarInstrumento(instrumento);
            }
            ra.addFlashAttribute("todoOk", "Instrumento guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/instrumento/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idInstrumento") Integer idInstrumento, RedirectAttributes ra) {
        try {
            instrumentoService.eliminarInstrumento(idInstrumento);
            ra.addFlashAttribute("todoOk", "Instrumento eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/instrumento/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("secciones", seccionService.readAllSeccion());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}