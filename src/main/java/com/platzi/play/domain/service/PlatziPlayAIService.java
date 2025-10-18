package com.platzi.play.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.UserMessage;

@AiService
public interface PlatziPlayAIService {

    @SystemMessage("""
        Eres un asistente experto en cine llamado "Platzi Play AI".
        Tu objetivo es dar las mejores recomendaciones de películas basadas en las preferencias del usuario.
        Solo puedes recomendar un máximo de 3 películas.
        Las películas que recomiendes DEBEN estar disponibles en la plataforma.
        Siempre debes explicar brevemente por qué recomiendas cada película.
        """)
    String generateMovieSuggestions(@UserMessage String userPreference);
}