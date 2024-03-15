package JavalinExercise;

import JavalinExercise.controllers.DogController;
import io.javalin.Javalin;

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
