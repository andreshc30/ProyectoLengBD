/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

import LengBD.service.CuotaService;
import LengBD.service.EstadoCuentaService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estadoCuenta")
public class EstadoCuentaController {

    @Autowired
    private EstadoCuentaService estadoCuentaService;

    @Autowired
    private CuotaService cuotaService; 

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("estadoCuenta", estadoCuentaService.readEstadoCuenta());
        return "estadoCuenta/listado";
    }

    @PostMapping("/pagar")
    public String pagarRapido(
            @RequestParam("cedula") Long cedula,
            @RequestParam(value = "monto", required = false) BigDecimal monto,
            RedirectAttributes ra) {
        try {
            cuotaService.registrarPago(cedula, monto);
            ra.addFlashAttribute("todoOk", "Pago registrado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al registrar el pago: " + ex.getMessage());
        }
        return "redirect:/estadoCuenta/listado";
    }
}
