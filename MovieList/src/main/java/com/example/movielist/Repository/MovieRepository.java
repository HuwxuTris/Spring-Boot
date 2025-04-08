package com.example.movielist.Repository;

import com.example.movielist.Entity.Movie;
import com.example.movielist.Utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MovieRepository {

    private static final String FILE_PATH = "movies.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public List<Movie> getAllMovies() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            throw new RuntimeException("Error reading movies file", e);
        }
    }

    public Movie getMovieById(int id) {
        return getAllMovies().stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addMovie(Movie movie) {
        List<Movie> movies = getAllMovies();
        movies.add(movie);
        saveMoviesToFile(movies);
    }

    public void deleteMovie(int id) {
        List<Movie> movies = getAllMovies();
        movies.removeIf(movie -> movie.getId() == id);
        saveMoviesToFile(movies);
    }

    private void saveMoviesToFile(List<Movie> movies) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(movies, writer);
        } catch (Exception e) {
            throw new RuntimeException("Error writing to movies file", e);
        }
    }
}

