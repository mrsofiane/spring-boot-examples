package me.mrsofiane.digimind.digimind.movies;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class MoviesService {

    private final MoviesRepository moviesRepository;
    private final OmdbApiHttpClient omdbApiHttpClient = OmdbApiHttpClient.getInstance();


    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> addMovies(MoviesInput moviesInput) {
        return moviesRepository.addMovies(moviesInput.getFilms());
    }

    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    public Map<String, Long> countGenreOfMoviesByName(String movieName) {
        List<String> allGenre = findAllGenre();
        List<Movie> moviesByTitle = moviesRepository.findMoviesByTitle(movieName);
        Map<String, Long> countByGenre = new HashMap<>();

        for (String genre : allGenre) {
            long count = moviesByTitle.stream().map(Movie::getGenre).filter(g -> g.contains(genre)).count();
            countByGenre.put(genre, count);
        }

        return countByGenre;
    }


    public List<String> findAllGenre() {
        return moviesRepository.findAllGenre();
    }

    public String getMoviesByTitleAndYear(String title, String year) {

        try {
            return omdbApiHttpClient.getMovieByTitleAndYear(title, year);
        }catch (IOException e) {
            log.error(e.getMessage());
        }
        return "not found";
    }

    public String getMoviesByImdbID(String imdbId) {
        try {
            return omdbApiHttpClient.getMovieByImdbId(imdbId);
        }catch (IOException e) {
            log.error(e.getMessage());
        }
        return "not found";
    }
}
