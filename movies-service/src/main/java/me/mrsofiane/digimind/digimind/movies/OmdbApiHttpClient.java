package me.mrsofiane.digimind.digimind.movies;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OmdbApiHttpClient {
    private static OmdbApiHttpClient instance = null;
    private static final String OMDB_URL = "http://www.omdbapi.com/";
    private static final String API_KEY = "a41f4d81"; // should be as ENV var

    private final OkHttpClient client;

    private OmdbApiHttpClient() {
        this.client = new OkHttpClient();
    }

    public static OmdbApiHttpClient getInstance() {
        if (instance == null) {
            instance = new OmdbApiHttpClient();
        }
        return instance;
    }

    public String getMovieByTitleAndYear(String title, String year) throws IOException {
        Request request = new Request.Builder()
                .url(OMDB_URL + "?apikey=" + API_KEY + "&t=" + title + "&y=" + year)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getMovieByImdbId(String imdbId) throws IOException {
        Request request = new Request.Builder()
                .url(OMDB_URL + "?apikey=" + API_KEY + "&i=" + imdbId)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}

