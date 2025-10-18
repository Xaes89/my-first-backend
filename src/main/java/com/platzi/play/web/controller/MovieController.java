package com.platzi.play.web.controller;

import com.platzi.play.domain.dto.UpdateMovieDTO;
import com.platzi.play.domain.service.MovieService;
import com.platzi.play.domain.dto.MovieDTO;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Movies", description = "API para gestionar películas")
public class MovieController {

    // <-- 2. AÑADIMOS LA DEPENDENCIA
    private final MovieService movieService;

    // 2. LA INYECTAMOS EN EL CONSTRUCTOR
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movieDTOs = this.movieService.getAll();
        return ResponseEntity.ok(movieDTOs);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "Obtener una película por su ID", description = "Busca y devuelve los detalles de una película específica basada en su identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Película encontrada exitosamente."),
            @ApiResponse(responseCode = "404", description = "Película no encontrada con el ID proporcionado.")
    })
    public ResponseEntity<MovieDTO> getById(
            @Parameter(description = "El ID único de la película a buscar.", example = "1")
            @PathVariable("movieId") Long id) {
        MovieDTO movieDTO = this.movieService.getById(id);
        if (movieDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping("/")
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        MovieDTO savedDTO = this.movieService.create(movieDTO);
        if (savedDTO != null) {
            return ResponseEntity
                    .status(201)
                    .body(savedDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDTO> update(
            @PathVariable Long movieId,
            @RequestBody UpdateMovieDTO updateMovieDTO) {
        MovieDTO savedDTO = this.movieService.update(movieId, updateMovieDTO).orElse(null);
        if (savedDTO != null) {
            return ResponseEntity.ok(savedDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable Long movieId) {
        if (this.movieService.delete(movieId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}