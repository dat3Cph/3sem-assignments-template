package The_Veterinarian.handlers;

import The_Veterinarian.Main;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class PatientHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        switch (context.path()){
            case "/":
                context.json(Main.patients);
                context.status(200);
                break;
            case "/patient/{id}":
                int id = Integer.parseInt(context.pathParam("id"));
                if(!Main.patients.containsKey(id)){
                    context.status(404);
                }
                context.json(Main.patients.get(id));
                break;
        }
    }
}
