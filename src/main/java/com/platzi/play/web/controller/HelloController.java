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

}