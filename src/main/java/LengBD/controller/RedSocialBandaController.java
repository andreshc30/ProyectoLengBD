package LengBD.controller;

import LengBD.domain.RedSocialBanda;
import LengBD.domain.RedSocialBandaListadoDTO;
import LengBD.service.RedSocialBandaService;
import LengBD.service.BandaService;
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
@RequestMapping("/redSocialBanda")
public class RedSocialBandaController {

    @Autowired
    private RedSocialBandaService redSocialBandaService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<RedSocialBandaListadoDTO> lista = redSocialBandaService.readAllRedSocialBanda();
        model.addAttribute("redesSociales", lista);
        model.addAttribute("nuevaRedSocialBanda", new RedSocialBandaListadoDTO());
        cargarCombos(model);
        return "redSocialBanda/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("redSocialBanda", new RedSocialBandaListadoDTO());
        cargarCombos(model);
        return "redSocialBanda/formulario";
    }

    @GetMapping("/editar/{idRedSocial}")
    public String editar(@PathVariable("idRedSocial") Integer id, Model model) {
        model.addAttribute("redSocialBanda", redSocialBandaService.buscarPorId(id));
        cargarCombos(model);
        return "redSocialBanda/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute RedSocialBandaListadoDTO dto, RedirectAttributes ra) {
        try {
            RedSocialBanda redSocialBanda = new RedSocialBanda();
            redSocialBanda.setIdRedSocial(dto.getIdRedSocial());
            redSocialBanda.setPlataforma(dto.getPlataforma());
            redSocialBanda.setLinkBanda(dto.getLinkBanda());
            redSocialBanda.setIdBanda(dto.getIdBanda());
            redSocialBanda.setIdEstado(dto.getIdEstado());

            if (dto.getIdRedSocial() != null) {
                redSocialBandaService.actualizarRedSocialBanda(redSocialBanda);
            } else {
                redSocialBandaService.insertarRedSocialBanda(redSocialBanda);
            }
            ra.addFlashAttribute("todoOk", "Red social guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/redSocialBanda/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idRedSocial") Integer idRedSocial, RedirectAttributes ra) {
        try {
            redSocialBandaService.eliminarRedSocialBanda(idRedSocial);
            ra.addFlashAttribute("todoOk", "Red social eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/redSocialBanda/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}