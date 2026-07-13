package LengBD.controller;

import LengBD.domain.CategoriaMovimiento;
import LengBD.domain.CategoriaMovimientoListadoDTO;
import LengBD.service.CategoriaMovimientoService;
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


@Controller
@RequestMapping("/categoriaMovimiento")
public class CategoriaMovimientoCrudController {

    @Autowired
    private CategoriaMovimientoService categoriaMovimientoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<CategoriaMovimientoListadoDTO> lista = categoriaMovimientoService.readAllCategoriaMovimiento();
        model.addAttribute("categoriasMovimientos", lista);
        model.addAttribute("nuevaCategoriaMovimiento", new CategoriaMovimientoListadoDTO());
        cargarCombos(model);
        return "categoriaMovimiento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoriaMovimiento", new CategoriaMovimientoListadoDTO());
        cargarCombos(model);
        return "categoriaMovimiento/formulario";
    }

    @GetMapping("/editar/{idCategoriaMovimiento}")
    public String editar(@PathVariable("idCategoriaMovimiento") Integer id, Model model) {
        model.addAttribute("categoriaMovimiento", categoriaMovimientoService.buscarPorId(id));
        cargarCombos(model);
        return "categoriaMovimiento/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CategoriaMovimientoListadoDTO dto, RedirectAttributes ra) {
        try {
            CategoriaMovimiento categoriaMovimiento = new CategoriaMovimiento();
            categoriaMovimiento.setIdCategoriaMovimiento(dto.getIdCategoriaMovimiento());
            categoriaMovimiento.setNombre(dto.getNombre());
            categoriaMovimiento.setDetalle(dto.getDetalle());
            categoriaMovimiento.setIdEstado(dto.getIdEstado());

            if (dto.getIdCategoriaMovimiento() != null) {
                categoriaMovimientoService.actualizarCategoriaMovimiento(categoriaMovimiento);
            } else {
                categoriaMovimientoService.insertarCategoriaMovimiento(categoriaMovimiento);
            }
            ra.addFlashAttribute("todoOk", "Categoria Movimiento guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/categoriaMovimiento/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idCategoriaMovimiento") Integer idCategoriaMovimiento, RedirectAttributes ra) {
        try {
            categoriaMovimientoService.eliminarCategoriaMovimiento(idCategoriaMovimiento);
            ra.addFlashAttribute("todoOk", "Categoria Movimiento eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/categoriaMovimiento/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}