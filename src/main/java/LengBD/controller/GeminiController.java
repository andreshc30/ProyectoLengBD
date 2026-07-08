/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.controller;

/**
 *
 * @author peper
 */
import LengBD.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/chat")
    public String chatear(@RequestBody String usuarioMensaje) {
        String respuestaIA = geminiService.llamarGemini(usuarioMensaje);
        return geminiService.llamarGemini(usuarioMensaje);
    }
}
