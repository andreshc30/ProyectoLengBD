package LengBD.controller;

import LengBD.domain.BandaInstrumento;
import LengBD.domain.BandaInstrumentoListadoDTO;
import LengBD.service.BandaInstrumentoService;
import LengBD.service.BandaService;
import LengBD.service.InstrumentoService;
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

/*
 * NOTA: FIDE_BANDA_INSTRUMENTO_TB usa clave compuesta (idBanda + idInstrumento).
 * Mismo patrón que RolUsuarioController: se usa un campo oculto "editando" en el
 * formulario para distinguir alta de edición.
 */
@Controller
@RequestMapping("/bandaInstrumento")
public class BandaInstrumentoController {

    @Autowired
    private BandaInstrumentoService bandaInstrumentoService;

    @Autowired
    private BandaService bandaService;

    // TODO: no tengo InstrumentoService confirmado. Verificar nombre real del método readAllX.
    @Autowired
    private InstrumentoService instrumentoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<BandaInstrumentoListadoDTO> lista = bandaInstrumentoService.readAllBandaInstrumento();
        model.addAttribute("bandaInstrumentos", lista);
        return "bandaInstrumento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("bandaInstrumento", new BandaInstrumentoListadoDTO());
        cargarCombos(model);
        return "bandaInstrumento/formulario";
    }

    @GetMapping("/editar/{idBanda}/{idInstrumento}")
    public String editar(@PathVariable("idBanda") Integer idBanda,
                          @PathVariable("idInstrumento") Integer idInstrumento, Model model) {
        model.addAttribute("bandaInstrumento", bandaInstrumentoService.buscarPorId(idBanda, idInstrumento));
        cargarCombos(model);
        return "bandaInstrumento/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute BandaInstrumentoListadoDTO dto,
                           @RequestParam(value = "editando", required = false, defaultValue = "false") boolean editando,
                           RedirectAttributes ra) {
        try {
            BandaInstrumento bandaInstrumento = new BandaInstrumento();
            bandaInstrumento.setIdBanda(dto.getIdBanda());
            bandaInstrumento.setIdInstrumento(dto.getIdInstrumento());
            bandaInstrumento.setIdEstado(dto.getIdEstado());

            if (editando) {
                bandaInstrumentoService.actualizarBandaInstrumento(bandaInstrumento);
            } else {
                bandaInstrumentoService.insertarBandaInstrumento(bandaInstrumento);
            }
            ra.addFlashAttribute("todoOk", "Instrumento de banda guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/bandaInstrumento/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idBanda") Integer idBanda,
                            @RequestParam("idInstrumento") Integer idInstrumento, RedirectAttributes ra) {
        try {
            bandaInstrumentoService.eliminarBandaInstrumento(idBanda, idInstrumento);
            ra.addFlashAttribute("todoOk", "Instrumento de banda eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/bandaInstrumento/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        // TODO: confirmar método real de InstrumentoService
        model.addAttribute("instrumentos", instrumentoService.readAllInstrumento());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}