// src/main/java/com/platzi/play/domain/dto/UpdateMovieDTO.java
package com.platzi.play.domain.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UpdateMovieDTO {

    @NotBlank(message = "El título no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres.")
    private String title;

    @NotNull(message = "La fecha de estreno es obligatoria.")
    @PastOrPresent(message = "La fecha de estreno no puede ser una fecha futura.")
    private LocalDate releaseDate;

    @NotBlank(message = "El género no puede estar vacío.")
    private String genre;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}