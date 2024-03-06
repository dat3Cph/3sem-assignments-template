package The_Veterinarian.controllers;

import The_Veterinarian.Main;
import io.javalin.http.Context;
import io.javalin.http.Handler;


public class AppointmentController {


    public static Handler getAllAppointments(Context ctx){
        return () -> ctx.json(Main.appointments);
    }

}
