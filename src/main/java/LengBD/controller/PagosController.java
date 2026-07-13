package LengBD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagos")
public class PagosController {

    // 1. Cambiamos esto para que coincida con tu th:href="@{/pagos/prueba}"
    @GetMapping("/prueba")
    public String abrirPrueba(Model model) {

        // 2. Esto se queda igual, busca el archivo "prueba.html" físico en la carpeta "pagos"
        return "pagos/prueba";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        // Aquí luego enviarás un objeto PagoDTO vacío al modelo
        // model.addAttribute("pago", new PagoDTO());

        return "pagos/formulario";
    }
}
