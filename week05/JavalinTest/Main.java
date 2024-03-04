package JavalinTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.javalin.Javalin;
import lombok.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {

    public static Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
    public static ObjectMapper om = new ObjectMapper();

    public static List<Person> persons = Arrays.asList(
            new Person("Helge", 30),
            new Person("Lise",44),
            new Person("Johny",22),
            new Person("Paul",11)
    );


    public static void main(String[] args) {


        Javalin app = Javalin.create().start(7007);
        //app.get("/", ctx -> ctx.result("Hello World"));
        /*app.get("/persons", ctx -> ctx.json(persons));
        app.get("/persons/{name}", ctx -> {
            String name = ctx.pathParam("name");
            for(Person p : persons){
                if(p.getName().equals(name)){
                    ctx.json(p);
                }
            }
        });
        */

        app.routes(() -> {
            path("persons", () -> {
                get("/", ctx -> ctx.json(persons));
                get("/{name}", ctx -> {
                    String name = ctx.pathParam("name");
                    for(Person p : persons){
                        if(p.getName().equals(name)){
                            ctx.json(p);
                            break;
                        }
                    }
                });
                post("/", ctx -> {
                    Person incoming = ctx.bodyAsClass(Person.class);
                    ctx.json(incoming);
                    persons.add(incoming);
                });
            });
        });

        app.get("/todaysDate", ctx -> ctx.json(om.createObjectNode().put("CurrentDate: ", LocalDate.now().toString())));
        app.get("joke", ctx -> ctx.json(getResponseBody("https://icanhazdadjoke.com")));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    private static class Person{
        String name;
        int age;
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
}
