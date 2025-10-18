package com.platzi.play.domain.service;

import com.platzi.play.domain.dto.MovieDTO;
import com.platzi.play.domain.dto.UpdateMovieDTO;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDTO> getAll();
    MovieDTO getById(Long movieId);
    MovieDTO create(MovieDTO movieDTO);
    Optional<MovieDTO> update(Long movieId, UpdateMovieDTO updateMovieDTO);
    boolean delete(Long movieId);
}