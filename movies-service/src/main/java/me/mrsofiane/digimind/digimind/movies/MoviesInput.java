package me.mrsofiane.digimind.digimind.movies;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MoviesInput {

    private final List<Movie> films;
    private final String total;
}
