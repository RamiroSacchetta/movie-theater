package com.example.movie_theater.Movie.Controllers;

import com.example.movie_theater.Movie.Models.Movie;
import com.example.movie_theater.Movie.Services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService service;

    @PostMapping
    public ResponseEntity<Movie> handleCreateMovie(@Valid @RequestBody Movie movie){
        Movie saved = service.createMovie(movie).orElseThrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> handleGetMovies(){
        List<Movie> movies = service.getMovies();
        if(movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> handleGetById(@PathVariable Long id){
        Optional<Movie> movie= service.getById(id);
        return movie.
                <ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la pelicula"));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<?> handleGetByYear(@PathVariable Integer year){
        List<Movie> movies = service.getByYear(year);
        if(movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }
}
