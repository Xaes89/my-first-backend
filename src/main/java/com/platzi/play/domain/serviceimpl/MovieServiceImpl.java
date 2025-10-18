package com.platzi.play.domain.serviceimpl;

import com.platzi.play.domain.dto.MovieDTO;
import com.platzi.play.domain.dto.UpdateMovieDTO;
import com.platzi.play.domain.service.MovieService;
import com.platzi.play.exception.MovieAlreadyExistsException;
import com.platzi.play.exception.ResourceNotFoundException;
import com.platzi.play.persistence.entity.MovieEntity;
import com.platzi.play.persistence.repository.MovieRepository;
import com.platzi.play.web.mapper.MovieMapper;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Tool("Busca todas las películas que existan dentro de la plataforma")
    @Override
    public List<MovieDTO> getAll() {
        return movieMapper.toDTOList(movieRepository.findAll());
    }

    @Tool("Busca una película por su ID")
    @Override
    public MovieDTO getById(Long movieId) {
        return movieRepository.findById(movieId)
                .map(movieMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con ID: " + movieId));
    }

    @Tool("Crea una nueva película")
    @Override
    public MovieDTO create(MovieDTO movieDTO) {

        this.movieRepository.findFirstByTitle(movieDTO.getTitle())
                .ifPresent(entity -> {
                    throw new MovieAlreadyExistsException(entity.getTitle());
                });
        MovieEntity entity = movieMapper.toEntity(movieDTO);
        MovieEntity savedEntity = movieRepository.save(entity);
        return movieMapper.toDTO(savedEntity);
    }

    @Tool("Actualiza una película existente")
    @Override
    public Optional<MovieDTO> update(Long movieId, UpdateMovieDTO updateMovieDTO) {
        return movieRepository.findById(movieId)
                .map(movie -> {
                    movieMapper.updateEntityFromDTO(updateMovieDTO, movie);
                    return movieMapper.toDTO(movieRepository.save(movie));
                });
    }

    @Tool("Elimina una película por su ID")
    @Override
    public boolean delete(Long movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        } else {
            return false;
        }
    }
}