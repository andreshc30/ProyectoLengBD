package LengBD.controller;

import LengBD.domain.Cuota;
import LengBD.domain.CuotaListadoDTO;
import LengBD.service.CuotaService;
import LengBD.service.EstadoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.CuotaService;
import LengBD.service.SuscripcionService;
import LengBD.service.UsuarioService;
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
@RequestMapping("/cuota")
public class CuotaCrudController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private CuotaService cuotaService;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<CuotaListadoDTO> lista = cuotaService.readAllCuota();
        model.addAttribute("cuotas", lista);
        model.addAttribute("nuevaCuota", new CuotaListadoDTO());
        cargarCombos(model);
        return "cuota/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cuota", new CuotaListadoDTO());
        cargarCombos(model);
        return "cuota/formulario";
    }

    @GetMapping("/editar/{idCuota}")
    public String editar(@PathVariable("idCuota") Integer id, Model model) {
        model.addAttribute("cuota", cuotaService.buscarPorId(id));
        cargarCombos(model);
        return "cuota/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CuotaListadoDTO dto, RedirectAttributes ra) {
        try {
            Cuota cuota = new Cuota();
            cuota.setIdCuota(dto.getIdCuota());
            cuota.setCedula(dto.getCedula());
            cuota.setFecha(dto.getFecha());
            cuota.setMontoPagado(dto.getMontoPagado());
            cuota.setIdEstado(dto.getIdEstado());

            if (dto.getIdCuota()!= null) {
                cuotaService.actualizarCuota(cuota);
            } else {
                cuotaService.insertarCuota(cuota);
            }
            ra.addFlashAttribute("todoOk", "Cuota guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/cuota/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idCuota") Integer idCuota, RedirectAttributes ra) {
        try {
            cuotaService.eliminarCuota(idCuota);
            ra.addFlashAttribute("todoOk", "Cuota eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/cuota/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("usuarios", usuarioService.readAllUsuario());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}