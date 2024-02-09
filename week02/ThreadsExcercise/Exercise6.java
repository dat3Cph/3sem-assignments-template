package ThreadsExcercise;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Exercise6 {
    public static Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
    static String[] urls = new String[]{
            "https://icanhazdadjoke.com",
            "https://api.chucknorris.io/jokes/random",
            "https://api.kanye.rest",
            "https://api.whatdoestrumpthink.com/api/v1/quotes/random",
            "https://pokeapi.co/api/v2/pokemon",
            "https://api.agify.io/?name=nicklas",
            "https://dog.ceo/api/breeds/image/random",
            "https://api.genderize.io?name=emilie",
            "https://catfact.ninja/fact",
            "https://www.boredapi.com/api/activity"
    };

    static Random random = new Random();

    static List<ResultDTO> results = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();


        for(int i = 0; i < 20; i++){
            int randomUrlIndex = random.nextInt(urls.length);
            results.add(getResult(urls[randomUrlIndex],randomUrlIndex));
            Future<ResultDTO> futureResult = executor.submit(new Callable<ResultDTO>() {
                 @Override
                 public ResultDTO call() throws Exception {
                     return getResult(urls[randomUrlIndex],randomUrlIndex);
                 }
             });
            try{
                ResultDTO result = futureResult.get();
                results.add(result);
            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }

        }

        executor.shutdown();
        try{
            executor.awaitTermination(30, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        results.forEach(System.out::println);
    }


    public static synchronized String getResponseBody(String URL) {
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

    public static synchronized ResultDTO getResult(String url, int index){
        String json = getResponseBody(url);
        ResultDTO result = new ResultDTO();

        try{
            switch(index){
                case 0:
                    result.setDadJoke(gson.fromJson(json,DadJokeDTO.class));
                    break;
                case 1:
                    result.setChuckNorris(gson.fromJson(json,ChuckNorrisDTO.class));
                    break;
                case 2:
                    result.setKanye(gson.fromJson(json,KanyeDTO.class));
                    break;
                case 3:
                    result.setTrump(gson.fromJson(json,TrumpDTO.class));
                    break;
                case 4:
                    result.setPokemon(gson.fromJson(json,PokémonListDTO.class));
                    break;
                case 5:
                    result.setAgeGuess(gson.fromJson(json,AgeGuessDTO.class));
                    break;
                case 6:
                    result.setDogImage(gson.fromJson(json,DogImageDTO.class));
                    break;
                case 7:
                    result.setNumber(gson.fromJson(json, GenderDTO.class));
                    break;
                case 8:
                    result.setCatFact(gson.fromJson(json,CatFactDTO.class));
                    break;
                case 9:
                    result.setBoredDTO(gson.fromJson(json,BoredDTO.class));
                    break;
            }
        }catch (Exception e){
            System.out.println("This is the one thats broken "+json);
        }

        return result;

    }

}
