package The_Veterinarian;

import The_Veterinarian.controllers.AppointmentController;
import The_Veterinarian.controllers.PatientController;
import The_Veterinarian.dtos.AppointmentDTO;
import The_Veterinarian.dtos.PatientDTO;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import java.time.LocalDateTime;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {


    public static Map<Integer, AppointmentDTO> appointments = new LinkedHashMap<>();
    public static Map<Integer, PatientDTO> patients = new HashMap<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);
        app.before(ctx -> {
            app.attribute("testAttribute", "Hello :)");
            System.out.println(ctx.req());
        });

        app.after(ctx -> {
            System.out.println(app.attribute("testAttribute").toString());
            System.out.println(ctx.res());
        });



        //id name allergies medications

        //create patients:
        PatientDTO patient1 = new PatientDTO(1, "Gunther", new String[]{"Pollen", "Mold spores"}, new String[]{"Amoxicillin"});
        PatientDTO patient2 = new PatientDTO(2, "Genevieve", new String[]{"Dust mites"}, new String[]{"None"});
        PatientDTO patient3 = new PatientDTO(3, "Lord", new String[]{"Shed skin cells", "Flea saliva"}, new String[]{"Carprofen"});
        PatientDTO patient4 = new PatientDTO(4, "Morten", new String[]{"None"}, new String[]{"None"});

        //id veterinarianName patientDTO date description

        //create appointments:
        AppointmentDTO appointment1 = new AppointmentDTO(1, "J. Honda", patient1, LocalDateTime.now().toString(), "Yearly check-up");
        AppointmentDTO appointment2 = new AppointmentDTO(2, "D. Monterell", patient2, LocalDateTime.now().toString(), "1 year vaccine");
        AppointmentDTO appointment3 = new AppointmentDTO(3, "M. Peterson", patient3, LocalDateTime.now().toString(), "Knee operation");
        AppointmentDTO appointment4 = new AppointmentDTO(4, "J. Honda", patient4, LocalDateTime.now().toString(), "Painkiller distribution");

        patients.put(1, patient1);
        patients.put(2, patient2);
        patients.put(3, patient3);
        patients.put(4, patient4);

        appointments.put(1, appointment1);
        appointments.put(2, appointment2);
        appointments.put(3, appointment3);
        appointments.put(4, appointment4);


        app.routes(appointmentRessource());
        app.routes(patientRessource());


    }

    private static EndpointGroup appointmentRessource() {
        return () -> {
            path("/api/vet", () -> {
                path("/appointments", () -> {
                    get("/", AppointmentController.getAllAppointments());
                    get("/appointment/{id}", AppointmentController.getAppointmentById());
                });
            });
        };
    }

    private static EndpointGroup patientRessource(){
        return () -> {
            path("/api/vet", () -> {
                path("/patients", () -> {
                    get("/", PatientController.getAllPatients());
                    get("/patient/{id}", PatientController.getPatientById());
                });
            });
        };
    }

}