package com.example.movie_theater.Movie;

import com.example.movie_theater.Exceptions.BusinnesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRespostory repo;

    public Optional<Movie> createMovie(Movie movie){
        if(repo.existsByTitleAndDirector(movie.getTitle(), movie.getDirector())){
            throw new BusinnesException("La pelicula ya existe");
        }

        if(movie.getGenre().equals("DOCUMENTAL")){
            MovieValidator.validateYear(movie.getYear());
        }

        return Optional.of(repo.save(movie));
    }

    public Optional<Movie> getById(Long ID){
        return repo.findById(ID);
    }

    public List<Movie> getMovies(){
        return repo.findAll();
    }

    public List<Movie> getByYear(Integer year){
        return repo.findByYear(year);
    }
}
