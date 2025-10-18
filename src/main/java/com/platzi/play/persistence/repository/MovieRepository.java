package com.platzi.play.persistence.repository;

import com.platzi.play.persistence.entity.MovieEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findFirstByTitle(String title);

}