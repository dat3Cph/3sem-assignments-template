import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        DolphinDAO dolphinDAO = DolphinDAO.getInstance(emf);


        Person p1 = new Person("Peter");
        Person p2 = new Person("Donnie");
        PersonDetail pd1 = new PersonDetail("Løjtegaardsvej 25",4200,"Amager",45);
        PersonDetail pd2 = new PersonDetail("Bellahøjvej 2",2700,"Brønshøj",32);
        p1.addPersonDetail(pd1);
        p2.addPersonDetail(pd2);
        Fee f1 = new Fee(2500,LocalDate.of(2023,9,23));
        Fee f2 = new Fee(50,LocalDate.of(2024,5,22));
        Fee f3 = new Fee(268,LocalDate.of(2023,6,11));
        p1.addFee(f1);
        p1.addFee(f2);
        p2.addFee(f3);

        ////////////////////////////////////////////////////////// US-1: /////////////////////////////////////////////////////////////

        Note note1 = new Note("This is Peter, he kinda smells",LocalDate.now(), "John");
        Note note2 = new Note("Oh, and he also hasn't paid in weeks", LocalDate.now(),"John");
        Note note3 = new Note("Good payer, but haven't been paying for a while. Will contact soon",LocalDate.now(), "Dennis");
        Note note4 = new Note("Contacted and the customer is aware of the situation, payment has begun",LocalDate.now(), "Dennis");

        p1.addNote(note1);
        p1.addNote(note2);
        p2.addNote(note3);
        p2.addNote(note4);


        dolphinDAO.register(p1);
        dolphinDAO.register(p2);

        ////////////////////////////////////////////////////////// US-2: /////////////////////////////////////////////////////////////

        int result = dolphinDAO.getAmountPaid(1);
        System.out.println(result);

        ////////////////////////////////////////////////////////// US-3: /////////////////////////////////////////////////////////////

        List<Note> notes = dolphinDAO.getNotes(1);
        System.out.println(notes.size());
        for(Note n : notes){
            System.out.println(n.getNote());
        }

        ////////////////////////////////////////////////////////// US-4: /////////////////////////////////////////////////////////////

        List<NoteWithNameAndAgeDTO> noteWithNameAndAges = dolphinDAO.getAllNotesWithNameAndAges();
        noteWithNameAndAges.forEach(System.out::println);



    }



}
