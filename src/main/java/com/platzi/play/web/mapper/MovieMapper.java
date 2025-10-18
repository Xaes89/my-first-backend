package com.platzi.play.web.mapper;

import com.platzi.play.domain.dto.UpdateMovieDTO;
import com.platzi.play.persistence.entity.MovieEntity;
import com.platzi.play.persistence.entity.MovieStatus;
import com.platzi.play.domain.dto.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public MovieDTO toDTO(MovieEntity entity) {
        if (entity == null) {
            return null;
        }

        MovieDTO dto = MovieDTO.fromEntity(entity);

        // Convertimos el Enum a un String legible para la API
        if (entity.getStatus() != null) {
            dto.setStatus(entity.getStatus().name()); // .name() devuelve "AVAILABLE" o "UNAVAILABLE"
        }

        return dto;
    }

    public List<MovieDTO> toDTOList(List<MovieEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MovieEntity toEntity(MovieDTO dto) {
        if (dto == null) {
            return null;
        }
        MovieEntity entity = new MovieEntity();
        entity.setTitle(dto.getTitle());
        entity.setGenre(dto.getGenre());
        entity.setReleaseDate(dto.getReleaseDate());

        if (dto.getStatus() != null) {
            entity.setStatus(MovieStatus.AVAILABLE);
        }

        return entity;
    }

    public void updateEntityFromDTO(UpdateMovieDTO dto, MovieEntity entity) {
        // Solo actualizamos un campo si no es nulo en el DTO.
        // Esto permite actualizaciones parciales (enviar solo el título, por ejemplo).
        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }
        if (dto.getReleaseDate() != null) {
            entity.setReleaseDate(dto.getReleaseDate());
        }
        if (dto.getGenre() != null) {
            entity.setGenre(dto.getGenre());
        }
    }
}