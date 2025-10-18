package com.platzi.play.domain.dto;

import com.platzi.play.persistence.entity.MovieEntity;
import com.platzi.play.persistence.entity.MovieStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MovieDTO {

    @NotNull(message = "El ID no puede estar vacío.")
    private Long id;

    @NotBlank(message = "El título no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres.")
    private String title;

    @NotBlank(message = "El género no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El género debe tener entre 3 y 100 caracteres.")
    private String genre;

    @NotNull(message = "La fecha de lanzamiento no puede estar vacía.")
    @PastOrPresent(message = "La fecha de estreno no puede ser una fecha futura.")
    private LocalDate releaseDate;

    private MovieStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public static MovieDTO fromEntity(MovieEntity movieEntity) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setGenre(movieEntity.getGenre());
        movieDTO.setReleaseDate(movieEntity.getReleaseDate());
        return movieDTO;
    }

    public void setStatus(String name) {
        this.status = MovieStatus.valueOf(name);
    }

    public MovieStatus getStatus() {
        return status;
    }
}