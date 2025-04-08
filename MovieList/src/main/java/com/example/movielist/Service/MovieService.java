package com.example.movielist.Service;

import com.example.movielist.Entity.Movie;
import com.example.movielist.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie getMovieById(int id) {
        return movieRepository.getMovieById(id);
    }

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteMovie(id);
    }
}