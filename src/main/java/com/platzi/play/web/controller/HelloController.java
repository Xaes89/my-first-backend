package com.platzi.play.web.controller;

import com.platzi.play.domain.service.PlatziPlayAIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/play")
public class HelloController {

    private final PlatziPlayAIService aiService;

    public HelloController(PlatziPlayAIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "¡Bienvenido a Platzi Play API! 🎬";
    }

    @GetMapping("/")
    public String home() {
        return "🎬 Platzi Play API - Backend funcionando correctamente 🚀\n\n" +
               "Endpoints disponibles:\n" +
               "• GET /api/v1/play/hello - Mensaje de bienvenida\n" +
               "• GET /api/v1/movies/all - Todas las películas\n" +
               "• GET /api/v1/movies/{id} - Película por ID\n" +
               "• POST /api/v1/movies/ - Crear película\n" +
               "• PUT /api/v1/movies/{id} - Actualizar película\n" +
               "• DELETE /api/v1/movies/{id} - Eliminar película\n" +
               "• POST /api/v1/suggest - Sugerencias de películas";
    }

}