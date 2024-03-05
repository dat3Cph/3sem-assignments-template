package The_Veterinarian.handlers;

import The_Veterinarian.Main;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class AppointmentHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        String path = context.path();
        String method = context.method().toString();
        switch (path){
            case "/appointments":
                context.json(Main.appointments);
                context.status(200);
                break;
            case "/appointment/{id}":
                int id = Integer.parseInt(context.pathParam("id"));
                if(!Main.appointments.containsKey(id)){
                    context.status(404);
                }
                break;
        }
    }
}
