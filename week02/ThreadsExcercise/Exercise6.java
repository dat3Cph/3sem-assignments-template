package ThreadsExcercise;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;

public class Exercise6 {
    static String[] urls = new String[]{
            "https://icanhazdadjoke.com",
            "https://api.chucknorris.io/jokes/random",
            "https://api.kanye.rest",
            "https://api.whatdoestrumpthink.com/api/v1/quotes/random",
            "https://pokeapi.co/api/v2/pokemon",
            "https://api.agify.io/?name=nicklas",
            "https://dog.ceo/api/breeds/image/random",
            "https://restcountries.com/v3.1/name/India?fullText=true",
            "https://catfact.ninja/fact",
            "https://www.boredapi.com/api/activity"
    };

    static Random random = new Random();

    public static void main(String[] args) {
        String result = getResponseBody(urls[random.nextInt(urls.length)]);
        System.out.println(result);
    }


    public static String getResponseBody(String URL) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL).get()
                .addHeader("Accept", "application/json")
                .method("GET", null)
                .build();


        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
