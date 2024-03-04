package JavalinExercise2.controllers;

import JavalinExercise2.dtos.DogDTO;
import io.javalin.http.Context;

import java.util.LinkedHashMap;
import java.util.Map;

public class DogController {

    public static Map<Integer, DogDTO> dogs = new LinkedHashMap<>();

    public static void getAllDogs(Context ctx){
        if(!dogs.isEmpty()){
            ctx.json(dogs);
        }else {
            ctx.status(404);
        }
    }

    public static void getDogById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(dogs.containsKey(id)){
            ctx.json(dogs.get(id));
        }else {
            ctx.status(404);
        }
    }

    public static void createDog(Context ctx){
        DogDTO incoming = ctx.bodyAsClass(DogDTO.class);
        incoming.setId(dogs.size()+1);
        ctx.json(incoming);
        dogs.put(dogs.size()+1,incoming);
    }

    public static void updateDog(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO incoming = ctx.bodyAsClass(DogDTO.class);
        if(!dogs.containsKey(id)){
            ctx.status(404);
        }

        dogs.put(id,incoming);
        ctx.json(incoming);
    }

    public static void deleteDog(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        DogDTO deleted = dogs.get(id);
        if(!dogs.containsKey(id)){
            ctx.status(404);
        }

        dogs.remove(id);
        ctx.json(deleted);
    }

    public static void fillDogMap(){
        dogs.put(1, new DogDTO(1,"Jens", "Beagle","Male", 2));
        dogs.put(2, new DogDTO(2,"Paulina", "Poodle","Female", 4));
        dogs.put(3, new DogDTO(3,"Svend", "Rottweiler","Male", 4));
    }

}
