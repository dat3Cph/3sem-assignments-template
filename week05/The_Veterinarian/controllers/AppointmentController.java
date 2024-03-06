package The_Veterinarian.controllers;

import The_Veterinarian.Main;
import io.javalin.http.Context;
import io.javalin.http.Handler;


public class AppointmentController {


    public static Handler getAllAppointments(){
        return ctx -> {
            ctx.json(Main.appointments);
        };
    }


    public static Handler getAppointmentById(){
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (!Main.appointments.containsKey(id)) {
                ctx.status(404);
            }
            ctx.json(Main.appointments.get(id));
        };
    }
}
