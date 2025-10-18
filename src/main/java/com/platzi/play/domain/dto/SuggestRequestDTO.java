package com.platzi.play.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record SuggestRequestDTO(
        @NotBlank String userPreference
) {}