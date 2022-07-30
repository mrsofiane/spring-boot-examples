package me.mrsofiane.digimind.digimind.movies;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MoviesServiceTest {

    @Spy
    MoviesRepository moviesRepository;
    @InjectMocks
    MoviesService moviesService;


    @Test
    void should_add_movies() {
        //Given
        List<Movie> movies = initMovies();
        MoviesInput moviesInput = MoviesInput.builder().films(movies).total(String.valueOf(movies.size())).build();

        //When
        List<Movie> result = moviesService.addMovies(moviesInput);

        //Then
        Assertions.assertThat(result).hasSize(3);
    }

    @Test
    void should_get_count_of_movies_by_genre() {
        //Given
        List<Movie> movies = initMovies();
        MoviesInput moviesInput = MoviesInput.builder().films(movies).total(String.valueOf(movies.size())).build();
        moviesService.addMovies(moviesInput);

        //When
        Map<String, Long> result = moviesService.countGenreOfMoviesByName("star");

        //Then
        Assertions.assertThat(result).extracting("Action").isEqualTo(2L);
        Assertions.assertThat(result).extracting("Adventure").isEqualTo(2L);
        Assertions.assertThat(result).extracting("Fantasy").isEqualTo(2L);
        Assertions.assertThat(result).extracting("Sci-Fi").isEqualTo(2L);
        Assertions.assertThat(result).extracting("Reality-TV").isEqualTo(1L);
    }


    private List<Movie> initMovies() {
        Movie startWarsIV = Movie.builder()
                .title("Star Wars: Episode IV - A New Hope")
                .year("1977")
                .genre("Action, Adventure, Fantasy, Sci-Fi")
                .type("serie")
                .build();

        Movie startWarsV = Movie.builder()
                .title("Star Wars: Episode V - The Empire Strikes Back")
                .year("1980")
                .genre("Action, Adventure, Fantasy, Sci-Fi")
                .type("movie")
                .build();

        Movie glowUp = Movie.builder()
                .title("Glow Up: Britain's Next Make-Up Star")
                .year("2019")
                .genre("Reality-TV")
                .type("series")
                .build();
        return List.of(startWarsIV, startWarsV, glowUp);
    }


}
