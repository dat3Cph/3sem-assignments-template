package DTOExcercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        String connection = getResponseBody("https://api.themoviedb.org/3/authentication");
        String movieResponse = getResponseBodyById("tt5177120");
        MovieDTO movie = getMovieById("tt5177120");
        System.out.println(connection);
        System.out.println(movieResponse);
        System.out.println(movie);
    }


    public static String getResponseBody(String URL){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL).get()
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOTQ2MGVkYzlkYjMyNDYzYjFjZjg2YzRhNTE2NDgxOCIsInN1YiI6IjY1YzBjYzhjYmYwOWQxMDE4NGE3OGFlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7oXOM3iuoSy0yjc_oTiE4hGpR74_1PmAZu0G57QRrKo")
                .build();


        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseBodyById(String id){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.themoviedb.org/3/find/{MOVIE ID}?external_source=imdb_id".replace("{MOVIE ID}",id);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkOTQ2MGVkYzlkYjMyNDYzYjFjZjg2YzRhNTE2NDgxOCIsInN1YiI6IjY1YzBjYzhjYmYwOWQxMDE4NGE3OGFlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7oXOM3iuoSy0yjc_oTiE4hGpR74_1PmAZu0G57QRrKo")
                .build();

        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static MovieDTO getMovieById(String id){
        String json = getResponseBodyById(id);
        ResultDTO movieResults = gson.fromJson(json,ResultDTO.class);

        String[] year = movieResults.movie_results[0].release_date.split("-");
        movieResults.movie_results[0].setReleaseYear(year[0]);
        movieResults.movie_results[0].setReleaseDate(LocalDate.parse(movieResults.movie_results[0].release_date));
        return movieResults.movie_results[0];
    }

}
