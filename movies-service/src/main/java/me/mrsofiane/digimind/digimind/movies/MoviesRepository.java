package me.mrsofiane.digimind.digimind.movies;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Log4j2
public class MoviesRepository {

    private List<Movie> moviesDataSource = new ArrayList<>();

    public List<Movie> addMovies(List<Movie> movies) {
        moviesDataSource.addAll(movies);
        return movies;
    }

    public List<Movie> findAll() {
        return moviesDataSource;
    }

    public List<Movie> findMoviesByTitle(String title) {
        return moviesDataSource.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title))
                .collect(Collectors.toList());

    }

    public List<String> findAllGenre() {
        return moviesDataSource.stream()
                .map(Movie::getGenre)
                .filter(genre -> !genre.isEmpty())
                .map(genre -> genre.split(", "))
                .flatMap(Stream::of)
                .map(Objects::toString)
                .distinct()
                .collect(Collectors.toList());
    }

}
