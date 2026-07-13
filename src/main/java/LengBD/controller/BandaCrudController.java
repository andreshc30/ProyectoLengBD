package LengBD.controller;

import LengBD.domain.Banda;
import LengBD.domain.BandaListadoDTO;
import LengBD.service.BandaService;
import LengBD.service.DireccionService;
import LengBD.service.CorreoService;
import LengBD.service.TelefonoService;
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
 * NOTA: ruta "/bandas" (plural) en vez de "/banda" porque "/banda/listado" ya está
 * tomada por la vista de hub/dashboard administrativo (BandaController.java existente).
 * Confirmar con el equipo si este es el nombre de ruta preferido antes de mergear.
 */
@Controller
@RequestMapping("/bandas")
public class BandaCrudController {

    @Autowired
    private BandaService bandaService;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private CorreoService correoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<BandaListadoDTO> lista = bandaService.readAllBanda();
        model.addAttribute("bandas", lista);
        return "bandas/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("banda", new BandaListadoDTO());
        cargarCombos(model);
        return "bandas/formulario";
    }

    @GetMapping("/editar/{idBanda}")
    public String editar(@PathVariable("idBanda") Integer id, Model model) {
        model.addAttribute("banda", bandaService.buscarPorId(id));
        cargarCombos(model);
        return "bandas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute BandaListadoDTO dto, RedirectAttributes ra) {
        try {
            Banda banda = new Banda();
            banda.setIdBanda(dto.getIdBanda());
            banda.setNombre(dto.getNombre());
            banda.setLogoUrl(dto.getLogoUrl());
            banda.setMontoCuota(dto.getMontoCuota());
            banda.setFechaFundacion(dto.getFechaFundacion());
            banda.setIdDireccion(dto.getIdDireccion());
            banda.setIdCorreo(dto.getIdCorreo());
            banda.setIdTelefono(dto.getIdTelefono());
            banda.setIdEstado(dto.getIdEstado());

            if (dto.getIdBanda() != null) {
                bandaService.actualizarBanda(banda);
            } else {
                bandaService.insertarBanda(banda);
            }
            ra.addFlashAttribute("todoOk", "Banda guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/bandas/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idBanda") Integer idBanda, RedirectAttributes ra) {
        try {
            bandaService.eliminarBanda(idBanda);
            ra.addFlashAttribute("todoOk", "Banda eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/bandas/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("direcciones", direccionService.readAllDireccion());
        model.addAttribute("correos", correoService.readAllCorreo());
        model.addAttribute("telefonos", telefonoService.readAllTelefono());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}