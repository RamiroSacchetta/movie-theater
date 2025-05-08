package com.example.movie_theater.Movie.Repositorys;

import com.example.movie_theater.Movie.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRespostory extends JpaRepository<Movie, Long> {
    List<Movie> findByYear (Integer year);
    boolean existsByTitleAndDirector(String title, String director);

}
