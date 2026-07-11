/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

/**
 *
 * @author peper
 */
import LengBD.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class GeminiService {
    

    
    
    
    private final UsuarioRepository usuarioRepository; // Inyectamos el repositorio
    
    public GeminiService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String preguntarIAIntegrante(String mensaje) {
        // 1. Verificamos si la pregunta es sobre los integrantes
        if (mensaje.toLowerCase().contains("integrante") || mensaje.toLowerCase().contains("quiénes son")
            || mensaje.toLowerCase().contains("integrantes")){
            return responderSobreIntegrantes(mensaje);
        }
        
        // 2. Si es otra pregunta, llamamos a la IA normal
        return llamarGemini(mensaje);
    }

    private String responderSobreIntegrantes(String pregunta) {
        // Consultamos a Oracle
        var listaIntegrantes = usuarioRepository.findAll();
        
        // Convertimos la lista a un texto simple para la IA
        StringBuilder contexto = new StringBuilder("Aquí tienes la lista de integrantes de la banda: ");
        for (var i : listaIntegrantes) {
            contexto.append(i.getNombre());
        }
        
        // Enviamos el contexto + la pregunta original a la IA
        return llamarGemini("Contexto: " + contexto.toString() + ". Pregunta: " + pregunta);
    }
    
    
    private static final String API_KEY = "APIKEY";
    private final String URL = "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    public String llamarGemini(String mensaje) {
        RestTemplate restTemplate = new RestTemplate();
        String promptFinal = "Eres el asistente inteligente de BandCore. Responde brevemente: " + mensaje;
        String mensajeLimpio = promptFinal.replace("\"", "\\\"");
        String jsonPayload = String.format("{\"contents\": [{\"parts\": [{\"text\": \"%s\"}]}]}", mensajeLimpio);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();
        } catch (Exception e) {
            return "Error al procesar la respuesta: " + e.getMessage();
        }
    }
    
}
