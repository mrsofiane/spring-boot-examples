package me.mrsofiane.digimind.digimind.movies;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(path = "api/v1/movies")
@Log4j2
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping
    public void addMovies(@RequestBody MoviesInput moviesInput) {
        moviesService.addMovies(moviesInput);
    }

    @GetMapping("genre/count")
    public Map<String, Long> countGenreOfMoviesByName(@RequestParam String title) {
        return moviesService.countGenreOfMoviesByName(title);
    }

    @GetMapping("movies-by-title-and-year")
    public String getMoviesByTitleAndYear(@RequestParam String title, @RequestParam String year) {
        return moviesService.getMoviesByTitleAndYear(title, year);
    }

    @GetMapping("{imdbId}")
    public String getMoviesByTitleAndYear(@PathVariable String imdbId ){
        return moviesService.getMoviesByImdbID(imdbId);
    }

}
