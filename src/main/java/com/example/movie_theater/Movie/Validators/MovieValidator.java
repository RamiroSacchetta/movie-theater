package com.example.movie_theater.Movie.Validators;

import com.example.movie_theater.Exceptions.BusinnesException;

public class MovieValidator {
    public static void validateYear(Integer year){
        if(year < 1920){
            throw new BusinnesException("El año no es valido, no puede sera anterior a 1920");
        }
    }
}
