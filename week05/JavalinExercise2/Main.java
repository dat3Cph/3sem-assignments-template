package JavalinExercise2;

import JavalinExercise2.controllers.DogController;
import JavalinExercise2.dtos.DogDTO;
import io.javalin.Javalin;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {



    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);
            DogController.fillDogMap();

            app.routes(() -> {
                path("/api/dogs", () -> {
                    get("/", DogController::getAllDogs);
                    get("/dog/{id}", DogController::getDogById);
                    post("/dog", DogController::createDog);
                    put("/dog/{id}", DogController::updateDog);
                    delete("/dog/{id}", DogController::deleteDog);
                });
            });

    }


}
