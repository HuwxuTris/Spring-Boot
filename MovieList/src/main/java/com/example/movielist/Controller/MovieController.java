package com.example.movielist.Controller;

import com.example.movielist.Entity.Movie;
import com.example.movielist.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping()
    public String showMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }


    @GetMapping("/create")
    public String createMovieForm() {
        return "create-movies";
    }

    @PostMapping("/save")
    public String saveMovie(
            @RequestParam String name,
            @RequestParam String genre,
            @RequestParam String director,
            @RequestParam int duration,
            @RequestParam String description,
            @RequestParam String publishedDate,
            @RequestParam String category) {

        Movie movie = new Movie();
        movie.setName(name);
        movie.setGenre(genre);
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setDescription(description);
        movie.setPublishedDate(LocalDate.parse(publishedDate));
        movie.setCategory(category);

        movieService.addMovie(movie);
        return "redirect:/movies";
    }
}
