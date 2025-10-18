package com.platzi.play.web.controller;

import com.platzi.play.domain.dto.SuggestRequestDTO;
import com.platzi.play.domain.service.PlatziPlayAIService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suggest")
public class SuggestionController{

    private final PlatziPlayAIService aiService;

    public SuggestionController(PlatziPlayAIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public ResponseEntity<String> suggestMovies(@Valid @RequestBody SuggestRequestDTO request) {
        String suggestion = aiService.generateMovieSuggestions(request.userPreference());
        return ResponseEntity.ok(suggestion);
    }
}