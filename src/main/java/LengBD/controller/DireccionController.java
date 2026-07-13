package LengBD.controller;

import LengBD.domain.Direccion;
import LengBD.domain.DireccionListadoDTO;
import LengBD.service.DireccionService;
import LengBD.service.ProvinciaService;
import LengBD.service.CantonService;
import LengBD.service.DistritoService;
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
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private CantonService cantonService;

    @Autowired
    private DistritoService distritoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<DireccionListadoDTO> lista = direccionService.readAllDireccion();
        model.addAttribute("direcciones", lista);
        return "direccion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("direccion", new DireccionListadoDTO());
        cargarCombos(model);
        return "direccion/formulario";
    }

    @GetMapping("/editar/{idDireccion}")
    public String editar(@PathVariable("idDireccion") Integer id, Model model) {
        model.addAttribute("direccion", direccionService.buscarPorId(id));
        cargarCombos(model);
        return "direccion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DireccionListadoDTO dto, RedirectAttributes ra) {
        try {
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(dto.getIdDireccion());
            direccion.setIdProvincia(dto.getIdProvincia());
            direccion.setIdCanton(dto.getIdCanton());
            direccion.setIdDistrito(dto.getIdDistrito());
            direccion.setOtrosDetalles(dto.getOtrosDetalles());
            direccion.setIdEstado(dto.getIdEstado());

            if (dto.getIdDireccion() != null) {
                direccionService.actualizarDireccion(direccion);
            } else {
                direccionService.insertarDireccion(direccion);
            }
            ra.addFlashAttribute("todoOk", "Dirección guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/direccion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idDireccion") Integer idDireccion, RedirectAttributes ra) {
        try {
            direccionService.eliminarDireccion(idDireccion);
            ra.addFlashAttribute("todoOk", "Dirección eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/direccion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("provincias", provinciaService.readAllProvincia());
        model.addAttribute("cantones", cantonService.readAllCanton());
        model.addAttribute("distritos", distritoService.readAllDistrito());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}