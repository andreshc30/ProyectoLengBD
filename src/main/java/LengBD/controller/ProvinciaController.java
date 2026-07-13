package LengBD.controller;

import LengBD.domain.Provincia;
import LengBD.domain.ProvinciaListadoDTO;
import LengBD.service.ProvinciaService;
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
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<ProvinciaListadoDTO> lista = provinciaService.readAllProvincia();
        model.addAttribute("provincias", lista);
        return "provincia/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("provincia", new ProvinciaListadoDTO());
        cargarCombos(model);
        return "provincia/formulario";
    }

    @GetMapping("/editar/{idProvincia}")
    public String editar(@PathVariable("idProvincia") Integer id, Model model) {
        model.addAttribute("provincia", provinciaService.buscarPorId(id));
        cargarCombos(model);
        return "provincia/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ProvinciaListadoDTO dto, RedirectAttributes ra) {
        try {
            Provincia provincia = new Provincia();
            provincia.setIdProvincia(dto.getIdProvincia());
            provincia.setNombre(dto.getNombre());
            provincia.setIdEstado(dto.getIdEstado());

            if (dto.getIdProvincia() != null) {
                provinciaService.actualizarProvincia(provincia);
            } else {
                provinciaService.insertarProvincia(provincia);
            }
            ra.addFlashAttribute("todoOk", "Provincia guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/provincia/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idProvincia") Integer idProvincia, RedirectAttributes ra) {
        try {
            provinciaService.eliminarProvincia(idProvincia);
            ra.addFlashAttribute("todoOk", "Provincia eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/provincia/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}