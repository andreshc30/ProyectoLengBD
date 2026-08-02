package LengBD.controller;

import LengBD.service.GeminiService;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/chat")
    public String chatear(@RequestBody Map<String, String> body, Authentication auth) {
        String mensaje = body.get("mensaje");
        return geminiService.preguntar(auth.getName(), mensaje);
    }
}