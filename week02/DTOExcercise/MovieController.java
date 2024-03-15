package DTOExcercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;

public class MovieController<T> implements MediaController<Media>  {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String getResponseBody(String URL){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL).get()
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOTQ2MGVkYzlkYjMyNDYzYjFjZjg2YzRhNTE2NDgxOCIsInN1YiI6IjY1YzBjYzhjYmYwOWQxMDE4NGE3OGFlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7oXOM3iuoSy0yjc_oTiE4hGpR74_1PmAZu0G57QRrKo")
                .build();


        try (Response response = client.newCall(request).execute()){
            assert response.body() != null;
            return response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public Media getById(String id){
        String url = "https://api.themoviedb.org/3/find/{MOVIE ID}?external_source=imdb_id".replace("{MOVIE ID}",id);
        String json = getResponseBody(url);
        ResultDTO results = gson.fromJson(json,ResultDTO.class);

        String[] year = results.movie_results[0].getRelease_date().split("-");
        results.movie_results[0].setReleaseYear(year[0]);
        results.movie_results[0].setReleaseDate(LocalDate.parse(results.movie_results[0].getRelease_date()));
        return results.movie_results[0];
    }

    public Media[] getAllByRating(String rating){
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=vote_average.desc&vote_average.lte={MOVIE RATING}&vote_count.gte=100".replace("{MOVIE RATING}",rating);
        String json = getResponseBody(url);
        ResultDTO movieResults = gson.fromJson(json,ResultDTO.class);

        for(int i = 0; i < movieResults.results.length-1; i++){
            String[] year = movieResults.results[i].getRelease_date().split("-");
            movieResults.results[i].setReleaseYear(year[0]);
            movieResults.results[i].setReleaseDate(LocalDate.parse(movieResults.results[i].getRelease_date()));
        }

        return movieResults.results;
    }

    public Media[] getAllByReleaseDate(){
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=primary_release_date.desc";
        String json = getResponseBody(url);
        ResultDTO movieResults = gson.fromJson(json,ResultDTO.class);

        for(int i = 0; i < movieResults.results.length-1; i++){
            String[] year = movieResults.results[i].getRelease_date().split("-");
            movieResults.results[i].setReleaseYear(year[0]);
            movieResults.results[i].setReleaseDate(LocalDate.parse(movieResults.results[i].getRelease_date()));
        }

        return movieResults.results;
    }

    @Override
    public Media getByTitle(String title) {
        String url ="https://api.themoviedb.org/3/search/movie?query={TITLE}&include_adult=false&language=en-US&page=1".replace("{TITLE}",title);
        String json = getResponseBody(url);
        ResultDTO results = gson.fromJson(json,ResultDTO.class);
        String[] year = results.results[0].getRelease_date().split("-");
        results.results[0].setReleaseYear(year[0]);
        results.results[0].setReleaseDate(LocalDate.parse(results.results[0].getRelease_date()));
        return results.results[0];
    }

}
