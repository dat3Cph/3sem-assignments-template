package The_Veterinarian.controllers;

import The_Veterinarian.Main;
import io.javalin.http.Handler;

public class PatientController {


    public static Handler getAllPatients(){
        if(Main.patients.isEmpty()){
            return ctx -> ctx.status(404);
        }
        return ctx -> ctx.json(Main.patients);
    }

    public static Handler getPatientById(){
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (!Main.patients.containsKey(id)) {
                ctx.status(404);
            }
            ctx.json(Main.patients.get(id));
        };
    }

}
